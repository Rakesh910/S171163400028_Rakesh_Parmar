package com.niit.sunglasses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.niit.sunglasses.model.*;
import com.niit.sunglasses.services.BrandSrv;
import com.niit.sunglasses.services.CategorySrv;
import com.niit.sunglasses.services.FrameColorSrv;
import com.niit.sunglasses.services.FrameMaterialSrv;
import com.niit.sunglasses.services.FrameTypeSrv;
import com.niit.sunglasses.services.LensColorSrv;
import com.niit.sunglasses.services.LensMaterialSrv;
import com.niit.sunglasses.services.ProductSizeSrv;
import com.niit.sunglasses.services.SupplierSrv;


@Controller
public class AdminIndexController {
	
	@Autowired
	private BrandSrv brandSrv;
	
	@Autowired
	private CategorySrv categorySrv;
	
	@Autowired
	private FrameColorSrv frameColorSrv;
	
	@Autowired
	private FrameMaterialSrv frameMaterialSrv;
	
	@Autowired
	private FrameTypeSrv frameTypeSrv;
	
	@Autowired
	private LensColorSrv lensColorSrv;
	
	@Autowired
	private LensMaterialSrv lensMaterialSrv;
	
	@Autowired
	private ProductSizeSrv productSizeSrv;
	
	@Autowired
	private SupplierSrv supplierSrv;
	
	
	

	@RequestMapping("/admin")
	public ModelAndView indexPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("isAdminClickHome","true");
		return mv;
	}
	
	@RequestMapping(value="/manageBrand",method=RequestMethod.GET)
	public ModelAndView brandPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("brandAttribute",new Brand());
		mv.addObject("isAdminClickBrand","true");
		return mv;
	}
	
	@RequestMapping(value="/manageCategory",method=RequestMethod.GET)
	public ModelAndView categoryPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("categoryAttribute",new Category());
		mv.addObject("brandList",brandSrv.getAllBrands());
		mv.addObject("isAdminClickCategory","true");
		return mv;
	}
	
	@RequestMapping(value="/manageSupplier",method=RequestMethod.GET)
	public ModelAndView supplierPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("supplierAttribute",new Supplier());
		mv.addObject("isAdminClickSupplier","true");
		return mv;
	}
	
	@RequestMapping(value="/manageProduct",method=RequestMethod.GET)
	public ModelAndView productPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		
		Product product = new Product();
		
		ProductSize productSize = new ProductSize();
		productSize.setSize_id(1);
		product.setProductSize(productSize);
		
		LensColor lensColor = new LensColor();
		lensColor.setLensColor_id(1);
		product.setLensColor(lensColor);
		
		LensMaterial lensMaterial = new LensMaterial();
		lensMaterial.setLensMaterial_id(1);
		product.setLensMaterial(lensMaterial);
		
		FrameColor frameColor = new FrameColor();
		frameColor.setFrameColor_id(1);
		product.setFrameColor(frameColor);
		
		FrameMaterial frameMaterial = new FrameMaterial();
		frameMaterial.setFrameMaterial_id(1);
		product.setFrameMaterial(frameMaterial);
		
		FrameType frameType = new FrameType();
		frameType.setFrameType_id(1);
		product.setFrameType(frameType);
		
		mv.addObject("productAttribute",product);
		mv.addObject("isAdminClickProduct","true");
		setLists(mv);
		return mv;
	}
	
	@RequestMapping(value="/manageProductSize",method=RequestMethod.GET)
	public ModelAndView productSizePage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("productSizeAttribute",new ProductSize());
		mv.addObject("isAdminClickProductSize","true");
		return mv;
	}
	
	@RequestMapping(value="/manageFrameType",method=RequestMethod.GET)
	public ModelAndView frameTypePage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("frameTypeAttribute",new FrameType());
		mv.addObject("isAdminClickFrameType","true");
		return mv;
	}
	
	@RequestMapping(value="/manageFrameColor",method=RequestMethod.GET)
	public ModelAndView frameColorPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("frameColorAttribute",new FrameColor());
		mv.addObject("isAdminClickFrameColor","true");
		return mv;
	}
	
	@RequestMapping(value="/manageFrameMaterial",method=RequestMethod.GET)
	public ModelAndView frameMaterialPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("frameMaterialAttribute",new FrameMaterial());
		mv.addObject("isAdminClickFrameMaterial","true");
		return mv;
	}
	
	@RequestMapping(value="/manageLensColor",method=RequestMethod.GET)
	public ModelAndView lensColorPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("lensColorAttribute",new LensColor());
		mv.addObject("isAdminClickLensColor","true");
		return mv;
	}
	
	@RequestMapping(value="/manageLensMaterial",method=RequestMethod.GET)
	public ModelAndView lensMaterialPage(){
		ModelAndView mv = new ModelAndView("adminIndex");
		mv.addObject("lensMaterialAttribute",new LensMaterial());
		mv.addObject("isAdminClickLensMaterial","true");
		return mv;
	}
	
	@RequestMapping("/getString")
	public ModelAndView pg(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("msg","Hello");
		return mv;
	}
	
	public void setLists(ModelAndView mv){
		mv.addObject("brandList",brandSrv.getAllBrands());
		mv.addObject("categoryList",categorySrv.getCategoryList());
		mv.addObject("frameColorList",frameColorSrv.getAllFrameColors());
		mv.addObject("frameMaterialList",frameMaterialSrv.getAllFrameMaterials());
		mv.addObject("frameTypeList",frameTypeSrv.getAllFrameTypes());
		mv.addObject("lensColorList",lensColorSrv.getAllLensColors());
		mv.addObject("lensMaterialList",lensMaterialSrv.getAllLensMaterials());
		mv.addObject("productSizeList",productSizeSrv.getAllProductSizes());
		mv.addObject("supplierList",supplierSrv.getAllSuppliers());		
	}
}
