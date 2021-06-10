package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6087_G4_레이저통신 {
	static int W, H, min = Integer.MAX_VALUE;
	static char[][] map;
	//시작점과 도착점을 저장할 배열
	static int[][] point = new int[2][2];
	//상 좌 하 우
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	
	static class Node{
		int y;
		int x;
		int dir;
		//사용한 거울 수
		int mirror;
		
		public Node(int y, int x, int dir, int mirror) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.mirror = mirror;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		
		int idx = 0;
		for (int i = 0; i < H; i++) {
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				map[i][j] = arr[j];
				// 'C'에 해당하는 지점을 저장
				if(map[i][j] == 'C') {
					point[idx][0] = i;
					point[idx++][1] = j;
				}
			}
		}
		
		//시작점과 도착점이 같은 경우
		if(point[0][0] == point[1][0] && point[0][1] == point[1][1]) {
			System.out.println(0);
			return;
		}
		
		//레이저를 발사할 4방향
		for (int d = 0; d < 4; d++) {
			int y = point[0][0];
			int x = point[0][1];
			
			bfs(y, x, d);
		}
		
		System.out.println(min);
	}
	
	private static void bfs(int y, int x, int d) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(y,x,d,0));
		int[][] visited = new int[H][W];
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int dir = node.dir;
			int ny = node.y + dy[dir];
			int nx = node.x + dx[dir];
			
			//다음 좌표가 범위 밖이거나 벽인 경우 다음으로
			if(!canGo(ny,nx)) {
				continue;
			}
			
			//현재 사용한 거울이 이미 구한 최소 거울 수보다 작은 경우 다음으로
			if(node.mirror >= min) {
				continue;
			}
			
			//해당 위치에 이미 더 적은 수의 거울로 방문한 경우 다음으로
			if(visited[ny][nx] != 0 && visited[ny][nx] <= node.mirror ) {
				continue;
			}
			//아닌경우 더 적은 거울의 수를 배열에 저장
			else {
				visited[ny][nx] = node.mirror;
			}
			
			//목표지점에 도착한 경우 더 작은 값 저장
			if(ny == point[1][0] && nx == point[1][1]) {
				min = Math.min(min, node.mirror);
			}
			
			//그대로 가는 경우
			q.offer(new Node(ny, nx, dir, node.mirror));
			
			// '/'거울 사용한 경우
			q.offer(new Node(ny, nx, slash(dir), node.mirror+1));
			
			// '\'거울 사용한 경우
			q.offer(new Node(ny, nx, reverseSlash(dir), node.mirror+1));
		}
	}
	
	// '/'거울 사용한 경우의 방향을 반환
	static int slash(int d) {
		if(d == 0 || d == 2)
			return d - 1 < 0 ? 3 : d - 1;
		else
			return d + 1 > 3 ? 0 : d + 1;
	}
	
	// '\'거울 사용한 경우의 방향을 반환
	static int reverseSlash(int d) {
		if(d == 0 || d == 2)
			return d + 1 > 3 ? 0 : d + 1;
		else
			return d - 1 < 0 ? 3 : d - 1;
	}

	//갈 수 있는 곳인지 확인
	static boolean canGo(int y, int x) {
		if(y < 0 || x < 0 || y >= H || x >= W || map[y][x] == '*') {
			return false;
		}
		
		return true;
	}
}
