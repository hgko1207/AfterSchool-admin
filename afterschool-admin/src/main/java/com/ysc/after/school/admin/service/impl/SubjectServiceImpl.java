package com.ysc.after.school.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.db.Subject;
import com.ysc.after.school.admin.domain.param.SubjectSearchParam;
import com.ysc.after.school.admin.repository.SubjectRepository;
import com.ysc.after.school.admin.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Subject> getList() {
		return subjectRepository.findAll();
	}

	@Override
	public boolean regist(Subject domain) {
		if (isNew(domain)) {
			return subjectRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Subject domain) {
		if (!isNew(domain)) {
			return subjectRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		subjectRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Subject domain) {
		return !subjectRepository.existsById(domain.getId());
	}

	@Override
	public Subject get(Integer id) {
		return subjectRepository.findById(id).get();
	}

	@Override
	public List<Subject> getList(SubjectSearchParam param) {
		if (param.getGroupId() == 0) {
			return getList();
		} else {
			return subjectRepository.findBySubjectGroupId(param.getGroupId());
		}
	}
}
