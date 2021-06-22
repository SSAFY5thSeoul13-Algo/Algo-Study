package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2234_성곽 {
	
	/*
	 * 
	 * 출력해야 하는 것
	 * 1. 방의 개수 => BFS
	 * 2. 가장 넓은 방의 넓이 => BFS
	 * 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 => 벽부수고 이동하기
	 * 성에는 최소 두 개의 방이 있어서, 항상 하나의 벽을 제거하여 두 방을 합치는 경우가 있다.
	 
	 * 2^0 => d=0      d=1            d=2           d=3
	 * 서쪽에 벽이있으면 1, 북쪽에 벽이있으면 2, 동쪽에 벽이있으면 4, 남쪽에 벽이있으면 8
	 *   2
	 * 1   4
	 *   8
	 *   
	 * 예를들어 11 이면 ,     6이면
	 *   ㅡ               		ㅡ
	 *  |                 |
	 *   ㅡ       
	
	 * 벽을 부신다 ==> 1. 직접 bfs로 flag를 두고 벽을 한번 무시하고 지나가본다 => 실패
	 * 			   2. map에서 벽을 하나씩 빼고 넣어본다. (1,2,4,8을 한번씩 빼보면 벽을 제거하는것과같음)
	
	*/
	
	
	
	
	
	static int N, M, map[][], roomCnt, maxRoomSize;
	static int dx[] = {0,-1,0,1}; //서 북 동 남
	static int dy[] = {-1,0,1,0};
	static int bit[] = {1,2,4,8}; 
	static boolean visited[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); 
		N = Integer.parseInt(st.nextToken()); 
		visited = new boolean[N][M];
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		//지도 셋팅

		maxRoomSize  = Integer.MIN_VALUE;
		roomCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					bfs(i,j);
					//섬에 번호 붙이는것처럼
					roomCnt++;
				}
			}
		}
		//1. bfs로 갈 수 있는 곳 까지 가보면서 room의 갯수를 체크한다.
		//2. room의 갯수를 체크하면서, 이동하는 칸의 갯수를 비교하며 방의 최대크기를 구한다.
		
		
		
		System.out.println(roomCnt); //방의 개수
		System.out.println(maxRoomSize); //방의 최대 크기
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited = new boolean[N][M];
				solve(i,j); //벽을 하나씩 제거해보는 메소드
			}
		}
		//3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 구한다.

		
		
		System.out.println(maxRoomSize); //벽 부시고 방 최대 크기
		
		
		
	}
	
	

	private static void solve(int i,int j) {
		
		for (int x = 0; x <= 3; x++) {
			
			if((map[i][j] & (1<<x)) != 0){
				map[i][j] -= bit[x]; //벽을 하나씩 제거해본다.
				bfs(i,j);
				map[i][j] += bit[x];
			}
			
		}
	}



	private static void bfs(int i, int j) {
		//방의 개수와 방의 최대 크기를 구하는 함수
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i,j));
		
		visited[i][j] = true;

		int size = 1;
		while(!queue.isEmpty()) {
			
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			
			for (int d = 0; d < 4; d++) {
				if((map[x][y] & (1<<d)) != 0 ) continue; //벽이 있으면 continue
				//d방향에 벽이 없으면 d 방향으로 이동한다
				
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx>=0&&ny>=0&&nx<N&&ny<M) {
					if(!visited[nx][ny] ){
						size++; //칸의 개수
						visited[nx][ny]=true;
						queue.add(new Node(nx,ny));
					}
				}
				
			}
		}
		//방의 갯수와 방의 최대 크기를 구했다
		maxRoomSize = Math.max(maxRoomSize, size); //방의 최대 크기
		
	}
	
	static class Node{
		int x,y;
		int size, avail;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;

		}
		
	}
}
