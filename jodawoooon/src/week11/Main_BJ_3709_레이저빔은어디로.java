package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_3709_레이저빔은어디로 {
	
/*	dfs로 풀었습니다
	- 우향우 거울에 레이저가 반사되므로 
	방향배열의 순서를 90도씩 오른쪽으로 회전하도록 상,우,하,좌로 설정하였습니다.
	그리고 처음에 레이저가 어느 방향을 향하는지 찾고, 
	그 위치와 dir를 가지고 dfs를 돌렸습니다.

	그리고 dfs안에서 한칸씩 이동하면서 우향우거울을 만나면 dir=(dir+1)%4해서 90도씩 꺾어주었습니다.
	그리고 맵을 탈출하면 해당 위치를 return합니다.


	- 결과
	12012	88
	
	*/
	static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int N, R, map[][], dir;
	static Node ans;
	static int dx[] = {-1,0,1,0}; //90도씩 우향우 -> 순서 : 상 우 하 좌 
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //보드의 크기
			R = Integer.parseInt(st.nextToken()); //우향우 거울의 개수
			
			map = new int[N+2][N+2]; //0부터 N+1까지의 맵을 가진다
			
			for (int r = 0; r < R; r++) {
				//우향우 거울이 배치된 좌표 x y
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x][y] = -1; //우향우거울 위치 맵핑
			}
			
			//레이저의 좌표
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			map[x][y] = 1; //레이저위치 맵핑
			
			ans = new Node(0,0);
			dir = checkDir(x,y); //레이저가 어느 방향을 향하고 있는지 찾는다
			
			dfs(x,y,dir);
			
			
			System.out.println(ans.x+" "+ans.y);
		}
		
		
	}
	
	
	private static void dfs(int x, int y, int d) {
		
		int nx = x + dx[d]; //위치
		int ny = y + dy[d];
		
		if(!isIn(nx,ny)) { //맵의 공간을 넘어서면, 결과 (x,y)를 갱신
			ans.x = nx;
			ans.y = ny;
			return;
		}
		
		if(map[nx][ny]==-1) { //우향우 거울을 만나면 
			d = (d+1)%4; //방향을 90도 꺾는다
			dfs(nx,ny,d);
		}else if(map[nx][ny]==0) { //거울안만나면 그냥 방향 그대로 dfs
			dfs(nx,ny,d);
		}

	}
	private static boolean isIn(int nx, int ny) {
		if(nx<1||ny<1||nx>N||ny>N) return false; //맵 range 체크
		return true;
	}


	private static int checkDir(int x, int y) {
		//0:상 1:우 2:하 3:좌
		if(x==0) return 2;//레이저빔이 향하는 방향. 0행에 잇을때는 아래로
		if(x==N+1) return 0; //N+1행 => 위로
		if(y==0) return 1; //0열 => 오른쪽으로
		else return 3; //왼쪽
	}
}
