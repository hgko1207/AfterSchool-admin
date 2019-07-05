package com.ysc.after.school.admin.domain.param;

import com.ysc.after.school.admin.domain.DomainParam;

import lombok.Data;

/**
 * 학생 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class StudentSearchParam implements DomainParam {

	private String school;
	
	private String grade;
	
	private String name;
}
