package com.ysc.after.school.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.ApplySearchParam;
import com.ysc.after.school.admin.domain.db.Apply;
import com.ysc.after.school.admin.repository.ApplyRepository;
import com.ysc.after.school.admin.service.ApplyService;

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
		if (param.getSchool().isEmpty()) {
			return getList();
		} else {
			return getList().stream().filter(data -> {
				return data.getStudent().getSchool().equals(param.getSchool());
			}).collect(Collectors.toList());
		}
	}
}
