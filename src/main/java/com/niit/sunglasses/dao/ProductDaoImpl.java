package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{

	private static final Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean productSaveOrUpdate(Product product) {
		try {
			log.debug("Stating of the method productSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			log.debug("Ending of the method productSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in productSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<Product> getAllProducts() {
		try {
			log.debug("Staring of the method getAllProducts");
		//	String hql = "select distinct b from Product as b left join fetch b.cat ";
			/*String hql = "select b from Product b left join fetch b.cat ";*/
			//String hql = "from Product b left outer join b.cat where b.status=true ";
		//	String hql = "Select product.product_id as product_id,brand.brand_name as brand_name,category.cat_name as cat_name from Product product join product.brand brand join product.category category where product.status = true";
		
			String hql ="Select product.product_id as product_id,product.product_name as product_name,product.product_price as product_price,product.product_discount as product_discount,product.product_UV as product_UV,product.status as status,product.stock as stock,product.product_image as product_image,brand.brand_id as brand_id,brand.brand_name as brand_name,category.cat_id as cat_id,category.cat_name as cat_name,supplier.supplier_id as supplier_id,supplier.supplier_name as supplier_name,frameColor.frameColor_id as frameColor_id,frameColor.frameColor_name as frameColor_name,frameMaterial.frameMaterial_id as frameMaterial_id,frameMaterial.frameMaterial_name as frameMaterial_name,frameType.frameType_id as frameType_id,frameType.frameType_name as frameType_name,lensMaterial.lensMaterial_id as lensMaterial_id,lensMaterial.lensMaterial_name as lensMaterial_name,lensColor.lensColor_id as lensColor_id,lensColor.lensColor_name as lensColor_name,productSize.size_id as size_id,productSize.size_name as size_name from Product product join product.brand brand join product.category category join product.supplier supplier join product.frameColor frameColor join product.frameMaterial frameMaterial join product.frameType frameType join product.lensMaterial lensMaterial join product.lensColor lensColor join product.productSize productSize where product.status = true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			log.debug("Staring of the method getAllProducts");
			@SuppressWarnings("unchecked")
			List<Product> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No Products are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllProducts method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from Product WHERE product_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<Product> productList = query.list();
			if(productList != null && !productList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return productList.get(0);
			}else{
				log.debug("No Record Found in getById with id ="+id);
				return null;
			}
		} catch (HibernateException e) {
			log.error("Error Occures in getById Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean removeProduct(int id) {
		try {
			log.debug("Stating of the method removeProduct");
			Product product = new Product();
			product.setProduct_id(id);
			log.debug("Ready To remove product with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(product);
			sessionFactory.getCurrentSession().createQuery("Update Product Set status = false Where product_id = '"+id+"'").executeUpdate();
			log.debug("Product Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeProduct Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> getNewArrivals() {
		try {
			log.debug("Staring of the method getAllProducts");
			String hql ="Select product.product_id as product_id,product.product_name as product_name,product.product_price as product_price,product.product_discount as product_discount,product.product_UV as product_UV,product.status as status,product.stock as stock,product.product_image as product_image,brand.brand_id as brand_id,brand.brand_name as brand_name,category.cat_id as cat_id,category.cat_name as cat_name,supplier.supplier_id as supplier_id,supplier.supplier_name as supplier_name,frameColor.frameColor_id as frameColor_id,frameColor.frameColor_name as frameColor_name,frameMaterial.frameMaterial_id as frameMaterial_id,frameMaterial.frameMaterial_name as frameMaterial_name,frameType.frameType_id as frameType_id,frameType.frameType_name as frameType_name,lensMaterial.lensMaterial_id as lensMaterial_id,lensMaterial.lensMaterial_name as lensMaterial_name,lensColor.lensColor_id as lensColor_id,lensColor.lensColor_name as lensColor_name,productSize.size_id as size_id,productSize.size_name as size_name from Product product join product.brand brand join product.category category join product.supplier supplier join product.frameColor frameColor join product.frameMaterial frameMaterial join product.frameType frameType join product.lensMaterial lensMaterial join product.lensColor lensColor join product.productSize productSize where product.status = true AND product.newArrival = true order by(product.product_id) desc";
			Query query = sessionFactory.getCurrentSession().createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			log.debug("Staring of the method getAllProducts");
			query.setMaxResults(8);
			@SuppressWarnings("unchecked")
			List<Product> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No Products are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllProducts method ");
			e.printStackTrace();
			return null;
		}
	}

	

}
