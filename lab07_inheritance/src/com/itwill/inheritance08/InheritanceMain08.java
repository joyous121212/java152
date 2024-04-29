package com.itwill.inheritance08;

public class InheritanceMain08 {

	public static void main(String[] args) {
		// Rectangle 타입의 객체 생성
//		Rectangle rect = new Rectangle(4.0, 3.0);
//		rect.draw();
		Shape rect = new Rectangle(4.0, 3.0);
		rect.draw();
		
//		Circle circle = new Circle(1.0);
//		circle.draw();
		Shape circle = new Circle(1.0);
		circle.draw();
		

	}

}
