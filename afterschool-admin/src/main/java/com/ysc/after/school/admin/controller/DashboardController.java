package com.ysc.after.school.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ysc.after.school.admin.service.ApplyService;
import com.ysc.after.school.admin.service.StudentService;
import com.ysc.after.school.admin.service.SubjectGroupService;
import com.ysc.after.school.admin.service.SubjectService;

@Controller
public class DashboardController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SubjectGroupService subjectGroupService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ApplyService applyService;

	@GetMapping("home")
	public void dashboard(Model model) {
		model.addAttribute("students", studentService.getList().size());
		model.addAttribute("subjectGroups", subjectGroupService.getList().size());
		model.addAttribute("subjects", subjectService.getList().size());
		model.addAttribute("applies", applyService.getList().size());
	}
}
