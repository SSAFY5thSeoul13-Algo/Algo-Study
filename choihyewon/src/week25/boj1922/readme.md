## BOJ 1922 네트워크 연결 
- Kruskal
- 🥇 Gold4
- 🔗[네트워 연결 문제 바로가기](https://www.acmicpc.net/problem/1922)



## 풀이

이 문제는 모든 네트워크를 최소의 비용으로 연결하는 문제이기 때문에 크루스칼 알고리즘을 이용하여 풀었습니다. 

먼저 간선정보가 담긴 Edge를 만들어주었고 가중치는 오름차순으로 정렬되게 하였습니다.

~~~java
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from,int to,int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// 모든 간선들의 가중치를 오름차순으로 정렬 
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
	}
~~~

그리고 두 정점을 연결하고 가중치가 가장 낮은 간선부터 선택하면서 트리를 증가시켰습니다.

사이클이 발생하지 않는다면 continue, 사이클이 발생한다면 다음으로 가중치가 낮은 간선을 선택해주었습니다.

~~~java
		for (Edge e : edgeList) {
			// 사이클이 발생하지 않는다면 
			if(union(e.from,e.to)) {
				result += e.weight;
				if(++count == N-1) {
					break;
				}
			}
		}
~~~

이 for문은 선택된 간선의 개수가 N-1이 될때까지 반복해주었습니다.


## 소스코드
~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922_G4_네트워크_연결 {
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		public Edge(int from,int to,int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// 모든 간선들의 가중치를 오름차순으로 정렬 
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
		
	}
	static int N,M;
	static int parents[];
	static Edge[] edgeList;
	
	static int findSet(int a) {
		if(parents[a]==a) {
			return a;
		}
		
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		edgeList = new Edge[M];
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(a,b,c);
		}
		
		// 모든 간선들의 가중치를 오름차순으로 정렬.
		Arrays.sort(edgeList);
		
		// 자기자신을 부모로 갖는 set을 만든다.
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		int result = 0;
		int count = 0;
		
		for (Edge e : edgeList) {
			// 사이클이 발생하지 않는다면 
			if(union(e.from,e.to)) {
				result += e.weight;
				if(++count == N-1) {
					break;
				}
			}
		}
			
		System.out.println(result);


	}
	

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 46720kb| 588ms|