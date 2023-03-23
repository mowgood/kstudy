package day12;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StandardInput2 {
	public static void main(String[] args) throws Exception {
		System.out.println("입력 : ");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String str = br.readLine();
		System.out.println("입력된 행 : "+ str);
	}
}
