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
import com.niit.sunglasses.model.ProductSize;
import com.niit.sunglasses.services.ProductSizeSrv;

@Controller
public class ProductSizeController {

	@Autowired
	private ProductSizeSrv productSizeSrv;

	@Autowired
	ServletContext srv;
	
	private ProductSize productSize;
	
	@RequestMapping(value = "/getAllProductSizes")
	@ResponseBody
	public List<ProductSize> getAllProductSizes() {
		return productSizeSrv.getAllProductSizes();
	}
	
	@RequestMapping(value = "/saveProductSize", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("productSizeAttribute") ProductSize productSizeObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickProductSize","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Error in BindingResult");
				return mv;
			}else{
				productSizeObj.setStatus(true);
				productSizeSrv.productSizeSaveOrUpdate(productSizeObj);
				mv.addObject("message","ProductSize Added Successfully..!!");
				mv.addObject("productSizeAttribute",new ProductSize());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="/editProductSize")
	public ModelAndView editProductSize(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickProductSize","true");
		try {
			productSize = productSizeSrv.getById(id);
			mv.addObject("productSizeAttribute",productSize);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteProductSize/{id}")
	public ModelAndView removeProductSize(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickProductSize","true");
		String message;
		try {
			boolean flag = productSizeSrv.removeProductSize(id);
			 message = "ProductSize Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this ProductSize..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("productSizeAttribute",new ProductSize());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="/deleteProductSize")
	public ModelAndView removeProductSize(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickProductSize","true");
		String message;
		try {
			boolean flag = productSizeSrv.removeProductSize(id);
			 message = "ProductSize Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this ProductSize..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("productSizeAttribute",new ProductSize());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
