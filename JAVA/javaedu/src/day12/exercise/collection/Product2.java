package day12.exercise.collection;

import java.util.Objects;

public class Product2 implements Comparable<Product2> {
	private String productID;
	private String productName;
	private String productPrice;
	
	public Product2(String productID, String productName, String productPrice) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	@Override
	public int compareTo(Product2 p) {
		if(Integer.parseInt(productPrice) < Integer.parseInt(p.getProductPrice())) 
			return -1;
		else if(Integer.parseInt(productPrice) == Integer.parseInt(p.getProductPrice()))
			return 0;
		else return 1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o != null && o instanceof Product2) {
			Product2 p = (Product2) o;
			if (productID == p.productID )
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%s\t %s\t %,d원\n", productID, productName, Integer.parseInt(productPrice)) ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productID);
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

}
