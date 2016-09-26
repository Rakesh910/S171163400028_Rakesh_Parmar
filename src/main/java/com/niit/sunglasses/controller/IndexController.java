package com.niit.sunglasses.controller;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.sunglasses.model.Brand;
import com.niit.sunglasses.model.Category;
import com.niit.sunglasses.model.Product;
import com.niit.sunglasses.model.ProductSize;
import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.services.BrandSrv;
import com.niit.sunglasses.services.CategorySrv;
import com.niit.sunglasses.services.FrameColorSrv;
import com.niit.sunglasses.services.FrameMaterialSrv;
import com.niit.sunglasses.services.FrameTypeSrv;
import com.niit.sunglasses.services.LensColorSrv;
import com.niit.sunglasses.services.LensMaterialSrv;
import com.niit.sunglasses.services.ProductSizeSrv;
import com.niit.sunglasses.services.ProductSrv;
import com.niit.sunglasses.services.SupplierSrv;
import com.niit.sunglasses.services.UserDetailSrv;

@Controller
public class IndexController {
	
	@Autowired
	private BrandSrv brandSrv;
	
	@Autowired
	private ProductSrv productSrv;
	
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
	
	private UserDetail userDetail;
	
	@Autowired
	private UserDetailSrv userDetailSrv;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView indexPage(HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("brandList",brandSrv.getAllBrands());
		mv.addObject("newProductsList",productSrv.getNewArrivals());
		if(session.getAttribute("cartSize") == null){
			mv.addObject("cartSize",0);
		}else{
			mv.addObject("cartSize",session.getAttribute("cartSize"));
		}
		
		try {
			if (request.getUserPrincipal().getName() != null) {
				userDetail = userDetailSrv.getByEmailID(request.getUserPrincipal().getName());
				System.out.println("UserName:-"+userDetail.getUser_fname());
				if (userDetail.getUserRole().getUser_role_id() == 1) {
					//mv.addObject("isAdminTrue",request.getUserPrincipal().getName());
					session.setAttribute("userId", userDetail.getUser_id());
					session.setAttribute("username", (userDetail.getUser_fname()+" "+userDetail.getUser_lname()));
					System.out.println("Name:-"+userDetail.getUser_fname());
				} else {
				//	mv.addObject("isUserTrue",request.getUserPrincipal().getName());
					session.setAttribute("userId", userDetail.getUser_id());
					session.setAttribute("username", (userDetail.getUser_fname()+" "+userDetail.getUser_lname()));
					System.out.println("Name:-"+userDetail.getUser_fname());
				}
			} else {

			}
			
		} catch (Exception e) {
			
		}
		return mv;
	}
	
	@RequestMapping("home")
	public ModelAndView homePage(HttpSession session){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("brandList",brandSrv.getAllBrands());
		mv.addObject("newProductsList",productSrv.getNewArrivals());
		if(session.getAttribute("cartSize") == null){
			mv.addObject("cartSize",0);
		}else{
			mv.addObject("cartSize",session.getAttribute("cartSize"));
		}
		return mv;
	}
	
	@RequestMapping(value="/showProductList")
	public ModelAndView productlist(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("productList");
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("brandList",brandSrv.getById(id));
		Brand brand = brandSrv.getById(id);
		int foundProducts = 0;
		Set<Category> cats = brand.getCategory();
		Iterator<Category> itr = cats.iterator();
		while(itr.hasNext()){
			Category cat = itr.next();
			 foundProducts += cat.getProduct().size();
			 
		}	
		mv.addObject("foundSize",foundProducts);
		mv.addObject("isUserClickBrand","true");
		mv.addObject("listOfBrands",brandSrv.getAllBrands());
		setLists(mv);
		return mv;
	}
	
	@RequestMapping(value="/productListByFilterID")
	public ModelAndView productListByCatId(@RequestParam(value="id") int id,@RequestParam(value="bid") int bid,@RequestParam(value="index") int index){
		ModelAndView mv = new ModelAndView("productList");
		mv.addObject("isUserClickByFilterID","true");
		mv.addObject("listOfBrands",brandSrv.getAllBrands());
		
		int foundProducts = 0;
		if(index == 0){
			mv.addObject("indexNumber",index);
			mv.addObject("resultList",categorySrv.getById(id));
			Category category = categorySrv.getById(id);
			Set<Product> product = category.getProduct();
			Iterator<Product> itr = product.iterator();
			while(itr.hasNext()){
				Product prd = itr.next();
				foundProducts ++;
			}
		}else if(index == 1){
			mv.addObject("indexNumber",index);
			mv.addObject("resultList",productSizeSrv.getById(id));
			ProductSize category = productSizeSrv.getById(id);
			Set<Product> product = category.getProduct();
			Iterator<Product> itr = product.iterator();
			while(itr.hasNext()){
				Product prd = itr.next();
				foundProducts ++;
			}
		}

		mv.addObject("brandList",brandSrv.getById(bid));
		mv.addObject("foundSize",foundProducts);
		setLists(mv);
		return mv;
	}
	
	@RequestMapping(value="/productDetail")
	public ModelAndView productDetail(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("productDetail");
		mv.addObject("productDetail",productSrv.getById(id));
		mv.addObject("listOfBrands",brandSrv.getAllBrands());
		return mv;
	}
	
	public void setLists(ModelAndView mv){
		mv.addObject("frameColorList",frameColorSrv.getAllFrameColors());
		mv.addObject("frameMaterialList",frameMaterialSrv.getAllFrameMaterials());
		mv.addObject("frameTypeList",frameTypeSrv.getAllFrameTypes());
		mv.addObject("lensColorList",lensColorSrv.getAllLensColors());
		mv.addObject("lensMaterialList",lensMaterialSrv.getAllLensMaterials());
		mv.addObject("productSizeList",productSizeSrv.getAllProductSizes());
		mv.addObject("supplierList",supplierSrv.getAllSuppliers());		
	}
	
}
