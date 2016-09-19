package com.niit.sunglasses.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.model.UserRole;
import com.niit.sunglasses.services.UserDetailSrv;
@Component
public class RegistrationHandler {
	
	@Autowired
	private UserDetailSrv userDetailSrv;
	 
	public UserDetail initFlow(){
		return new UserDetail();
	}
	
	public String validateDetails(UserDetail userDetails,MessageContext messageContext){
		String status = "success";
		
		if(userDetails.getUser_fname().isEmpty()){
			/*messageContext.addMessage(new MessageBuilder().error().source(
					"name").defaultText("Name cannot be Empty").build());*/
			status = "failure";
		}
		
		if(userDetails.getUser_lname().isEmpty()){
			status = "failure";
		}
		
		if(userDetails.getUser_address().isEmpty()){
			status = "failure";
		}
	
		if(userDetails.getUser_contact().isEmpty()){
			status = "failure";
		}
		
		if(userDetails.getUser_email().isEmpty()){
			status = "failure";
		}
		
		if(userDetailSrv.getByEmailID(userDetails.getUser_email()) != null){
			messageContext.addMessage(new MessageBuilder().error().source(
					"user_email").defaultText("Sorry..!!User Already Exist with This Email Id.").build());
			status = "failure";
			
		}
		if(userDetails.getUser_password().isEmpty()){
			status = "failure";
		}
		return status;
	}
	
	public String registerDetails(UserDetail userDetails,MessageContext messageContext){
		try {
			userDetails.setStatus(true);
			UserRole userRole = new UserRole() ;
			userRole.setUser_role_id(2);
			userDetails.setUserRole(userRole);
			userDetailSrv.userDetailSaveOrUpdate(userDetails);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			messageContext.addMessage(new MessageBuilder().error().source(
					"registrationFail").defaultText("Registration Failure..Please Try Again..!!").build());
			return "failure";
		}
		
		
	}

}
