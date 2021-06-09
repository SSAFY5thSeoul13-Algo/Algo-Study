package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17822_원판돌리기3 {
	/*
	 * 
	 * 
i번째 원판에 적힌 j번째 수의 위치는 (i, j)로 표현
(i, 1)은 (i, 2), (i, M)과 인접하다.
(i, M)은 (i, M-1), (i, 1)과 인접하다.
(i, j)는 (i, j-1), (i, j+1)과 인접하다. (2 ≤ j ≤ M-1)
(1, j)는 (2, j)와 인접하다.
(N, j)는 (N-1, j)와 인접하다.
(i, j)는 (i-1, j), (i+1, j)와 인접하다. (2 ≤ i ≤ N-1)



1. 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
2. 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
	1. 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
	2. 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
	
	원판을 T번 회전시킨 후 원판에 적힌 수의 합을 구해보자.
	
	
	
	
	풀이방법 : 시뮬레이션
	 */
	static class Node{
		int x,d,k;
		
		int y;
		public Node(int x, int d, int k) {
			super();
			this.x = x;
			this.d = d;
			this.k = k;
		}
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int N,M,T,map[][];
	static Node rotate[];
	static int dx[] = {-1,1,0,0}; //상하좌우
	static int dy[] = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //원판의 반지름
		M = Integer.parseInt(st.nextToken()); //정수의 개수
		T = Integer.parseInt(st.nextToken()); //회전 횟수
		
		//원판 Arr 셋팅
		map = new int[N+1][M+1];
		for (int i =1; i <=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//회전 명령 Arr 셋팅
		rotate = new Node[T];
		for (int i = 0; i < T ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			rotate[i] = new Node(x,d,k);
			
		}
		
	
		//원판 돌리기
		//번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. 
		for (int i = 0; i < T; i++) {
			int X = rotate[i].x;
			int D = rotate[i].d;
			int K = rotate[i].k;
			

			
			//1. 원판 k칸 돌리기
			for (int j = X; j <= N; j++) {
				if(j%X!=0) continue; //번호가 x의 배수가 아니면 pass
				
				for (int k = 0; k < K ; k++) {
					if(D==0) { //di가 0인 경우는 시계 방향
						
						int tmp = map[j][M];
						for (int m = M; m > 1; m--) {
							map[j][m] = map[j][m-1];
						}
						map[j][1] = tmp;
						
					}else if(D==1){ //1인 경우는 반시계 방향이다.
						
						int tmp = map[j][1];
						for (int m = 1; m < M; m++) {
							map[j][m] = map[j][m+1];
						}
						map[j][M] = tmp;
						
					}
				}

			}
			

			
			//print();
			//2. 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
			
			int flag = 0;
			for (int n = 1; n <=N; n++) {
				for (int m = 1; m <=M; m++) {
					if(map[n][m]!=0) {
						
						flag += bfs(n,m);
						
						//flag가 true일 경우에는 bfs 함수 안에서 인접하면서 같은 수를 지움
						//flag가 false일 경우 평균구하기
						

					}
				}
			}
			
			
			//원판을 T번 회전시킨 후 원판에 적힌 수의 합
			if(flag==0) { 
				//인접하면서 같은 수가 없으면 
				//원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.	
				getAvg();

			}
			//print();

		}
		
		
		//print();
		int ans = 0;
		for (int i =1; i <=N; i++) {
			for (int j = 1; j <= M; j++) {
				ans += map[i][j];
			}
		}
		
		System.out.println(ans);
		
	}
	

	
	private static void getAvg() {
		
		//1. 평균을 구하고
		int sum = 0;
		int cnt = 0;
		double avg = 0;
		
		for (int i =1; i <=N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j]!=0) {
					//원판에 적힌 수의 평균 => 0인 수는 빼고
					sum += map[i][j];
					cnt++;
				}
				
			}
		}
		

		avg = (double)sum / (double)cnt;
		//System.out.println(avg);
		
		//2. 평균보다 크면 -1, 평균보다 작으면 +1
		for (int i =1; i <=N; i++) {
			for (int j = 1; j <= M; j++) {
				
				if(map[i][j]!=0) {
					if(map[i][j]<avg) map[i][j]+=1;
					else if(map[i][j]>avg) map[i][j]-=1;
				}
				
			}
		}

	}
	
	
	
	private static int bfs(int i, int j) {
		boolean[][] visited = new boolean[N+1][M+1];
		Queue<Node> queue = new LinkedList<>();
		
		ArrayList<Node> list = new ArrayList<>();
		
		queue.add(new Node(i,j));
		visited[i][j] = true;
		list.add(new Node(i,j));
		
		
		int tgt = map[i][j];
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
		
			int x = n.x;
			int y = n.y;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(ny>M) ny = 1;
				else if(ny<1) ny=M;
				//원형구조
				
				if(nx<=N&&nx>=1&&!visited[nx][ny]) {
					
					if(map[nx][ny]==tgt) { //인접하면서 같은 수 찾으면
						visited[nx][ny] = true;
						queue.add(new Node(nx,ny));
						list.add(new Node(nx,ny));
					}
				}
			}
		}
		
		//인접하면서 같은 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
		if(list.size()>1) { 
			for (int k = 0; k < list.size(); k++) {
				Node n= list.get(k);
				int x = n.x;
				int y = n.y;
				
				map[x][y] = 0;
			}
			
			return 1;
		}
	
		//인접하면서 같은 수가 하나도 없으면 0 리턴
		
		return 0;
		
	}
	
	private static void print() {
		for (int[] i : map) {
			System.out.println(Arrays.toString(i));
		}
		System.out.println("-----------------------------------------");
	}

}