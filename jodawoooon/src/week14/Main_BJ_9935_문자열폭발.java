package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열폭발 {
	/*
	폭발과정
	1. 문자열이 폭발 문자열 포함 시 모든 폭발 문자열 폭발 => 남은 문자열 이어붙임
	2. 계속반복
	3. 남은 문자열 업스면  "FRULA"를 출력
	
	
	첫번째시도 -> 아무생각없이 일단 풀어봄
	메모리대폭발
	*/
	static String str, bomb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine(); //문자열
		bomb = br.readLine(); //폭발 문자열
		
		Stack<Character> stack = new Stack<>();
		
		
		while(str.contains(bomb)) {
			
			//System.out.println(str);
			stack.clear();
			for (int i = 0; i < str.length() ; i++) {
				stack.add(str.charAt(i));
				
			}
			
			
			StringBuilder tmp = new StringBuilder();
			
			
			while(!stack.isEmpty()) {
				
				tmp.append(stack.pop());

				//System.out.println(tmp);
				
				String bombStr = "";
				for (int i = tmp.length()-1; i >=0 ; i--) {
					bombStr += tmp.charAt(i);
				}
				
				
				if(bombStr.contains(bomb)) {
					//System.out.println(bombStr);
					for (int i = bomb.length(); i < bombStr.length() ; i++) {
						bombStr.charAt(i);
						stack.add(bombStr.charAt(i));
					}
					
					break;
					
				}
			}
			
			str = "";
			for (int i = 0; i < stack.size() ; i++) {
				str += stack.elementAt(i);
			}
			
			
		}
		
		if(str.length()==0) System.out.println("FRULA");
		else {
			StringBuilder ans = new StringBuilder();
			for (int i = 0; i < str.length() ; i++) {
				ans.append(stack.pop());
			}
			
			System.out.println(ans.reverse().toString());
		}
	}
}
