package com.ysc.after.school.admin.service;

import java.util.List;

import com.ysc.after.school.admin.domain.Domain;

public interface CRUDService<T extends Domain> {
	
	List<T> getList();

	boolean regist(T domain);

	boolean update(T domain);
	
	boolean delete(T domain);
	
}
