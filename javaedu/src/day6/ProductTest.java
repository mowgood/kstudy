package day6;

class Product {
	String name;
	int balance;
	int price;
	
	Product() {
		this("듀크인형", 5, 10000);
	}
	
	Product(String name, int balance, int price) {
		this.name = name;
		this.balance = balance;
		this.price = price;
	}
	
	String getName() {
		return name;
	}
	
	int getBalance() {
		return balance;
	}
	
	int getPrice() {
		return price;
	}
}
public class ProductTest {

	public static void main(String[] args) {
		Product[] productArr = new Product[5];
		productArr[0] = new Product();
		productArr[1] = new Product("상품1", 10, 23000);
		productArr[2] = new Product("상품2", 20, 50000);
		productArr[3] = new Product("상품3", 30, 28000);
		productArr[4] = new Product("상품4", 40, 22000);
		
		for(Product p : productArr) {
			System.out.printf("%s %d %,d원\n", p.name, p.balance, p.price);
		}
		

	}

}
