package com.ysc.after.school.admin.service;

import java.io.Serializable;
import java.util.List;

import com.ysc.after.school.admin.domain.Domain;
import com.ysc.after.school.admin.domain.DomainParam;

public interface CRUDService<T extends Domain, P extends DomainParam, ID extends Serializable> {
	
	T get(ID id);
	
	List<T> getList();
	
	boolean regist(T domain);

	boolean update(T domain);
	
	boolean delete(ID id);

	List<T> getList(P param);
	
}
