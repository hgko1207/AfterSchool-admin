package com.ysc.after.school.admin.repository;

import java.util.List;

import com.ysc.after.school.admin.domain.db.Apply;

public interface ApplyRepository extends DefaultRepository<Apply, Integer> {

	Apply findByStudentId(int studentId);

	List<Apply> findBySubjectId(int parseInt);

}
