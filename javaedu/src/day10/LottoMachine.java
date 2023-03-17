package day10;

import java.util.Random;


public class LottoMachine {
	private int[] nums;
	
	public LottoMachine() {
		nums = new int[6];
	}
	
	public void createLottoNums( ) {
		Random r = new Random();

		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(20)+1;
		}
		
	}
	public void checkLottoNums() throws DuplicateException {
//		boolean isLotto = true;
		
		for(int i=0; i<nums.length-1; i++) {
			for(int j=i+1; j<nums.length; j++) {
				if(nums[i] == nums[j]) {
//					isLotto = false;
					throw new DuplicateException();
				}
			}
		}
		
//		if(!isLotto) {
//			throw new DuplicateException();
//		}
		
	}
	public int[] getNums() {
		return nums;
	}
	
	
}
