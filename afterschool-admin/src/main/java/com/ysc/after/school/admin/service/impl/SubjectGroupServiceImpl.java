package com.ysc.after.school.admin.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.db.SubjectGroup;
import com.ysc.after.school.admin.domain.param.SearchParam;
import com.ysc.after.school.admin.repository.SubjectGroupRepository;
import com.ysc.after.school.admin.service.SubjectGroupService;

@Transactional
@Service
public class SubjectGroupServiceImpl implements SubjectGroupService {

	@Autowired
	private SubjectGroupRepository subjectGroupRepository;

	@Override
	public List<SubjectGroup> getList() {
		return subjectGroupRepository.findAll();
	}

	@Override
	public boolean regist(SubjectGroup domain) {
		if (isNew(domain)) {
			return subjectGroupRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(SubjectGroup domain) {
		if (!isNew(domain)) {
			return subjectGroupRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		subjectGroupRepository.deleteById(id);
		return true;
	}

	private boolean isNew(SubjectGroup domain) {
		return !subjectGroupRepository.existsById(domain.getId());
	}

	@Override
	public SubjectGroup get(Integer id) {
		return subjectGroupRepository.findById(id).get();
	}

	@Override
	public List<SubjectGroup> getList(SearchParam param) {
		return getList();
	}
}
