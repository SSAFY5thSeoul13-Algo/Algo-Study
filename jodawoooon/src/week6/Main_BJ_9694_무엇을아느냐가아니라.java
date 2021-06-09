package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main_BJ_9694_무엇을아느냐가아니라 {
	/*
	 *  PriorityQueue를 이용한 다익스트라 알고리즘으로 풀었습니다.

		최소비용으로 M-1정점까지 가는 것은 일반적인 우선순위큐 다익스트라로 구현할 수 있었고 고민했던 부분은 방문 정점들을 출력하는 부분이었습니다.

		최고의원(M-1정점)까지 가기 위해 방문했던 정점들을 출력하기위해
		meet라는 Integer형 ArrayList 1차원 배열을 만들었습니다
		새로운 노드로 이동할때 그동안 방문했던 meet 리스트를 가져와서 
		새로운 노드의 meet리스트에 add해서 옮겨주는 방식으로 구현했습니다.


		- 막혔던부분
		인접리스트를 양방향으로 연결하지 않아서...ㅠ 계속 틀렸습니다가 나왔었습니다ㅠ

		- 결과
		메모리 60092	시간 584
	 */
	
	static class Node{
		int v;
		int weight;
		public Node(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}
		
		
	}
	
	static int T, N, M;
	static ArrayList<Node> adjList[];
	static ArrayList<Integer>[] meet;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //관계의 개수
			M = Integer.parseInt(st.nextToken()); //정치인의 수
			
			meet = new ArrayList[M]; //meet리스트 : 그동안 거쳐온 노드 저장할 리스트
			for (int i = 0; i < M; i++) {
				meet[i] = new ArrayList<>();
			}
			
			adjList = new ArrayList[M]; //인접리스트
			for (int i = 0; i < M; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			//0번 : 한신이, M-1번 : 최고의원
			//최고의원을 만나기까지의 인맥간 친밀도의 합을 최소화하고 싶어한다.
			//다익스트라
			
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken()); //친밀도
				adjList[x].add(new Node(y,z)); //양방향 연결
				adjList[y].add(new Node(x,z));
			}
			
			
			//가중치를 저장할 배열
			int[] weight = new int[M];
			for (int i = 1; i < M; i++) {
				weight[i] = Integer.MAX_VALUE;
			}
			weight[0] = 0;
			
			
			
			//PQ 선언
			PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					return o1.weight-o2.weight;
				}
				
			});
			
			pq.add(new Node(0,0));

			//PQ를 이용한 다익스트라
			while(!pq.isEmpty()) {
				Node n = pq.poll();
				int from = n.v;
				int fWeight = n.weight;
				
				
				if(fWeight > weight[from]) continue;
				
				for (Node node : adjList[from]) {
					//from 정점에서 인접한 노드들을 탐색한다
					
					int to = node.v;
					int tWeight = node.weight + fWeight;

					if(weight[to] > tWeight) {
						meet[to].clear(); //meet리스트 clear하고
						int tmpIdx = from; 
						
						//이전의 노드가 그동안 방문했던 노드 리스트를 다 가져와서 현재 노드의 meet리스트에 옮긴다
						for (int i = 0; i < meet[tmpIdx].size(); i++) {
							int tmp = meet[tmpIdx].get(i);
							meet[to].add(tmp);
						}
						
						//그리고 이전 노드도 meet리스트에 담는다.
						meet[to].add(from); 
						weight[to] = tWeight;
					}
					
					//pq에 넣어준다.
					pq.add(new Node(to, tWeight));
					
				}
			}

			System.out.print("Case #"+t+": ");
			if(weight[M-1]==Integer.MAX_VALUE) System.out.println(-1); //최고의원을 만날수없다면 -1출력
			else {
				//최고의원(M-1 정점) 을 만나기 전에 방문했던 meet 리스트 출력
				for (int i = 0; i < meet[M-1].size(); i++) {
					int tmp = meet[M-1].get(i);
					System.out.print(tmp+" ");
				}
				//M-1정점도 방문했으므로 추가해줌
				System.out.println(M-1+" "); 
			}
		}
	}
	
}