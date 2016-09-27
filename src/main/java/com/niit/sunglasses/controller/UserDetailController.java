package com.niit.sunglasses.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.sunglasses.model.Product;
import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.services.BrandSrv;
import com.niit.sunglasses.services.ProductSrv;

@Controller
public class UserDetailController {
	
	
	
	@Autowired
	private BrandSrv brandSrv;
	
	@Autowired
	private ProductSrv productSrv;
	
	
	/*@RequestMapping(value="loginPage")
	public String loginpage(){
		
		System.out.println("Return LoginPage");
		return "loginPage";
	}*/
	
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
	
	//Comment for implementing Spring Security.(This Method for Normal Login)
	/*@RequestMapping(value="login",method=RequestMethod.POST)
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
				mv = new ModelAndView("AdminPages/adminIndex");
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
	}*/
	
	@RequestMapping(value="/loginPage")
	public ModelAndView loginMethod(@RequestParam(value = "authfailed", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model){
		if(error != null) {
			System.out.println("Error Not Null");
			ModelAndView mv = new ModelAndView("loginPage");
			mv.addObject("message", "Invalid username and password");
			return mv;
		} else if (logout != null){
			System.out.println("Logout Not Null");
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("brandList",brandSrv.getAllBrands());
			mv.addObject("message","Logout Successfully");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView("loginPage");
			mv.addObject("isLoginPage",true);
			return mv;
		}
		
		/*System.out.println("Start If");
		if (error != null) {
			System.out.println("IN If Error not null");
			model.addAttribute("error", "Invalid username and password");
		}
		System.out.println("End IF error");
		System.out.println("Start Logout if");
		if (logout != null) {
			System.out.println("Start Logout if");
			model.addAttribute("msg", "You have been logged out successfully.");
			System.out.println("End Logout if");
		}
		System.out.println("Return View");
		ModelAndView mv = new ModelAndView("loginPage");
		return  mv;*/
	}
	
	/*@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session,Model model) {
		session.removeAttribute("userId");
		session.removeAttribute("username");
		session.invalidate();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("brandList",brandSrv.getAllBrands());
		List<Product> product = productSrv.getNewArrivals();
		System.out.println("New Arrival List Size :-"+product.size());
		mv.addObject("newProductsList",product);
		System.out.println("Simple Logout");
		return mv;
	}*/
	
	/*@RequestMapping(value = "AdminPages/logout")
	public ModelAndView Adminlogout(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("username");
		session.invalidate();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("brandList",brandSrv.getAllBrands());
		mv.addObject("newProductsList",productSrv.getNewArrivals());
		System.out.println("Admin Logout");
		return mv;
	}*/
	
	/*@RequestMapping(value = "UserPages/logout")
	public ModelAndView removeProduct(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("username");
		session.invalidate();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("brandList",brandSrv.getAllBrands());
		mv.addObject("newProductsList",productSrv.getNewArrivals());
		System.out.println("User Logout");
		return mv;
	}*/

	@RequestMapping(value = "/exception")
	public String exceptionPage(){
		return "exception";
	}
	
}
