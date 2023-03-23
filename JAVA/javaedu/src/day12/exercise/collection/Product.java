package day12.exercise.collection;

import java.util.Objects;


public class Product {
	private String productID;
	private String productName;
	private String productPrice;
	
	public Product(String productID, String productName, String productPrice) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o != null && o instanceof Product) {
			Product p = (Product) o;
			if (productID == p.productID )
				return true;
		}
		return false;
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
