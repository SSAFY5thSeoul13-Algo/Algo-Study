package week16.BOJ_10799_S3_쇠막대기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_10799_S3_쇠막대기 {

	static int cnt;

	public static void main(String[] args) throws Exception {
		
		// 잘려진 쇠막대기 조각의 총 개수
		// ()이면 레이저, 안 닫히면 막대기 개수 증가
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		String input = br.readLine();
		boolean flag = true;
		
		for (int i = 0; i < input.length(); i++) {
			
			char c = input.charAt(i);
			
			if(c=='(') { //현재 값이 (이면 스택에 넣는다.
				flag = true;
				stack.push(c);
			}else if(c==')'){ 
				//현재 값이 )이면 이전값을 확인한다
				
				//레이저이다 -> ()인 경우
				if(flag) {
					flag = false;
					stack.pop();
					cnt += stack.size();
				}else { //))이다 -> 쇠막대기 끝
					stack.pop();
					cnt++;
				}
	
				
			}

		}
		
		System.out.println(cnt);
	}

}
