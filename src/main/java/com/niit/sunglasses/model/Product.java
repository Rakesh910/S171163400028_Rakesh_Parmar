package com.niit.sunglasses.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int product_id;
	
	@NotBlank(message="Name Should Not be Blank")
	@Length(min=3,max=100,message="Name Must be a 3 to 20 Characters")
	private String product_name;
	
	@Min(value=0,message="Cannot give a price less than zero")
	private float product_price;
	
	private int product_discount;
	
	private int product_UV;
	
	
	private boolean status;
	
	@Min(value=1,message="Cannot give a stock less than zero")
	@Max(value=1000,message="Cannot give a stock More than 1000 Quantity")
	private int stock;
	
	private boolean newArrival;
	
	private String product_image;
	
	@Transient
	private MultipartFile product_file;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="brand_id",unique = false, nullable = false)
	@JsonIgnore // @JsonIgnore ignore this field at the time of JSON file creation. Simply ignore it.
	private Brand brand;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="cat_id",unique = false, nullable = false)
	@JsonIgnore 
	private Category category;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="frameColor_id",unique = false, nullable = false)
	@JsonIgnore 
	private FrameColor frameColor;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="frameMaterial_id",unique = false, nullable = false)
	@JsonIgnore 
	private FrameMaterial frameMaterial;
	
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="frameType_id",unique = false, nullable = false)
	@JsonIgnore 
	private FrameType frameType;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="lensMaterial_id",unique = false, nullable = false)
	@JsonIgnore 
	private LensMaterial lensMaterial;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="lensColor_id",unique = false, nullable = false)
	@JsonIgnore 
	private LensColor lensColor;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="size_id",unique = false, nullable = false)
	@JsonIgnore 
	private ProductSize productSize;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="supplier_id",unique = false, nullable = false)
	@JsonIgnore 
	private Supplier supplier;

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public float getProduct_price() {
		return product_price;
	}

	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}

	public int getProduct_discount() {
		return product_discount;
	}

	public void setProduct_discount(int product_discount) {
		this.product_discount = product_discount;
	}

	public int getProduct_UV() {
		return product_UV;
	}

	public void setProduct_UV(int product_UV) {
		this.product_UV = product_UV;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isNewArrival() {
		return newArrival;
	}

	public void setNewArrival(boolean newArrival) {
		this.newArrival = newArrival;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public MultipartFile getProduct_file() {
		return product_file;
	}

	public void setProduct_file(MultipartFile product_file) {
		this.product_file = product_file;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public FrameColor getFrameColor() {
		return frameColor;
	}

	public void setFrameColor(FrameColor frameColor) {
		this.frameColor = frameColor;
	}

	public FrameMaterial getFrameMaterial() {
		return frameMaterial;
	}

	public void setFrameMaterial(FrameMaterial frameMaterial) {
		this.frameMaterial = frameMaterial;
	}

	public FrameType getFrameType() {
		return frameType;
	}

	public void setFrameType(FrameType frameType) {
		this.frameType = frameType;
	}

	public LensMaterial getLensMaterial() {
		return lensMaterial;
	}

	public void setLensMaterial(LensMaterial lensMaterial) {
		this.lensMaterial = lensMaterial;
	}

	public LensColor getLensColor() {
		return lensColor;
	}

	public void setLensColor(LensColor lensColor) {
		this.lensColor = lensColor;
	}

	public ProductSize getProductSize() {
		return productSize;
	}

	public void setProductSize(ProductSize productSize) {
		this.productSize = productSize;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFilePath(String path1,String contextPath)
	{
	String fileName=null;
	if(!product_file.isEmpty())
	{
	try
	{
	fileName=product_file.getOriginalFilename();
	byte[] bytes = product_file.getBytes();
	String npath=path1+"\\WEB-INF\\resources\\images\\"+fileName;
	BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(npath)));
	buffStream.write(bytes);
	           buffStream.close();
	           String dbfilename=contextPath+"/resources/images/"+fileName;
	           setProduct_image(dbfilename);
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
