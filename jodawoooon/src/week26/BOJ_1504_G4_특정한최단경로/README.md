## BOJ 1504 G4 특정한 최단경로
- 다익스트라
- gold4



<br><br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1504

방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.


<br>

#### ✔ 입력
첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1)
<br>

#### ✔ 출력
첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.
<br>


<br>

###  💡 풀이

정점 V1과 V2를 꼭 포함하여 1에서 N까지 이동할 때의 최단경로 길이를 찾는 문제이다.

두 가지 경우의 수로 나누어서 생각할 수 있다.

방법 1. `1->v1->v2->N`
방법 2. `1->v2->v1->N`

다익스트라를 이용하여 각 정점 사이의 최단 경로를 찾은 후 더했다.
그리고 방법 1과 방법 2중 min값을 찾아 출력했다.

이 때, 답이 INF이상 이라면 1->N까지의 이동이 불가능 한 것이므로 -1을 출력하면 된다.

 
<br><br>

###  💬 소스코드

```java
package week26.BOJ_1504_G4_특정한최단경로;

import java.io.*;
import java.util.*;


/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1502 특정한 최단경로
 * @Author : Daun JO
 * @Date : 2021. 9. 14. 
 * @Algorithm : 다익스트라
 *
 */
public class Main_BOJ_1504_G4_특정한최단경로 {
	
	/*
	 * 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성
	 */

	static int N, E, v1, v2, dis[];
	static int INF = 200000000;
	static ArrayList<Edge>[] edgeList;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //정점의 개수
		E = Integer.parseInt(st.nextToken()); //간선의 개수
		
		
		edgeList = new ArrayList[N+1];
		for(int i = 1 ; i <= N ; i++) {
			edgeList[i] = new ArrayList<>();
		}
		
		for(int i = 0 ;i<E ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			

			edgeList[a].add(new Edge(b,c));
			edgeList[b].add(new Edge(a,c));
			/* a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻 */
			
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		/* 반드시 거쳐야 하는 두 개의 서로 다른 정점  */
		
		
		
		/* 이동 방법은 두가지이다.
		 * 1. 1->v1->v2->N
		 * 2. 1->v2->v1->N
		 * 
		 * 둘중 최단 경로 구하기
		 */
		
		dis = new int[N+1];
		int len1 = getMinLength(1,v1) + getMinLength(v1,v2) + getMinLength(v2,N);
		int len2 = getMinLength(1,v2) + getMinLength(v2,v1) + getMinLength(v1,N);
		
		int ans = Math.min(len1,len2);
		System.out.println(ans>=INF?-1:ans);
	
	}
	
	private static int getMinLength(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			dis[i] = INF;
		}

		dis[start] = 0;
		pq.add(new Edge(start, 0)); //시작점
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int curV = cur.v;
			int curDis = cur.dis;

			if(curDis > dis[curV]) continue;
			
			for(Edge next : edgeList[curV]) {
				int nextV = next.v;
				int nextDis = dis[curV] + next.dis;
				
				if(dis[nextV] > nextDis) { //최단경로 갱신
					dis[nextV] = nextDis;
					pq.add(new Edge(nextV, nextDis));
				}
			}
		}
		
		return dis[end]; // from->to 최단경로의 길이
	}

	static class Edge implements Comparable<Edge> {
		int v,dis;

		public Edge(int v, int dis) {
			super();
			this.v = v;
			this.dis = dis;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.dis - o.dis;
		}
		
	}
}


```
<br><br>


###  💯 채점 결과
67272	712