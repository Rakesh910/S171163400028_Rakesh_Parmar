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
import com.niit.sunglasses.model.LensColor;
import com.niit.sunglasses.services.LensColorSrv;

@Controller
public class LensColorController {

	@Autowired
	private LensColorSrv lensColorSrv;

	@Autowired
	ServletContext srv;
	
	private LensColor lensColor;
	
	@RequestMapping(value = "/getAllLensColors")
	@ResponseBody
	public List<LensColor> getAllLensColors() {
		return lensColorSrv.getAllLensColors();
	}
	
	@RequestMapping(value = "/saveLensColor", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("lensColorAttribute") LensColor lensColorObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickLensColor","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Error in BindingResult");
				return mv;
			}else{
				lensColorObj.setStatus(true);
				lensColorSrv.lensColorSaveOrUpdate(lensColorObj);
				mv.addObject("message","LensColor Added Successfully..!!");
				mv.addObject("lensColorAttribute",new LensColor());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="/editLensColor")
	public ModelAndView editLensColor(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickLensColor","true");
		try {
			lensColor = lensColorSrv.getById(id);
			mv.addObject("lensColorAttribute",lensColor);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteLensColor/{id}")
	public ModelAndView removeLensColor(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickLensColor","true");
		String message;
		try {
			boolean flag = lensColorSrv.removeLensColor(id);
			 message = "LensColor Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this LensColor..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("lensColorAttribute",new LensColor());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="/deleteLensColor")
	public ModelAndView removeLensColor(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickLensColor","true");
		String message;
		try {
			boolean flag = lensColorSrv.removeLensColor(id);
			 message = "LensColor Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this LensColor..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("lensColorAttribute",new LensColor());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
