package week24.BOJ_1593_G4_문자해독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1593_G4_문자해독 {
	static int[] targetWord, foundWord;
	static int tLen, fLen;
	static char[] W, S; 
	static int count;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		tLen = Integer.parseInt(st.nextToken());
		fLen = Integer.parseInt(st.nextToken());
		
		targetWord = new int[300];
		foundWord = new int[300];
		
		//찾으려는 단어
		W = br.readLine().toCharArray();
		//추출한 문자열
		S = br.readLine().toCharArray();
		
		//찾으려는 단어의 알파벳 수를 배열에 저장
		for (char c : W) {
			int idx = c;
			
			targetWord[idx]++;
		}
		
		
		for (int i = 0; i < fLen; i++) {
			int idx = S[i];
			
			foundWord[idx]++;
			
			//추출한 문자열에서 선택한 문자가 찾으려는 단어길이보다 작은 경우
			if(i < tLen - 1)
				continue;
			
			boolean check = true;
			
			//문자열에서 선택한 알파벳과 단어의 알파벳 수가 일치하는지 확인
			for (char c : W) {
				int wIdx = c;
				
				if(foundWord[wIdx] != targetWord[wIdx]) {
					check = false;
					break;
				}
			}
			
			//수가 일치하면 카운트
			if(check)
				count++;
			
			//가장 앞에있는 문자를 제외
			idx = S[i-(tLen-1)];
			
			foundWord[idx]--;
			
		}
		
		System.out.println(count);
		
		
	}

}
