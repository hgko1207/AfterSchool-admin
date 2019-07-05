package com.ysc.after.school.admin.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ysc.after.school.admin.domain.db.Student.TargetType;
import com.ysc.after.school.admin.domain.db.Subject;
import com.ysc.after.school.admin.domain.db.Subject.GradeType;
import com.ysc.after.school.admin.domain.param.SubjectSearchParam;
import com.ysc.after.school.admin.service.CRUDService;
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
public class SubjectController extends AbstractController<Subject, SubjectSearchParam, Integer> {
	
	@Autowired
	private SubjectGroupService subjectGroupService;
	
	@Autowired
	private SubjectService subjectService;

	public SubjectController(CRUDService<Subject, SubjectSearchParam, Integer> crudService) {
		super(crudService);
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
	 * 과목 조회List<Subject>
	 * @param param
	 * @return
	 */
	@Override
	public ResponseEntity<?> search(@RequestBody SubjectSearchParam param) {
		return new ResponseEntity<>(subjectService.getList(param).stream().map(data -> {
			data.setTarget(data.getTargetType().getName() + " " + data.getGradeType().getName() + "<br>(" + data.getFixedNumber() + ")");
			return data;
		}).collect(Collectors.toList()), HttpStatus.OK);
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
	@Override
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
	@Override
	public ResponseEntity<?> update(Subject subject) {
		Subject result = subjectService.get(subject.getId());
		result.setSubjectGroup(subjectGroupService.get(subject.getGroupId()));
		result.setTeacher(subject.getTeacher());
		result.setTargetType(subject.getTargetType());
		result.setGradeType(subject.getGradeType());
		result.setFixedNumber(subject.getFixedNumber());
		result.setPeriod(subject.getPeriod());
		result.setTime(subject.getTime());
		result.setWeek(subject.getWeek());
		result.setCostDesc(subject.getCostDesc());
		result.setLocation(subject.getLocation());
		result.setDescription(subject.getDescription());
		
		if (subjectService.update(result)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
