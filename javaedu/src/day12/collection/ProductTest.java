package day12.collection;

import java.util.HashSet;

public class ProductTest {
	public static void main(String[] args) {
		Product[] p = new Product[4];

		p[0] = new Product("p100", "TV", "20000");
		p[1] = new Product("p200", "Computer", "10000");
		p[2] = new Product("p100", "MP3", "700");
		p[3] = new Product("p300", "Audio", "1000");

		HashSet<Product> hs = new HashSet<>(4);

		for (int i = 0; i < p.length; i++) {
			if (hs.add(p[i])) {
				System.out.println("성공적으로 저장되었습니다.");
			} else {
				System.out.println("동일한 ID의 제품이 이미 저장되어 있습니다.");
			}
		}

		System.out.println(hs.size());

		for (Product pp : hs) {
			System.out.println(pp);
		}

		System.out.printf("%-10s\t %-10s\t %-10s\n", "제품ID", "제품명", "가격");
		System.out.println("---------------------------------------");
		for (Product pp : hs) {
			System.out.printf("%-10s\t %-10s\t %-10s\n", pp.getProductID(), pp.getProductName(), pp.getProductPrice());
		}

	}

}
