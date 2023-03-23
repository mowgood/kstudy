package day9.exercise.emp;

public class Company {
	public static void main(String[] args) {
		 Employee[] emp = new Employee[2];
		 emp[0] = new Secretary("Duke", 1, "secretary", 800);
		 emp[1] = new Sales("Tuxi", 2, "sales", 1200);
		 
		 printEmployee(emp, false);
		 
		 for(int i=0; i<emp.length; i++) {
			 ((Bonus)emp[i]).incentive(100);
		 }
		 
		 
		 System.out.println("[인센티브 100 지급]");
		 printEmployee(emp, true);
		 

	}
	public static void printEmployee(Employee[] emp, boolean isTax) {		
		if(isTax) {
			System.out.println("name\t department\t salary\t tax\t extra pay");			
			System.out.println("-----------------------------------------------");
			for(int i=0; i<emp.length; i++) {
				if(emp[i].getDepartment().equals("sales")) {
					System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t\t" + emp[i].getSalary() + "\t" + emp[i].tax() + "\t" + ((Sales)emp[i]).getExtraPay());
				} else {
					System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t" + emp[i].getSalary() + "\t  " + emp[i].tax());
				}
			}
		} else {
			System.out.println("name\t department\t salary\t extra pay");
			System.out.println("-----------------------------------------------");
			for(int i=0; i<emp.length; i++) {
				if(emp[i].getDepartment().equals("sales")) {
					System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t\t" + emp[i].getSalary() + "\t "  + ((Sales)emp[i]).getExtraPay());
				} else {
					System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t  " + emp[i].getSalary() + "\t     " + " " );
				}
			}
					
		}
		System.out.println();
		
	}

}
