package com.ysc.after.school.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.db.User;
import com.ysc.after.school.admin.repository.UserRepository;
import com.ysc.after.school.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getList() {
		return userRepository.findAll();
	}

	@Override
	public boolean regist(User domain) {
		if (isNew(domain)) {
			return userRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(User domain) {
		if (!isNew(domain)) {
			return userRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		userRepository.deleteById(id);
		return true;
	}

	private boolean isNew(User domain) {
		return !userRepository.existsById(domain.getId());
	}

	@Override
	public User login(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}

	@Override
	public User get(Integer id) {
		return userRepository.getOne(id);
	}
}
