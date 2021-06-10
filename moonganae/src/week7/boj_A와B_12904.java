package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : A와B
 * 번호 : 12904
 * 난이도 : 골5
 * 풀이시간 : 30분
 * 사용 알고리즘 : 문자열 
 */
public class boj_A와B_12904 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String T = br.readLine();
		
		StringBuilder sb = new StringBuilder(T);
		
		/* S와 T가 같은 길이가 될때까지 */
		while(S.length() != sb.length()) {
			/* 현재 T의 길이 */
			int length = sb.length()-1;
			/* 현재 T의 마지막 문자 */
			char last = sb.charAt(length);
			/* 길이줄이기 */
			sb.setLength(length);
			
			/* 만약 B면 T뒤집기 */
			if(last == 'B') {
				sb.reverse();
			}
		}
		
		/* S == T라면 1출력 */
		if(S.equals(sb.toString())) {
			System.out.println(1);
		}
		/* 아니라면 0출력 */
		else {
			System.out.println(0);
		}

	}

}
