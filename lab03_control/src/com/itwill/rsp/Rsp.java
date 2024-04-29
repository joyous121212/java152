package com.itwill.rsp;

import java.util.Random;
import java.util.Scanner;

public class Rsp {

	public static void main(String[] args) {
		// 가위/바위/보 (Rock-Scissors-Paper) 게임:
        // 가위 - 0, 바위 - 1, 보 - 2
        
        // TODO: Random 타입 변수를 선언, 초기화.
		Random rand = new Random();
        // TODO: Scanner 타입 변수를 선언, 초기화.
		Scanner sc = new Scanner(System.in);

        System.out.println("가위바위보 게임");
        System.out.println("---------------");
        System.out.println("[0] 가위");
        System.out.println("[1] 바위");
        System.out.println("[2] 보");
        System.out.println("---------------");
        System.out.print("선택>>> ");
        
        // TODO: 사용자가 콘솔창에서 입력한 정수를 변수(user)에 저장.
        int user = sc.nextInt();
        System.out.println("user = " + user);
        
        // TODO: 3 미만의 정수 난수 1개를 변수(computer)에 저장.
        int computer = rand.nextInt(3);
        System.out.println("computer = " + computer);
        
        // TODO: 가위바위보 게임 결과(User win/Computer win/Tie)를 출력. -> if 구문 연습
        

        if ((user == computer)) { // 비김
        	System.out.println("Tie");        	
        } else if ((user == 0 && computer == 2) || 
        		(user == 1 && computer == 0) || 
        		(user == 2 && computer == 1)) { // user가 이긴 경우
        	System.out.println("User win");
        } else { //computer가 이긴 경우
        	System.out.println("Computer Win");
        	}
        
        /*
         * if (user == 0) { // user: 가위
        	if (computer == 0) {
        		System.out.println("Tie");
        	} else if (computer == 1) {
        		System.out.println("Computer Win");
        	} else {
        		System.out.println("User Win");
        	}
        } else if (user == 1) { // user: 바위
        	if (computer == 0) {
        		System.out.println("User Win");
        	} else if (computer == 1) {
        		System.out.println("Tie");
        	} else {
        		System.out.println("Computer Win");
        	}
        } else { // user: 보
        	if (computer == 0) {
        		System.out.println("Computer Win");
        	} else if (computer == 1) {
        		System.out.println("User Win");
        	} else {
        		System.out.println("Tie");
        	}
        	*/
        
        /*
        if (user == computer) { // 비김
        	System.out.println("Tie");      
        } else if (user == 0) { // user: 가위
        	if (computer == 1) { // com: 바위
        		
        	} else { //com: 보
        		
        	}
        } else if (user == 1) { // user: 바위
        	if (computer == 0) { // com: 가위
        		
        	} else { // com: 보
        		
        	}
        } else { // user: 보
        	if (computer == 0) { // com: 가위
        		
        	} else { // com: 바위
        		
        	}
        }
        */
        
        /*
        int result = user - computer;
        if (result == 0) { 
        	System.out.println("Tie");
        } else if (result == 1 || result == -2) {
        	System.out.println("User Win");
        } else {
        	System.out.println("Computer Win");
        }
        */
        
        
    }
        
}


