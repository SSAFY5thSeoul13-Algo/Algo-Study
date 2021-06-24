package week16.BOJ_10799_S3_쇠막대기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception{
		/**
		 * 조건
		 * 1. 자신보다 긴 쇠막대기 위에만 놓일 수 있음., 완전히 포함되도록 놓되, 끝점은 겹치지 않도록 놓는다.
		 * 2.각 쇠막대기를 자르는 레이저는 적어도 하나는 존재한다.
		 * 3. 어떤 쇠막대기의 양 끝점과도 겹치지 않는다.
		 * 
		 * 표현
		 * 1. 레이저는 여는 괄호 닫는괄호 '(' ')'로 표현된다.
		 * 2. 쇠막대기의 왼쪽 긑은 여는 괄호 '('로 오른쪽 끝은 닫힌 괄호 ')'로 표현된다.
		 * 
		 * [풀이]
		 * 1. '('만나면 삽입
		 * 2. ')'만나면 직전이 '('였는지 확인한다. -> 레이저 => 스택에 있는 수만큼 +
		 * 3. ')'인데, 닫힌 쇠막대기이면 => +1
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> s = new Stack<>();
		
		char[] input = br.readLine().toCharArray();
		
		int cnt = 0;
		for(int i=0; i< input.length; i++) {
			
			char cur = input[i];
			// 열린괄호
			if(cur=='(') {
				// 레이저라면
				if(input[i+1] ==')') {
					cnt += s.size();	// 현재 스택의 크기만큼 갯수증가
					i+=1;				// 닫힌괄호도 넘기기
				}
				// 쇠막대기라면 시작이라면
				else {
					s.push(cur);		// 스택에 삽입
				}
			}
			// 쇠막대기 닫혔다면
			else {
				cnt +=1;				// 조각 1개추가
				s.pop();				// 쇠막대기 시작 '('제거
			}
		}
		
		System.out.println(cnt);
	}
}
