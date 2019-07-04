package com.ysc.after.school.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.db.Setting;
import com.ysc.after.school.admin.repository.SettingRepository;
import com.ysc.after.school.admin.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private SettingRepository settingRepository;

	@Override
	public Setting get(Integer id) {
		return settingRepository.findById(id).get();
	}
	
	@Override
	public List<Setting> getList() {
		return settingRepository.findAll();
	}

	@Override
	public boolean regist(Setting domain) {
		if (isNew(domain)) {
			return settingRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Setting domain) {
		if (!isNew(domain)) {
			return settingRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		settingRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Setting domain) {
		return !settingRepository.existsById(domain.getId());
	}
	
}
