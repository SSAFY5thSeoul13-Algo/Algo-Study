## BOJ 1504 특정한 최단경로 
- Dijkstra
- 🥇 Gold4
- 🔗[특정한 최단 경로 문제 바로가기](https://www.acmicpc.net/problem/1504)



## 풀이

어느 한점에서 다른 정점으로 최단 경로를 구하는 문제이기 때문에 다익스트라 알고리즘을 사용하였습니다. 
다익스트라 알고리즘을 사용하기 위해 dist 배열은 (거리 값이 들어있는 배열) 나올 수 있는 가장 큰 값으로 초기화 해줍니다.

~~~java
		Arrays.fill(dist, INF);
~~~

그리고 문제는 1번 정점이 시작점이기 때문에 1번노드와 거리 값 0 (자기자신까지 거리는 0이므로)을 우선순위 큐에 넣어줍니다. dist 배열도 0으로 넣어줍니다.

~~~java
		pq.add(new Node(start,0));
		dist[start] = 0;
~~~

그리고 시작노드에 해당하는 boolean 타입의 visited 값을 true로 바꾸어주고 방문하지 않은 노드 중 최소값을 찾아 dist 값을 갱신해줍니다.

~~~java
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			if(visited[cur])	continue;
			
			visited[cur] = true;
			
			for (Node node : list[cur]) {
				if(dist[node.end] > dist[cur] + node.dis) {
					dist[node.end] = dist[cur] + node.dis;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
		}
~~~

각각 1번 정점, v1, v2, N번 정점으로부터 각 정점까지의 최단거리를 dist에 저장해주어 
1->v1->v2->N 순으로 지나는 경우와 1->v2->v1->N 순으로 지나는 거리를 비교하여 더 작은 값을 출력합니다.


## 막힌점

1->v1->v2->N 순으로 지나는 것만 생각했습니다.



## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_G4_특정한_최단경로 {
	static int N,E;
	static final int INF = 100_000_000;
	static List<Node>[] list;
	static int[] dist;
	static class Node implements Comparable<Node>{
		int end;
		int dis;
		
		public Node(int end, int dis) {
			this.end = end;
			this.dis = dis;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.dis - o.dis;
		}
		
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		dist = new int[N+1];

		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 양방향 이므로 각각 넣어준다.
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int result1 = 0;
		int result2 = 0;
		// 1, v1, v2, N 노드 순서로 이동해야함 
		dijkstra(1);
		result1 += dist[v1];
		dijkstra(v1);
		result1 += dist[v2];
		dijkstra(v2);
		result1 += dist[N];
		
		// 1, v2, v1, N 노드 순서로 이동해야함 
		dijkstra(1);
		result2 += dist[v2];
		dijkstra(v2);
		result2 += dist[v1];
		dijkstra(v1);
		result2 += dist[N];
		
		if(result1 >= INF && result2 >= INF) {
			System.out.println("-1");
		}else {
			System.out.println(Math.min(result1, result2));
		}


	}
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[N+1];
		pq.add(new Node(start,0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			if(visited[cur])	continue;
			
			visited[cur] = true;
			
			for (Node node : list[cur]) {
				if(dist[node.end] > dist[cur] + node.dis) {
					dist[node.end] = dist[cur] + node.dis;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
		}
		
		
	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 71272kb| 724ms|