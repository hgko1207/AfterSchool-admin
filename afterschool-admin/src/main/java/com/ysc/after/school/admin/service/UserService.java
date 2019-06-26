package com.ysc.after.school.admin.service;

import com.ysc.after.school.admin.domain.db.User;

public interface UserService extends CRUDService<User> {

	User login(String username, String password);

}
