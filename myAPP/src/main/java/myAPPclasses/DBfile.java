package myAPPclasses;

import java.io.*;
import java.time.LocalDate;

import myAPPclasses.Product;
import java.util.*;

import myAPP.Exceptions.NotFoundException;

public class DBfile {
	
	List<Product> dbList;
	
	//getter
	public List<Product> getDBList(){
		return this.dbList;
	}
	
	//as we are not using a DB, we store our products in a file
	//reads file(DB) and places all data in a List of Products
	public void extractFromFile(String path)throws Exception { 
		File file = new File(path); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		List<Product> plist= new ArrayList<Product>();
		String st;
		String temp;
		int i=0;
		Product p= new Product();

		while ((st = br.readLine()) != null) {
			if(i==0 && st.contentEquals("{")) {
				i++;
			}
			else if(i>0 && i<7) {
				temp=st.split(":")[1];
				switch (i) {
				case 1:
					p.setName(temp);
					break;
				case 2:
					p.setPrice(Double.parseDouble(temp));
					break;
				case 3:
					p.setCode(temp);
					break;
				case 4:
					p.setExpDate(LocalDate.parse(temp));
					break;
				case 5:
					p.setAvDate(LocalDate.parse(temp));
					break;
				case 6:
					p.setIsAvailable(Boolean.valueOf(temp));
					break;
				}
			
				i++;
			}
			else if(i>6 || st.contentEquals("}")) {
				i=0;
				plist.add(p);
				p= new Product();
			}
		} 
		
		br.close();
		this.dbList= plist;
	}
	
	//saves the list of products in the file(DB)
	public void saveToFile(String path) throws IOException {
		    FileWriter fileWriter = new FileWriter(path);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    
		    int i;
		    for(i=0;i<this.dbList.size();i++)
		    	printWriter.print(this.dbList.get(i).toString());
		    
		    printWriter.close();
	}
	
	// finds product by ProductNAME and returns it, if not returns null
	public Product getByName(String name) {
		Product p;
		int i=0;
	    while(i<this.dbList.size()) {
	    	p=this.dbList.get(i);
	    	if(p.getName().contentEquals(name))
	    		return p;
	    	i++;
	    }
		return null;
	}
	
	// finds product by ProductCODE and returns it, if not returns null
	public Product getByCode(String code) {
		Product p;
		int i=0;
	    while(i<this.dbList.size()) {
	    	p=this.dbList.get(i);
	    	if(p.getCode().contentEquals(code))
	    		return p;
	    	i++;
	    }
		return null;
	}
	
	//adds a product in the list
	public void addProduct(Product np) {
		this.dbList.add(np);
	}
	
	//true if product exists, false if not
	public boolean exists(Product np) {
		Product p;
		int i=0;
	    while(i<this.dbList.size()) {
	    	p=this.dbList.get(i);
	    	if(p.getCode().contentEquals(np.getCode()) || p.getName().contentEquals(np.getName()))
	    		return true;
	    	i++;
	    }
		return false;
	}
	
	//creates an ascending sorted list of products based on the price
	public List<Product> AscendingList(){
		List<Product> APlist= this.dbList;
		Collections.sort(APlist, new ProductAscendingSort());
		
		return APlist;
	}

	//creates a descending sotred list of products based on the price
	public List<Product> DescendingList(){
		List<Product> APlist= this.dbList;
		Collections.sort(APlist, new ProductDescendingSort());
		
		return APlist;
	}

	// find a product by code and deletes it
	public void DeleteProductByCode(String code) {
		int index=FindIndexByCode(code);
		
		if(index<0)
			throw new NotFoundException();
		
		this.dbList.remove(index);
	}
	
/*	
	public Product ActivateProductByCode(String code) {
		int index=FindIndexByCode(code);
		
		if(index<0)
			throw new NotFoundException();
		
		this.dbList.get(index).setIsAvailable(true);
		return this.dbList.get(index);
	}

	
	public Product DeactivateProductByCode(String code) {
		int index=FindIndexByCode(code);
		
		if(index<0)
			throw new NotFoundException();
		
		this.dbList.get(index).setIsAvailable(false);
		return this.dbList.get(index);
	}
*/
	//returns the index of a product, returns -1 if product does not exist
	public int FindIndexByCode(String code) {
		int i;
		for(i=0;i<this.dbList.size();i++) {
			if(this.dbList.get(i).getCode().contentEquals(code))
				return i;
		}
		return -1;
	}
	
}
