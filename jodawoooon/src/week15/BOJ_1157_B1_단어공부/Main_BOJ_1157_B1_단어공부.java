package week15.BOJ_1157_B1_단어공부;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_1157_B1_단어공부 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int size = input.length();
		//대문자와 소문자를 구분하지 않는다.
		int[] cnt = new int[26];
		
		for (int i = 0; i < size; i++) {
			if(input.charAt(i)>='A'&&input.charAt(i)<='Z') {
				//대문자
				cnt[input.charAt(i)-'A']++;
			}else {
				//소문자
				cnt[input.charAt(i)-'a']++;
			}
		}
		
		int max = Integer.MIN_VALUE;
		char ans = '?';
		//가장 많이 사용된 알파벳을 대문자로 출력한다
		for (int i = 0; i < cnt.length; i++) {
			if(cnt[i]>max) {
				max = cnt[i];
				ans = (char)(i+'A');
			}else if(cnt[i]==max){
				ans = '?';
			}
		}
		
		System.out.println(ans);
	}
}
