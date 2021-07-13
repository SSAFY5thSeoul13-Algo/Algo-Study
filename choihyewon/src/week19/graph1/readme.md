## Programmers - 가장 먼 노드 
- bfs, Graph 
- Level3

[가장 먼 노드 문제링크](https://programmers.co.kr/learn/courses/30/lessons/49189)
## 풀이
먼저 인접리스트를 생성하여 연결된 노드를 넣어주었습니다.

~~~java
list = new ArrayList<ArrayList<Integer>>();
for(int i=0; i<=n; i++) {
	        	list.add(new ArrayList<Integer>());
	        }
	        
	        for(int i=0; i<edge.length; i++) {
	        	list.get(edge[i][0]).add(edge[i][1]);
	        	list.get(edge[i][1]).add(edge[i][0]);
	        }
~~~

그리고 bfs를 이용하여 1번 노드부터 차례로 탐색하여 방문하지 않은 노드를 순회하여 연결이 되어있다면 queue에 넣어주었습니다. 그리고 visited 배열을 int형으로 생성하여 1번 노드와 떨어진 거리를 배열에 갱신시켜주 최대 거리를 구하고, while문이 종료가 되면 최대 거리를 가진 노드의 값을 count하여 답을 구해주었습니다.

## 막힌점 
처음에 인접행렬로 문제를 풀었는데, 7,8번이 메모리 초과로 나왔습니다. 
이는 인접리스트를 사용하여 해결할 수 있었습니다.

## 소스코드
~~~java
import java.util.*;

public class 가장_먼_노드 {
    static ArrayList<ArrayList<Integer>> list;
    static int[] visited;
	class Solution {
	    public int solution(int n, int[][] edge) {
	        int answer = 0;
	        // 인접 리스트 
	        list = new ArrayList<ArrayList<Integer>>();
	        visited = new int[n+1];
	        
	        for(int i=0; i<=n; i++) {
	        	list.add(new ArrayList<Integer>());
	        }
	        
	        for(int i=0; i<edge.length; i++) {
	        	list.get(edge[i][0]).add(edge[i][1]);
	        	list.get(edge[i][1]).add(edge[i][0]);
	        }
	        
	        answer = bfs(n);
	        
	        return answer;
	    }

		private int bfs(int n) {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(1);
			visited[1] = 1;
			int max = 0;
			while(!queue.isEmpty()) {
				int num = queue.poll();
				for (Integer i : list.get(num)) {
					if(visited[i]==0) {
						visited[i] = visited[num]+1;
						queue.add(i);
						max = Math.max(max, visited[i]);
					}
				}

			}
			int cnt = 0;
			for(int i=1; i<=n; i++) {
				if(visited[i]==max) {
					cnt++;
				}
			}
			
			return cnt;
			
		}
	}

}

~~~

## 결과 

| 정확성  | 테스트 |
|----|----|
|테스트 1 |	통과 (0.23ms, 51.7MB)|
|테스트 2 |	통과 (0.25ms, 52.4MB)|
|테스트 3 |	통과 (0.36ms, 52.4MB)|
|테스트 4 |	통과 (1.18ms, 53MB)|
|테스트 5 |	통과 (5.72ms, 53.1MB)|
|테스트 6 |	통과 (9.46ms, 58.9MB)|
|테스트 7 |	통과 (36.00ms, 75.5MB)|
|테스트 8 |	통과 (63.61ms, 75.8MB)|
|테스트 9 |	통과 (61.06ms, 77.2MB)|
