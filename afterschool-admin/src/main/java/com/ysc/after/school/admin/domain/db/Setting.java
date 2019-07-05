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
 * 설정 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_setting")
@Data
public class Setting implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SettingType type;
	
	/** 타이틀 */
	@Column(length = 45)
	private String title;
	
	/** 신청기간 */
	@Column(length = 255)
	private String period;
	
	@Enumerated(EnumType.STRING)
    @Column
	private StatusType status;
	
	public enum SettingType {
		MOBILE, GUIDANCE, SCHOOL
	}
	
	public enum StatusType {
		OPEN, CLOSE;
	}
}
