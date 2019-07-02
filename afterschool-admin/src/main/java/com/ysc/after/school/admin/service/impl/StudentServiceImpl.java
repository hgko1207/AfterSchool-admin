package com.ysc.after.school.admin.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysc.after.school.admin.domain.db.Student;
import com.ysc.after.school.admin.domain.param.StudentSearchParam;
import com.ysc.after.school.admin.repository.StudentRepository;
import com.ysc.after.school.admin.service.StudentService;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student get(Integer id) {
		return studentRepository.findById(id).get();
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
			return studentRepository.findBySchoolAndGradeAndNameContaining(param.getSchool(), 
					Integer.parseInt(param.getGrade()), param.getName());
		} else if (param.getSchool().isEmpty() && !param.getGrade().isEmpty()) {
			return studentRepository.findByGradeAndNameContaining(Integer.parseInt(param.getGrade()), param.getName());
		} else if (!param.getSchool().isEmpty() && param.getGrade().isEmpty()) {
			return studentRepository.findBySchoolAndNameContaining(param.getSchool(), param.getName());
		} else {
			return studentRepository.findByNameContaining(param.getName());
		}
	}
}
