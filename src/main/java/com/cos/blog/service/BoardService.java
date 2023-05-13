package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplyDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void write(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board getOne(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글을 불러오는 데 실패하였습니다.");
		});
	}
	
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void update(int id, Board requestBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글을 불러오는 데 실패하였습니다.");
		});
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료 시 트랜잭션 종료 -> 더티체킹: 자동 업데이트(flush)
	}
	
	@Transactional
	public void writeReply(ReplyDTO replyDTO) {
		/* User 오브젝트 영속화 */
		User user = userRepository.findById(replyDTO.getUserId()).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 작성에 실패했습니다(해당 사용자를 찾을 수 없습니다.).");
		});
		
		/* Board 오브젝트 영속화 */
		Board board = boardRepository.findById(replyDTO.getBoardId()).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 작성에 실패했습니다(게시글 번호를 불러올 수 없습니다.).");
		});
		
//		Reply reply = Reply.builder().user(user).board(board).content(replyDTO.getContent()).build();
		
		Reply reply = new Reply();
		reply.update(user, board, replyDTO.getContent());
		
		replyRepository.save(reply);
	}
	
	@Transactional
	public void deleteReply(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
