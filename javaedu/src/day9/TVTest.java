package day9;

public class TVTest {
	public static void main(String[] args) {
		 SaleTV saleTv = new SaleTV(300000, "SALETV-1", 40, 1);
		 RentalTV rentalTv = new RentalTV(100000, "RENTALTV-10", 42, 1);
		 
		 saleTv.channeUp();
		 saleTv.channeUp();
		 
		 rentalTv.channelDown();
		 rentalTv.channelDown();
		 rentalTv.channelDown();
		 
		 printAllTV(saleTv);
		 printAllTV(rentalTv);
		 
		 printRentalTV(rentalTv);

	}
	static void printAllTV(TV tv) {
		System.out.print(tv.toString());
		if(tv instanceof SaleTV) {
			((SaleTV)tv).play();
			((SaleTV) tv).sale();
		} else {
			((RentalTV)tv).play();
		}
		
	}
	static void printRentalTV(Rentable tv) {
		((Rentable) tv).rent();
	}

}
