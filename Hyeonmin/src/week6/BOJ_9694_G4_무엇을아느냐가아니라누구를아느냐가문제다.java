package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_9694_G4_무엇을아느냐가아니라누구를아느냐가문제다 {
	static int T, N, M;
	//각 경로들을 저장할 자료구조
	static List<List<Node>> list = new ArrayList<List<Node>>();
	//최소 비용을 저장할 배열
	static int[] cost;
	//경로를 저장하기 위한 배열
	static int[] root;
	
	static class Node implements Comparable<Node>{
		int to;
		int cost;
		
		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		//테스트 케이스만큼 반복
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//배열과 리스트 초기화
			list.clear();
			cost = new int[M];
			root = new int[M];
			
			//처음 시작 루트는 자기 자신
			for (int i = 0; i < M; i++) {
				root[i] = i;
			}
			
			//비용을 가장 큰 값으로 채움
			Arrays.fill(cost, Integer.MAX_VALUE);
			
			for (int i = 0; i < M; i++) {
				list.add(new ArrayList<Node>());
			}
			
			//친밀도를 읽어서 저장
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				list.get(from).add(new Node(to, cost));
				list.get(to).add(new Node(from, cost));
			}
			
			//최소 비용 경로 찾기
			dijkstra();
			
			sb.append("Case #"+t+":");
			
			//최고의원과 연락하지 못한 경우
			if(cost[M-1] == Integer.MAX_VALUE) {
				sb.append(" ").append(-1).append("\n");
				continue;
			}
			
			
			//최고 위원과 연락을 한 경우
			int idx = M-1;
			Stack<Integer> stack = new Stack<>();
			
			//최고 위원에서 부터 직전 연락한 사람을 찾아가면서 0까지 온다
			while(true) {
				//연락을 한 위원을 스택에 넣음
				stack.push(idx);
				idx = root[idx];
				
				//한신이 까지 역으로 돌아왔으면 스택에 넣고 종료
				if(idx == 0) {
					stack.push(idx);
					break;
				}
			}
			
			//스택에 넣은 순서를 차례로 꺼내서 문자열로 저장
			while(!stack.isEmpty()) {
				sb.append(" ").append(stack.pop());
			}
			sb.append("\n");
			
			
			/*
			 * 이걸로 하면 왜 안될까요.....
			 
			StringBuilder temp = new StringBuilder();
			temp.append(M-1).append(" ");
			while(true) {
				temp.append(idx).append(" ");
				
				if(idx == 0) {
					break;
				}
				
				idx = root[idx];
			}
			sb.append(temp.reverse().toString()).append("\n");
 * 
 */
			
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
	
	//최소 비용 거리 찾기
	static void dijkstra() {		
		boolean[] visited = new boolean[M];
		
		//시작은 0번으로 고정
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(0, 0));
		cost[0] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(visited[node.to])
				continue;
			
			visited[node.to] = true;
			
			//최고 위원에게 연락이 갔으면 종료
			if(node.to == M-1)
				break;
			
			//현재 위원이 연락할 수 있는 모든 위원
			for (Node n : list.get(node.to)) {
				//현재 저장된 최소 비용보다 작은 경우
				if(cost[n.to] >= n.cost + cost[node.to]) {
					//비용 갱신
					cost[n.to] = n.cost + cost[node.to];
					//최소 비용으로 연락이 온 위원의 번호를 저장
					root[n.to] = node.to;
					pq.offer(new Node(n.to, cost[n.to]));
				}
			}
		}
	}
}
