package day6;

class SalaryExpr {
	int bonus;
	
	SalaryExpr() {
//		this(0); // 생략 가능
	}
	
	SalaryExpr(int bonus) {
		this.bonus = bonus;
	}
	
	int getSalary(int grade) {
		if(grade == 1) {
			return bonus + 100;
		} else if(grade == 2) {
			return bonus + 90;
		} else if(grade == 3) {
			return bonus + 80;
		} else {
			return bonus + 70;
		}
	}
}

public class SalaryExam {

	public static void main(String[] args) {
		 int month = day5.MethodLab5.getRandom(1, 12);
		 int grade = day5.MethodLab5.getRandom(1, 4);
		 SalaryExpr salary;
		 
		 if(month % 2 == 0) { // 짝수달
			 salary = new SalaryExpr(100);
		 } else {
			 salary = new SalaryExpr();
		 }
		 
		 System.out.printf("%d월 %d등급의 월급은 %d입니다.", month, grade, salary.getSalary(grade));

	}

}
