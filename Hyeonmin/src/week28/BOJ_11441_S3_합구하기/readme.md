## BOJ 11441 S3 합 구하기
- 누적합
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/11441

N개의 수 A1, A2, ..., AN이 입력으로 주어진다. 총 M개의 구간 i, j가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.

#### 입력
첫째 줄에 수의 개수 N이 주어진다. (1 ≤ N ≤ 100,000) 둘째 줄에는 A1, A2, ..., AN이 주어진다. (-1,000 ≤ Ai ≤ 1,000) 셋째 줄에는 구간의 개수 M이 주어진다. (1 ≤ M ≤ 100,000) 넷째 줄부터 M개의 줄에는 각 구간을 나타내는 i와 j가 주어진다. (1 ≤ i ≤ j ≤ N)

#### 출력
총 M개의 줄에 걸쳐 입력으로 주어진 구간의 합을 출력한다.

###  💡 풀이

크기가 `N+1`인 배열 `arr`의 각 `i`번째 데이터에 1~i번까지 수의 합을 저장한 후에 start~end까지의 합을 `arr[end] - arr[start-1]`의 값으로 구해서 풀었다

<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11441_S3_합구하기 {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken()); 
			arr[i] = arr[i-1] + num;
		}
		
		M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(arr[end] - arr[start-1]).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		
		System.out.println(sb.toString());

	}

}





```


<br>



메모리|시간
--|--
56700 KB|632 ms