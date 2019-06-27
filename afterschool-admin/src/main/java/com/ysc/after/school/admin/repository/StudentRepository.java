package com.ysc.after.school.admin.repository;

import java.util.List;

import com.ysc.after.school.admin.domain.db.Student;

public interface StudentRepository extends DefaultRepository<Student, Integer> {

	Student findByNameAndTel(String name, String tel);

	List<Student> findBySchoolAndGrade(String school, int grade);

	List<Student> findBySchool(String school);

	List<Student> findByGrade(int grade);

}
