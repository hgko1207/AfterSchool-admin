package com.ysc.after.school.admin.service;

import java.util.List;

import com.ysc.after.school.admin.domain.db.Apply;
import com.ysc.after.school.admin.domain.param.ApplySearchParam;

public interface ApplyService extends CRUDService<Apply, Integer> {

	List<Apply> getList(ApplySearchParam param);
}
