package com.ysc.after.school.admin.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.after.school.admin.domain.db.Student;
import com.ysc.after.school.admin.domain.db.Student.TargetType;
import com.ysc.after.school.admin.domain.param.StudentSearchParam;
import com.ysc.after.school.admin.service.CRUDService;
import com.ysc.after.school.admin.service.SchoolService;
import com.ysc.after.school.admin.service.StudentService;

/**
 * 학생 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("student")
public class StudentController extends AbstractController<Student, StudentSearchParam, Integer> {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StudentService studentService;
	
	public StudentController(CRUDService<Student, StudentSearchParam, Integer> crudService) {
		super(crudService);
	}

	/**
	 * 학생 조회 화면
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("schools", schoolService.getList().stream().map(data -> {
			if (data.getName().contains("분교")) {
				return data.getName();
			}
			return data.getName() + data.getSchoolType().getName();
		}).sorted().collect(Collectors.toList()));
	}
	
	/**
	 * 학생 정보 수정
	 * @param student
	 * @return
	 */
	@Override
	public ResponseEntity<?> update(Student student) {
		Student temp = studentService.get(student.getId());
		temp.setSchool(student.getSchool());
		temp.setGrade(student.getGrade());
		temp.setClassType(student.getClassType());
		temp.setNumber(student.getNumber());
		temp.setTel(student.getTel());
		if (student.getSchool().contains("초등")) {
			temp.setTargetType(TargetType.초등);
		} else {
			temp.setTargetType(TargetType.중등);
		}
		
		if (studentService.update(temp)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
