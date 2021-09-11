## Programmers 배달
- 다익스트라

<br>


### 🔍 문제 설명
https://programmers.co.kr/learn/courses/30/lessons/12978?language=java

마을의 개수 N, 각 마을을 연결하는 도로의 정보 road, 음식 배달이 가능한 시간 K가 매개변수로 주어질 때, 음식 주문을 받을 수 있는 마을의 개수를 return 하도록 solution 함수를 완성해주세요.


#### 제한사항
마을의 개수 N은 1 이상 50 이하의 자연수입니다.
road의 길이(도로 정보의 개수)는 1 이상 2,000 이하입니다.
road의 각 원소는 마을을 연결하고 있는 각 도로의 정보를 나타냅니다.
road는 길이가 3인 배열이며, 순서대로 (a, b, c)를 나타냅니다.
a, b(1 ≤ a, b ≤ N, a != b)는 도로가 연결하는 두 마을의 번호이며, c(1 ≤ c ≤ 10,000, c는 자연수)는 도로를 지나는데 걸리는 시간입니다.
두 마을 a, b를 연결하는 도로는 여러 개가 있을 수 있습니다.
한 도로의 정보가 여러 번 중복해서 주어지지 않습니다.
K는 음식 배달이 가능한 시간을 나타내며, 1 이상 500,000 이하입니다.
임의의 두 마을간에 항상 이동 가능한 경로가 존재합니다.
1번 마을에 있는 음식점이 K 이하의 시간에 배달이 가능한 마을의 개수를 return 하면 됩니다.

###  💡 풀이

1번 마을을 기준으로 다익스트라 알고리즘을 사용해서 풀었습니다

<br><br>

###  💡 소스코드
```java
import java.util.*;

public class Programmers_배달 {
    static List<List<Node>> list;
    static int[] distance;
    static final int INF = Integer.MAX_VALUE;
    
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
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        list = new ArrayList<>();
        
        for(int i =0 ; i<= N; i++){
            list.add(new ArrayList<Node>());
        }
        
        //경로 저장
        for(int[] edge : road){
            int idx1 = edge[0];
            int idx2 = edge[1];
            int dis = edge[2];
            
            list.get(idx1).add(new Node(idx2, dis));
            list.get(idx2).add(new Node(idx1, dis));
        }
        
        //1번 마을 기준 다익스트라
        distance = dijkstra(1, N);
        
        for(int i = 1; i <= N; i++){
            if(distance[i] <= K)    answer++;
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


#### 정확성  테스트

메모리|시간
--|--
테스트 1 	|통과 (0.59ms, 58.3MB)
테스트 2 	|통과 (0.66ms, 60.2MB)
테스트 3 	|통과 (0.58ms, 58.7MB)
테스트 4 	|통과 (0.85ms, 59.5MB)
테스트 5 	|통과 (0.61ms, 71.7MB)
테스트 6 	|통과 (0.51ms, 72.6MB)
테스트 7 	|통과 (0.81ms, 58.4MB)
테스트 8 	|통과 (0.76ms, 79.1MB)
테스트 9 	|통과 (0.87ms, 73.5MB)
테스트 10 	|통과 (0.53ms, 67.9MB)
테스트 11 	|통과 (0.84ms, 60.5MB)
테스트 12 	|통과 (0.79ms, 57.8MB)
테스트 13 	|통과 (0.67ms, 58.6MB)
테스트 14 	|통과 (2.95ms, 77.7MB)
테스트 15 	|통과 (2.31ms, 61.1MB)
테스트 16 	|통과 (0.71ms, 71.4MB)
테스트 17 	|통과 (0.91ms, 74.4MB)
테스트 18 	|통과 (1.82ms, 60.3MB)
테스트 19 	|통과 (2.36ms, 62.3MB)
테스트 20 	|통과 (12.41ms, 74.7MB)
테스트 21 	|통과 (3.14ms, 76MB)
테스트 22 	|통과 (2.28ms, 70.6MB)
테스트 23 	|통과 (3.01ms, 60.4MB)
테스트 24 	|통과 (3.86ms, 75.7MB)
테스트 25 	|통과 (2.76ms, 58.8MB)
테스트 26 	|통과 (3.45ms, 75.2MB)
테스트 27 	|통과 (4.51ms, 77.3MB)
테스트 28 |	통과 (9.67ms, 75.8MB)
테스트 29 	|통과 (2.70ms, 61.8MB)
테스트 30 	|통과 (3.07ms, 60.3MB)
테스트 31 	|통과 (0.62ms, 58.2MB)
테스트 32 |	통과 (1.03ms, 73.1MB)