## BOJ 1647 도시 분할 계획 
- Kruskal
- 🥇 Gold4
- 🔗[도시 분할 계획 문제 바로가기](https://www.acmicpc.net/problem/1647)



## 풀이

이 문제는 기본 크루스칼 알고리즘을 적용하였습니다.
크루스칼 알고리즘 이용시 모든 마을이 다 연결되게 되므로 
도시를 2개로 분할하기 위해서는 N-2개의 간선이 선택되면 for문을 break하면 됩니다.

~~~java
				if(++count == N-2)	break;
~~~

근데 메모리와 시간 무슨일...?!?! 
 


## 소스코드

~~~java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1647_G4_도시_분할_계획 {
	static int N,M;
	static int[] parents;
	static Edge[] EdgeList;
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
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		EdgeList = new Edge[M];
		
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			EdgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(EdgeList);

		int result = 0;
		int count = 0;
		
		for (Edge e : EdgeList) {
			if(union(e.from,e.to)) {
				result += e.weight;
				// 도시를 분할하므로 N-2만큼 간선이 count되면 break
				if(++count == N-2)	break;
			}
		}
		
		System.out.println(result);
		

	}

}

~~~

## 결과 

| 메모리  | 시간 |
|----|----|
| 330144kb| 2748ms|
