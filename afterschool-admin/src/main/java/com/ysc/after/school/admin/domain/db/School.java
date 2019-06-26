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
import lombok.Getter;

/**
 * 학교 관리 도메인
 * 
 * @author hgko
 *
 */
@Entity
@Table(name = "tb_school")
@Data
public class School implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/** 이름 */
	@Column(nullable = false, length = 20)
	private String name;
	
	/** 학생 수 */
	private int number;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
	private SchoolType schoolType;
	
	@Getter
	public enum SchoolType {
		Elementary("초등학교"),
		Middle("중학교");
		
		private String name;
		
		private SchoolType(String name) {
			this.name = name;
		}
	}
}
