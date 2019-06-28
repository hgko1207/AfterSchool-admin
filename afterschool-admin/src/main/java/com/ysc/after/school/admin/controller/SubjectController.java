package com.ysc.after.school.admin.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.after.school.admin.domain.SubjectSearchParam;
import com.ysc.after.school.admin.domain.db.Subject;
import com.ysc.after.school.admin.domain.db.SubjectGroup;
import com.ysc.after.school.admin.domain.db.Student.TargetType;
import com.ysc.after.school.admin.domain.db.Subject.GradeType;
import com.ysc.after.school.admin.service.SubjectGroupService;
import com.ysc.after.school.admin.service.SubjectService;

/**
 * 과목 관리 컨트롤러 클래스
 * 
 * @author hgko
 *
 */
@Controller
@RequestMapping("subject")
public class SubjectController {
	
	@Autowired
	private SubjectGroupService subjectGroupService;
	
	@Autowired
	private SubjectService subjectService;

	/**
	 * 과목 그룹 화면
	 * @param model
	 */
	@GetMapping("groupList")
	public void groupList(Model model) {
	}
	
	/**
	 * 과목 그룹 조회
	 * @return
	 */
	@PostMapping("group/search")
	@ResponseBody 
	public ResponseEntity<?> searchGroup() {
		return new ResponseEntity<>(subjectGroupService.getList(), HttpStatus.OK);
	}
	
	/**
	 * 과목 그룹 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping("group/get")
	@ResponseBody 
	public SubjectGroup getSubjectGroup(int id) {
		return subjectGroupService.get(id);
	}
	
	/**
	 * 과목 그룹 등록
	 * @param subjectGroup
	 * @return
	 */
	@PostMapping("group/regist")
	@ResponseBody
	public ResponseEntity<?> registSubjectgroup(SubjectGroup subjectGroup) {
		if (subjectGroupService.regist(subjectGroup)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 과목 그룹 수정
	 * @param subjectGroup
	 * @return
	 */
	@PutMapping("group/update")
	@ResponseBody
	public ResponseEntity<?> updateSubjectgroup(SubjectGroup subjectGroup) {
		if (subjectGroupService.update(subjectGroup)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 과목 그룹 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("group/delete")
	@ResponseBody
	public ResponseEntity<?> deleteSubjectgroup(int id) {
		if (subjectGroupService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 과목 조회 화면
	 * @param model
	 */
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("subjectGroups", subjectGroupService.getList());
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("gradeTypes", GradeType.values());
	}
	
	/**
	 * 과목 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public List<Subject> search(@RequestBody SubjectSearchParam param) {
		return subjectService.getList(param).stream().map(data -> {
			data.setTarget(data.getTargetType().getName() + " " + data.getGradeType().getName() + "(" + data.getFixedNumber() + ")");
			return data;
		}).collect(Collectors.toList());
	}
	
	/**
	 * 과목 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping("get")
	@ResponseBody 
	public Subject get(int id) {
		return subjectService.get(id);
	}
	
	/**
	 * 과목 등록 화면
	 * @param model
	 */
	@GetMapping("regist")
	public void regist(Model model) {
		model.addAttribute("subjectGroups", subjectGroupService.getList());
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("gradeTypes", GradeType.values());
	}
	
	/**
	 * 과목 등록 화면
	 * @param subject
	 */
	@PostMapping("regist")
	@ResponseBody
	public ResponseEntity<?> regist(Subject subject) {
		subject.setApplyStartTime("2019-07-01");
		subject.setApplyEndTime("2019-07-05");
		subject.setSubjectGroup(subjectGroupService.get(subject.getGroupId()));
		
		if (subjectService.regist(subject)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	/**
	 * 과목 그룹 수정
	 * @param subject
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(Subject subject) {
		Subject temp = subjectService.get(subject.getId());
		temp.setSubjectGroup(subjectGroupService.get(subject.getGroupId()));
		temp.setTeacher(subject.getTeacher());
		temp.setTargetType(subject.getTargetType());
		temp.setGradeType(subject.getGradeType());
		temp.setFixedNumber(subject.getFixedNumber());
		temp.setPeriod(subject.getPeriod());
		temp.setTime(subject.getTime());
		temp.setWeek(subject.getWeek());
		temp.setCostDesc(subject.getCostDesc());
		temp.setLocation(subject.getLocation());
		temp.setDescription(subject.getDescription());
		
		if (subjectService.update(temp)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 과목 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(int id) {
		if (subjectService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
