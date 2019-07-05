package com.ysc.after.school.admin.domain.param;

import com.ysc.after.school.admin.domain.DomainParam;

import lombok.Data;

/**
 * 수강 신청 목록 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class ApplySearchParam implements DomainParam {
	
	private String subjectId;

	private String school;
	
	private String grade;
}
