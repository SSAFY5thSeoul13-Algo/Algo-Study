## Programmers 2021 KAKAO BLIND RECRUITMENT 합승 택시 요금

### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/72413

지점의 개수 n, 출발지점을 나타내는 s, A의 도착지점을 나타내는 a, B의 도착지점을 나타내는 b, 지점 사이의 예상 택시요금을 나타내는 fares가 매개변수로 주어집니다. 이때, A, B 두 사람이 s에서 출발해서 각각의 도착 지점까지 택시를 타고 간다고 가정할 때, 최저 예상 택시요금을 계산해서 return 하도록 solution 함수를 완성해 주세요.
만약, 아예 합승을 하지 않고 각자 이동하는 경우의 예상 택시요금이 더 낮다면, 합승을 하지 않아도 됩니다.


#### 제한사항
지점갯수 n은 3 이상 200 이하인 자연수입니다.
지점 s, a, b는 1 이상 n 이하인 자연수이며, 각기 서로 다른 값입니다.
즉, 출발지점, A의 도착지점, B의 도착지점은 서로 겹치지 않습니다.
fares는 2차원 정수 배열입니다.
fares 배열의 크기는 2 이상 n x (n-1) / 2 이하입니다.
예를들어, n = 6이라면 fares 배열의 크기는 2 이상 15 이하입니다. (6 x 5 / 2 = 15)
fares 배열의 각 행은 [c, d, f] 형태입니다.
c지점과 d지점 사이의 예상 택시요금이 f원이라는 뜻입니다.
지점 c, d는 1 이상 n 이하인 자연수이며, 각기 서로 다른 값입니다.
요금 f는 1 이상 100,000 이하인 자연수입니다.
fares 배열에 두 지점 간 예상 택시요금은 1개만 주어집니다. 즉, [c, d, f]가 있다면 [d, c, f]는 주어지지 않습니다.
출발지점 s에서 도착지점 a와 b로 가는 경로가 존재하는 경우만 입력으로 주어집니다.

###  💡 풀이

출발지와 A,B의 목적지 각각 다익스트라 알고리즘을 통해 각 지점에 대한 거리를 구한 후에 최소경로 값을 구하였다

양방향 경로 정보를 리스트에 저장한다

```java
		//경로를 리스트에 저장
		for (int[] fare : fares) {
			int idx1 = fare[0];
			int idx2 = fare[1];
			int dis = fare[2];

			list.get(idx1).add(new Node(idx2, dis));
			list.get(idx2).add(new Node(idx1, dis));
		}
```

세 지점에서 다익스트라 알고리즘을 통해서 각 지점까지의 거리를 구한다

```java
		disA = dijkstra(a, n);
		disB = dijkstra(b, n);
		disS = dijkstra(s, n);
```

가능 한 모든 경로중에서 가장 작은 값을 택시요금이 가장 적은 경우로 저장한다

```java
		for (int i = 1; i <= n; i++) {
			answer = Math.min(disS[i] + disA[i] + disB[i], answer);
		}
```



<br><br>

###  💡 소스코드
```java
import java.util.*;

public class Programmers_합승택시요금 {
	//연결되어 있는 경로 정보
	static List<List<Node>> list;
	//각 지점에서 나머지 까지의 거리
	static int[] disA, disB, disS;
	static final int INF = 200 * 100000 + 1;

	static class Node implements Comparable<Node> {
		int index;
		int dis;

		public Node(int index, int dis) {
			this.index = index;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dis, o.dis);
		}
	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;

		list = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<Node>());
		}

		//경로를 리스트에 저장
		for (int[] fare : fares) {
			int idx1 = fare[0];
			int idx2 = fare[1];
			int dis = fare[2];

			list.get(idx1).add(new Node(idx2, dis));
			list.get(idx2).add(new Node(idx1, dis));
		}

		disA = dijkstra(a, n);
		disB = dijkstra(b, n);
		disS = dijkstra(s, n);

		for (int i = 1; i <= n; i++) {
			answer = Math.min(disS[i] + disA[i] + disB[i], answer);
		}

		return answer;
	}

	static int[] dijkstra(int start, int n) {
		boolean[] isVisited = new boolean[n + 1];
		int[] distance = new int[n + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (isVisited[node.index])
				continue;

			isVisited[node.index] = true;

			for (Node nNode : list.get(node.index)) {
				if (!isVisited[nNode.index] && distance[nNode.index] >= nNode.dis + node.dis) {
					distance[nNode.index] = nNode.dis + node.dis;
					pq.offer(new Node(nNode.index, distance[nNode.index]));
				}
			}
		}

		return distance;
	}
}





```


<br>

#### 정확성

메모리|시간
--|--
테스트 1 |	통과 (0.56ms, 70.2MB)
테스트 2 |	통과 (0.51ms, 61.7MB)
테스트 3 |	통과 (0.63ms, 58.6MB)
테스트 4 |	통과 (0.96ms, 70.6MB)
테스트 5 |	통과 (0.63ms, 57.8MB)
테스트 6 |	통과 (0.67ms, 59.4MB)
테스트 7 |	통과 (0.74ms, 58.8MB)
테스트 8 |	통과 (0.96ms, 60MB)
테스트 9 	통과 (0.88ms, 69.6MB)
테스트 10 |	통과 (0.71ms, 68.7MB)



#### 효율성

메모리|시간
--|--
테스트 1	|통과 (5.08ms, 52.9MB)
테스트 2 	|통과 (11.05ms, 55.5MB)
테스트 3 	|통과 (6.29ms, 52.7MB)
테스트 4 	|통과 (4.86ms, 52.7MB)
테스트 5 	|통과 (5.08ms, 52.5MB)
테스트 6 	|통과 (5.58ms, 52.4MB)
테스트 7 	|통과 (32.23ms, 66MB)
테스트 8 	|통과 (23.98ms, 62.5MB)
테스트 9 	|통과 (28.31ms, 65.4MB)
테스트 10	|통과 (27.06ms, 66.5MB)
테스트 11	|통과 (26.77ms, 64.9MB)
테스트 12 	|통과 (21.56ms, 61.3MB)
테스트 13 	|통과 (16.45ms, 60.3MB)
테스트 14 	|통과 (20.44ms, 59.9MB)
테스트 15 	|통과 (21.53ms, 58MB)
테스트 16 	|통과 (3.32ms, 52MB)
테스트 17 	|통과 (5.12ms, 52MB)
테스트 18 	|통과 (4.74ms, 53.4MB)
테스트 19 	|통과 (7.77ms, 53.2MB)
테스트 20 	|통과 (9.73ms, 57.2MB)
테스트 21 	|통과 (9.72ms, 54MB)
테스트 22 |	통과 (20.86ms, 61MB)
테스트 23 	|통과 (26.14ms, 60.2MB)
테스트 24 	|통과 (29.01ms, 59.9MB)
테스트 25 	|통과 (3.34ms, 53.5MB)
테스트 26 	|통과 (3.36ms, 53MB)
테스트 27 	|통과 (10.40ms, 54.6MB)
테스트 28 	|통과 (8.57ms, 55.5MB)
테스트 29 |	통과 (3.10ms, 53.3MB)
테스트 30 |	통과 (3.15ms, 52.4MB)