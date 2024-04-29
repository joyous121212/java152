package com.itwill.class05;

public class ClassMain05 {

	public static void main(String[] args) {
		// TODO: Subject, Student 클래스 객체 생성과 메서드 호출 결과 테스트.
		Subject subject = new Subject(100, 90, 85, 70);
//		System.out.println("korean = " + subject.korean);
//		System.out.println("english = " + subject.english);
//		System.out.println("math = " + subject.math);
//		System.out.println("science = " + subject.science);
		subject.info();
		
		System.out.println();
		
		// 기본생성자를 사용해서 Subject 타입의 객체 생성
		Subject subject1 = new Subject();
		System.out.println("국어: " + subject1.korean);
		System.out.println("영어: " + subject1.english);
		System.out.println("수학: " + subject1.math);
		System.out.println("과학: " + subject1.science);
		System.out.println("총점: " + subject1.total());
		System.out.println("평균: " + subject1.mean());
		
		System.out.println("-----------------");
		
		// 아규먼트를 갖는 생성자를 사용해서 Student 타입의 객체를 생성
		Student student = new Student(1, "홍길동", subject);
		student.information();
		
		// 기본생성자를 사용해서 Student 타입의 객체를 생성
		Student student2 = new Student();
		student2.information();
		
		// Student 생성자 (3)을 사용해서 객체 생성
		Student student3 = new Student(10, "오쌤", 10, 20, 30, 25);
		student3.information();
		
		
		

	}

}
