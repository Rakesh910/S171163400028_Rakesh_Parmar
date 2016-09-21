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
import com.niit.sunglasses.services.ProductSrv;
import com.niit.sunglasses.services.UserDetailSrv;

@Controller
public class OrderDetailController {
	
	private Set<CartDetail> cartDetail = new HashSet<CartDetail>();
	
	private OrderDetail order = new OrderDetail();
	
	private float grandTotal = 0;
	
	
	@Autowired
	private ProductSrv productSrv;
	
	@Autowired
	private UserDetailSrv userDetailSrv;
	
	@RequestMapping(value="/addToCart")
	public ModelAndView addCart(@RequestParam(value="id") int id,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("index");
		
		Product product = productSrv.getById(id);
		CartDetail cart = new CartDetail();
		cart.setProduct(product);
		cart.setQuantity(1);
		cart.setTotal((cart.getQuantity()*((product.getProduct_price()-(((product.getProduct_price())*(product.getProduct_discount()))/100)))));
		grandTotal = (float) (grandTotal + cart.getTotal());
		cartDetail.add(cart);
		order.setCartDetail(cartDetail);
		order.setGrandTotal(grandTotal);
		session.setAttribute("cartSize", order.getCartDetail().size());
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("isUserClickHome","true");
		
		order.setOrderDate(new Date());
		order.setPayment_status(true);
		UserDetail user = userDetailSrv.getById((int) session.getAttribute("userId"));
		order.setUser_detail(user);
		session.setAttribute("order", order);
		return mv;
	}
	
	@RequestMapping(value="/viewMyCart")
	public ModelAndView viewCartDetail(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("order",order);
		mv.addObject("isUserClickViewCart","true");
		mv.addObject("userLoginAttribute",new UserDetail());
		return mv;
	}
	
	@RequestMapping(value="/updateCart",method=RequestMethod.POST)
	public ModelAndView addCart(HttpServletRequest request,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("index");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		float updateGrandTotal = 0;
		
		Set<CartDetail> updatedCartDetail = new HashSet<CartDetail>();
		Set<CartDetail> cartDetail = order.getCartDetail();
		Iterator<CartDetail> itr  = cartDetail.iterator();
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
			order.setCartDetail(updatedCartDetail);
		}
		mv.addObject("order",order);
		order.setGrandTotal(updateGrandTotal);
		session.setAttribute("cartSize", order.getCartDetail().size());
		session.setAttribute("order", order);
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("isUserClickViewCart","true");
		return mv;
	}
	
	@RequestMapping(value="/deleteCart")
	public ModelAndView removeCart(@RequestParam(value="id") int product_id,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("index");
		float updateGrandTotal = 0;
		Set<CartDetail> cartDetail = order.getCartDetail();
		Iterator<CartDetail> itr  = cartDetail.iterator();
		while(itr.hasNext()){
			CartDetail cart = itr.next();
			if(cart.getProduct().getProduct_id() == product_id){
				itr.remove();
				grandTotal -= cart.getTotal();
			}else{
				cart.setTotal((cart.getQuantity()*((cart.getProduct().getProduct_price()-(((cart.getProduct().getProduct_price())*(cart.getProduct().getProduct_discount()))/100)))));
				updateGrandTotal = (float) (updateGrandTotal + cart.getTotal());
			}
			
		}
		order.setCartDetail(cartDetail);
		mv.addObject("order",order);
		order.setGrandTotal(updateGrandTotal);
		session.setAttribute("cartSize", order.getCartDetail().size());
		session.setAttribute("order", order);
		mv.addObject("userLoginAttribute",new UserDetail());
		mv.addObject("isUserClickViewCart","true");
		return mv;
	}
	
	/*@RequestMapping(value="/checkOut")
	public ModelAndView checkOut(){
		
		try {
			System.out.println("Start");
			orderDetailSrv.orderDetailSave(order);
			Set<CartDetail> cartDetail = order.getCartDetail();
			Iterator<CartDetail> itr  = cartDetail.iterator();
			while(itr.hasNext()){
				CartDetail cart = itr.next();
				cart.setOrderDetail(order);
				cartDetailSrv.cartDetailSave(cart);
			}	
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
		return null;
		
	}*/
}
