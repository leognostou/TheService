package myAPP.controller;

import myAPPclasses.Product;
import myAPPclasses.DBfile;

import java.time.LocalDate;
import java.util.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import myAPP.Exceptions.ForbiddenException;
import myAPP.Exceptions.NotFoundException;

@RestController
public class Controller {
	DBfile db= new DBfile();
	String dbpath="C:\\Users\\lanag\\eclipse-workspace\\myAPP\\simpleDB.txt";

//---------------------POST--------------------------------------------------	
	// address--> "http://localhost:8080/product method-->POST"
	@RequestMapping(value="/product", method= RequestMethod.POST)
	public Product PostProduct(	@RequestParam(value="name") String name,
							@RequestParam(value="price") double price,
							@RequestParam(value="code") String code,
							@RequestParam(value="ExpDate") String ExpDate,
							@RequestParam(value="AvDate") String AvDate ){
		
		if(price<0 || name==null || code==null || AvDate==null || name.length()>100 ||
				LocalDate.parse(ExpDate).isBefore(LocalDate.now()) || LocalDate.parse(AvDate).isBefore(LocalDate.now()) )
			throw new ForbiddenException();
		
		Product NProduct= new Product(name, price, code, LocalDate.parse(ExpDate), LocalDate.parse(AvDate));
		try {
		db.extractFromFile(dbpath);
		db.addProduct(NProduct);
		db.saveToFile(dbpath);
		}
		catch(Exception e)
		{throw new ForbiddenException();}
			
		return NProduct;
	}
//---------------------POST--END---------------------------------------------	
	
//---------------------GET---------------------------------------------------		
	// address--> "http://localhost:8080/products method-->GET"
	@RequestMapping(value="/products", method= RequestMethod.GET)
	public List<Product> GetProductList(){
		try {
			db.extractFromFile(dbpath);
		} catch (Exception e) {
			throw new ForbiddenException();
		}
		
		return db.getDBList();
	}
	
	
	// address--> "http://localhost:8080/product/name method-->GET"
	@RequestMapping(value="/product/name", method= RequestMethod.GET)
	public Product GetProductByName(@RequestParam(value="name") String name){
		try {
			db.extractFromFile(dbpath);
		} catch (Exception e) {
			throw new ForbiddenException();
		}
		
		Product p= db.getByName(name);
		if(p==null)
			throw new NotFoundException();
		return p;
	}
	
	
	// address--> "http://localhost:8080/product/code method-->GET"
	@RequestMapping(value="/product/code", method= RequestMethod.GET)
	public Product GetProductByCode(@RequestParam(value="code") String code){
		try {
			db.extractFromFile(dbpath);
		} catch (Exception e) {
			throw new ForbiddenException();
		}
		
		Product p= db.getByCode(code);
		if(p==null)
			throw new NotFoundException();
		return p;
	}
	
	
	// address--> "http://localhost:8080/product/price/ascending method-->GET"
	@RequestMapping(value="/product/price/ascending", method= RequestMethod.GET)
	public List<Product> GetAscendingOrder(){
		try {
			db.extractFromFile(dbpath);
		} catch (Exception e) {
			throw new ForbiddenException();
		}
		
		List<Product> list= db.AscendingList();
		if(list==null)
			throw new NotFoundException();
		return list;
	}
	
	
	// address--> "http://localhost:8080/product/price/descending method-->GET"
		@RequestMapping(value="/product/price/descending", method= RequestMethod.GET)
		public List<Product> GetDescendingOrder(){
			try {
				db.extractFromFile(dbpath);
			} catch (Exception e) {
				throw new ForbiddenException();
			}
			
			List<Product> list= db.DescendingList();
			if(list==null)
				throw new NotFoundException();
			return list;
		}
//---------------------GET---END---------------------------------------------	

//---------------------DELETE------------------------------------------------	
		
		// address--> "http://localhost:8080/product method-->DELETE"
		@RequestMapping(value="/product", method= RequestMethod.DELETE)
		public List<Product> DeleteProduct(@RequestParam(value="code") String code){
			try {
				db.extractFromFile(dbpath);
				db.DeleteProductByCode(code);
				db.saveToFile(dbpath);
			} catch (Exception e) {
				throw new ForbiddenException();
			}
			
			return db.getDBList();
		}	
		
//---------------------DELETE--END-------------------------------------------
		
		
//---------------------PUT---------------------------------------------------	

		// address--> "http://localhost:8080/product/activate method-->PUT"
		@RequestMapping(value="/product/activate", method= RequestMethod.PUT)
		public Product ActivateProduct(@RequestParam(value="code") String code){
			int index;
			try {
				db.extractFromFile(dbpath);
				index=db.FindIndexByCode(code);
				db.getDBList().get(index).setIsAvailable(true);
				db.saveToFile(dbpath);
			} catch (Exception e) {
				throw new ForbiddenException();
			}
			
			return db.getDBList().get(index);
		}	
		
		
		// address--> "http://localhost:8080/product/deactivate method-->PUT"
		@RequestMapping(value="/product/deactivate", method= RequestMethod.PUT)
		public Product DeactivateProduct(@RequestParam(value="code") String code){
			int index;
			try {
				db.extractFromFile(dbpath);
				index= db.FindIndexByCode(code);
				db.getDBList().get(index).setIsAvailable(false);
				db.saveToFile(dbpath);
			} catch (Exception e) {
				throw new ForbiddenException();
			}
			
			return db.getDBList().get(index);
		}	
		
//---------------------PUT--END-----------------------------------------------		
		@RequestMapping(value="/test", method= RequestMethod.GET)
		public Date DeactivateProduct(){
			return new Date();
		}
}
