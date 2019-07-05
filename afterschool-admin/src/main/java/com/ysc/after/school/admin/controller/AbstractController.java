package com.ysc.after.school.admin.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysc.after.school.admin.domain.Domain;
import com.ysc.after.school.admin.domain.DomainParam;
import com.ysc.after.school.admin.domain.db.Subject;
import com.ysc.after.school.admin.domain.param.SubjectSearchParam;
import com.ysc.after.school.admin.service.CRUDService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * GenericController - 컨트롤러의 공통 기능 추상화(CRUD)
 * 
 * @author hgko
 *
 */
@Slf4j
public abstract class AbstractController<T extends Domain, P extends DomainParam, ID extends Serializable> {
	
	@Autowired
	private CRUDService<T, P, ID> crudService;
	
	public AbstractController(CRUDService<T, P, ID> crudService) {
		this.crudService = crudService;
	}

	/**
	 * 정보 불러오기
	 * @param id
	 * @return
	 */
	@GetMapping(value = {"get", "{type}/get"})
	@ResponseBody
	public T get(ID id) {
		log.debug("AbstractController.get - {}", id);
		return crudService.get(id);
	}
	
	/**
	 * 조회
	 * @param param
	 * @return
	 */
	@PostMapping("search")
	@ResponseBody 
	public ResponseEntity<?> search(@RequestBody P param) {
		return new ResponseEntity<>(crudService.getList(param), HttpStatus.OK);
	}
	
	/**
	 * 정보 등록
	 * @param domain
	 * @return
	 */
	@PostMapping("regist")
	@ResponseBody
	public ResponseEntity<?> regist(T domain) {
		if (crudService.regist(domain)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 정보 수정
	 * @param domain
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(T domain) {
		if (crudService.update(domain)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 정보 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<?> delete(ID id) {
		log.debug("AbstractController.delete - {}", id);
		if (crudService.delete(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
