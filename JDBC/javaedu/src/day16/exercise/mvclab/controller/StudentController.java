package day16.exercise.mvclab.controller;

import java.util.*;

import day16.exercise.mvclab.model.StudentDAO;
import day16.exercise.mvclab.model.StudentDTO;

public class StudentController {

	StudentDAO dao = new StudentDAO();

	List<StudentDTO> slist = null;

	public void printAll() {
		slist = dao.getAllStudent();
		for (StudentDTO dto : slist) {
			System.out.println(dto);
		}
		System.out.println();
	}

	public void printScore(String name) {
		StudentDTO dto = new StudentDTO();
		dto.setName(name);
		if (dao.getScore(dto) != -1) {
			System.out.println(name + " 학생의 점수는 " + dao.getScore(dto) + " 입니다\n");
		} else {
			System.out.println(name + " 학생은 존재하지 않습니다.\n");
		}

	}

	public void insert(String name, int score) {
		StudentDTO dto = new StudentDTO();
		dto.setName(name);
		dto.setScore(score);
		if (dao.insertStudent(dto)) {
			System.out.println("입력 성공\n");
		} else {
			System.out.println("입력 실패\n");
		}

	}

	public void update(String name, int score) {
		StudentDTO dto = new StudentDTO();
		dto.setName(name);
		dto.setScore(score);
		if (dao.updateStudent(dto)) {
			System.out.println(name + " 학생의 점수를 변경했습니다.\n");
		} else {
			System.out.println(name + " 학생은 존재하지 않습니다.\n");
		}
	}

	public void delete(String name) {
		StudentDTO dto = new StudentDTO();
		dto.setName(name);
		if (dao.deleteStudent(dto)) {
			System.out.println(name + " 학생의 데이터를 삭제했습니다.\n");
		} else {
			System.out.println(name + " 학생은 존재하지 않습니다.\n");
		}
	}

}
