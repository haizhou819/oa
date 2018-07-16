package com.yhz.framework.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yhz.framework.dao.dao.Dao;

public abstract class S3H3Template<M,Q extends M> extends HibernateDaoSupport{
	public M getByUuid(M m,String uuid) {
		return (M)this.getHibernateTemplate().get(m.getClass(), uuid);
	}
	
	public void executeMyUpdate(final String hql,final Map map){
		HibernateCallback call = new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				setMyUpdateParams(q,map);
				
				int a = q.executeUpdate();	
				System.out.println("a===="+a+" , hql=="+hql);
				return null;
			}
		};		
		
		this.getHibernateTemplate().execute(call);
	}
	protected void setMyUpdateParams(Query q,Map map){
		//不实现
	}

	public List<M> getAll(final Q qm,final boolean needPage,final int fromNum,final int pageNum) {
		HibernateCallback call = new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = S3H3Template.this.getQueryByConditionMainSql(qm);
				Query q = session.createQuery(hql);
				
				if(needPage){
					q.setFirstResult(fromNum);
					q.setMaxResults(pageNum);
				}
				
				return q.list();
			}
		};		
		return this.getHibernateTemplate().execute(call);
	}
	public int getCount(Q qm){
		return this.getCount(qm, new ArrayList<String>());
	}
	
	public int getCount(final Q qm,final List<String> excludeName) {
		int count = 0;
		HibernateCallback call = new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = S3H3Template.this.getCountMainSql(qm);
				hql = S3H3Template.this.prepareSql(hql, qm,excludeName);

				Query q = session.createQuery(hql);
				
				S3H3Template.this.setValue(q, qm, excludeName);
				
				return q.uniqueResult();
			}
		};
		count = ((Long)this.getHibernateTemplate().execute(call)).intValue();
		return count;
	}
	public List<M> getByCondition(Q qm, boolean needPage,int fromNum,int pageNum){
		return this.getByCondition(qm, needPage,fromNum,pageNum,new ArrayList<String>());
	}
	public List<M> getByCondition(final Q qm,final boolean needPage, final int fromNum,final int pageNum,final List<String> excludeName) {
		return this.getHibernateTemplate().execute(this.getByConditionCall(qm, needPage, fromNum, pageNum, excludeName));
	}
	
	protected HibernateCallback getByConditionCall(final Q qm,final boolean needPage, final int fromNum,final int pageNum,final List<String> excludeName){
		HibernateCallback call = new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = S3H3Template.this.getQueryByConditionMainSql(qm);
				hql = S3H3Template.this.prepareSql(hql, qm,excludeName);

				Query q = session.createQuery(hql);
				
				S3H3Template.this.setValue(q, qm, excludeName);
				if(needPage){
					q.setFirstResult(fromNum);
					q.setMaxResults(pageNum);
				}
				return q.list();
			}
		};
		return call;
	}
	
	protected String getQueryByConditionMainSql(Q m)throws SQLException {
		return this.getQueryByConditionMainSqlSelectPart(m)+this.getQueryByConditionMainSqlFormPart(m)
		+this.getQueryByConditionMainSqlWherePart(m);
	}
	protected String getQueryByConditionMainSqlSelectPart(Q m)throws SQLException {
		return "select o ";
	}
	protected String getQueryByConditionMainSqlFormPart(Q m)throws SQLException {
		String tempStr = m.getClass().getSuperclass().getSimpleName();
		return  " from "+tempStr+" o ";
	}
	protected String getQueryByConditionMainSqlWherePart(Q m)throws SQLException {
		return  " where 1=1 ";
	}
	
	
	protected String getCountMainSql(Q m)throws SQLException {
		String tempStr = m.getClass().getSuperclass().getSimpleName();
		String sql = "select count(o) from "+tempStr+" o where 1=1 ";
		return sql;
	}
	protected String prepareSql(String sql,Q qm,List<String> excludeName)throws SQLException {
		StringBuffer buffer = new StringBuffer(sql);
		Field fs[] = qm.getClass().getSuperclass().getDeclaredFields();
		Field fs2[] = qm.getClass().getDeclaredFields();

		for(Field f : fs){
			if(Modifier.isTransient(f.getModifiers())){
				continue;
			}
			if(excludeName.contains(f.getName())){
				continue;
			}
			
			String type = f.getType().toString();
			if(type.endsWith("java.lang.String")){
				String v = ""+this.getValue(qm, f.getName());
				if(v!=null && v.trim().length()>0 && !v.equalsIgnoreCase("null")){
					buffer.append(" and o."+f.getName()+" like :"+f.getName()+" ");
				}
			}else if(type.endsWith("int")){
				int v = Integer.parseInt(""+this.getValue(qm, f.getName()));
				if(v > 0){
					if(this.isAreaValue(fs2, f.getName())){
						buffer.append(" and o."+f.getName()+" >=:"+f.getName()+" ");	
					}else{
						buffer.append(" and o."+f.getName()+" =:"+f.getName()+" ");
					}
				}
			}else if(type.endsWith("float")){
				float v = Float.parseFloat(""+this.getValue(qm, f.getName()));
				if(v > 0){
					if(this.isAreaValue(fs2, f.getName())){
						buffer.append(" and o."+f.getName()+" >=:"+f.getName()+" ");	
					}else{
						buffer.append(" and o."+f.getName()+" =:"+f.getName()+" ");
					}
				}
			}else if(type.endsWith("long")){
				long v = Long.parseLong(""+this.getValue(qm, f.getName()));
				if(v > 0){
					if(this.isAreaValue(fs2, f.getName())){
						buffer.append(" and o."+f.getName()+" >=:"+f.getName()+" ");	
					}else{
						buffer.append(" and o."+f.getName()+" =:"+f.getName()+" ");
					}
				}
			}		
		}
		
		for(Field f : fs2){
			if(Modifier.isTransient(f.getModifiers())){
				continue;
			}
			String type = f.getType().toString();
			if(type.endsWith("int")){
				int v = Integer.parseInt(""+this.getValue(qm, f.getName()));
				if(v>0){
					buffer.append(" and o."+f.getName().substring(0,f.getName().length()-1)+" <=:"+f.getName()+"2 ");
				}
			}else if(type.endsWith("float")){
				float v = Float.parseFloat(""+this.getValue(qm, f.getName()));
				if(v > 0){
					buffer.append(" and o."+f.getName().substring(0,f.getName().length()-1)+" <=:"+f.getName()+"2 ");
				}
			}else if(type.endsWith("long")){
				long v = Long.parseLong(""+this.getValue(qm, f.getName()));
				if(v > 0){
					buffer.append(" and o."+f.getName().substring(0,f.getName().length()-1)+" <=:"+f.getName()+"2 ");
				}
			}
		}
		
		return buffer.toString();	
	}
	protected void setValue(Query q,Q qm,List<String> excludeName) throws SQLException{
		Field fs[] = qm.getClass().getSuperclass().getDeclaredFields();
		Field fs2[] = qm.getClass().getDeclaredFields();

		for(Field f : fs){
			if(Modifier.isTransient(f.getModifiers())){
				continue;
			}
			if(excludeName.contains(f.getName())){
				continue;
			}
			String type = f.getType().toString();
			if(type.endsWith("java.lang.String")){
				String v = ""+this.getValue(qm, f.getName());
				if(v!=null && v.trim().length()>0  && !v.equalsIgnoreCase("null")){
					q.setString(f.getName(), "%"+v+"%");
				}
			}else if(type.endsWith("int")){
				int v = Integer.parseInt(""+this.getValue(qm, f.getName()));
				if(v > 0){
					q.setInteger(f.getName(), v);
				}
			}else if(type.endsWith("float")){
				float v = Float.parseFloat(""+this.getValue(qm, f.getName()));
				if(v > 0){
					q.setFloat(f.getName(), v);
				}
			}else if(type.endsWith("long")){
				long v = Long.parseLong(""+this.getValue(qm, f.getName()));
				if(v > 0){
					q.setLong(f.getName(), v);
				}
			}		
		}
		
		for(Field f : fs2){
			if(Modifier.isTransient(f.getModifiers())){
				continue;
			}
			String type = f.getType().toString();
			if(type.endsWith("int")){
				int v = Integer.parseInt(""+this.getValue(qm, f.getName()));
				if(v>0){
					q.setInteger(f.getName()+"2", v);
				}
			}else if(type.endsWith("float")){
				float v = Float.parseFloat(""+this.getValue(qm, f.getName()));
				if(v > 0){
					q.setFloat(f.getName()+"2", v);
				}
			}else if(type.endsWith("long")){
				long v = Long.parseLong(""+this.getValue(qm, f.getName()));
				if(v > 0){
					q.setLong(f.getName()+"2", v);
				}
			}
		}
	}

	
	private boolean isAreaValue(Field fs2[] , String name){
		for(Field f : fs2){
			if(f.getName().equalsIgnoreCase(name+"2")){
				return true;
			}
		}
		return false;
	}	
	private Object getValue(M m,String name) {
		Object ret = null;
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		
		context.setVariable("obj",m);
		
		try{
			ret = parser.parseExpression("#obj."+name).getValue(context);
		}catch(Exception err){
			//
		}		
		return ret;
	}
	////////////////////////////////////////////////////////////
	public void create(M m) {
		this.getHibernateTemplate().save(m);
	}	
	public void update(M m) {
		this.getHibernateTemplate().update(m);
	}
	public void delete(M m) {
		this.getHibernateTemplate().delete(m);
	}
}

