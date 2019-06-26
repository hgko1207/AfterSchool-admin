package com.ysc.after.school.admin.repository;

import com.ysc.after.school.admin.domain.db.User;

public interface UserRepository extends DefaultRepository<User, Integer> {

	User findByUserIdAndPassword(String userId, String password);

}
