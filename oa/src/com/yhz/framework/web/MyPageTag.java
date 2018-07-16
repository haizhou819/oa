package com.yhz.framework.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MyPageTag extends TagSupport{
	private String actionName="";
	public void setActionName(String actionName){
		this.actionName = actionName;
	}
	private String toList="";
	public void setToList(String toList){
		this.toList = toList;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return this.SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {
		
		WebModel wm = (WebModel)this.pageContext.findAttribute("wm");
		
		StringBuffer buffer = new StringBuffer("");
		
		buffer.append("<script language=\"javascript\">");
		buffer.append("function funToPage(){");
		buffer.append("var v = document.getElementById(\"toPage\").value;");
		buffer.append("if(parseInt(v)< 1 || parseInt(v)>parseInt("+wm.getMaxPage()+")){");
		buffer.append("alert('对不起，输入的页面号超出了范围，您可以输入的值为1-"+wm.getMaxPage()+"');");
		buffer.append("return;}");
		buffer.append("window.location.href=\""+this.pageContext.getServletContext().getContextPath()
				+"/"+this.actionName+".action?wm.submitFlag="+toList+"&wm.nowPage=\"+v;");
		buffer.append("}</script>");
		
		
		buffer.append("<tr><td colspan=18><div align=\"center\"  class=\"page\">");
		if(wm.getNowPage()==1){
			buffer.append("首页 	| 上一页");
		}
		if(wm.getNowPage()>1){
			buffer.append("<a href=\""+this.pageContext.getServletContext().getContextPath()+"/"
					+this.actionName+".action?wm.submitFlag="+toList+"&wm.nowPage=1\">首页</a>| <a href='"+
					this.pageContext.getServletContext().getContextPath()+"/"
					+this.actionName+".action?wm.submitFlag="+toList+"&wm.nowPage="+(wm.getNowPage()-1)+"'>上一页</a>");
		}
		
		if(wm.getNowPage()==wm.getMaxPage()){
			buffer.append("| 下一页 | 尾页");
		}
		if(wm.getNowPage()>=1 && wm.getNowPage() < wm.getMaxPage()){
			buffer.append("| <a href='"+this.pageContext.getServletContext().getContextPath()+"/"
					+this.actionName+".action?wm.submitFlag="+toList+"&wm.nowPage="+(wm.getNowPage()+1)
					+"'>下一页</a>	| <a href='"+this.pageContext.getServletContext().getContextPath()+"/"
					+this.actionName+".action?wm.submitFlag="+toList+"&wm.nowPage="+wm.getMaxPage()+"'>尾页</a>");
		}
		buffer.append("| 页次："+wm.getNowPage()+"/"+wm.getMaxPage()+"页 | 共有"+wm.getCount()
				+"条	 <input id=\"toPage\" type=\"text\" class=\"input\" size=\"2\" /> <a href=\"#\" onclick=\"funToPage();\">转到</a>");
		
		buffer.append("</div></td></tr>");
		
		  		 
		try {
			this.pageContext.getOut().println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}  		
		  	
		return this.SKIP_BODY;
	}
	
}

