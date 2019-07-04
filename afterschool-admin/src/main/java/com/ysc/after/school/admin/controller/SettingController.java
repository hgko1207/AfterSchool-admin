package com.ysc.after.school.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 데이터 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("setting")
public class SettingController {

	/**
	 * 학교 관리 화면
	 * @param model
	 */
	@GetMapping("school")
	public void school(Model model) {
		
	}
}
