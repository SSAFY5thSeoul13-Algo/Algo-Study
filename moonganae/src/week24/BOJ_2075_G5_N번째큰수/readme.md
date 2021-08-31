## BAEKJOON GOLD5 52075 N번째ㅔ 큰 
- 정렬, 우선순위큐
- Gold 5
- https://www.acmicpc.net/problem/2075
<br>

### 문제설명
> N×N의 표에 수 N2개 채워져 있다. 채워진 수에는 한 가지 특징이 있는데, 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것이다. N=5일 때의 예를 보자.

```
12	7	9	15	5
13	8	11	19	6
21	10	26	31	16
48	14	28	35	25
52	20	32	41	49
```
> 이러한 표가 주어졌을 때, N번째 큰 수를 찾는 프로그램을 작성하시오. 표에 채워진 수는 모두 다르다.

### 입력
- 첫째 줄에 N(1 ≤ N ≤ 1,500)이 주어진다.
- 다음 N개의 줄에는 각 줄마다 N개의 수가 주어진다.
- 표에 적힌 수는 -10억보다 크거나 같고, 10억보다 작거나 같은 정수이다.

### 출력
첫째 줄에 N번째 큰 수를 출력한다.

### 입출력 예

#### 예제 1
입력
```
5
12 7 9 15 5
13 8 11 19 6
21 10 26 31 16
48 14 28 35 25
52 20 32 41 49
```
출력
```
35
```

### 풀이 및 과정
2가지 방식으로 해결하였습니다.

메모리 공간과 시간이 1500*1500의 크기의 배열과 연산을 감당할 수 있다고 판단하여 List에 넣어 정렬시키는 방식으로 해결하니 문제가 해결되었습니다.

하지만, 시간이 너무 오래걸려 다른 방식인 우선순위큐를 이용하여 문제를 다시 풀었습니다.<br>
우선순위 큐안에는 N개의 가장 큰수만 가지고 있고, 큐의 가장 작은수보다 큰 수가 존재할때 교체하는 방식으로 구현하였습니다.


### 소스코드 1 우선순위큐
```java
package week24.BOJ_2075_G5_N번째큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 가장 큰수 N개를 가질 우선순위큐
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				
				int data = Integer.parseInt(st.nextToken());
				
				// 우선순위큐에 n개 이하라면 그냥 넣기
				if(q.size() < N) q.offer(data);
				// 현재 큐에서 가장 작은 수가 data보다 작다면
				else if(data > q.peek()) {
					// 데이터 교체
					q.poll();
					q.offer(data);
					
				}
			}
		}
		
		// q.peek() == N번째 큰수
		System.out.println(q.peek());
	}

}		
```

### 결과 1
```
메모리 : 294480KB	
시간 : 784ms
```

### 소스코드 2 그냥 정렬
```java
package week24.BOJ_2075_G5_N번째큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		
		Collections.sort(list, Collections.reverseOrder());
		
		System.out.println(list.get(N-1));
	}
}		
```

### 결과 2
```
메모리 : 338856KB	
시간 : 1776ms
```
