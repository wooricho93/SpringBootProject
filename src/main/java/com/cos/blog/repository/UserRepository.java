package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

/* DAO; 자동으로 bean 등록 -> @Repository 생략 가능 */
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	/* JPA Naming 쿼리 전략 */
	// select * from user where username  = ?1 and password = ?2
//	User findByUserNameAndPassword(String userName, String Password);
	
//	@Query(value = "select * from user where username = ?1 and password = ?2", nativeQuery = true)
//	User login(String userName, String password);
}
