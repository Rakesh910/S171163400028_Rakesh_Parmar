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
import com.niit.sunglasses.model.FrameMaterial;
import com.niit.sunglasses.services.FrameMaterialSrv;

@Controller
public class FrameMaterialController {

	@Autowired
	private FrameMaterialSrv frameMaterialSrv;

	@Autowired
	ServletContext srv;
	
	private FrameMaterial frameMaterial;
	
	@RequestMapping(value = "/getAllFrameMaterials")
	@ResponseBody
	public List<FrameMaterial> getAllFrameMaterials() {
		return frameMaterialSrv.getAllFrameMaterials();
	}
	
	@RequestMapping(value = "AdminPages/saveFrameMaterial", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("frameMaterialAttribute") FrameMaterial frameMaterialObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickFrameMaterial","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Record Can't be Added..!!Please Provide Valid Data..!!");
				return mv;
			}else{
				frameMaterialObj.setStatus(true);
				frameMaterialSrv.frameMaterialSaveOrUpdate(frameMaterialObj);
				mv.addObject("message","FrameMaterial Added Successfully..!!");
				mv.addObject("frameMaterialAttribute",new FrameMaterial());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="AdminPages/editFrameMaterial")
	public ModelAndView editFrameMaterial(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickFrameMaterial","true");
		try {
			frameMaterial = frameMaterialSrv.getById(id);
			mv.addObject("frameMaterialAttribute",frameMaterial);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteFrameMaterial/{id}")
	public ModelAndView removeFrameMaterial(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickFrameMaterial","true");
		String message;
		try {
			boolean flag = frameMaterialSrv.removeFrameMaterial(id);
			 message = "FrameMaterial Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this FrameMaterial..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("frameMaterialAttribute",new FrameMaterial());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="AdminPages/deleteFrameMaterial")
	public ModelAndView removeFrameMaterial(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickFrameMaterial","true");
		String message;
		try {
			boolean flag = frameMaterialSrv.removeFrameMaterial(id);
			 message = "FrameMaterial Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this FrameMaterial..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("frameMaterialAttribute",new FrameMaterial());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
