package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_9935_문자열폭발3 {
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
			stack.push(str.charAt(i)); //스택에 문자열을 넣는다
			
			
			if(stack.size() >= bomb.length()){ //스택사이즈가 폭발문자열의 길이보다 길어지면 검사
				
				boolean flag = false;
				int idx = bomb.length()-1; 
				
				StringBuilder tmp = new StringBuilder();
				
				
				char find = bomb.charAt(bomb.length()-1); //찾을 문자
				
				while(true) {
					if(stack.peek() == find) { //찾으면
						tmp.append(stack.pop()); //스택에서빼서 stringbuilder에 붙인다
						idx-=1; //인덱스 하나 감소
						if(idx >= 0) { 
							find = bomb.charAt(idx); //해당 인덱스에 해당하는 find char 변경
						}else { 
							flag = true;
							break;
						}
					}else {
						break;
					}
				}
				
				
				if(!flag) { //폭발문자열 못찾았다
					
					for (int j = tmp.length()-1; j >= 0 ; j--) {
						stack.push(tmp.charAt(j)); // 스택에 다시 집어넣는다. 이때 tmp는 거꾸로되어있으므로 뒤에서부터 집어넣음
					}
				}

			}
		}
		
		if(stack.isEmpty()) System.out.println("FRULA"); //남아있는 문자가 없으면 FRULA 출력
		else {
			StringBuilder ans = new StringBuilder();
			while(!stack.isEmpty()) ans.append(stack.pop());
			
			System.out.println(ans.reverse()); //남은문자열 출력
		}
	}
}
