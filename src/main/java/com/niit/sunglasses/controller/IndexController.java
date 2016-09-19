package com.niit.sunglasses.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.services.BrandSrv;
import com.niit.sunglasses.services.ProductSrv;

@Controller
public class IndexController {
	
	@Autowired
	private BrandSrv brandSrv;
	
	@Autowired
	private ProductSrv productSrv;
		
	@RequestMapping("/")
	public ModelAndView indexPage(HttpSession session){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("isUserClickHome","true");
		mv.addObject("brandList",brandSrv.getAllBrands());
		if(session.getAttribute("cartSize") == null){
			mv.addObject("cartSize",0);
		}else{
			mv.addObject("cartSize",session.getAttribute("cartSize"));
		}
		
		return mv;
	}
	
	@RequestMapping("/home")
	public ModelAndView homePage(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("isUserClickHome","true");
		mv.addObject("brandList",brandSrv.getAllBrands());
		return mv;
	}
	
	/*@RequestMapping("/register")
	public ModelAndView registerPage(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("isUserClickRegister","true");
		return mv;
	}*/
	
	@RequestMapping(value="/showProductList")
	public ModelAndView productlist(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("isUserClickProductList","true");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("brandList",brandSrv.getById(id));
		return mv;
	}
	
	@RequestMapping(value="/productDetail")
	public ModelAndView productDetail(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("productDetail",productSrv.getById(id));
		mv.addObject("isUserClickProductDetail","true");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("brandList",brandSrv.getById(id));
		return mv;
	}
	
	
	
	
}
