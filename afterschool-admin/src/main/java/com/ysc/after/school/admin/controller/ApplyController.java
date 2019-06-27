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

import com.ysc.after.school.admin.domain.ApplySearchParam;
import com.ysc.after.school.admin.domain.db.Apply;
import com.ysc.after.school.admin.service.ApplyService;
import com.ysc.after.school.admin.service.SchoolService;

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

	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("schools", schoolService.getList());
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
