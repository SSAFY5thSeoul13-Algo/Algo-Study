## BAEKJOON SILVER3 11441 합구하기 
- 누적합
- Silver 3
- https://www.acmicpc.net/problem/11441
<br>

### 문제설명
> N개의 수 A1, A2, ..., AN이 입력으로 주어진다. 총 M개의 구간 i, j가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.

### 입력
- 첫째 줄에 수의 개수 N이 주어진다. (1 ≤ N ≤ 100,000)
- 둘째 줄에는 A1, A2, ..., AN이 주어진다. (-1,000 ≤ Ai ≤ 1,000)
- 셋째 줄에는 구간의 개수 M이 주어진다. (1 ≤ M ≤ 100,000) 
- 넷째 줄부터 M개의 줄에는 각 구간을 나타내는 i와 j가 주어진다. (1 ≤ i ≤ j ≤ N)

### 출력
총 M개의 줄에 걸쳐 입력으로 주어진 구간의 합을 출력한다.

### 입출력 예

#### 예제 1
입력
```
5
10 20 30 40 50
5
1 3
2 4
3 5
1 5
4 4
```
출력
```
60
90
120
150
40
```

#### 예제 2
입력
```
3
1 0 -1
6
1 1
2 2
3 3
1 2
2 3
1 3
```
출력
```
1
0
-1
1
-1
0
```

### 풀이 및 과정
가장 기초적인 누적합으로 해결하였습니다.

1. 누적합을 저장할 배열(arr)을 이용하여, 배열의 수를 넣을때, arr[i-1] + val을 이용하여 저장합니다.
2. 구간(i,j) 를 계산할때, arr[j] - arr[i-1]의 수식을 이용하여 해당 구간의 합을 계산합니다.



### 소스코드 1
```java
package week28_BOJ__11441_S3_합구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/* 배열의 길이, 구간의 갯수*/
	static int N, M;
	/* 누적합 배열 */
	static int[] arr;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/* 인덱스 0은 0을 가짐 */
		arr = new int[N+1];
		
		/* 누적합 계산*/
		for(int i=1; i<=N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		/* 구간계산 */
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(arr[to] - arr[from-1]);
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

}
	
```

### 결과 1
```
메모리 : 57020KB	
시간 : 644ms
```
