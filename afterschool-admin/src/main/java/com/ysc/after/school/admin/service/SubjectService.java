package com.ysc.after.school.admin.service;

import java.util.List;

import com.ysc.after.school.admin.domain.SubjectSearchParam;
import com.ysc.after.school.admin.domain.db.Subject;

public interface SubjectService extends CRUDService<Subject, Integer> {

	List<Subject> getList(SubjectSearchParam param);
}
