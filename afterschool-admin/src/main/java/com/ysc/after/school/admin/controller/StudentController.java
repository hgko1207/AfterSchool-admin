package com.ysc.after.school.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.after.school.admin.domain.StudentSearchParam;
import com.ysc.after.school.admin.domain.db.Student;
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
public class StudentController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StudentService studentService;

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
	 * 학생 조회 기능
	 * @param param
	 */
	@PostMapping("search")
	@ResponseBody
	public List<Student> search(@RequestBody StudentSearchParam param) {
		return studentService.getList(param);
	}
	
	/**
	 * 학생 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping("get")
	@ResponseBody 
	public Student get(int id) {
		return studentService.get(id);
	}
	
	/**
	 * 학생 정보 수정
	 * @param student
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(Student student) {
		Student temp = studentService.get(student.getId());
		temp.setSchool(student.getSchool());
		temp.setGrade(student.getGrade());
		temp.setClassType(student.getClassType());
		temp.setNumber(student.getNumber());
		temp.setTel(student.getTel());
		
		if (studentService.update(temp)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
