package com.ysc.after.school.admin.repository;

import java.util.List;

import com.ysc.after.school.admin.domain.db.Subject;

public interface SubjectRepository extends DefaultRepository<Subject, Integer> {

	List<Subject> findBySubjectGroupId(int groupId);
}
