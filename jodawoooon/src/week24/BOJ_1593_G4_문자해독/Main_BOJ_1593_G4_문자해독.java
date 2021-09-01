package week24.BOJ_1593_G4_문자해독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1593 G4 문자해독
 * @Author : Daun JO
 * @Date : 2021. 8. 29. 
 * @Algorithm : Sliding Window
 *
 */
public class Main_BOJ_1593_G4_문자해독 {
	
	static int g, s, ans;
	static String W, S;
	static int[] upperW, lowerW, upperS, lowerS;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		g = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		W = br.readLine();
		S = br.readLine();
		
		//W의 순열이 S 안에 있을 수 있는 형태의 개수 => 순열 검사 시간초과
		
		//검사해야하는 문자열의 길이는 고정(g)이기 때문에 슬라이딩 윈도우 기법을 사용하여 구간을 검사
		
		
		upperW = new int[26];
		lowerW = new int[26];
		upperS = new int[26];
		lowerS = new int[26];
		
		for(int i = 0 ; i < g ; i++) {
			char c = W.charAt(i);
			//W 단어에 사용된 알파벳
			if(c<'a') upperW[c-'A']++;
			else lowerW[c-'a']++;
		}
		
		
		int len = 0;

		for(int i =0;i<s;i++) {
			char c = S.charAt(i);
			if(c<'a') upperS[c-'A']++;
			else lowerS[c-'a']++;
			
			len++;
			
			if(len==g) {
				if(check()) ans++;
				
				c = S.charAt(i-g+1);
				if(c<'a') upperS[c-'A']--;
				else lowerS[c-'a']--;
				len--;
			}
		}
		
		System.out.println(ans);
		
	}
	private static boolean check() {
		
		for(int i =0;i<26;i++) {
			if(upperW[i]!=upperS[i]) return false;
			if(lowerW[i]!=lowerS[i]) return false;
		}
		
		return true;
	}
}
