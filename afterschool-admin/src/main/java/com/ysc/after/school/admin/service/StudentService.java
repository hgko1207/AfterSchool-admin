package com.ysc.after.school.admin.service;

import java.util.List;

import com.ysc.after.school.admin.domain.StudentSearchParam;
import com.ysc.after.school.admin.domain.db.Student;

public interface StudentService extends CRUDService<Student> {

	List<Student> getList(StudentSearchParam param);

}
