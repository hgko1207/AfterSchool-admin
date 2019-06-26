package com.ysc.after.school.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.db.School;
import com.ysc.after.school.admin.repository.SchoolRepository;
import com.ysc.after.school.admin.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

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
	public boolean delete(School domain) {
		schoolRepository.delete(domain);
		return true;
	}

	private boolean isNew(School domain) {
		return !schoolRepository.existsById(domain.getId());
	}
}
