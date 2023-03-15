package mobile;

public class MobileTest {
	public static void main(String[] args) {
		
		Mobile Lt = new Ltab("Ltab", 500, "ABC-01");
		Mobile Ot = new Otab("Otab", 1000, "XYZ-20");
		
		printTitle();
		printMobile(Lt);
		printMobile(Ot);
		
		Lt.charge(10);
		Ot.charge(10);
		
		System.out.println("[ 10분 충전 ]");
		printTitle();
		printMobile(Lt);
		printMobile(Ot);
		
		Lt.operate(5);
		Ot.operate(5);
		
		System.out.println("[ 5분 통화 ]");
		printTitle();
		printMobile(Lt);
		printMobile(Ot);
		
	}
	public static void printMobile(Mobile mobile) {
		System.out.println(mobile.getMobileName() + "\t" + mobile.getBatterySize() + "\t" + mobile.getOsType());
	}
	public static void printTitle() {
		System.out.println("Mobile\t Battery\t Os");
		System.out.println("-----------------------------");
	}

}
