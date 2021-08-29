package week24.BOJ_1593_G4_문자해독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * g : 찾으려는 특정단어 길이
	 * s : 발굴기록 길이
	 * W : 특정단어
	 * S : 발굴기록
	 * 
	 * wCnt : 찾을 문자열 상태
	 * sCnt : 슬라이싱 윈도우 상태
	 */
	static int g, s, ans;
	static String W, S;
	
	static int[] wCnt, sCnt;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		g = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		
		wCnt = new int[52];
		sCnt = new int[52];
		
		W = br.readLine();
		
		// 찾을 문자열 상태 설정
		for(char tar : W.toCharArray()) {
			if(tar <= 'Z') wCnt[tar - 'A']++;
			else wCnt[tar - 'a' + 26]++;
		}
		
		
		
		S = br.readLine();
		
		// 슬라잇이 윈도우
		int length = 0, first = 0;
		for(char tar : S.toCharArray()) {
			
			// 슬라이싱 위도우 증가
			if(tar <= 'Z') sCnt[tar - 'A']++;
			else sCnt[tar - 'a' + 26]++;
			
			length++;
			
			// 찾을 문자열길이라면
			if(length == g) {
				
				// 같은 것인지 확인
				if(sameCnt()) ans++;
				
				// 맨 앞글자 빼기
				tar = S.charAt(first);
				
				if(tar <= 'Z') sCnt[tar - 'A']--;
				else sCnt[tar - 'a' + 26]--;
				
				length--;
				first++;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean sameCnt() {
		for(int i=0; i<52; i++) {
			if(wCnt[i] != sCnt[i]) return false;
		}
		
		return true;
	}
}
