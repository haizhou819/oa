package com.yhz.oa.usermgr.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.yhz.framework.web.WebModel;
import com.yhz.oa.usermgr.vo.UserModel;

public class UserWebModel extends WebModel{
	private List<UserModel> listCol = new ArrayList<UserModel>();

	public List<UserModel> getListCol() {
		return listCol;
	}

	public void setListCol(List<UserModel> listCol) {
		this.listCol = listCol;
	}
}
