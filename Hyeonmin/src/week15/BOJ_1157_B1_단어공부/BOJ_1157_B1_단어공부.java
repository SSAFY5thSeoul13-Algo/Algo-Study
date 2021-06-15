package week15.BOJ_1157_B1_단어공부;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1157_B1_단어공부 {
	static int max, maxCnt, maxIdx;
	static int[] alphCnt;
	static char[] alph;
			
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		alphCnt = new int[200];
		alph = new char[200];
		
		String str = br.readLine();
		
		int length = str.length();
		
		for (int i = 0; i < length; i++) {
			char a = Character.toUpperCase(str.charAt(i));
			
			alphCnt[a]++;
			alph[a] = a;
			
			if(alphCnt[a] > max) {
				max = alphCnt[a];
				maxCnt = 1;
				maxIdx = a;
			}
			else if(alphCnt[a] == max) {
				maxCnt++;
			}
		}
		
		System.out.println(maxCnt > 1 ? "?" : alph[maxIdx]);
	}
}
