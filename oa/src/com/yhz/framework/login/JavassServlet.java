package com.yhz.framework.login;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class JavassServlet extends HttpServlet implements Servlet{
	  public static JavassDefaultManageableImageCaptchaService service = new JavassDefaultManageableImageCaptchaService();
	  protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws ServletException, IOException {
	      httpServletResponse.setDateHeader("Expires", 0L);
	      httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	      httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
	      httpServletResponse.setHeader("Pragma", "no-cache");
	      httpServletResponse.setContentType("image/jpeg");
	      
	      BufferedImage bi=service.getImageChallengeForID(httpServletRequest.getSession(true).getId());
	      ServletOutputStream out = httpServletResponse.getOutputStream();
	      ImageIO.write(bi, "jpg", out);
	      try {  
	    	  out.flush(); 
	      } finally { 
	    	  out.close(); 
	      }
	  } 

	  public static boolean validateResponse(HttpServletRequest request,String userCaptchaResponse){
	      if (request.getSession(false) == null){ 
	    	  return false;
	      }
	      boolean validated = false;
	      try { 
	    	  validated = service.validateResponseForID(request.getSession().getId(), userCaptchaResponse).booleanValue();
	      } catch (CaptchaServiceException e) { 
	    	  e.printStackTrace(); 
	      }
	      return validated;
	  }
	  public static boolean hasCaptcha(HttpServletRequest request, String userCaptchaResponse) {
	      if (request.getSession(false) == null){ 
	    	  return false;
	      }
	      boolean valided = false;
	      try {
	    	  valided = service.hasCapcha(request.getSession().getId(), userCaptchaResponse);
	      } catch (CaptchaServiceException e) {  
	    	  e.printStackTrace(); 
	      }
	      return valided;
	  }
	}
	class JavassDefaultManageableImageCaptchaService extends DefaultManageableImageCaptchaService{
	  public boolean hasCapcha(String id, String userCaptchaResponse) {
	      return store.getCaptcha(id).validateResponse(userCaptchaResponse);
	  }
	}

