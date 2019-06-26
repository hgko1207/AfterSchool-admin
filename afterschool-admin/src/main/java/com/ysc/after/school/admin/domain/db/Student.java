package com.ysc.after.school.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ysc.after.school.admin.domain.Domain;

import lombok.Data;

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
	
	@Transient
	private String service;
	
	/** 등록시간 */
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;
}
