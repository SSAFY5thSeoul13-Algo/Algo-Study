package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열폭발2 {
	/*
	폭발과정
	1. 문자열이 폭발 문자열 포함 시 모든 폭발 문자열 폭발 => 남은 문자열 이어붙임
	2. 계속반복
	3. 남은 문자열 업스면  "FRULA"를 출력
	
	
	두번째 시도 -> 조금 개선
	str.length만큼만 for문 돌고, 못찾으면 바로 break하도록
	
	161280	596
	*/
	static String str, bomb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine(); //문자열
		bomb = br.readLine(); //폭발 문자열
		
		Stack<Character> stack = new Stack<>();
		
		
		
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			
			
			if(stack.size() >= bomb.length()){
				
				boolean flag = false;
				int idx = bomb.length()-1;
				
				Stack<Character> temp = new Stack<>();
				
				
				char find = bomb.charAt(bomb.length()-1);
				
				while(true) {
					if(stack.peek() == find) {
						temp.push(stack.pop());
						idx-=1;
						if(idx >= 0) {
							find = bomb.charAt(idx);
						}else {
							flag = true;
							break;
						}
					}else {
						break;
					}
				}
				
				
				if(!flag) {
					while(!temp.isEmpty()) {
						
						stack.push(temp.pop());
					}
				}

			}
		}
		
		if(stack.isEmpty()) System.out.println("FRULA");
		else {
			StringBuilder ans = new StringBuilder();
			while(!stack.isEmpty()) ans.append(stack.pop());
			
			System.out.println(ans.reverse());
		}
	}
}
