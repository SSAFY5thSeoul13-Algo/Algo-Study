package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_12763_지각하면안돼 {

/*	PriorityQueue를 이용한 다익스트라 알고리즘으로 풀었습니다.


	주어진 정점의 인접 노드와 시간, 비용을 담을 인접 리스트를 구현하였고,
	각 Node별 cost를 담을 수 있는 배열 cost[]를 사용했습니다.

	cost배열을 Max값으로 초기화해준뒤 시작점을 pq에 담았습니다


	pq가 빌 때까지, 인접리스트를 이용해 poll한 from노드 중 연결된 노드들을 찾고
	T분이내, M원 이하라는 문제의 조건을 만족하지 않으면  continue해주었습니다

	그렇지않고, 새로운 최솟값을 찾아 cost를 갱신할 수 있다면 cost 배열을 갱신하고
	pq에 노드를 add했습니다.

	cost[N] 값이 바로 T분이내로 M원이하의 비용으로 N정점까지 갈 수 있는 최소비용이므로
	이 값을 출력했습니다.



	- 막혔던부분
	pq를 이용한 다익스트라 풀이법이 낯설어서 많이 해맸습니다.
	처음에는 최솟값을 갱신할 수 없다면 pq에 넣지 않았었는데 이럴경우 모든 경우의 수를 따질 수 없다는 문제가 생겼습니다.

	그리고 처음에는 정점간 이동하는 시간과 비용을 다루기 위해
	 time배열, cost배열 하나씩 1차원 배열 2개를 사용하다가,  
	뭔가 잘 해결되지 않아서
	time과 cost를 같이 담을 수 있는 2차원 배열을 사용해 time별로 cost값을 관리했었는데, 생각해보니 어차피 T분이하라면 5분이던 T-1분이던 상관없기 때문에 cost값들은 1차원 배열 하나로 관리할 수 있을 것 같아서 그렇게 바꾸었더니 시간과 메모리를 절약할 수 있었습니다.



	- 결과
	메모리 : 59036	시간 : 752*/
	
	static class Node {
		int cost;
		int time;
		int v;
		
		public Node(int v, int time, int cost) {

			this.v = v;
			this.time = time;
			this.cost = cost;
		}
		
	
	}

	static int N, T, M, L, ans;
	static ArrayList<Node>[] adjList;
	static int cost[];
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		N = Integer.parseInt(br.readLine()); // 정점의 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(br.readLine());
		
		visit = new boolean[N+1];
		
		//인접리스트
		adjList = new ArrayList[N+1];
		for (int i = 1; i <=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//최저 cost, time을 저장할 배열
		//시간대 별로 cost를 저장한다 => 이 때 시간은 T이하이다
		//N번 정점 cost중에서 여러개의 t중 가장 적은 값을 구하면 된다.
		
		cost = new int[N+1];

		
		//입력값 처리
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adjList[x].add(new Node(y, t,c)); //양방향 연결
			adjList[y].add(new Node(x, t,c));
		}
		

		
		
		for (int j = 2; j <= N; j++) {
			cost[j] = Integer.MAX_VALUE;  //max 값으로 초기화
		}
		
		cost[1] = 0; //출발점은 0


		
		//PQ 선언
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.time!=o2.time) {
					return o1.cost - o2.cost;
				}else
					return o1.time - o2.time;
			}
			
		});
		pq.add(new Node(1,0,0));
		
		
		
		
		
		//PQ를 이용한 다익스트라
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int from = n.v;
			int fromCost = n.cost;
			int fromTime = n.time;
			
			if(fromCost>M ||fromTime>T) continue; //조건을 만족하지 못하면 continue
			
			
			
			for (Node node : adjList[from]) {
				//from 정점에서 인접한 노드들을 탐색한다
				
				int to = node.v;
				
				int toTime = node.time;
				int toCost = node.cost;
				
				
				if(toTime + fromTime > T || toCost + fromCost > M ) continue;
				//조건을 만족한다면
				
				//다음 노드(to 노드) 의 time은  fromTime + toTime이고
				//다음 노드의 비용도 fromCost + toCost 이다
				//따라서 toTime변수와 toCost 변수를 갱신한다.
				toTime += fromTime;
				toCost += fromCost;
				
				//cost배열에 값을 min값으로 갱신
				cost[to] = Math.min(toCost, cost[to]);
				//pq에 넣어준다.
				pq.add(new Node(to, toTime, toCost));

			}
			
			
		}
		
		
		//N번노드의 값을 출력하면 된다.
		//이때 T분 이하로 들어올수 있는 경우 중 가장 cost가 낮은 값을출력하면 된다.
	
		ans = cost[N];
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);

		
	}
}