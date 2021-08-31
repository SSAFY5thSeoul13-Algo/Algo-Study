## BOJ 1197 G4 최소 스패닝 트리
- MST (Kruskal, Prim)
- Gold4



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1197

그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.

최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
<br>

#### ✔ 입력
첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.

그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다. 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.
<br>

#### ✔ 출력
첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.
<br>


<br>

###  💡 풀이

MST를 구하는 문제였다.
이를 구하는 방법은 크게 2가지 프림, 크루스칼 알고리즘이 있다.

이미 풀어본 문제지만.. 몹쓸 기억력.. 가물가물해서 두 가지 방법으로 모두 풀어보았다.


#### ✅ 크루스칼 알고리즘
간선의 가중치의 합이 최솟값이 되도록 모든 노드를 연결

1. 간선 데이터를 오름차순 정렬
2. 간선을 확인하며 현 간선이 사이클을 발생시키는지 확인
	- 발생시키지 않으면 MST에 포함
3. 모든 간선에 대해 반복한다.

<br>

#### ✅ 프림 알고리즘
시작 정점을 기준으로 가장 작은 간선과 연결된 정점을 선택하여 모든 노드를 연결

1. 임의의 간선 선택
2. 현 정점으로부터 가장 낮은 가중치를 갖는 정점 선택
3. 모든 간선에 대해 반복한다.


<br><br>

###  💬 소스코드

#### ✅ 크루스칼 알고리즘
```java
package week25.BOJ_1197_G4_최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1197 최소 스패닝 트리
 * @Author : Daun JO
 * @Date : 2021. 8. 31. 
 * @Algo : MST (크루스칼)
 *
 */
public class Main_BOJ_1197_G4_최소스패닝트리_크루스칼 {
	
	static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;
		
		

		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}



		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	static int V, E;
	static int[] parents;
	static ArrayList<Node> nodeList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		
		parents = new int[V+1];
		nodeList = new ArrayList<>();
		
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//from번 정점과 to번 정점이 가중치 cost인 간선으로 연결되어 있다
			nodeList.add(new Node(from,to, cost));
		}
		
		Collections.sort(nodeList);
		
		make();
		
		int sum = 0;
		int cnt = 0;
		
		for(Node n : nodeList) {
			if(union(n.from, n.to)){
				sum += n.cost;
				cnt++;
				
				if(cnt==E-1) break;
			}
		}
		
		System.out.println(sum);
	}
	
	private static boolean union(int from, int to) {
		
		int fromRoot = findSet(from);
		int toRoot = findSet(to);
		
		if(fromRoot==toRoot) return false;
		else parents[toRoot] = fromRoot;
		return true;
	}

	private static int findSet(int v) {
		
		if(parents[v]==v) return v;
		else return parents[v] = findSet(parents[v]);
	}

	private static void make() {
		for(int i = 1 ; i <= V ; i++) {
			parents[i] = i;
		}
	}
	

}

```

<br>

#### ✅ 프림 알고리즘
```java
package week25.BOJ_1197_G4_최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1197 최소 스패닝 트리
 * @Author : Daun JO
 * @Date : 2021. 8. 31. 
 * @Algo : MST (프림)
 *
 */
public class Main_BOJ_1197_G4_최소스패닝트리_프림 {
	
	static class Node implements Comparable<Node> {
		int to;
		int cost;


		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}



		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
	static int V, E, ans;
	static boolean[] visited;
	static ArrayList<Node>[] nodeList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		
		visited = new boolean[V+1];
		nodeList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			//from번 정점과 to번 정점이 가중치 cost인 간선으로 연결되어 있다
			nodeList[from].add(new Node(to, cost));
			nodeList[to].add(new Node(from, cost));
		}
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0));
		
		while(!pq.isEmpty()){
			Node n = pq.poll();
			int to = n.to;
			int cost = n.cost;
			
			if(visited[to]) continue;
			visited[to] = true;
			ans += cost;
			
			for(Node next : nodeList[to]) {
				if(!visited[next.to]) pq.add(next);
			}
		}
		
		System.out.println(ans);
	}
	


}

```
<br><br>


###  💯 채점 결과
크루스칼 - 메모리 48772	시간 	524
프림 - 메모리 54644	시간 	648