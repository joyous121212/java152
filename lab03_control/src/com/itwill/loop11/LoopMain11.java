package com.itwill.loop11;

public class LoopMain11 {

	public static void main(String[] args) {
		// Ex.1 1부터 100까지 자연수들의 합을 계산하고 출력
		// 1 + 2 + 3 + ... + 9 + 10 = 55
		int sum = 0; // 1 ~ 10 자연수들의 합을 저장할 변수
		
		for (int x = 1; x <= 10; x++) {
			sum += x; // sum = sum + x;
			System.out.println("sum = " + sum);
		}
		System.out.println("1~10까지 합: " + sum);
		
		sum = 0;
		int i = 1;
		while (i <= 10) {
			sum += i;
			i++;
		} System.out.println("1~10까지 합: " + sum);
		
		System.out.println();
		
		// Ex.2 1부터 100까지 자연수들 중에서 짝수들의 합을 계산하고 출력.
		// 2 + 4 + 6 + ... + 98 + 100 = ?
		sum = 0;
		for (int x = 2; x <= 100; x += 2) {
			sum += x;
			System.out.println("sum = " + sum);
		} System.out.println("1~100까지 짝수의 합: " + sum);
		
		sum = 0;
		for (int x = 1; x <= 100; x++) {
			if (x % 2 == 0) {
				sum += x;
			}
		}
		System.out.println("sum = " + sum);
		
		sum = 0;
		i = 2;
		while (i <= 100) {
			sum += i;
			i += 2;
		} System.out.println("1~100까지 짝수의 합: " + sum);
		
		System.out.println();
		
		// Ex.3 1부터 100까지 자연수들 중에서 홀수들의 합을 계산하고 출력.
		// 1 + 3 + 5 + ... + 97 + 99 = ?
		int s = 0;
		for (int x = 1; x <= 100; x += 2) {
			s += x;
		} System.out.println("1~100까지 홀수의 합: " + s);
		
		s = 0;
		int y  = 1;
		while (y <= 100) {
			if (y % 2 == 1) {
				s += y;
			} y++;
		} System.out.println("홀수 합: " + s);
		
		s = 0;
		int x = 1;
		while (x <= 100) {
			s += x;
			x += 2;			
		} System.out.println("1~100까지 홀수의 합: " + s);
		
		System.out.println();
		
		System.out.println("1~100까지 합: " + (s + sum));

	}

}
