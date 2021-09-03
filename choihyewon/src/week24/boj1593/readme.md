## BOJ 1593 문자해독 
- SlidingWindow
- 🥇 Gold4
- 🔗[문자해독 문제 바로가기](https://www.acmicpc.net/problem/1593)



## 풀이

먼저 word의 각 문자를 index의 개수만큼 저장합니다.

~~~java
		for(int i=0; i<g; i++) {
			w_cnt[(int)word.charAt(i)]++;
		}
~~~

그리고 S만큼 반복문을 실행합니다.
sentence의 각 문자에 맞는 index에 count를 해주고 길이를 증가시킵니다.

만약 길이가 word의 길이만큼 된다면 s_cnt와 w_cnt가 같은지 확인합니다.
만약 같다면 answer+1을 해줍니다.
그리고 s_cnt에 저장된 맨 첫번째로 저장된 문자의 개수를 -1 해주고 길이도 -1 해줍니다.


## 소스코드
~~~java
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
~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 33804kb| 280ms|
