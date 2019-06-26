package com.ysc.after.school.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("schools", schoolService.getList());
	}
	
	/**
	 * 조직원 조회 기능
	 * @param param
	 */
	@PostMapping("search")
	@ResponseBody
	public List<Student> search(@RequestBody StudentSearchParam param) {
		return studentService.getList(param);
	}
}
