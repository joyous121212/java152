package com.itwill.class04;

public class ClassMain04 {

	public static void main(String[] args) {
		// 기본생성자를 사용해서 Rectangle 객체 생성, 메서드 호출
		Rectangle rect1 = new Rectangle();
		System.out.println("rect1: " + rect1);
		System.out.println("rect1 넓이: " + rect1.area());
		System.out.println("rect1 둘레: " + rect1.perimeter());

		// 아규먼트를 갖는 생성자를 사용:
		Rectangle rect2 = new Rectangle(5.3, 6.2);
		System.out.println("rect 2: " + rect2);
		System.out.println("세로길이: " + rect2.height + ", 가로길이: " + rect2.width);
		System.out.println("rect2 넓이: " + rect2.area());
		System.out.println("rect2 둘레: " + rect2.perimeter());
		
		rect2 = new Rectangle(4, 3);
		System.out.println("rect2: " + rect2);

	}

}
