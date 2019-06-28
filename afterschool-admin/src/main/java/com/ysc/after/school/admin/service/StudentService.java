package com.ysc.after.school.admin.service;

import java.util.List;

import com.ysc.after.school.admin.domain.db.Student;
import com.ysc.after.school.admin.domain.param.StudentSearchParam;

public interface StudentService extends CRUDService<Student, Integer> {

	List<Student> getList(StudentSearchParam param);

}
