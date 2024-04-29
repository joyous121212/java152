package com.itwill.loop09;

public class LoopMain09 {

	public static void main(String[] args) {
		// 중첩 while 문장을 사용해서 구구단 1단부터 12단까지 출력.
		int x = 1;
		while (x <= 12) {
			System.out.println(x + " 단 ======");

			int y = 1;
			while (y <= 12) {
				System.out.printf("%d x %d = %d\n", x, y, x * y);
				y++;
			}	
			
			x++;
			
		} 

	}

}
