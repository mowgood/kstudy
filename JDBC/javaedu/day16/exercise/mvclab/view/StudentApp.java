package day16.exercise.mvclab.view;

import java.util.Scanner;

import day16.exercise.mvclab.controller.StudentController;

public class StudentApp {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StudentController controller = new StudentController();
		String name = "";
		int score = 0;

		loop: while (true) {
			System.out.println("처리하려는 기능을 선택하세요.");
			System.out.println("1. 학생 정보 출력");
			System.out.println("2. 학생 정보 입력");
			System.out.println("3. 학생 정보 삭제");
			System.out.println("4. 학생 정보 수정");
			System.out.println("5. 학생 점수 확인");
			System.out.println("6. 종료");
			System.out.println("입력 : ");

			switch (Integer.parseInt(scan.nextLine())) {
			case 1:
				controller.printAll();
				break;
			case 2:
				System.out.println("추가할 학생 이름을 입력하세요 : ");
				name = scan.nextLine();
				System.out.println("추가할 학생 점수를 입력하세요 : ");
				score = Integer.parseInt(scan.nextLine());
				controller.insert(name, score);
				break;
			case 3:
				System.out.println("삭제하려는 학생 이름 입력 : ");
				name = scan.nextLine();
				controller.delete(name);
				break;
			case 4:
				System.out.println("수정하려는 학생 이름 입력 : ");
				name = scan.nextLine();
				System.out.println("수정하려는 학생 점수 입력 : ");
				score = Integer.parseInt(scan.nextLine());
				controller.update(name, score);
				break;
			case 5:
				System.out.println("점수 확인하려는 학생 이름 입력 : ");
				name = scan.nextLine();
				controller.printScore(name);
				break;
			case 6:
				break loop;

			}

		}

		scan.close();

	}

}
