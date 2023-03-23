package day10.exercise;

import day10.DuplicateException;

public class LottoGame {

	public static void main(String[] args) {
		 LottoMachine lotto = new LottoMachine();
		 lotto.createLottoNums();
		 
		 try {
			 lotto.checkLottoNums();
		 } catch (DuplicateException e) {
				System.out.println(e.getMessage());
				return;
		 }	
		 day5.exercise.MethodLab7.printArray(lotto.getNums());
	}

}
