package com.yhz.oa.usermgr.business.ebi;

import java.util.List;

import com.yhz.oa.usermgr.vo.UserModel;
import com.yhz.oa.usermgr.vo.UserQueryModel;

public interface UserEbi {
	public String create(UserModel m);
	public void update(UserModel m);
	public void delete(UserModel m);
	
	public UserModel getByUuid(String uuid);
	public List<UserModel> getAll(boolean needPage,int fromNum,int pageNum);
	public List<UserModel> getByCondition(UserQueryModel qm,boolean needPage,int fromNum,int pageNum);
	public int getCount(UserQueryModel qm);
}
