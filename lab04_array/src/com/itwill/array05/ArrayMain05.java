package com.itwill.array05;

import java.util.Random;

public class ArrayMain05 {

	public static void main(String[] args) {
        // 과제:
        // 정수(int) 5개를 저장할 수 있는 배열을 선언하고, 기본값으로 초기화.
		int[] number = new int[5];
        // 배열에 0 이상 10 이하 난수 5개를 저장.
		Random random = new Random();
		for (int i = 0; i < number.length; i++) {
			number[i] = random.nextInt(11);

		}
		
		// 배열 원소들을 출력.
		for (int x : number) {
			System.out.print(x + " ");
		}
		
		System.out.println();
		
        // 배열 원소들 중 최댓값을 찾아서 출력.
		int max = number[0]; // 배열의 첫번째 원소를 최댓값이라고 지정.
		/* for (int x : number) { // 배열의 모든 원소를 순서대로 하나씩 꺼내면서 반복
			if (x > max) { // 배열에서 꺼낸 값이 (현재까지의) 최대값보다 크다면
				max  = x; // 최댓값을 배열에서 꺼낸 값으로 변경.
			}
		}
		*/
		for (int i = 0; i < number.length; i++) {
			if (max < number[i]) {
				max = number[i];
			}
		}
		System.out.println("최댓값 = " + max);
		
        // 배열 원소들 중 최솟값을 찾아서 출력.
		int min = number[0];
		/*
		for (int x : number) {
			if (x < min) {
				min = x;
			}
		}
		*/
		for (int i = 0; i < number.length; i++) {
			if (min > number[i]) {
				min = number[i];
			}
		}
		System.out.println("최솟값 = " + min);
        
		


	}

} 
