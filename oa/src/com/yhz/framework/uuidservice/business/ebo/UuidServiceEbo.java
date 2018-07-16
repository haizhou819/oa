package com.yhz.framework.uuidservice.business.ebo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhz.framework.uuidservice.business.ebi.UuidServiceEbi;
import com.yhz.framework.uuidservice.dao.dao.UuidServiceDao;
import com.yhz.framework.uuidservice.vo.UuidModel;
import com.yhz.framework.uuidservice.vo.UuidQueryModel;


public class UuidServiceEbo implements UuidServiceEbi{
	private UuidServiceDao dao = null;
	public void setDao(UuidServiceDao dao){
		this.dao = dao;
	}
	
	private static Map<String,Integer> mapDB = null;
	private static Map<String,Integer> mapNow = new HashMap<String,Integer>();
	private void init(){
		//延迟初始化mapDB和mapNow
		if(mapDB==null){
			mapDB = new HashMap<String,Integer>();
			List<UuidModel> list = dao.getAll(new UuidQueryModel(), false, 0,0);
			for(UuidModel um : list){
				mapDB.put(um.getPreKey(), um.getNum());
				mapNow.put(um.getPreKey(), um.getNum());
			}
		}		
	}
	
	@Override
	public int getNextUuid(String preKey, int cacheNum) {
		//延迟初始化
		init();
		//1:到mapNow去查找
		Object obj = mapNow.get(preKey);
		int nowNum = 0;
		if(obj!=null){
			//2:如果存在，就取出来加1，并修改回MapNow
			nowNum = (Integer)obj;
			nowNum = nowNum + 1;
			mapNow.put(preKey, nowNum);
			//3:判断是否需要修改数据库
			if(nowNum >= mapDB.get(preKey)){
				UuidModel tempUm = new UuidModel();
				tempUm.setPreKey(preKey);
				tempUm.setNum(mapDB.get(preKey) + cacheNum);
				dao.update(tempUm);
				//同时修改mapDB
				mapDB.put(preKey, tempUm.getNum());
			}
		}else{
			nowNum = 1;
			//4:如果不存在，就直接操作数据库，同时向MapNow和MapDB添加数据
			mapNow.put(preKey, 1);
			mapDB.put(preKey, cacheNum);
			UuidModel tempUm = new UuidModel();
			tempUm.setPreKey(preKey);
			tempUm.setNum(cacheNum);
			
			dao.create(tempUm);
		}
		
		return nowNum;
	}

	@Override
	public String getNextUuid(String preKey, String formatStr, int numLen,
			int cacheNum) {
		//1：得到顺序号
		int num = this.getNextUuid(preKey, cacheNum);
		//2:前面补充字符串
		String str = "";
		for(int i=numLen;i>(""+num).length();i--){
			str += "0";
		}
		str = str + num;
		//3:替换格式字符串中#号     Javass_GDZC_#_Viewer
		str = formatStr.replace("#", str);
		return str;
	}
}
