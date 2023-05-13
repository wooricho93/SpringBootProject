package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* ORM: Java 등 여타 언어들의 Object를 테이블로 매핑해주는 기술 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert // null값인 필드를 제외하고 insert
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략
	private int id;
	
	// 실질 상 아이디
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 200)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
//	@ColumnDefault("'user'")
//	private String role; // -> Enum 사용
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp // 시간 자동입력
	private Timestamp createDate;
	
	private String oauth;
}
