package com.itwill.project.model;

import java.util.Scanner;
import java.util.Calendar;

public class Calender {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		int y, m, dayNum;
		
		do {
			System.out.print("년도 >>");
			y = sc.nextInt();
		} while (y<1);
		
		do {
			System.out.print("월 >>");
			m = sc.nextInt();
		} while (m > 12 | m < 1);
		
		cal.set(y, m-1, 1);
		dayNum = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("일\t월\t화\t수\t목\t금\t토\n--------------");
		
		for (int i = 0; i < dayNum -1; i++) {
			System.out.print("\t");
		}
		
		for (int i = 0; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			if (dayNum % 7 !=0) {
				System.out.print(i + "\t");
			} else if (dayNum %7 == 0) {
				System.out.println(i);
			}
			dayNum++;
			
			if (dayNum %7 != 1) {
				System.out.println();
			}
			System.out.println("-----------------------------");
		}
	}

}
