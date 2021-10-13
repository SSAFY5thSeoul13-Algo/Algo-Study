package week29.BOJ_2206_G4_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2206_G4_벽부수고이동하기 {
	static int N, M, map[][],ans;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][M+1];
        
        for(int i = 1 ; i <= N ; i++) {
        	String str = br.readLine();
        	for(int j= 1; j <= M; j++) {
        		map[i][j] = str.charAt(j-1)-'0';
        	}
        }
        //기본 인풋 세팅
        
        bfs();
        
    }
	private static void bfs() {
		
		//최단경로 => BFS
		boolean visited[][][] = new boolean[N+1][M+1][2];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(1,1,1,1)); //(1,1)에서 시작
		visited[1][1][1] = true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			if(n.x==N&&n.y==M) { //(N,M)도착
				System.out.println(n.cnt);
				return;
			}
			
			for(int d = 0 ; d < 4; d++) {
				int nx = n.x + dx[d];
				int ny = n.y + dy[d];
				if(!isIn(nx,ny)) continue;
				if(visited[nx][ny][n.canBroken]) continue;
				
				if(map[nx][ny]==0) { //이동 가능하면
					visited[nx][ny][n.canBroken] = true;
					queue.add(new Node(nx,ny,n.cnt+1, n.canBroken));
				}else if(map[nx][ny]==1) { //벽이라면
					if(n.canBroken==1&&!visited[nx][ny][0]) { //벽을 부실 수 있다면 
						visited[nx][ny][0]=true;
						queue.add(new Node(nx,ny,n.cnt+1, 0));
					}
				}
			}
		}
		System.out.println(-1); //N,M까지 갈 수 없다
	}
	
	private static boolean isIn(int nx, int ny) {
		if(nx<1||ny<1||nx>N||ny>M) return false;
		return true;
	}

	static class Node {
		int x,y;
		int cnt;
		int canBroken;
		public Node(int x, int y, int cnt, int canBroken) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.canBroken = canBroken;
		}
	}
}
