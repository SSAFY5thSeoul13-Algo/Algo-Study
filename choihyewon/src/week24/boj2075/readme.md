## BOJ 2075 N번째 큰 수 
- PriorityQueue, SlidingWindow
- 🥇 Gold4
- 🔗[N번째 큰 수 문제 바로가기](https://www.acmicpc.net/problem/2075)



## 풀이

슬라이딩 윈도우를 이용해서 문제를 풀고싶어서 다시 풀었는데, 우선순위 큐를 사용하여 초기에는 값을 넣어주고 큐는 N크기만큼 길이를 고정합니다. N번째 큰수를 구하는 것이기 때문에 큐의 상단에 있는 값이 들어오는 숫자보다 작다면 그 값을 poll해주고 새로운 값을 넣어줍니다.

입력받은 숫자가 모두 들어갔다면 우선순위 큐에서 제일 상단에 있는 값을 출력해주면 N번째 큰수를 구할 수 있습니다.


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075_G5_N번째_큰_수 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 큐의 길이가 N과 같다면 
				if(pq.size()==N) {
					if(pq.peek()<num) {
						pq.poll();
						pq.add(num);
					}
				}else {
					pq.add(num);
				}
				
			}
		}
		
		System.out.println(pq.poll());

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
|293420kb| 888ms|