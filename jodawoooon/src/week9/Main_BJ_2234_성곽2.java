package week9;

import java.io.*;
import java.util.*;

public class Main_BJ_2234_성곽2 {
	
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
	
	
	*/

	
	
	
	static int N, M, map[][], roomCnt, maxRoomSize, newMaxRoomSize, arr[][];
	static int dx[] = {0,-1,0,1}; //서 북 동 남
	static int dy[] = {-1,0,1,0};
	static boolean visited[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); 
		N = Integer.parseInt(st.nextToken()); 
		visited = new boolean[N][M];
		map = new int[N][M];
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		//지도 셋팅

		maxRoomSize = Integer.MIN_VALUE;
		newMaxRoomSize = Integer.MIN_VALUE;
		roomCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j]) {		
					bfs_roomNumbering(i,j);
					roomCnt++;
					
				}
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bfs_Break(i,j);
			}
		}
		
		//하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기 => BFS

		
		System.out.println(roomCnt); //방의 개수
		System.out.println(maxRoomSize); //방의 최대 크기
		System.out.println(newMaxRoomSize); //벽 부시고 방 최대 크기
		
		
		
	}
	
	
	
	private static void bfs_Break(int i, int j) {
		Queue<Node> queue = new LinkedList<>();
		boolean visited2[][][] = new boolean[N][M][2];
		
		queue.add(new Node(i,j,1));
		visited2[i][j][1] = true; 
		
		//초기위치 i,j에서 벽을 한번 부실수 있는 상태로 시작 (avail=1)
		int size = 1;
		while(!queue.isEmpty()) {
			
			Node n = queue.poll();
			int x = n.x;
			int y = n.y;
			int avail = n.avail;
			//System.out.println(x+" "+y+" "+size);
			
			for (int d = 0; d < 4; d++) {
				
				
				
				//d방향에 벽이 없으면 d 방향으로 이동한다
				int nx = x + dx[d];
				int ny = y + dy[d];
					
				if(nx>=0&&ny>=0&&nx<N&&ny<M) {
					if(visited2[nx][ny][avail])  continue;
					
					
					if((map[x][y] & (1<<d)) > 0) { //벽이 있다면
						if(avail==1) { //벽을 부실수 있다면
							if(!visited2[nx][ny][0]) { //방문안했으면
								visited2[nx][ny][0]=true;//벽부수고 이동
								size++;
								queue.add(new Node(nx,ny,0));
							}
						}
					}else {
						visited2[nx][ny][avail]=true;
						size++;
						queue.add(new Node(nx,ny,avail));
						
					}
						
						
										
				}
			}
		}
		
		newMaxRoomSize = Math.max(newMaxRoomSize, size); //세번째 답  : 벽 하나 부신 방의 최대 크기
		
		
	}

	private static void bfs_roomNumbering(int i, int j) {
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
				//벽이 있없 확인하고 방 찾기
				
				if((map[x][y] & (1<<d)) == 0) {
					//d방향에 벽이 없으면 d 방향으로 이동한다
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx>=0&&ny>=0&&nx<N&&ny<M) {
						if(!visited[nx][ny]) {
							arr[nx][ny]=roomCnt;
							visited[nx][ny]=true;
							size++;
							queue.add(new Node(nx,ny));
						}
					}
				}
			}
		}
		//방의 갯수와 방의 최대 크기를 구했다
		maxRoomSize = Math.max(maxRoomSize, size); //두번째 답 : 방의 최대 크기
		
	}
	
	static class Node{
		int x,y;
		int size, avail;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;

		}
		
		public Node(int x, int y,int avail) {
			super();
			this.x = x;
			this.y = y;

			this.avail = avail;
		}
		
		
		
	}
}
