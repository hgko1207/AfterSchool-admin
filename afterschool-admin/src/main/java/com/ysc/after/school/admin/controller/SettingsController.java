package com.ysc.after.school.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.after.school.admin.domain.db.School;
import com.ysc.after.school.admin.domain.db.School.SchoolType;
import com.ysc.after.school.admin.domain.db.Setting.SettingType;
import com.ysc.after.school.admin.service.SchoolService;

/**
 * 데이터 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("settings")
public class SettingsController {
	
	@Autowired
	private SchoolService schoolService;

	/**
	 * 학교 관리 화면
	 * @param model
	 */
	@GetMapping("school")
	public void school(Model model) {
		model.addAttribute("schoolTypes", SchoolType.values());
	}
	
	/**
	 * 과목 그룹 조회
	 * @return
	 */
	@PostMapping("search/{type}")
	@ResponseBody 
	public ResponseEntity<?> search(@PathVariable SettingType type) {
		if (type == SettingType.SCHOOL) {
			return new ResponseEntity<>(schoolService.getList(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	/**
	 * 과목 그룹 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping("get/{id}/{type}")
	@ResponseBody 
	public ResponseEntity<?> getSchool(int id) {
		return new ResponseEntity<>(schoolService.get(id), HttpStatus.OK);
	}
	
	/**
	 * 과목 그룹 등록
	 * @param subjectGroup
	 * @return
	 */
	@PostMapping("regist")
	@ResponseBody
	public ResponseEntity<?> registSubjectgroup(School school) {
		if (schoolService.regist(school)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 과목 그룹 수정
	 * @param subjectGroup
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody
	public ResponseEntity<?> updateSubjectgroup(School school) {
		if (schoolService.update(school)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
