package com.itwill.map03;

import java.util.HashMap;

public class MapMain03 {

	public static void main(String[] args) {
		// 단어 개수 세기(word counting):
		// 문자열에 등장하는 단어를 key로 하고,
		// 단어가 문자열에 등장하는 횟수를 value로 하는 Map 객체를 만들고, 출력.
		
		String sentence = "짜장면 짬뽕 짜장면 짬뽕 볶음밥";
		//-> 결과: {짜장면=2, 짬뽕=1, 볶음밥=1, 김치찌개=2, 부대찌개=1, 된장찌개=1}
		
		//String[]
		String[] words = sentence.split(" ");
		for (String x : words) {
			System.out.print(x + ", ");
			}
		System.out.println();
		
		//Map<String, Integer>
		// 단어(key)-빈도수(value) 저장하기 위한 Map을 선언, 초기화.
		HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();
		System.out.println(wordCounts);
		
		// 배열에 저장된 단어들을 순서대로 하나씩 반복
		for(String k : words) {	
			Integer count = wordCounts.getOrDefault(k, 0);
			wordCounts.put(k, count + 1);
			System.out.println(wordCounts);
		} 
		
		System.out.println("--------------------------");
		
		HashMap<String, Integer> wordCounts2 = new HashMap<String, Integer>();
		for(String w : words) {
			Integer count = wordCounts2.get(w);
			if (count != null) {
				wordCounts2.put(w, count + 1);
			} else {
				wordCounts2.put(w, 1);
			}
			System.out.println(wordCounts2);
		}
		

	}

}
