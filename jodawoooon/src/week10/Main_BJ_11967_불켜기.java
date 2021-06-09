package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_BJ_11967_불켜기 {

/*	- 알고리즘
	BFS
	
	- 과정
	먼저, 2차원 ArrayList에 각 방에서 킬 수 있는 방들의 정보를 저장했습니다

	그리고 (1,1)에서 시작하여 
	list에서 정보를 가져와서 해당 좌표의 방의 불을 킵니다.
	그리고 내가 이동 가능한 방인지 확인하고 (그 방 주변에 방문이 가능한지 확인, 즉 visit=true인지 확인), 방문 가능하다면 queue에 넣습니다
	그 후 4방탐색으로 불 켜진 방으로 이동합니다.

	- 결과
	메모리 23504KB	시간 240ms*/
	
	
	
	
	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int N,M, map[][], ans;
	static ArrayList<Node>[][] list;
	static boolean visited[][];
	
	
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = 1;
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		list = new ArrayList[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <=N; j++) {
				list[i][j] = new ArrayList<>();
			}
		}
		
		
		for (int m = 0; m < M; m++) {
			//네 개의 정수 x, y, a, b가 주어진다. (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[x][y].add(new Node(a,b));
			
		}
		
		//베시는 불이 켜져있는 방으로만 들어갈 수 있고, 
		//각 방에서는 상하좌우에 인접한 방으로 움직일 수 있다. 
		
		
		bfs(1,1);
//		for (int[] ia : map) {
//			System.out.println(Arrays.toString(ia));
//		}
		
		System.out.println(ans);
	}

	private static void bfs(int i, int j) {
		Queue<Node> queue = new LinkedList<>();
		map[i][j] = 1; //처음 위치 불 밝히기
		visited[i][j] = true;
		
		queue.add(new Node(i,j));
		
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			int x = n.x;
			int y = n.y;
			
			int size = list[x][y].size();
			
			//list에서 정보를 가져와서, 킬 수 있는 방의 불을 킨다
			for (int k = 0; k < size ; k++) {
				Node node = list[x][y].get(k);
				int nx = node.x;
				int ny = node.y;
				
				if(map[nx][ny]==1) continue; //불이 이미 켜져있으면 또 킬 필요 X
				
				//불이 꺼져있으면 킨다
				map[nx][ny] = 1; //불을 킨다 
				ans++; //불켜진 방 개수 ++
				
				//이동가능한 방인지 확인한다
				if(canGo(nx,ny)) {
					queue.add(new Node(nx,ny)); //방문가능한곳이면 q에 넣는다
				}
				
			}
			
		
			//이동 ( 불 켜진 곳만 이동 가능 )
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(!isIn(nx,ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==0) continue;
				
				//불 켜져 있는데 내가 간 적 없으면
				visited[nx][ny] = true; //이동
				queue.add(new Node(nx,ny));
			}
			
			
		}
	}

	private static boolean canGo(int nx, int ny) {
		
		for (int d = 0; d < 4; d++) {
			int nnx = nx + dx[d];
			int nny = ny + dy[d];
			
			if(!isIn(nnx,nny)) continue;
			if(visited[nnx][nny]) {
				//내가 불 킨 곳 주변에 방문가능하면 => (nx,ny)에 방문 가능하다
                visited[nx][ny] = true; 						
					
				return true;
			}
			
		}
		return false;
	}

	private static boolean isIn(int nx, int ny) {
		if(nx<=0||ny<=0||nx>N||ny>N) return false;
		return true;
	}
}
