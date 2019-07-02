package com.ysc.after.school.admin.repository;

import java.util.List;

import com.ysc.after.school.admin.domain.db.Student;

public interface StudentRepository extends DefaultRepository<Student, Integer> {

	Student findByNameAndTel(String name, String tel);

	List<Student> findBySchoolAndGradeAndNameContaining(String school, int grade, String name);

	List<Student> findBySchoolAndNameContaining(String school, String name);

	List<Student> findByGradeAndNameContaining(int grade, String name);

	List<Student> findByNameContaining(String name);

}
