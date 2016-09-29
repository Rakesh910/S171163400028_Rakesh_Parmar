package com.niit.sunglasses.controller;

import java.util.Date;
import java.util.HashSet;
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

import com.niit.sunglasses.model.CartDetail;
import com.niit.sunglasses.model.OrderDetail;
import com.niit.sunglasses.model.Product;
import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.services.BrandSrv;
import com.niit.sunglasses.services.ProductSrv;
import com.niit.sunglasses.services.UserDetailSrv;

@Controller
public class OrderDetailController {
	
	@Autowired
	private BrandSrv brandSrv;
	
	@Autowired
	private ProductSrv productSrv;

	@Autowired
	private UserDetailSrv userDetailSrv;
	
	private float grandTotal = 0;
	
	private Set<CartDetail> cartDetail = new HashSet<CartDetail>();
	
	@RequestMapping(value="addToCart")
	public ModelAndView addCart(@RequestParam(value="id") int id,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("productDetail");
		mv.addObject("productDetail",productSrv.getById(id));
		mv.addObject("listOfBrands",brandSrv.getAllBrands());
		if(session.getAttribute("userId") != null){
			OrderDetail order = new OrderDetail();
			Product product = productSrv.getById(id);
			CartDetail cart = new CartDetail();
			cart.setProduct(product);
			cart.setQuantity(1);
			cart.setTotal(Math.round((cart.getQuantity()*((product.getProduct_price()-(((product.getProduct_price())*(product.getProduct_discount()))/100))))));
			System.out.println("Total:-"+cart.getTotal());
			grandTotal = (float) (grandTotal + cart.getTotal());
			System.out.println("GrandTotal:-"+grandTotal);
			System.out.println("Product Add:-"+product.getProduct_id());
			cartDetail.add(cart);
			order.setCartDetail(cartDetail);
			order.setGrandTotal(grandTotal);
			order.setOrderDate(new Date());
			order.setPayment_status(true);
			UserDetail user = userDetailSrv.getById((int) session.getAttribute("userId"));
			order.setUser_detail(user);
			session.setAttribute("cartSize", order.getCartDetail().size());
			session.setAttribute("order", order);
		}else{
			mv.addObject("message","Sorry..You Must be Login First..!!");
		}
		return mv;
	}
	
	@RequestMapping(value="UserPages/viewMyCart")
	public ModelAndView viewCartDetail(HttpSession session){
		ModelAndView mv = new ModelAndView("UserPages/viewCart");
		OrderDetail order = (OrderDetail) session.getAttribute("order");
		mv.addObject("order",order);
		try {
			mv.addObject("cartList",order.getCartDetail().size());
		} catch (Exception e) {
			mv.addObject("cartList",0);
		}
		
		Iterator<CartDetail> crt = cartDetail.iterator();
		while (crt.hasNext()) {
			CartDetail c = crt.next();
			System.out.println("Product Id:-"+c.getProduct().getProduct_id());
		}

		return mv;
	}
	
	
	@RequestMapping(value="UserPages/updateCart",method=RequestMethod.POST)
	public ModelAndView addCart(HttpServletRequest request,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("UserPages/viewCart");
		
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		float updateGrandTotal = 0;
		Set<CartDetail> updatedCartDetail = new HashSet<CartDetail>();
		OrderDetail upOrder = 	(OrderDetail) session.getAttribute("order");
		Set<CartDetail> upcartDetail = upOrder.getCartDetail();
		Iterator<CartDetail> itr  = upcartDetail.iterator();
		while(itr.hasNext()){
			CartDetail cart = itr.next();
			if(cart.getProduct().getProduct_id() == product_id){
				cart.setQuantity(quantity);
				cart.setTotal((cart.getQuantity()*((cart.getProduct().getProduct_price()-(((cart.getProduct().getProduct_price())*(cart.getProduct().getProduct_discount()))/100)))));
				updateGrandTotal = (float) (updateGrandTotal + cart.getTotal());
				updatedCartDetail.add(cart);
			}else{
				cart.setTotal((cart.getQuantity()*((cart.getProduct().getProduct_price()-(((cart.getProduct().getProduct_price())*(cart.getProduct().getProduct_discount()))/100)))));
				updateGrandTotal = (float) (updateGrandTotal + cart.getTotal());
				updatedCartDetail.add(cart);
			}
		}
		upOrder.setCartDetail(updatedCartDetail);
		upOrder.setGrandTotal(updateGrandTotal);
		try {
			mv.addObject("cartList",upOrder.getCartDetail().size());
		} catch (Exception e) {
			mv.addObject("cartList",0);
		}
		mv.addObject("order",upOrder);
		session.setAttribute("cartSize", upOrder.getCartDetail().size());
		session.setAttribute("order", upOrder);
		return mv;
	}
	
	@RequestMapping(value="UserPages/deleteCart")
	public ModelAndView removeCart(@RequestParam(value="id") int product_id,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("UserPages/viewCart");
		float updateGrandTotal = 0;
		OrderDetail delOrder = 	(OrderDetail) session.getAttribute("order");
		Set<CartDetail> delcartDetail = delOrder.getCartDetail();
		Iterator<CartDetail> itr  = delcartDetail.iterator();
		while(itr.hasNext()){
			CartDetail cart = itr.next();
			if(cart.getProduct().getProduct_id() == product_id){
				itr.remove();
				System.out.println("Remove Id:-"+cart.getProduct().getProduct_id());
				grandTotal -= cart.getTotal();
			}else{
				cart.setTotal((cart.getQuantity()*((cart.getProduct().getProduct_price()-(((cart.getProduct().getProduct_price())*(cart.getProduct().getProduct_discount()))/100)))));
				updateGrandTotal = (float) (updateGrandTotal + cart.getTotal());
			}	
		}
		delOrder.setCartDetail(delcartDetail);
		delOrder.setGrandTotal(updateGrandTotal);	
		try {
			mv.addObject("cartList",delOrder.getCartDetail().size());
		} catch (Exception e) {
			mv.addObject("cartList",0);
		}	
		cartDetail = delOrder.getCartDetail();
		grandTotal = (float) delOrder.getGrandTotal();
		session.setAttribute("cartSize", delOrder.getCartDetail().size());
		session.setAttribute("order", delOrder);
		return mv;
	}
	
}
