package com.ysc.after.school.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ysc.after.school.admin.domain.Domain;

import lombok.Data;

/**
 * 사용자 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_user")
@Data
public class User implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 사용자ID */
	@Column(nullable = false, length = 20)
	private String userId;
	
	/** 사용자명 */
	@Column(nullable = false, length = 100)
	private String name;
	
	/** 사용자비밀번호암호화 */
	@Column(nullable = false, length = 100)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;
	
	public enum UserRole {
		ADMIN, GUEST;
	}
}
