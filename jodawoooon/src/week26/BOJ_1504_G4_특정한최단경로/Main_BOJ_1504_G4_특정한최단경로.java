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
