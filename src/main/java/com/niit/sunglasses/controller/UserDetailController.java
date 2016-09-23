package com.niit.sunglasses.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.model.UserRole;
import com.niit.sunglasses.services.BrandSrv;
import com.niit.sunglasses.services.UserDetailSrv;

@Controller
public class UserDetailController {
	
	@Autowired
	private UserDetailSrv userDetailSrv;
	
	private UserDetail userDetail;
	
	private UserRole userRole;
		
	@Autowired
	private BrandSrv brandSrv;
	
	/*@RequestMapping(value = "/saveUserDetail", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("userLoginAttribute") UserDetail userDetailObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("index");
		mv.addObject("isUserClickRegister","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Error in BindingResult");
				return mv;
			}else if(userDetailSrv.getByEmailID(userDetailObj.getUser_email()) == null){
				userDetailObj.setStatus(true);
				UserRole userRole = new UserRole() ;
				userRole.setUser_role_id(2);
				userDetailObj.setUserRole(userRole);
				userDetailSrv.userDetailSaveOrUpdate(userDetailObj);
				mv.addObject("message","Registration Successfully..!!");
				mv.addObject("userLoginAttribute",new UserDetail());
				return mv;
			}else{
				mv.addObject("message","User Already exist with this Email Address..!!");
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="userLogin",method=RequestMethod.POST)
	public ModelAndView Login(@ModelAttribute("userLoginAttribute") UserDetail userDetailObj, BindingResult result, Model model,HttpSession session){
		ModelAndView mv = new ModelAndView("index");
		userDetail = userDetailSrv.isValidUser(userDetailObj.getUser_email(), userDetailObj.getUser_password());
		if(userDetail == null){
			mv.addObject("message","Invalid User..!!Please Try Again..!!");
			mv.addObject("userLoginAttribute",new UserDetail());
			mv.addObject("isUserClickHome","true");
			return mv;
		}else{
			userRole = userDetail.getUserRole();
			if(userRole.getUser_role_id()==1){
				mv = new ModelAndView("adminIndex");
				mv.addObject("isAdminClickHome","true");
				session.setAttribute("userId", userDetail.getUser_id());
				session.setAttribute("username", (userDetail.getUser_fname()+" "+userDetail.getUser_lname()));
				return mv;
			}else{
				mv = new ModelAndView("index");
				mv.addObject("userLoginAttribute",new UserDetail());
				mv.addObject("isUserClickHome","true");
				session.setAttribute("userId", userDetail.getUser_id());
				session.setAttribute("username", (userDetail.getUser_fname()+" "+userDetail.getUser_lname()));
				session.setAttribute("cartSize",0);
				mv.addObject("brandList",brandSrv.getAllBrands());	
				return mv;
			}
		}	
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView removeProduct(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("username");
		session.invalidate();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("isUserClickHome","true");
		mv.addObject("brandList",brandSrv.getAllBrands());
		return mv;
	}

}
