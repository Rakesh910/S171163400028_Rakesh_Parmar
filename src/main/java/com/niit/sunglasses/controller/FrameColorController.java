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
import com.niit.sunglasses.model.FrameColor;
import com.niit.sunglasses.services.FrameColorSrv;

@Controller
public class FrameColorController {

	@Autowired
	private FrameColorSrv frameColorSrv;

	@Autowired
	ServletContext srv;
	
	private FrameColor frameColor;
	
	@RequestMapping(value = "/getAllFrameColors")
	@ResponseBody
	public List<FrameColor> getAllFrameColors() {
		return frameColorSrv.getAllFrameColors();
	}
	
	@RequestMapping(value = "AdminPages/saveFrameColor", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("frameColorAttribute") FrameColor frameColorObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickFrameColor","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Record Can't be Added..!!Please Provide Valid Data..!!");
				return mv;
			}else{
				frameColorObj.setStatus(true);
				frameColorSrv.frameColorSaveOrUpdate(frameColorObj);
				mv.addObject("message","FrameColor Added Successfully..!!");
				mv.addObject("frameColorAttribute",new FrameColor());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="AdminPages/editFrameColor")
	public ModelAndView editFrameColor(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickFrameColor","true");
		try {
			frameColor = frameColorSrv.getById(id);
			mv.addObject("frameColorAttribute",frameColor);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteFrameColor/{id}")
	public ModelAndView removeFrameColor(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickFrameColor","true");
		String message;
		try {
			boolean flag = frameColorSrv.removeFrameColor(id);
			 message = "FrameColor Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this FrameColor..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("frameColorAttribute",new FrameColor());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="AdminPages/deleteFrameColor")
	public ModelAndView removeFrameColor(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickFrameColor","true");
		String message;
		try {
			boolean flag = frameColorSrv.removeFrameColor(id);
			 message = "FrameColor Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this FrameColor..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("frameColorAttribute",new FrameColor());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
