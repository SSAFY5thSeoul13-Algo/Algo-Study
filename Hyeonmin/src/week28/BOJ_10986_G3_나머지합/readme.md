## BOJ 10986 G3 나머지 합
- 누적합
- 수학
- gold3

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/10986

수 N개 A1, A2, ..., AN이 주어진다. 이때, 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 구하는 프로그램을 작성하시오.

즉, Ai + ... + Aj (i ≤ j) 의 합이 M으로 나누어 떨어지는 (i, j) 쌍의 개수를 구해야 한다.


#### 입력
첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 106, 2 ≤ M ≤ 103)

둘째 줄에 N개의 수 A1, A2, ..., AN이 주어진다. (0 ≤ Ai ≤ 109)

#### 출력
첫째 줄에 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 출력한다.

###  💡 풀이

`M`으로 나누어 떨어지는 구간합은 `(j-i) % M == 0`인 경우이다

각 i번째 숫자까지의 합을 `M`으로 나눈 나머지를 구하고 `arr`배열에 해당 나머지에 해당하는 위치에 카운트를 한다

`arr`배열의 각 인덱스에서 해당 값이 `n`일 경우 nC2의 값을 모두 더한 값이 `(j-i) % M == 0`인 모든 경우의 수이다

단독적으로 추가로 나머지가 0인 값이 있는 경우를 고려해 `arr[0]`의 값을 더해준다


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10986_G3_나머지합 {
	static int N, M;
	static long count;
	static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		int num = 0;
		
		for (int i = 1; i <= N; i++) {
			num = (num + Integer.parseInt(st.nextToken()))%M;
			arr[num]++;
		}
		
		for (int i = 0; i < M; i++) {
			count += (long)arr[i]*(arr[i]-1)/2;
		}
		
		count += arr[0];
		
		System.out.println(count);
	}

}





```


<br>



메모리|시간
--|--
164008 KB|456 ms