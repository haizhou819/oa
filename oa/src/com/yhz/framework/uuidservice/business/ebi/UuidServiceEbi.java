package com.yhz.framework.uuidservice.business.ebi;

public interface UuidServiceEbi {
	public int getNextUuid(String preKey,int cacheNum);
	public String getNextUuid(String preKey,String formatStr,int numLen,int cacheNum);
}
