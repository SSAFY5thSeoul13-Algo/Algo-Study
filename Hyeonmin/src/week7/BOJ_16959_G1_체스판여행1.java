package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16959_G1_체스판여행1 {
	static int N, min;
	static int[][] map;
	//나이트 델타
	static int[][] dN = {{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};
	//비숍 델타
	static int[][] dB = {{-1,-1},{-1,1},{1,1},{1,-1}};
	//룩 델타
	static int[][] dL = {{1,0},{-1,0},{0,-1},{0,1}};
	//방문 체크할 배열. 체스말의 종류, 타켓에 따른 방문 좌표를 표시
	static boolean[][][][] visited;
	static int startX, startY;
	static int last;
	
	static class Node{
		/* type
		 * 0: 나이트
		 * 1: 비숍
		 * 2: 룩
		 * */
		int type;
		int cnt;
		int y;
		int x;
		int tgt;
		int roof;
		
		public Node(int type, int cnt, int y, int x, int tgt, int roof) {
			super();
			this.type = type;
			this.cnt = cnt;
			this.y = y;
			this.x = x;
			this.tgt = tgt;
			this.roof = roof;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		map = new int[N][N];
		visited = new boolean[3][N][N][N*N+1];
		
		//마지막 타겟
		last = N*N;
		
		//숫자 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				//시작 좌표 저장
				if(num ==1) {
					startX = j;
					startY = i;
				}
			}
		}
		
		bfs();
		
		System.out.println(min);
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<Node>();
		
		//시작점에 3종류의 체스말을 사용
		queue.add(new Node(0, 0, startY, startX, 2, 0));
		queue.add(new Node(1, 0, startY, startX, 2, 0));
		queue.add(new Node(2, 0, startY, startX, 2, 0));
		
		//방문 체크
		visited[0][startY][startX][2] = true;
		visited[1][startY][startX][2] = true;
		visited[2][startY][startX][2] = true;
		
		while(!queue.isEmpty()) {
			
			Node node = queue.poll();
			
			int cnt = node.cnt;
			int type= node.type;
			int tgt= node.tgt;
			int x= node.x;
			int y= node.y;
			int roof = node.roof;
			
			//마지막 타겟에 있는 경우 최솟값을 저장하고 중단
			if(tgt == last+1) {
				min = cnt;
				break;
			}
			
			//한 점에서 다른 점으로 가는 최대는 룩으로 변경 + 이동 2번 이므로 roof가 3번보다 크면 그 데이터는 더 이상 사용하지 않는다.
			if(roof > 3) {
				continue;
			}
			
			//말을 변경하는 경우
			for (int i = 0; i < 3; i++) {
				if(i != node.type && !visited[i][y][x][tgt]) {
					visited[i][y][x][tgt] = true;
					queue.add(new Node(i, cnt+1, y, x, tgt, 0));
				}
			}
			
			//각 타입의 이동
			switch(type) {
			//나이트
			case 0:
				//이동 가능한 모든 방향에 대해서
				for (int d = 0; d < 8; d++) {
					int ny = y +dN[d][0];
					int nx = x +dN[d][1];
					
					//범위 이내이고 방문하지 않았으면
					if(canGo(ny, nx) && !visited[0][ny][nx][tgt]) {
						//방문 체크
						visited[0][ny][nx][tgt] = true;
						//타겟에 도착한 경우
						if(map[ny][nx] == tgt) {
							queue.add(new Node(type, cnt+1, ny, nx, tgt+1, 0));
							break;
						}
						//타겟에 도착하지 못한 경우
						else {
							queue.add(new Node(type, cnt+1, ny, nx, tgt, roof+1));
						}
					}
				}
				break;
			//비숍
			case 1:
				//모든 방향에 대해서 1~칸을 이동
				for (int d = 0; d < 4; d++) {
					for (int i = 1; true ; i++) {
						int ny = y + dB[d][0]*i;
						int nx = x + dB[d][1]*i;
						
						//범위 밖으로 벗어나면 중단
						if(!canGo(ny,nx)) {
							break;
						}
						
						//이미 방문한 위치인 경우 다음 위치로
						if(visited[1][ny][nx][tgt])
							continue;
						
						//방문 체크
						visited[1][ny][nx][tgt] = true;
						
						//타겟에 도착한 경우
						if(map[ny][nx] == tgt) {
							queue.add(new Node(type, cnt+1, ny, nx, tgt+1, 0));
							break;
						}
						//타겟에 도착하지 않은 경우
						else {
							queue.add(new Node(type, cnt+1, ny, nx, tgt, roof+1));
						}
					}
				}
				break;
			//룩
			case 2:
				//모든 방향에 대해서 1~칸을 이동
				for (int d = 0; d < 4; d++) {
					for (int i = 1; true ; i++) {
						int ny = y + dL[d][0]*i;
						int nx = x + dL[d][1]*i;
						
						//범위 밖으로 벗어나면 중단
						if(!canGo(ny, nx)) {
							break;
						}

						//이미 방문한 위치인 경우 다음 위치로
						if(visited[2][ny][nx][tgt])
							continue;
						
						//방문 체크
						visited[2][ny][nx][tgt] = true;
						
						//타겟에 도착한 경우
						if(map[ny][nx] == tgt) {
							queue.add(new Node(type, cnt+1, ny, nx, tgt+1, 0));
							break;
						}
						//타겟에 도착하지 않은 경우
						else {
							queue.add(new Node(type, cnt+1, ny, nx, tgt, roof+1));
						}
					}
				}
				break;
			}
		}
	}
	
	//범위 체크하는 메소드
	static boolean canGo(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N) {
			return false;
		}
		
		return true;
	}
}