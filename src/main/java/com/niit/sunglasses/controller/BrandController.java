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
import com.niit.sunglasses.model.Brand;
import com.niit.sunglasses.services.BrandSrv;

@Controller
public class BrandController {

	@Autowired
	private BrandSrv brandSrv;

	@Autowired
	ServletContext srv;
	
	private Brand brand;
	
	@RequestMapping(value = "/getAllBrands")
	@ResponseBody
	public List<Brand> getAllBrands() {
		return brandSrv.getAllBrands();
	}
	
	@RequestMapping(value = "AdminPages/saveBrand", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("brandAttribute") Brand brandObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickBrand","true");
		try
		{
			if(result.hasErrors())
			{	
				mv.addObject("message","Record Can't be Added..!!Please Provide Valid Data..!!");
				return mv;
			}else if(brandObj.getFilePath(srv.getRealPath("/"), srv.getContextPath()) == "fail"){
				String str = brand.getBrand_image();
				if(str != null && !str.isEmpty()){
					brandObj.setBrand_image(str);
					brandObj.setStatus(true);
					brandSrv.brandSaveOrUpdate(brandObj);
					mv.addObject("message","Brand Updated Successfully..!!");
					mv.addObject("brandAttribute",new Brand());
				}else{
					mv.addObject("message","Image Uploading Fail..!!");
				}
				return mv;	
			}else{
				brandObj.setStatus(true);
				brandSrv.brandSaveOrUpdate(brandObj);
				mv.addObject("message","Brand Added Successfully..!!");
				mv.addObject("brandAttribute",new Brand());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="AdminPages/editBrand")
	public ModelAndView editBrand(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickBrand","true");
		try {
			brand = brandSrv.getById(id);
			mv.addObject("brandAttribute",brand);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteBrand/{id}")
	public ModelAndView removeBrand(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickBrand","true");
		String message;
		try {
			boolean flag = brandSrv.removeBrand(id);
			 message = "Brand Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this Brand..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("brandAttribute",new Brand());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="AdminPages/deleteBrand")
	public ModelAndView removeBrand(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickBrand","true");
		String message;
		try {
			boolean flag = brandSrv.removeBrand(id);
			 message = "Brand Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this Brand..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("brandAttribute",new Brand());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
