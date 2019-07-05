package com.ysc.after.school.admin.service;

import com.ysc.after.school.admin.domain.DomainParam;
import com.ysc.after.school.admin.domain.db.User;

public interface UserService extends CRUDService<User, DomainParam, Integer> {

	User login(String username, String password);

}
