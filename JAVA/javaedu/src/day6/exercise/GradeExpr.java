package day6.exercise;

public class GradeExpr {
	private int[] jumsu;
	
	GradeExpr(int[] jumsu) {
		this.jumsu = jumsu;
	}
	
	double getAverage() {
		return getTotal()/(double)jumsu.length;
	}
	
	int getTotal() {
		int total = 0;
		for(int j : jumsu) {
			total += j;
		}
		return total;
	}
	
	int getGoodScore() {
		int max = jumsu[0];
		for(int i=1; i<jumsu.length; i++) {
			if(max < jumsu[i]) 
				max = jumsu[i];
		}
		return max;		
	}
	
	int getBadScore() {
		int min = jumsu[0];
		for(int i=1; i<jumsu.length; i++) {
			if(min > jumsu[i])
				min = jumsu[i];
		}
		return min;
		
	}
}
