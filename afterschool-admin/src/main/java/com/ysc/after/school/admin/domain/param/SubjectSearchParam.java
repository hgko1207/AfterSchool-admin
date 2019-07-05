package com.ysc.after.school.admin.domain.param;

import com.ysc.after.school.admin.domain.DomainParam;

import lombok.Data;

/**
 * 과목 검색 조건
 * 
 * @author hgko
 *
 */
@Data
public class SubjectSearchParam implements DomainParam {

	private int groupId;
	
}
