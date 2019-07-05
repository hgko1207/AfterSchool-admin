package com.ysc.after.school.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.after.school.admin.domain.db.SubjectGroup;
import com.ysc.after.school.admin.domain.param.SearchParam;
import com.ysc.after.school.admin.service.CRUDService;

/**
 * 과목 그룹 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("subject/group")
public class SubjectGroupController extends AbstractController<SubjectGroup, SearchParam, Integer> {
	
	public SubjectGroupController(CRUDService<SubjectGroup, SearchParam, Integer> crudService) {
		super(crudService);
	}

	/**
	 * 과목 그룹 화면
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
	}
}
