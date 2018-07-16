package com.yhz.framework.dao.dao;

import java.util.List;

public interface Dao<M,Q extends M> {//M-Model Q-QueryModel
	public void create(M m);
	public void update(M m);
	public void delete(M m);
	
	public M getByUuid(M m,String uuid);
	public List<M> getAll(Q qm,boolean needPage,int fromNum,int pageNum);
	public List<M> getByCondition(Q qm,boolean needPage,int fromNum,int pageNum);
	public int getCount(Q qm);
}
