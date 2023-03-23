package day7.exercise;

public class StudentTest {
	public static void main(String[] args) {
		 Student arrays[] = new Student[3];
		 arrays[0] = new Student("둘리", 11, 100, 40, "201101", "컴퓨터");
		 arrays[1] = new Student("또치", 12, 90, 30, "201102", "음악");
		 arrays[2] = new Student("도우너", 13, 110, 50, "201103", "체육");
		 
		 for(int i=0; i<arrays.length; i++) {
			 System.out.println(arrays[i].printInformation());
		 }

	}

}
