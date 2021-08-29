## BOJ 2075 G5 N번쨰 수
- 우선순위 큐
- gold4

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2075

N×N의 표에 수 N2개 채워져 있다. 채워진 수에는 한 가지 특징이 있는데, 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것이다. N=5일 때의 예를 보자.

12	|7	|9|	15|	5
--|--|--|--|--|--
13|	8|	11|	19|	6
--|--|--|--|--|--
21|	10|	26|	31|	16
--|--|--|--|--|--
48|	14|	28|	35|	25
--|--|--|--|--|--
52|	20|	32|	41|	49


이러한 표가 주어졌을 때, N번째 큰 수를 찾는 프로그램을 작성하시오. 표에 채워진 수는 모두 다르다


#### 입력
첫째 줄에 N(1 ≤ N ≤ 1,500)이 주어진다. 다음 N개의 줄에는 각 줄마다 N개의 수가 주어진다. 표에 적힌 수는 -10억보다 크거나 같고, 10억보다 작거나 같은 정수이다.

#### 출력
첫째 줄에 N번째 큰 수를 출력한다.

###  💡 풀이

<br>

내림 차순으로 정렬되는 `pq`에 입력되는 숫자들을 저장한다 

```java
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		//숫자들을 우선순위 큐에 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
```

`pq`에 저장된 숫자들에서 N-1번쨰 큰 수까지 제거 한 후에 N번째 큰 수를 출력한다

```java
		int idx = 0;
		
		//N번째 이전까지의 pq 값 제거 
		while(!pq.isEmpty()) {
			if(idx == N-1)
				break;
			
			pq.remove();
			
			idx++;
		}
		
		System.out.println(pq.poll());
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075_G5_N번째큰수 {
	static int N;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		//숫자들을 우선순위 큐에 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		
		int idx = 0;
		
		//N번째 이전까지의 pq 값 제거 
		while(!pq.isEmpty()) {
			if(idx == N-1)
				break;
			
			pq.remove();
			
			idx++;
		}
		
		System.out.println(pq.poll());

	}

}




```


<br>



메모리|시간
--|--
334428 KB|932 ms