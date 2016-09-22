package com.niit.sunglasses.handler;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.niit.sunglasses.model.CardDetail;
import com.niit.sunglasses.model.CartDetail;
import com.niit.sunglasses.model.OrderDetail;
import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.services.CardDetailSrv;
import com.niit.sunglasses.services.CartDetailSrv;
import com.niit.sunglasses.services.OrderDetailSrv;
import com.niit.sunglasses.services.UserDetailSrv;

@Component
public class CheckOutHandler {
	
	@Autowired
	private UserDetailSrv userDetailSrv;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private CardDetailSrv cardDetailSrv;
	
	@Autowired
	private OrderDetailSrv orderDetailSrv;
	
	@Autowired
	private CartDetailSrv cartDetailSrv;
	
	
	public OrderDetail initFlow(){
		UserDetail user = userDetailSrv.getById((int)(session.getAttribute("userId")));
		OrderDetail orderDetail = (OrderDetail) session.getAttribute("order");
		orderDetail.setShipping_address(user.getUser_address());
		orderDetail.setBilling_address(user.getUser_address());
		CardDetail cardDetail = new CardDetail();
		orderDetail.setCardDetail(cardDetail);
		return orderDetail;
	}
	
	public String validateDetails(OrderDetail orderDetails,MessageContext messageContext){
		String status = "success";
		
		if(orderDetails.getShipping_address().isEmpty()){
			status = "failure";
		}
		
		if(orderDetails.getBilling_address().isEmpty()){
			status = "failure";
		}
		return status;	
	}
	
	public String validateCardDetail(OrderDetail orderDetails,MessageContext messageContext){
		String status = "success";
		
		if(orderDetails.getCardDetail().getCardType() == "" || orderDetails.getCardDetail().getCardType() == null ){
			messageContext.addMessage(new MessageBuilder().error().source(
					"cardType").defaultText("Please Select CardType").build());
			status = "failure";
		}
		
		if(orderDetails.getCardDetail().getCardNumber().isEmpty()){
			messageContext.addMessage(new MessageBuilder().error().source(
					"cardNumber").defaultText("Card Number can not be blank").build());
			status = "failure";
		}
		
		if(orderDetails.getCardDetail().getCvNumber().isEmpty()){
			messageContext.addMessage(new MessageBuilder().error().source(
					"cvvNumber").defaultText("CVV number can not be blank").build());
			status = "failure";
		}
		
		if(orderDetails.getCardDetail().getExpiryMonth() == "" || orderDetails.getCardDetail().getExpiryMonth() == null ){
			messageContext.addMessage(new MessageBuilder().error().source(
					"expiryMonth").defaultText("Please Select Expiry Month").build());
			status = "failure";
		}
		
		if(orderDetails.getCardDetail().getExpiryYear() == "" || orderDetails.getCardDetail().getExpiryYear() == null){
			messageContext.addMessage(new MessageBuilder().error().source(
					"expiryYear").defaultText("Please Select Expiry Year").build());
			status = "failure";
		}
		
		if(orderDetails.getCardDetail().getNameOnCard().isEmpty()){
			messageContext.addMessage(new MessageBuilder().error().source(
					"NmOnCard").defaultText("Card Holder Name can not be blank").build());
			status = "failure";
		}
		return status;
	}
	
	
	public String saveCardDetails(OrderDetail orderDetails,MessageContext messageContext){
		try {
			CardDetail cardDetail = orderDetails.getCardDetail();
			cardDetail.setTotalCost(orderDetails.getGrandTotal());
			cardDetailSrv.cardDetailSaveOrUpdate(cardDetail);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			messageContext.addMessage(new MessageBuilder().error().source(
					"paymentFail").defaultText("Payment can not be done..Please Try Again..!!").build());
			return "failure";
		}
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
	public String saveOrderDetails(OrderDetail orderDetails,MessageContext messageContext){
		try {			
			orderDetailSrv.orderDetailSave(orderDetails);
			Set<CartDetail> cartDetail = orderDetails.getCartDetail();
			Iterator<CartDetail> itr = cartDetail.iterator();
			while(itr.hasNext()){
				CartDetail cart = itr.next();
				cart.setOrderDetail(orderDetails);
				cartDetailSrv.cartDetailSave(cart);
				cartDetail.remove(cart);
			}	
			orderDetails.setCartDetail(cartDetail);
			orderDetails.setCardDetail(new CardDetail());
			session.setAttribute("cartSize", orderDetails.getCartDetail().size());
			return "success"; 	
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			return "failure";

		}
	}
}
