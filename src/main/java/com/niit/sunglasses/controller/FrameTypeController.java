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
import com.niit.sunglasses.model.FrameType;
import com.niit.sunglasses.services.FrameTypeSrv;

@Controller
public class FrameTypeController {

	@Autowired
	private FrameTypeSrv frameTypeSrv;

	@Autowired
	ServletContext srv;
	
	private FrameType frameType;
	
	@RequestMapping(value = "/getAllFrameTypes")
	@ResponseBody
	public List<FrameType> getAllFrameTypes() {
		return frameTypeSrv.getAllFrameTypes();
	}
	
	@RequestMapping(value = "/saveFrameType", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("frameTypeAttribute") FrameType frameTypeObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickFrameType","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Error in BindingResult");
				return mv;
			}else{
				frameTypeObj.setStatus(true);
				frameTypeSrv.frameTypeSaveOrUpdate(frameTypeObj);
				mv.addObject("message","FrameType Added Successfully..!!");
				mv.addObject("frameTypeAttribute",new FrameType());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="/editFrameType")
	public ModelAndView editFrameType(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickFrameType","true");
		try {
			frameType = frameTypeSrv.getById(id);
			mv.addObject("frameTypeAttribute",frameType);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteFrameType/{id}")
	public ModelAndView removeFrameType(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickFrameType","true");
		String message;
		try {
			boolean flag = frameTypeSrv.removeFrameType(id);
			 message = "FrameType Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this FrameType..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("frameTypeAttribute",new FrameType());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="/deleteFrameType")
	public ModelAndView removeFrameType(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickFrameType","true");
		String message;
		try {
			boolean flag = frameTypeSrv.removeFrameType(id);
			 message = "FrameType Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this FrameType..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("frameTypeAttribute",new FrameType());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
