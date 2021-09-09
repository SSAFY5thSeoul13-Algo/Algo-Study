## BOJ 16398 G4 행성연결
- MST (Kruskal)
- G4



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/16398

홍익 제국의 중심은 행성 T이다. 제국의 황제 윤석이는 행성 T에서 제국을 효과적으로 통치하기 위해서, N개의 행성 간에 플로우를 설치하려고 한다.

두 행성 간에 플로우를 설치하면 제국의 함선과 무역선들은 한 행성에서 다른 행성으로 무시할 수 있을 만큼 짧은 시간만에 이동할 수 있다. 하지만, 치안을 유지하기 위해서 플로우 내에 제국군을 주둔시켜야 한다.

모든 행성 간에 플로우를 설치하고 플로우 내에 제국군을 주둔하면, 제국의 제정이 악화되기 때문에 황제 윤석이는 제국의 모든 행성을 연결하면서 플로우 관리 비용을 최소한으로 하려 한다.

N개의 행성은 정수 1,…,N으로 표시하고, 행성 i와 행성 j사이의 플로우 관리비용은 Cij이며, i = j인 경우 항상 0이다.

제국의 참모인 당신은 제국의 황제 윤석이를 도와 제국 내 모든 행성을 연결하고, 그 유지비용을 최소화하자.  이때 플로우의 설치비용은 무시하기로 한다.
<br>

#### ✔ 입력
입력으로 첫 줄에 행성의 수 N (1 ≤ N ≤ 1000)이 주어진다.

두 번째 줄부터 N+1줄까지 각 행성간의 플로우 관리 비용이 N x N 행렬 (Cij),  (1 ≤ i, j ≤ N, 1 ≤ Cij ≤ 100,000,000, Cij = Cji) 로 주어진다.
<br>

#### ✔ 출력
모든 행성을 연결했을 때, 최소 플로우의 관리비용을 출력한다.
<br>


<br>

###  💡 풀이

모든 행성을 연결했을 때 최소 비용 -> MST

크루스칼 알고리즘으로 풀었다.

N개의 행성이 있을 때 최소비용으로 N-1개의 간선을 이용해 모든 행성을 연결했다.

 
<br><br>

###  💬 소스코드

```java
package week25.BOJ_16398_G4_행성연결;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 16398 행성연결
 * @Author : Daun JO
 * @Date : 2021. 9. 6. 
 * @Algorithm : MST (Kruskal)
 *
 */
public class Main_BOJ_16398_G4_행성연결 {
	
	static int N, parents[];
	static long ans;
	static ArrayList<Edge> edgeList = new ArrayList<>();
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
		
		N = Integer.parseInt(br.readLine()); //행성의 수

		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {

				int cost = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(i,j, cost));
			}
		}
		
		Collections.sort(edgeList);
		
		make();
		
		int cnt = 0;
		
		for(Edge edge : edgeList) {
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
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

}


```
<br><br>


###  💯 채점 결과
174644	1240