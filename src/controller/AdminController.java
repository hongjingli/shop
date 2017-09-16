package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Admin;
import model.entity.CartItem;
import model.entity.User;
import model.view.request.AdminLoginForm;
import model.view.request.CategoryEditForm;
import model.view.request.GoodsEditForm;
import model.view.request.InvalidFormException;
import model.view.request.PersonEditForm;
import model.view.request.UserLoginForm;
import model.view.request.UserRegisterForm;
import model.view.response.CartItemDetail;
import model.view.response.GoodsDetail;
import model.view.response.JsonResult;
import model.view.response.OrderDetail;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import exception.AuthException;
import exception.DuplicateException;
import util.E;
import service.*;
import service.impl.AdminServiceImpl;
import service.impl.BuyerServiceImpl;


@Controller
public class AdminController {
	  @Autowired
	  private AdminService admin;
	  
    @RequestMapping("/admin/user/list")    
    public String user_list(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
    	map.put("users",admin.getUserList());
    	return "admin/user";
    }

  @RequestMapping("/admin/user/do-remove")  
  @ResponseBody
  public JsonResult user_do_remove(HttpSession session,HttpServletRequest request, HttpServletResponse response,ModelMap map) {
  	try {
    	admin.removeUser(((User)session.getAttribute("User")).getId());
    	}
  	catch(DataAccessException e) {
		return new JsonResult(false,e.getMessage());
	}
	return new JsonResult(true);
  }   
 
  @RequestMapping("/admin/category/list")  
  public String category_list(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
  	map.put("categoryList",admin.getCategoryList() );
  	return "admin/category";
  } 
  
@RequestMapping("/admin/category/do-edit")
@ResponseBody
public JsonResult category_do_edit(CategoryEditForm form) {
	try {
		form.validate();
		admin.editCategory(form);
	}
	catch(InvalidFormException e) {
		return new JsonResult(false,e.getMessage());
	}
	return new JsonResult(true);
}

@RequestMapping("/admin/category/do-add")
@ResponseBody
public JsonResult category_do_add(CategoryEditForm form) {
	try {
		form.validate();
	    admin.addCategory(form);
	}
	catch(InvalidFormException e) {
		return new JsonResult(false,e.getMessage());
	}
	return new JsonResult(true);
}

@RequestMapping("/admin/category/do-remove")  
@ResponseBody
public JsonResult category_do_remove(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
	try {
    	admin.removeCategory(Integer.parseInt(request.getAttribute("categoryId").toString()));
    	}
  	catch(DataAccessException e) {
		return new JsonResult(false,e.getMessage());
	}
	return new JsonResult(true);
}

@RequestMapping("/admin/login")
public String admin_login(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
	
	return "admin/login";
}
@RequestMapping("/admin/login/do-login")
@ResponseBody
public JsonResult admin_do_login(AdminLoginForm form)  {
	try {
		form.validate();
		admin.login(form);
	}
	catch(InvalidFormException e) {
		return new JsonResult(false,e.getMessage());
	}
	catch(AuthException e) {
		return new JsonResult(false,e.getMessage());
	}
	return new JsonResult(true);
}

    
}
