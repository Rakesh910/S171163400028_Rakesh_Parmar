package com.niit.sunglasses.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table
public class Brand implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int brand_id;

	@OneToMany(mappedBy = "brand",fetch = FetchType.EAGER)
	private Set<Category> category;
		
	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	@NotBlank(message="Name Should Not be Blank")
	@Length(min=3,max=20,message="Name Must be a 3 to 20 Characters")
	private String brand_name;
	
	private String brand_image;
	
	@Transient
	private MultipartFile brand_file;
	
	private boolean status;

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getBrand_image() {
		return brand_image;
	}

	public void setBrand_image(String brand_image) {
		this.brand_image = brand_image;
	}

	public MultipartFile getBrand_file() {
		return brand_file;
	}

	public void setBrand_file(MultipartFile brand_file) {
		this.brand_file = brand_file;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getFilePath(String path1,String contextPath)
	{
	String fileName=null;
	if(!brand_file.isEmpty())
	{
	try
	{
	fileName=brand_file.getOriginalFilename();
	byte[] bytes = brand_file.getBytes();
	String npath=path1+"\\WEB-INF\\resources\\images\\"+fileName;
	BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(npath)));
	buffStream.write(bytes);
	           buffStream.close();
	           String dbfilename=contextPath+"/resources/images/"+fileName;
	           setBrand_image(dbfilename);
	           System.out.println(dbfilename);
	           return dbfilename;
	}
	catch(Exception e)
	{
	System.out.println(e.getMessage());
	return "fail";
	}
	}
	else
	{
		return "fail";
	}
}


}
