package com.ysc.after.school.admin.repository;

import com.ysc.after.school.admin.domain.db.Student;

public interface StudentRepository extends DefaultRepository<Student, Integer> {

	Student findByNameAndTel(String name, String tel);

}
