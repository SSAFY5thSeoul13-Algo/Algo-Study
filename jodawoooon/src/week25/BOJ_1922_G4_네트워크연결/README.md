## BOJ 1922 G4 네트워크연결
- MST (Kruskal)
- G4



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1922

도현이는 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하려 한다. 하지만 아쉽게도 허브가 있지 않아 컴퓨터와 컴퓨터를 직접 연결하여야 한다. 그런데 모두가 자료를 공유하기 위해서는 모든 컴퓨터가 연결이 되어 있어야 한다. (a와 b가 연결이 되어 있다는 말은 a에서 b로의 경로가 존재한다는 것을 의미한다. a에서 b를 연결하는 선이 있고, b와 c를 연결하는 선이 있으면 a와 c는 연결이 되어 있다.)

그런데 이왕이면 컴퓨터를 연결하는 비용을 최소로 하여야 컴퓨터를 연결하는 비용 외에 다른 곳에 돈을 더 쓸 수 있을 것이다. 이제 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용을 출력하라. 모든 컴퓨터를 연결할 수 없는 경우는 없다.
<br>

#### ✔ 입력
첫째 줄에 컴퓨터의 수 N (1 ≤ N ≤ 1000)가 주어진다.

둘째 줄에는 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)가 주어진다.

셋째 줄부터 M+2번째 줄까지 총 M개의 줄에 각 컴퓨터를 연결하는데 드는 비용이 주어진다. 이 비용의 정보는 세 개의 정수로 주어지는데, 만약에 a b c 가 주어져 있다고 하면 a컴퓨터와 b컴퓨터를 연결하는데 비용이 c (1 ≤ c ≤ 10,000) 만큼 든다는 것을 의미한다. a와 b는 같을 수도 있다.
<br>

#### ✔ 출력
모든 컴퓨터를 연결하는데 필요한 최소비용을 첫째 줄에 출력한다.
<br>


<br>

###  💡 풀이

모든 행성을 연결했을 때 최소 비용 -> MST

크루스칼 알고리즘으로 풀었다.

N개의 컴퓨터가 있을 때 최소비용으로 모든 컴퓨터를 연결했다.

 
<br><br>

###  💬 소스코드

```java
package week25.BOJ_1922_G4_네트워크연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1922 네트워크연결
 * @Author : Daun JO
 * @Date : 2021. 9. 6. 
 * @Algorithm : MST (Kruskal)
 *
 */

public class Main_BOJ_1922_G4_네트워크연결 {
	
	static int N, M, parents[], ans;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static class Edge implements Comparable<Edge>{
		
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //컴퓨터의 수
		M = Integer.parseInt(br.readLine()); //간선의 수
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(from, to, cost));
			
		}
		
		parents = new int[N+1];
	
		make();
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.from, edge.to)) {
				ans += edge.cost;
				cnt++;
				
				if(cnt==N-1) break;
			}
		}
		
		System.out.println(ans);
		
	}
	private static boolean union(int from, int to) {
		int fRoot = findSet(from);
		int tRoot = findSet(to);
		
		if(fRoot==tRoot) return false;
		
		parents[tRoot] = fRoot;
		return true;
	}
	private static int findSet(int v) {
		if(parents[v]==v) return v;
		return parents[v] = findSet(parents[v]);
	}
	private static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

}

```
<br><br>


###  💯 채점 결과
	45324	396