## BOJ 2075 G5 N번째 큰 수 
- 슬라이딩 윈도우
- Gold5



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2075

N×N의 표에 수 N2개 채워져 있다. 채워진 수에는 한 가지 특징이 있는데, 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것이다. N=5일 때의 예를 보자.
![](https://images.velog.io/images/jodawooooon/post/84763ccb-6596-497f-8997-ca142ef3ebab/image.png)
이러한 표가 주어졌을 때, N번째 큰 수를 찾는 프로그램을 작성하시오. 표에 채워진 수는 모두 다르다.
<br>

#### ✔ 입력
첫째 줄에 N(1 ≤ N ≤ 1,500)이 주어진다. 다음 N개의 줄에는 각 줄마다 N개의 수가 주어진다. 표에 적힌 수는 -10억보다 크거나 같고, 10억보다 작거나 같은 정수이다.
<br>

#### ✔ 출력
첫째 줄에 N번째 큰 수를 출력한다.
<br>


<br>

###  💡 풀이

우선순위큐를 활용한 슬라이딩 알고리즘으로 풀었다.

우선순위 큐에 입력 데이터를 넣고 Queue.size()가 N이 되면, 새로 들어온 값과 Queue.peek()을 비교하여, 새로운 값이 더 클 경우 데이터를 교체한다.

이로써 구간 N만 탐색하게 된다. (슬라이딩 윈도우)

슬라이딩 윈도우는 size가 불변인게 포인트..


<br><br>

###  💬 소스코드


```java
package week24.BOJ_2075_G5_N번째큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 2075 N번째 큰 수
 * @Author : Daun JO
 * @Date : 2021. 8. 27. 
 * @Algorithm : Sliding Window
 *
 */
public class Main_BOJ_2075_G5_N번째큰수 {
	
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args)  throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				
				if(pq.size()<N){
					pq.add(input);
				}else {
					if(pq.peek()<input) {
						pq.poll();
						pq.add(input);
					}
				}
			}
		}
		
		
		System.out.println(pq.peek());
	}
	
}

```

<br><br>


###  💯 채점 결과
메모리 296552	시간 812
		