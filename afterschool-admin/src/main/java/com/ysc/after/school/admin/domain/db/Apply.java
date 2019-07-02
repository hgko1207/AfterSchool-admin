package com.ysc.after.school.admin.domain.db;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.ysc.after.school.admin.domain.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 수강 신청 관리
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_apply")
@Data
@NoArgsConstructor
public class Apply implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 학생 */
	@OneToOne
    @JoinColumn(name = "student_id")
	private Student student;
	
	/** 과목 */
	@OneToOne
    @JoinColumn(name = "subject_id")
	private Subject subject;
	
	@CreationTimestamp
	private LocalDateTime createDate;
}
