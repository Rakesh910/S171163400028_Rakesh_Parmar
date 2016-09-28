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
import com.niit.sunglasses.model.LensMaterial;
import com.niit.sunglasses.services.LensMaterialSrv;

@Controller
public class LensMaterialController {

	@Autowired
	private LensMaterialSrv lensMaterialSrv;

	@Autowired
	ServletContext srv;
	
	private LensMaterial lensMaterial;
	
	@RequestMapping(value = "/getAllLensMaterials")
	@ResponseBody
	public List<LensMaterial> getAllLensMaterials() {
		return lensMaterialSrv.getAllLensMaterials();
	}
	
	@RequestMapping(value = "AdminPages/saveLensMaterial", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("lensMaterialAttribute") LensMaterial lensMaterialObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickLensMaterial","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Record Can't be Added..!!Please Provide Valid Data..!!");
				return mv;
			}else{
				lensMaterialObj.setStatus(true);
				lensMaterialSrv.lensMaterialSaveOrUpdate(lensMaterialObj);
				mv.addObject("message","LensMaterial Added Successfully..!!");
				mv.addObject("lensMaterialAttribute",new LensMaterial());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="AdminPages/editLensMaterial")
	public ModelAndView editLensMaterial(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickLensMaterial","true");
		try {
			lensMaterial = lensMaterialSrv.getById(id);
			mv.addObject("lensMaterialAttribute",lensMaterial);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteLensMaterial/{id}")
	public ModelAndView removeLensMaterial(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickLensMaterial","true");
		String message;
		try {
			boolean flag = lensMaterialSrv.removeLensMaterial(id);
			 message = "LensMaterial Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this LensMaterial..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("lensMaterialAttribute",new LensMaterial());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="AdminPages/deleteLensMaterial")
	public ModelAndView removeLensMaterial(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickLensMaterial","true");
		String message;
		try {
			boolean flag = lensMaterialSrv.removeLensMaterial(id);
			 message = "LensMaterial Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this LensMaterial..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("lensMaterialAttribute",new LensMaterial());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
