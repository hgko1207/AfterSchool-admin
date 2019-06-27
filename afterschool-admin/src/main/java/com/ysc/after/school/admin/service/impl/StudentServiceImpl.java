package com.ysc.after.school.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.StudentSearchParam;
import com.ysc.after.school.admin.domain.db.Student;
import com.ysc.after.school.admin.repository.StudentRepository;
import com.ysc.after.school.admin.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student get(Integer id) {
		return studentRepository.getOne(id);
	}
	
	@Override
	public List<Student> getList() {
		return studentRepository.findAll();
	}

	@Override
	public boolean regist(Student domain) {
		if (isNew(domain)) {
			return studentRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean update(Student domain) {
		if (!isNew(domain)) {
			return studentRepository.save(domain) != null;
		} else {
			return false;
		}	
	}

	@Override
	public boolean delete(Integer id) {
		studentRepository.deleteById(id);
		return true;
	}

	private boolean isNew(Student domain) {
		return !studentRepository.existsById(domain.getId());
	}

	@Override
	public List<Student> getList(StudentSearchParam param) {
		if (!param.getSchool().isEmpty() && !param.getGrade().isEmpty()) {
			return studentRepository.findBySchoolAndGrade(param.getSchool(), Integer.parseInt(param.getGrade()));
		} else if (param.getSchool().isEmpty() && !param.getGrade().isEmpty()) {
			return studentRepository.findByGrade(Integer.parseInt(param.getGrade()));
		} else if (!param.getSchool().isEmpty() && param.getGrade().isEmpty()) {
			return studentRepository.findBySchool(param.getSchool());
		} else {
			return studentRepository.findAll();
		}
	}
}
