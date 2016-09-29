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

import com.niit.sunglasses.model.Category;
import com.niit.sunglasses.model.FrameColor;
import com.niit.sunglasses.model.FrameMaterial;
import com.niit.sunglasses.model.FrameType;
import com.niit.sunglasses.model.LensColor;
import com.niit.sunglasses.model.LensMaterial;
import com.niit.sunglasses.model.Product;
import com.niit.sunglasses.model.ProductSize;
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

@Controller
public class ProductController {

	@Autowired
	private ProductSrv productSrv;
	
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

	@Autowired
	ServletContext srv;
	
	private Product product;
	
	@RequestMapping(value = "/getAllProducts")
	@ResponseBody
	public List<Product> getAllProducts() {
		return productSrv.getAllProducts();
	}
	
	@RequestMapping(value = "AdminPages/saveProduct", method=RequestMethod.POST)
	public ModelAndView getSaveCategory(@Valid @ModelAttribute("productAttribute") Product productObj, BindingResult result, Model model)
	{	ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickProduct","true");
		setLists(mv);
		try
		{
			if(result.hasErrors())
			{	
				mv.addObject("message","Record Can't be Added..!!Please Provide Valid Data..!!");
				return mv;
			}else if(productObj.getFilePath(srv.getRealPath("/"), srv.getContextPath()) == "fail"){
				System.out.println("Image :-"+product.getProduct_image());
				String str = product.getProduct_image();
				if(str != null && !str.isEmpty()){
					productObj.setProduct_image(str);
					productObj.setStatus(true);
					productSrv.productSaveOrUpdate(productObj);
					mv.addObject("message","Product Updated Successfully..!!");
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
					/*mv.addObject("isAdminClickProduct","true");
					setLists(mv);*/
				}else{
					mv.addObject("message","Image Uploading Fail..!!");
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
				}
				return mv;	
			}else{
				productObj.setStatus(true);
				productSrv.productSaveOrUpdate(productObj);
				System.out.println("Cat ID:-"+productObj.getCategory().getCat_id());
				mv.addObject("message","Product Added Successfully..!!");
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
				/*mv.addObject("isAdminClickProduct","true");
				setLists(mv);*/
				/*mv.addObject("categoryList",categorySrv.getCategoryList());*/
				return mv;
			}
		}
		catch(HibernateException ex){
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			mv.addObject("productAttribute",product);
			return mv;
		}
	}
	
	
	@RequestMapping(value="AdminPages/editProduct")
	public ModelAndView editProduct(@RequestParam(value="id") int id){
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickProduct","true");
		setLists(mv);
		try {
			product = productSrv.getById(id);
			mv.addObject("productAttribute",product);
			mv.addObject("categoryList",categorySrv.getCategoryList());
			return mv;
		} catch (HibernateException ex) {
			ex.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
			mv.addObject("productAttribute",product);
			return mv;
		}
	}
	
	@RequestMapping(value="AdminPages/deleteProduct")
	public ModelAndView removeProduct(@RequestParam(value="id") int id) throws Exception{
		ModelAndView mv = new ModelAndView("AdminPages/adminIndex");
		mv.addObject("isAdminClickProduct","true");
		setLists(mv);
		String message;
		try {
			boolean flag = productSrv.removeProduct(id);
			 message = "Product Deleted Successfully....!!!";
			if(flag != true){
				message = "Sorry..Can't delete this Product..!! Try Again";
			}
			mv.addObject("message",message);
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
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message","Exception Occures..!!!");
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
			setLists(mv);
			return mv;
		}
	}
	
	public void setLists(ModelAndView mv){
		mv.addObject("brandList",brandSrv.getAllBrands());
		mv.addObject("frameColorList",frameColorSrv.getAllFrameColors());
		mv.addObject("frameMaterialList",frameMaterialSrv.getAllFrameMaterials());
		mv.addObject("frameTypeList",frameTypeSrv.getAllFrameTypes());
		mv.addObject("lensColorList",lensColorSrv.getAllLensColors());
		mv.addObject("lensMaterialList",lensMaterialSrv.getAllLensMaterials());
		mv.addObject("productSizeList",productSizeSrv.getAllProductSizes());
		mv.addObject("supplierList",supplierSrv.getAllSuppliers());		
	}
	
	@RequestMapping(value="/brandOnchange")
	@ResponseBody
	public List<Category> brandOnChange(@RequestParam("id") int id){
		return categorySrv.getCategoryListByBrandId(id);
	}
	
	
}
