package work;

public class PlaneTest {
	public static void main(String[] args) {	 
		 Plane[] planeArr = new Plane[2];
		 planeArr[0] = new Airplane("L747", 1000);
		 planeArr[1] = new Cargoplane("C40", 1000);
		 
		 printInfo(planeArr);
		 
		 planeArr[0].flight(100);
		 planeArr[1].flight(100);
		 
		 System.out.println("[100 운항]");
		 printInfo(planeArr);
		 
		 planeArr[0].refuel(200);
		 planeArr[1].refuel(200);
		 
		 System.out.println("[200 주유]");
		 printInfo(planeArr);

	}
	public static void printInfo(Plane[] list) {
		System.out.println("Plane\t fuelSize");
		System.out.println("-------------------");
		System.out.println(list[0].getPlaneName() + "\t" + list[0].getFuelSize());
		System.out.println(list[1].getPlaneName() + "\t" + list[1].getFuelSize());
		System.out.println();
	}

}
