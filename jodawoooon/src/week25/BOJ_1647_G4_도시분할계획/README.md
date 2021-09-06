## BOJ 1647 G4 도시분할계획 
- MST (Kruskal, Prim)
- Gold4



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1647

동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다. 그러다가 평화로운 마을에 가게 되었는데, 그곳에서는 알 수 없는 일이 벌어지고 있었다.

마을은 N개의 집과 그 집들을 연결하는 M개의 길로 이루어져 있다. 길은 어느 방향으로든지 다닐 수 있는 편리한 길이다. 그리고 각 길마다 길을 유지하는데 드는 유지비가 있다.

마을의 이장은 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다. 마을이 너무 커서 혼자서는 관리할 수 없기 때문이다. 마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다. 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻이다. 마을에는 집이 하나 이상 있어야 한다.

그렇게 마을의 이장은 계획을 세우다가 마을 안에 길이 너무 많다는 생각을 하게 되었다. 일단 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다. 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다. 마을의 이장은 위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을 최소로 하고 싶다. 이것을 구하는 프로그램을 작성하시오.
<br>

#### ✔ 입력
첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다. N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다. 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데 A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.
<br>

#### ✔ 출력
첫째 줄에 없애고 남은 길 유지비의 합의 최솟값을 출력한다.
<br>


<br>

###  💡 풀이

크루스칼 알고리즘으로 MST를 구현했다.  
이를 통해 N개의 집에서 최소비용으로 N-1개의 간선을 이용해 모든 집을 연결할 수 있다.  

이 간선 중에서 최대 가중치를 가진 간선을 구해 이를 연결하지 않는다면 (전체 edge의 가중치에서 빼면) 마을을 2개로 분리하고, 나머지 길의 유지비의 합을 구할 수 있다.  

<br><br>

###  💬 소스코드

```java
package week25.BOJ_1647_G4_도시분할계획;

import java.io.*;
import java.util.*;
/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1647 G4 도시분할계획
 * @Author : Daun JO
 * @Date : 2021. 9. 3. 
 *
 */
public class Main_BOJ_1647_G4_도시분할계획 {
	

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
	static int N, M, parents[], ans;
	static ArrayList<Edge> edgeList = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
	
		for(int i = 0; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			//A번 집과 B번 집을 연결하는 길의 유지비가 C
			
			edgeList.add(new Edge(A,B,C));
			
		}

		Collections.sort(edgeList);
		make();
		
		
		int max = 0;
		int cnt = 0;
		
		for(Edge edge : edgeList) {
			if(union(edge .from, edge .to)){
				
				max = Math.max(edge.cost, max); //가장 큰 가중치값 구하기
				
				ans += edge.cost;
				cnt++;
				
				if(cnt==N-1) break;
			}
		}
		
		ans -= max;
		System.out.println(ans);

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
		for(int i = 1 ; i <= N ; i++) {
			parents[i] = i;
		}
	}
}

```
<br><br>


###  💯 채점 결과
325028	1596