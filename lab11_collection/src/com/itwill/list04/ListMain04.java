package com.itwill.list04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListMain04 {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("java", "sql", "html", "css", "javascript", "servlet", "jsp", "spring");
		System.out.println(names.size());

		// 1. names에서 5글자 이상인 문자열을 대문자로 변환해서 저장하는 ArrayList를 만들고 출력.
		// -> [JAVASCRIPT, SERVLET, SPRING]
		ArrayList<String> languages = new ArrayList<String>();
		System.out.println(languages);
//		System.out.println(languages.get(0));
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).length() >= 5) {
				languages.add(names.get(i).toUpperCase());
			}
		}
		System.out.println(languages);
//		

		// 2. names에 저장된 문자열의 글자수들을 저장하는 ArrayList를 만들고 출력.
		// -> [4, 3, 4, 3, 10, 7, 3, 6]
		ArrayList<Integer> size = new ArrayList<Integer>();
		for(int i = 0; i < names.size(); i++) {
			size.add(names.get(i).length());
		}
		System.out.println(size);

		List<Integer> codes = Arrays.asList(0, 1, 0, 1, 1, 0);

		// 3. codes의 원소가 0이면 "남성", 1이면 "여성"을 저장하는 ArrayList를 만들고 출력.
		ArrayList<String> x = new ArrayList<String>();
		for (int i = 0; i < codes.size(); i++) {
			if (codes.get(i) == 0) {
				x.add("남성");
			} else {
				x.add("여성");
			}
		} System.out.println(x);
		
	}



}
