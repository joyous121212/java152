package com.itwill.loop14;

import java.util.Scanner;

public class LoopMain14 {

	public static void main(String[] args) {
		// 교재 연습문제 7
		boolean run = true; // 프로그램을 계속 실행할 지, 종료할 지를 결정하기 위한 변수.
		int balance = 0; // 은행 계좌의 잔고를 저장하기 위한 변수.
		Scanner scanner = new Scanner(System.in); // 콘솔 입력을 위해서.
		
		while(run) {
			System.out.println("----------------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고 | 4.종료");
			System.out.println("----------------------------------");
			System.out.print("선택> ");
			
			
				int menu = scanner.nextInt();
				
				/* if (menu == 1) {
					
				} else if (menu == 2) {
					
				} else if (menu == 3) {
					
				} else if (menu == 4) {
					System.out.println("안녕히 가세요.");
					break;
				} else {
					System.out.println("1~4중에 선택하세요!");
				}
				*/
				
				switch (menu) {
				case 1:
					System.out.println("예금액> ");
					int deposit = scanner.nextInt();
					balance += deposit;
					System.out.printf("입금: %d, 잔고: %d\n", deposit, balance);
					break;
				case 2:
					System.out.println("출금액> ");
					int withdraw = scanner.nextInt();
					balance -= withdraw;
					break;
				case 3:
					System.out.println("잔고> "+ balance);					
					break; 
				case 4:
					run = false; // while 문을 종료하기 위한 조건 변경.
					System.out.println("\n프로그램 종료");
					break; // switch 문장을 종료.
				default:
					System.out.println("1~4중에서 선택하세요!");
				
				}
			
		}


	}

}
