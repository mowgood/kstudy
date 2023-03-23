package day6.exercise;

public class SalaryExpr {
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
