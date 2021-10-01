## BOJ 2661 좋은 수열 
- BackTracking
- 🥇 Gold4
- 🔗[좋은 수열 문제 바로가기](https://www.acmicpc.net/problem/2661)



## 풀이

이 문제를 풀면서 백트래킹의 개념을 명확히 다진것 같습니다!
먼저 수열의 자릿수에 들어갈 수 있는 모든 경우를 구해서 좋은 수열인 경우에만 탐색하도록 진행하였습니다.

~~~java
		for(int i=1; i<=3; i++) {
			if(isGood(str+i)) {
				backTracking(str+i);
			}
		}
~~~

좋은 수열인지 판별하는 함수는 
예시로 str이 32121 인 경우 
1자리수씩 비교 321<u>2</u><u>1</u> -> 동일하지 않으므로 좋은 수열 
2자리수씩 비교 3<u>21</u><u>21</u> -> 동일하므로 나쁜 수열 
이렇게 판별해주었습니다.

~~~java
	private static boolean isGood(String str) {
		// 수열의 모든 경우를 탐색
		for(int i=1; i<=str.length()/2; i++) {
			String str1 = str.substring(str.length()-i-i, str.length()-i);
			String str2 = str.substring(str.length()-i,str.length());
			// 만약 나쁜 수열이라면 false return 
			if(str1.equals(str2)) {
				return false;
			}
		}
		
		return true;
		
	}
~~~

그리고 가장 먼저 나온 수열이 N길이의 제일 작은 수열이기 때문에 isEnd 변수를 통해 결과를 출력하고 멈추게 해주었습니다.


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2661_G4_좋은수열 {
	static int N;
	static boolean isEnd = false;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = "";
		
		backTracking(str);

	}
	private static void backTracking(String str) {
		// 제일 작은 수열이 나왔으므로 탐색 종료 
		if(isEnd) {
			return;
		}
		
		if(str.length()==N) {
			System.out.println(str);
			// 종료되었음을 의미 
			isEnd = true;
			return;
		}
		
		// 숫자는 1,2,3 으로만 이루어져 있으므로 1부터 3까지 반복 
		for(int i=1; i<=3; i++) {
			if(isGood(str+i)) {
				backTracking(str+i);
			}
		}
		
	}
	// 좋은 수열인지 아닌지 판별 
	private static boolean isGood(String str) {
		// 수열의 모든 경우를 탐색
		for(int i=1; i<=str.length()/2; i++) {
			String str1 = str.substring(str.length()-i-i, str.length()-i);
			String str2 = str.substring(str.length()-i,str.length());
			// 만약 나쁜 수열이라면 false return 
			if(str1.equals(str2)) {
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
| 11956kb| 80ms|
