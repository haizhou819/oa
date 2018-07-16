package com.yhz.oa.usermgr.business.ebo;

import java.util.List;

import com.yhz.oa.usermgr.business.ebi.UserEbi;
import com.yhz.oa.usermgr.dao.dao.UserDao;
import com.yhz.oa.usermgr.vo.UserModel;
import com.yhz.oa.usermgr.vo.UserQueryModel;

public class UserEbo implements UserEbi{
	private UserDao userDao = null;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public String create(UserModel m) {
		
		userDao.create(m);
		return m.getUuid();
	}
	@Override
	public void update(UserModel m) {
		userDao.update(m);
	}
	@Override
	public void delete(UserModel m) {
		userDao.delete(m);
	}
	@Override
	public UserModel getByUuid(String uuid) {
		return userDao.getByUuid(new UserModel(), uuid);
	}
	@Override
	public List<UserModel> getAll(boolean needPage, int fromNum, int pageNum) {
		return userDao.getAll(new UserQueryModel(), needPage, fromNum, pageNum);
	}
	@Override
	public List<UserModel> getByCondition(UserQueryModel qm, boolean needPage, int fromNum, int pageNum) {
		return userDao.getByCondition(qm, needPage, fromNum, pageNum);
	}
	@Override
	public int getCount(UserQueryModel qm) {
		return userDao.getCount(qm);
	}
	
}
