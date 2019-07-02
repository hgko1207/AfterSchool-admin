package com.ysc.after.school.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ysc.after.school.admin.domain.Domain;

import lombok.Data;
import lombok.Getter;

/**
 * 학생 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_student")
@Data
public class Student implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 이름 */
	@Column(nullable = false, length = 20)
	private String name;
	
	/** 소속(학교 명) */
	@Column(nullable = false, length = 45)
	private String school;
	
	/** 학년 */
	private int grade;
	
	/** 학급(반) */
	private int classType;
	
	/** 번호 */
	private int number;
	
	/** 연락처 */
	@Column(length = 20)
	private String tel;
	
	/** 개인정보제공 동의 */
	private boolean agree;
	
	/** 주민등록번호 */
	@Column(length = 15)
	private String residentNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TargetType targetType;
	
	/** 등록시간 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;
	
	@Transient
	private String service;
	
	@Getter
	public enum TargetType {
		전체("전체"), 
		초등("초등"), 
		중등("중등"), 
		초_중등("초,중등");
		
		private String name;
		
		private TargetType(String name) {
			this.name = name;
		}
	}
}
