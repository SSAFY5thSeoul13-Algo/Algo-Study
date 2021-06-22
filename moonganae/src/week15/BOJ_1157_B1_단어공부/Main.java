package week15.BOJ_1157_B1_단어공부;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
 * 풀이시간 : 16분
 */
public class Main {

	public static void main(String[] args) throws Exception{
		/*
		 * 	대소문자 주어지면 가장 많이 사용된 알파벳구하기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = br.readLine().toCharArray();
		// 26길이의 cnt배열 선언
		int[] cnt = new int[27];
		
		int max=0, maxIdx=-1, maxCnt=0;
		for(int i=0; i<str.length; i++) {
			char cur = str[i];
			
			int idx = 26;
			// 소문자인경우 cnt[cur-'a']++
			if('a'<=cur && cur<='z') {
				idx = cur-'a';
			}
				
			
			// 대문자인 경우 cnt[cur-'A']++;
			else if('A'<=cur && cur<='Z') {
				idx= cur-'A';
			}
			
			cnt[idx]++;
			
 			
		}
		
		/* 최대 사용 알파벳 확인 */
		for(int i=0; i<26; i++) {
			if(cnt[i] > max) {
				max = cnt[i];
				maxIdx = i;
				maxCnt = 1;
			}else if(max == cnt[i]) {
				maxCnt++;
			}
		}
		
		// 최댓값이 여러개면 ?출력
		if(maxCnt > 1) {
			System.out.println("?");
		} else {
			char result = (char) ('A' + maxIdx); 
			System.out.println(result);
		}
		
		
		
		
		
	}

}
