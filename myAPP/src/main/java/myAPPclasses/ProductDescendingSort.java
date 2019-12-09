package myAPPclasses;

import java.util.Comparator;

public class ProductDescendingSort implements Comparator<Product>{
	//sorting in descending order based to the price of the product 
    public int compare(Product a, Product b) 
    {
    	int A=(int)(a.getPrice()*100);
    	int B=(int)(b.getPrice()*100);
        return B - A; 
    } 
}