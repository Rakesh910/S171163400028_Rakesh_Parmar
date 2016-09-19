package com.niit.sunglasses.controller;

import java.util.List;
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
import com.niit.sunglasses.model.Supplier;
import com.niit.sunglasses.services.SupplierSrv;

@Controller
public class SupplierController {

	@Autowired
	private SupplierSrv supplierSrv;
	
	private Supplier supplier;
	
	@RequestMapping(value = "/getAllSuppliers")
	@ResponseBody
	public List<Supplier> getAllSuppliers() {
		return supplierSrv.getAllSuppliers();
	}
	
	@RequestMapping(value = "/saveSupplier", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("supplierAttribute") Supplier supplierObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickSupplier","true");
		try
		{
			if(result.hasErrors())
			{
				mv.addObject("message","Error in BindingResult");
				return mv;
			}else{
				supplierObj.setStatus(true);
				supplierSrv.supplierSaveOrUpdate(supplierObj);
				mv.addObject("message","Supplier Added Successfully..!!");
				mv.addObject("supplierAttribute",new Supplier());
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	@RequestMapping(value="/editSupplier")
	public ModelAndView editSupplier(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickSupplier","true");
		try {
			supplier = supplierSrv.getById(id);
			mv.addObject("supplierAttribute",supplier);
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	/*@RequestMapping(value="deleteSupplier/{id}")
	public ModelAndView removeSupplier(@PathVariable("id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickSupplier","true");
		String message;
		try {
			boolean flag = supplierSrv.removeSupplier(id);
			 message = "Supplier Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this Supplier..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("supplierAttribute",new Supplier());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}*/
	
	@RequestMapping(value="/deleteSupplier")
	public ModelAndView removeSupplier(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickSupplier","true");
		String message;
		try {
			boolean flag = supplierSrv.removeSupplier(id);
			 message = "Supplier Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this Supplier..!! Try Again";
			}
			mv.addObject("message",message);
			mv.addObject("supplierAttribute",new Supplier());
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			return mv;
		}
	}
	
	
}
