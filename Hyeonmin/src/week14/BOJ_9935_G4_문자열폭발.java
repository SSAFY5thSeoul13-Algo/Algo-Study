package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_G4_문자열폭발 {
	//str : 문자열, tgt : 폭발 문자열
	static String str, tgt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		tgt = br.readLine();
		//폭발하는 문자열 가장 마지막 글자
		char last = tgt.charAt(tgt.length()-1);
		//폭발하는 문자열 길이
		int len = tgt.length();

		Stack<Character> stack = new Stack<Character>();
		
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			stack.push(c);
			//폭발 여부
			boolean bomb = true;
			
			//스택의 크기가 폭발 문자열 이상이고 새 문자가 폭발 문자열의 마지막 문자인 경우
			if(c==last && stack.size() >= len) {
				int size = stack.size();
				//폭발 문자열 길이 만큼 스택의 뒷부분 비교
				for (int j = 0; j < len; j++) {
					//폭발 문자열이 아닌경우
					if(stack.get( size - len + j) != tgt.charAt(j)) {
						bomb = false;
						break;
					}
				}
				//폭발 문자열인 경우 그 길이만큼 스택에서 제거
				if(bomb) {
					for (int j = 0; j < len; j++) {
						stack.pop();
					}
				}
			}
			
			
		}
		
		//스택에있는 문자들을 stringbuilder로 연결
		for (Character c : stack) {
			sb.append(c);
		}

		//출력
		if (sb.length() <= 0)
			System.out.println("FRULA");
		else
			System.out.println(sb.toString());

	}
}