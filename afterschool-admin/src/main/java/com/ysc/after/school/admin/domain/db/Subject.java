package com.ysc.after.school.admin.domain.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ysc.after.school.admin.domain.Domain;
import com.ysc.after.school.admin.domain.db.Student.TargetType;

import lombok.Data;
import lombok.Getter;

/**
 * 과목 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_subject")
@Data
public class Subject implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 이름 */
	@Column(nullable = false, length = 255)
	private String name;
	
	/** 강사 */
	@Column(nullable = false, length = 45)
	private String teacher;
	
	/** 대상 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TargetType targetType;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private GradeType gradeType;
	
	@Transient
	private String target;
	
	/** 수강기간 */
	@Column(nullable = false, length = 255)
	private String period;
	
	/** 요일 */
	@Column(nullable = false, length = 45)
	private String week;
	
	/** 운영시간 */
	@Column(nullable = false, length = 45)
	private String time;
	
	/** 장소 */
	@Column(nullable = false, length = 255)
	private String location;
	
	/** 재료비 및 교구비 */
	private int cost;
	
	/** 재료비 및 교구비 부가적인 설명 */
	@Column(nullable = false, length = 45)
	private String costDesc;
	
	/** 정원 */
	private int fixedNumber;
	
	/** 수강신청 인원 */
	private int applyNumber;
	
	/** 수강대기 인원 */
	private int waitNumber;
	
	/** 수강신청 시작 날짜 */
	@Column(nullable = false, length = 20)
	private String applyStartTime;
	
	/** 수강신청 종료 날짜 */
	@Column(nullable = false, length = 20)
	private String applyEndTime;
	
	/** 과목특징 */
	@Column(nullable = false, length = 255)
	private String description;
	
	/** 과목 */
	@OneToOne
    @JoinColumn(name = "subject_group_id")
	private SubjectGroup subjectGroup;
	
	/** 순서지정 */
	private int ordered;
	
	@Transient
	private ApplyType applyType;
	
	public enum ApplyType {
		NONE, APPLY, WAIT, NOTAPPLY;
	}
	
	@Getter
	public enum GradeType {
		NONE("", 0, 0),
		GRADE_1_2("1-2학년", 1, 2),
		GRADE_1_3("1-3학년", 1, 3),
		GRADE_4_6("4-6학년", 4, 6),
		GRADE_5_6("5-6학년", 5, 6);
		
		private String name;
		
		private int min;
		
		private int max;
		
		private GradeType(String name, int min, int max) {
			this.name = name;
			this.min = min;
			this.max = max;
		}
	}
	
	/**
	 * 학생 학년에 따라 과목 대상학년만 보여지도록
	 * @param gradeType
	 * @param grade
	 * @return
	 */
	public boolean targetTrue(GradeType gradeType, int grade) {
		 if (gradeType == GradeType.NONE || (gradeType.getMin() <= grade && grade <= gradeType.getMax())) {
			return true;
		}
		return false;
	}
}
