package com.ysc.after.school.admin.domain;

import lombok.Data;

/**
 * 조직원 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class StudentSearchParam {

	private String school;
	
	private String grade;
}
