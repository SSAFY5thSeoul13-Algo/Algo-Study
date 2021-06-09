package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 난이도 : 골드4
 * 시간 : 1시간
 */
public class boj_드래곤커브_15685 {

	/* 드래곤커브수, 꼭지점 모두 드래곤 커브인 사각형의 수 */
	static int N, ans = 0;
	
	/* delta array */
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	/* check[i][j] = true : 드래곤커브에 속하는 좌표 */
	static boolean[][] check = new boolean[101][101];
		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			/* 시작점, 방향, 세대를 입력받고 커브그리기 */
			Curve curve = new Curve(x,y,d);
			curve.setDir(g);
			curve.draw();
		}
		
		solve();
		System.out.println(ans);
	}
	/* 모든좌표를 조사해서 네 꼭짓점이 드래곤커브에 속하는 사각형 찾기 */
	static void solve() {
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				// 선택안된 좌표는 넘기기
				if(!check[i][j]) continue;
				
				// 기준점으로부터 오른쪽, 아래쪽, 대각선 아래쪽이 모두 드래곤커브이면
				if(check[i+1][j] && check[i][j+1] && check[i+1][j+1]) {
					ans++;		// 정답 카운트
				}
			}
		}
	}

	static class Curve{
		int x, y;
		List<Integer> dirs = new ArrayList<>();
		public Curve(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			dirs.add(dir);
		}
		/* <드래곤 커브의 방향공식>
		 * 공식1. 각 방향에서 시계방향으로 90도 회전하면 다음과 같다.
		 * 			0 -> 1
		 * 			1 -> 2
		 * 			2 -> 3
		 * 			3 -> 4
		 * 공식2. 가장 최근에 입력된 방향순서대로(기존값은 그대로 두고) 90도씩 회전시킨 값을 add 해준다.
		 * */
		public void setDir(int gener) {
			while(gener-- > 0) {
				int size = dirs.size()-1;
				// 가장 최근에 입력된 방향순서대로 90도 회전하고 그걸 그대로 삽입힌다.
				for(int i=size; i>=0; i--) {
					dirs.add( (dirs.get(i)+1)%4 );
				}
			}
		}
		/* 기준좌표를 중심으로 드래곤커브를 그린다. */
		public void draw() {
			check[y][x] = true;
			for(int dir : dirs) {
				y += dy[dir];
				x += dx[dir];
				check[y][x] = true;
			}
		}
		
	}
}
