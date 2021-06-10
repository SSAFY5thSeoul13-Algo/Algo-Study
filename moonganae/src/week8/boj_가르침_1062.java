package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_가르침_1062 {

	/* 남극어 기본 언어 ANTA TICA! */
	static int ANTATICA = (1<<'a'-'a') | (1<<'c'-'a') | (1 <<'n'-'a') | (1<<'i'-'a') | (1<<'t'-'a');
	/* 각 단어의 mask를 저장할 배열 */
	static List<Integer> maskList = new ArrayList<>();
	/* 정답을 저장할 변수 */
	static int max = 0;
	/* 단어의 수, 지민이가 가르칠 알파벳의 수 */
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		/* 시험치기 전 이미 읽을 수 있는 단어의 숫자 */
		int readCnt = 0;
		for(int i=0; i<N; i++) {
			String data = br.readLine();
			int limit = data.length()-4;
			
			/* 단어를 마스킹할때 처음 4자리와 끝 4자리는 고정이라 할필요가 없다.
			 * 범위 : 4 ~ length()-4 
			 * */
			int mask = ANTATICA;
			for(int j=4; j<limit; j++) {
				char c = data.charAt(j);
				mask = mask | 1<<c-'a';
			}
			
			/* 기본 글자와 같다면 무조건 읽을 수 있다. */
			if( (mask | ANTATICA) == ANTATICA )
				readCnt++;
			/* 기본 글자 외에 다른 글자도 있다면 나중에 확인해봐야함 */
			else 
				maskList.add(mask);
			
		}
		/* 기본글자도 못읽는데 무슨 시험을 친다는 소리임? 0점 */
		if(K<5) {
			System.out.println(0);
		}else {
			
			/* 이미배운 알파벳 5, a는 무조건포함이니 b부터(1), 기본 masking acnit */
			comb(5, 1, ANTATICA);
			
			/* 이미 읽었던 단어 + 오늘 시험중 가장 많이 읽은 단어의 수 */
			System.out.println(readCnt + max);
		}
	}
	/* 알파벳을 선택하는 조합함수 
	 * num : 배운 알파벳 수
	 * start : 배우기 시작할 알파벳의 번호
	 * mask : 현재까지 배운 알파벳 mask
	 * */
	static void comb(int num, int start, int mask) {
		
		if(num == K) {
			test(mask);
			return;
		}
		
		for(int i=start; i <26; i++) {
			if( (mask & 1<<i) != 0) continue;
			comb(num+1, i+1, mask | 1<<i);
		}	
	}
	/* 지금까지 배운 알파벳(mask)로 몇단어를 읽을 수 있는지 확인하는 함수
	 * */
	static void test(int mask) {
		
		int readCnt = 0;
		// 각 단어 순회
		for(int word : maskList) {
			// 배운 알파벳이 해당 단어를 커버할수 있다면
			if( (mask & word) == word) 
				readCnt++;				// 읽은 단어 추가
		}
		
		max = Math.max(max, readCnt);
	}
}
