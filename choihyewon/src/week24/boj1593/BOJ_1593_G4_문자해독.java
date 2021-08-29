package week24.boj1593;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1593_G4_문자해독 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int g = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		String word = br.readLine();
		String sentence = br.readLine();

		int[] w_cnt = new int[123];
		int[] s_cnt= new int[123];
		
		for(int i=0; i<g; i++) {
			w_cnt[(int)word.charAt(i)]++;
		}
		
		int len = 0;
		int start = 0;
		int answer = 0;
		
		for(int i=0; i<S; i++) {
			s_cnt[(int)sentence.charAt(i)]++;
			len++;
			
			if(len==g) {
				if(isSame(w_cnt,s_cnt)) {
					answer++;
				}
				
				s_cnt[(int)sentence.charAt(start)]--;

				len--;
				start++;
			}
		}
		
		System.out.println(answer);
			

	}

	private static boolean isSame(int[] w_cnt, int[] s_cnt) {
		for(int i=0; i<123; i++) {
			if(w_cnt[i]!=s_cnt[i]) {
				return false;
			}
		}
		return true;
	}

}
