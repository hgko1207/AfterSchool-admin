package com.ysc.after.school.admin.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.DomainParam;
import com.ysc.after.school.admin.domain.db.School;
import com.ysc.after.school.admin.repository.SchoolRepository;
import com.ysc.after.school.admin.service.SchoolService;

@Transactional
@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Override
	public School get(Integer id) {
		return schoolRepository.findById(id).get();
	}
	
	@Override
	public List<School> getList() {
		return schoolRepository.findAll();
	}

	@Override
	public boolean regist(School domain) {
		if (isNew(domain)) {
			return schoolRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(School domain) {
		if (!isNew(domain)) {
			return schoolRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		schoolRepository.deleteById(id);
		return true;
	}

	private boolean isNew(School domain) {
		return !schoolRepository.existsById(domain.getId());
	}

	@Override
	public List<School> getList(DomainParam param) {
		return getList();
	}
	
}
