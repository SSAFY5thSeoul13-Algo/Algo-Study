package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/* 난이도 : 골드5
 * 시간 : 50분
 * */
public class boj_인구이동_16234 {
	/* 땅의크기, 인구차이(L,R) */
	static int N, L, R;
	/* 나라별 인구수 저장배열 */
	static int[][] map;
	/* dfs 방문 체크배열 */
	static boolean[][] vis;
	/* 연합의수, 인구이동 횟수 */
	static int unionNum = 0, moveNum=0;
	/* 연합저장 리스트 */
	static List<Union> unionList = new ArrayList<>();
	
	/* Delta Array */
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,1,0,-1};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		vis = new boolean[N][N];
		
		/* 각나라별 초기인구수 저장 */
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			/* 국경선 오픈결정 */
			setOpen();
			
			// 연합의 수가 0 -> 인구이동이 없다면 => 종료
			if(unionNum == 0) {
				break;
			}
			else {
				/* 인구이동 */
				move();
				/* 연합초기화 */
				unionList.clear();
				unionNum = 0;
				/* vis배열 초기화 */
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						vis[i][j] = false;
					}
				}
			}
		}
		
		System.out.println(moveNum);
		
	}
	
	static void setOpen() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(vis[i][j]) continue;
				
				/* 국경선오픈 결정 */
				unionNum++;
				unionList.add(new Union());
				/* vis[i][j] = true : 이미 소속된 연합이 있음
				 * */
				vis[i][j] = true;
				dfs(i,j);
				
				// 연합의 소속국가수가 1이라면 => 연합이 아님
				if(unionList.get(unionNum-1).countries.size() == 1){
					unionList.remove(unionNum-1);
					unionNum--;
				}	
			}
		}
	}
	/* 인구이동 */
	static void move() {
		moveNum++;
		for(Union uni : unionList) {
			int pop = uni.totalPop / uni.countries.size();
			
			for(Point country : uni.countries) {
				map[country.y][country.x] = pop;
			}
		}
		
	}
	static void dfs(int y, int x) {
		/* 현재 연합에 추가 */
		unionList.get(unionNum-1).add(new Point(y,x));
		for(int z=0; z<4; z++) {
			
			int zx = x + dx[z];
			int zy = y + dy[z];
			
			// 범위체크
			if(zx<0 || zy<0 || zx>=N|| zy>=N ) continue;
			// 인구수 차이확인
			int diff = Math.abs(map[y][x] - map[zy][zx]);
			if( diff < L || R < diff ) continue;
			
			// 이미 다른 연합에 소속된 국가라면
			if(vis[zy][zx]) continue;
			
			// 다른 연합에 소속되지 않고, 조건에 맞는다면
			vis[zy][zx] = true;
			dfs(zy,zx);
		}
	}
	
	static class Point{
		int y,x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static class Union{
		List<Point> countries = new ArrayList<Point>();
		int totalPop = 0;
		
		public void add(Point a) {
			countries.add(a);
			totalPop += map[a.y][a.x];
		}
	}
}
