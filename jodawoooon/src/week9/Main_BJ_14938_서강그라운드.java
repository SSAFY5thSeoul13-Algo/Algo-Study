package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_14938_서강그라운드 {
	
	/*
	 *  다익스트라 
	 *  
	 *  
	 *  pq를 이용한 다익스트라로 풀었습니다.
		for문으로 1~n을 예은이가 내릴 장소(시작점)으로 두었고,
		다익스트라로 다른 지점까지 수색범위(m)이하로 이동할 수 있는지 확인했습니다.
		그 결과 weight가 INF가 아니면 이동가능한 정점으로 고려하여
		해당 정점에서의 아이템수를 누적하여 max아이템수를 구했습니다


		- 결과
		메모리 11804	시간 96

	 * 
	 */
	//예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.
	static int n, m, r, arr[];
	static int INF = Integer.MAX_VALUE;
	static ArrayList<Node> adjList[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		n = Integer.parseInt(st.nextToken()); //지역의 개수
		m = Integer.parseInt(st.nextToken()); //수색범위
		r = Integer.parseInt(st.nextToken()); //길의 개수
		
		int ans = Integer.MIN_VALUE;

		adjList = new ArrayList[n+1]; //인접리스트
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList<>();
		}
		
	
		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {

			arr[i] = Integer.parseInt(st.nextToken()); //각 구역에 있는 아이템의 수
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			adjList[x].add(new Node(y,l)); //양방향연결
			adjList[y].add(new Node(x,l));
		}

	
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight-o2.weight;
			}
			
		});
		//PQ 선언

		
		int[] weight = new int[n+1];
		//가중치를 저장할 배열

		for (int i = 1; i <= n; i++) { //예은이의 착륙지점
			
			for (int k = 1; k <= n; k++) {
				weight[k] = INF;
			}
			//가중치 배열 초기화


			
			weight[i] = 0;
			pq.add(new Node(i,0));
			//시작점 설정

			while(!pq.isEmpty()) {
				Node n = pq.poll();
				int from = n.v;
				int fWeight = n.weight;
				
				if(fWeight > weight[from]) continue;
				
			
				
				for (Node node : adjList[from]) {
					int to = node.v;
					int tWeight = node.weight + fWeight;
					
					if(tWeight>m) continue; //수색범위를 넘어가면 continue
					
					weight[to] = Math.min(tWeight, weight[to]);
					
					pq.add(new Node(to, tWeight));
				}
			}
			
			int tmp = 0;
			for (int k = 1; k <= n; k++) {
				//System.out.println(weight[k]);
				if(weight[k]!=INF) { //이동가능한 정점이면
					tmp += arr[k]; //아이템수 누적
				}
			}
			
			ans = Math.max(tmp, ans); // max 아이템수
			
		}

		System.out.println(ans);

	}
	

	static class Node{
		int v, weight;

		public Node(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}
		
	}
}