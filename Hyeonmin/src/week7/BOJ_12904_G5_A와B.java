package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12904_G5_A와B {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//문자열 1
		String S= br.readLine();
		//문자열 2
		StringBuilder T = new StringBuilder(br.readLine());
		
		//문자열 1의 길이
		int length = S.length();
		
		while(true) {
			//두 문자열의 길이가 같아진 경우
			if(length == T.length()) {
				//두 문자열이 같은 경우
				if(S.equals(T.toString())) {
					System.out.println(1);
				}
				//두 문자열이 다른 경우
				else {
					System.out.println(0);
				}
				
				break;
			}
			
			//문자열2의 가장 마지막 문자
			char c = T.charAt(T.length()-1);
			
			//마지막 문자가 A인 경우
			if(c == 'A') {
				//맨 뒤 A를 삭제
				T.setLength(T.length()-1);
			}
			//마지막 문자가 B인 경우
			else if(c == 'B') {
				//맨 뒤 B를 삭제
				T.setLength(T.length()-1);
				//문자열을 뒤집음
				T = T.reverse();
			}
		}
	}
}
