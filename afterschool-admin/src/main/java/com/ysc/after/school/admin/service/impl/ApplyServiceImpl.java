package com.ysc.after.school.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.db.Apply;
import com.ysc.after.school.admin.domain.param.ApplySearchParam;
import com.ysc.after.school.admin.repository.ApplyRepository;
import com.ysc.after.school.admin.service.ApplyService;

@Transactional
@Service
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyRepository applyRepository;

	@Override
	public List<Apply> getList() {
		return applyRepository.findAll();
	}

	@Override
	public boolean regist(Apply domain) {
		if (isNew(domain)) {
			return applyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Apply domain) {
		if (!isNew(domain)) {
			return applyRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		applyRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Apply domain) {
		return !applyRepository.existsById(domain.getId());
	}

	@Override
	public Apply get(Integer id) {
		return applyRepository.findById(id).get();
	}

	@Override
	public List<Apply> getList(ApplySearchParam param) {
		String subjectId = param.getSubjectId();
		String school = param.getSchool();
		String grade = param.getGrade();
		
		List<Apply> applies = null;
		if (!subjectId.isEmpty()) {
			applies = applyRepository.findBySubjectId(Integer.parseInt(subjectId));
		} else {
			applies = getList();
		}
		
		if (!school.isEmpty() && !grade.isEmpty()) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool()) 
							&& data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else if (!school.isEmpty() && grade.isEmpty()) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getSchool().equals(param.getSchool());})
					.collect(Collectors.toList());
		} else if (school.isEmpty() && !grade.isEmpty()) {
			return applies.stream()
					.filter(data -> {return data.getStudent().getGrade() == Integer.parseInt(param.getGrade());})
					.collect(Collectors.toList());
		} else {
			return applies;
		}
	}
}
