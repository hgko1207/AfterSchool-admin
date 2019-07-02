package com.ysc.after.school.admin.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.after.school.admin.domain.db.Apply;
import com.ysc.after.school.admin.domain.param.ApplySearchParam;
import com.ysc.after.school.admin.service.ApplyService;
import com.ysc.after.school.admin.service.SchoolService;
import com.ysc.after.school.admin.service.SubjectService;

/**
 * 수강신청 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("apply")
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SubjectService subjectService;

	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("schools", schoolService.getList().stream().map(data -> {
			if (data.getName().contains("분교")) {
				return data.getName();
			}
			return data.getName() + data.getSchoolType().getName();
		}).sorted().collect(Collectors.toList()));
		
		model.addAttribute("subjects", subjectService.getList().stream()
				.sorted(Comparator.comparing(subject -> subject.getName())).collect(Collectors.toList()));
	}
	
	/**
	 * 과목 조회
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public List<Apply> search(@RequestBody ApplySearchParam param) {
		return applyService.getList(param);
	}
}
