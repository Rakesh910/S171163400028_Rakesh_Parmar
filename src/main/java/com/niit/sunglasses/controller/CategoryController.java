package com.niit.sunglasses.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.niit.sunglasses.model.Category;
import com.niit.sunglasses.services.BrandSrv;
import com.niit.sunglasses.services.CategorySrv;

@Controller
public class CategoryController {

	@Autowired
	private BrandSrv brandSrv;
	
	@Autowired
	private CategorySrv categorySrv;

	@Autowired
	ServletContext srv;
	
	private Category category;
	
	@RequestMapping(value = "/getAllCategorys")
	@ResponseBody
	public List<Category> getAllCategorys() {
		return categorySrv.getAllCategorys();
	}
	
	@RequestMapping(value = "/saveCategory", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("categoryAttribute") Category categoryObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickCategory","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Error in BindingResult");
				return mv;
			}else{
				categoryObj.setStatus(true);
				categorySrv.categorySaveOrUpdate(categoryObj);
				mv.addObject("message","Category Added Successfully..!!");
				mv.addObject("categoryAttribute",new Category());
				mv.addObject("brandList",brandSrv.getAllBrands());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="/editCategory")
	public ModelAndView editCategory(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickCategory","true");
		try {
			category = categorySrv.getById(id);
			mv.addObject("categoryAttribute",category);
			mv.addObject("brandList",brandSrv.getAllBrands());
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="/deleteCategory")
	public ModelAndView removeCategory(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickCategory","true");
		String message;
		try {
			boolean flag = categorySrv.removeCategory(id);
			 message = "Category Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this Category..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("categoryAttribute",new Category());
			mv.addObject("brandList",brandSrv.getAllBrands());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
