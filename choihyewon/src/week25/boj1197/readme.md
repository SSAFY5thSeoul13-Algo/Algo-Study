## BOJ 1197 최소 스패닝 트리 
- Prim, Kruskal
- 🥇 Gold4
- 🔗[최소 스패닝 트리 문제 바로가기](https://www.acmicpc.net/problem/1197)



## 풀이

모든 정점을 연결하는 간선들의 가중치 합이 최소가 되는 트리를 최소 스패닝 트리, MST 라고 한다.
주로 MST 알고리즘에는 크루스칼과 프림 알고리즘이 있다.
딱 MST 기본 문제 같아서 두 알고리즘을 각각 이용해서 풀어보았다.

크루스칼 알고리즘을 사용한 경우 


~~~java
		Arrays.sort(EdgeList);
~~~

모든 간선을 가중치에 따라 오름차순으로 정렬을 해주었다.


~~~java
		for (Edge e : EdgeList) {
			// 싸이클이 발생한다면 
			if(union(e.from,e.to)) {
				result += e.weight;
				if(++count==E-1) {
					break;
				}
			}
		}
~~~

그리고 union을 이용하여 두 정점을 연결해주고, 싸이클이 존재한다면 다음으로 가중치가 낮은 간선을 선택하게 하였고 N-1개의 간선이 선택되면 break.


프림 알고리즘을 사용한 경우에는 

~~~java
		pq.add(new Node(1,0));
~~~

임의의 정점을 하나 선택해서 시작하는데 1을 우선순위 큐에 넣어주었다.

~~~java
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.vertex])	continue;
			
			visited[cur.vertex] = true;
			
			answer += cur.weight;
			
			for (Node node : arr[cur.vertex]) {
				if(!visited[node.vertex]) {
					pq.add(node);
				}
			}
			// 모든 정점이 선택되었다면 break
			if(++count == V)	break;
		}
~~~

그리고 우선순위 큐에서 poll한 node의 정점의 인접하는 정점들 중 방문하지 않은 node를 탐색하고 모든 정점이 선택되면 break.


이 문제에서는 프림 알고리즘 보다 크루스칼 알고리즘이 더 빠르다는 것을 알 수 있다!



## 소스코드

### kruskal 알고리즘을 사용한 코드 

~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197_G4_최소_스패닝_트리_Kruskal {
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
	}
	static int find(int a) {
		if(parents[a]==a) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a,int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	static int V,E;
	static int[] parents;
	static Edge[] EdgeList;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		EdgeList = new Edge[E];
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			EdgeList[i] = new Edge(from,to,weight);
		}
		
		Arrays.sort(EdgeList);
		
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
		
		int result = 0;
		int count = 0;
		
		for (Edge e : EdgeList) {
			// 싸이클이 발생한다면 
			if(union(e.from,e.to)) {
				result += e.weight;
				if(++count==E-1) {
					break;
				}
			}
		}
		
		System.out.println(result);

	}

}

~~~


### prim 알고리즘을 사용한 코드 

~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197_G4_최소_스패닝_트리_Prim {
	static int V;
	static int E;
	static boolean[] visited;
	static ArrayList<Node>[] arr;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		public Node(int vertex,int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		visited = new boolean[V+1];
		arr = new ArrayList[V+1];
		
		for(int i=1; i<=V; i++) {
			arr[i] = new ArrayList<>();
		}

		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			arr[from].add(new Node(to,weight));
			arr[to].add(new Node(from,weight));
		}
		
		int answer = 0;
		int count = 0;
		
		// 1번 노드 부터 탐색 시작 
		pq.add(new Node(1,0));
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(visited[cur.vertex])	continue;
			
			visited[cur.vertex] = true;
			
			answer += cur.weight;
			
			for (Node node : arr[cur.vertex]) {
				if(!visited[node.vertex]) {
					pq.add(node);
				}
			}
			// 모든 정점이 선택되었다면 break
			if(++count == V)	break;
		}
		
		System.out.println(answer);

	}

}

~~~


## 결과 

### kruskal 알고리즘 사용시 

| 메모리  | 시간 |
|----|----|
|48488KB|592ms|

### prim 알고리즘 사용시 

| 메모리  | 시간 |
|----|----|
| 54680kb| 616ms|