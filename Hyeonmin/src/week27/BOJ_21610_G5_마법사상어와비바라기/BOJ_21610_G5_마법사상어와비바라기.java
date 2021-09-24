package week27.BOJ_21610_G5_마법사상어와비바라기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21610_G5_마법사상어와비바라기 {
	static int N,M;
	static int[][] map;
	static boolean[][] isCloud;
	static Queue<int[]> q = new LinkedList<>();
	//좌 좌상 상 우상 우 우하 하 좌하
	static int[] dx = {0,-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,0,-1,-1,-1,0,1,1,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작 구름 위치
		q.offer(new int[] {0,N-1});
		q.offer(new int[] {1,N-1});
		q.offer(new int[] {0,N-2});
		q.offer(new int[] {1,N-2});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int dir = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken())%N;
			
			//이번에 생긴 구름이 이동한 위치를 체크할 배열
			isCloud = new boolean[N][N];
			
			int size = q.size();
			
			for (int j = 0; j < size; j++) {
				int[] p = q.poll();
				
				int nx = (p[0] +dx[dir]*num) < 0 ? N + (p[0] +dx[dir]*num) : (p[0] +dx[dir]*num)%N;
				int ny = (p[1] +dy[dir]*num) < 0 ? N + (p[1] +dy[dir]*num) : (p[1] +dy[dir]*num)%N;
				
				isCloud[ny][nx] = true;
				//비내림
				map[ny][nx]++;
				//물복사 버그 실행할 위치 저장
				q.offer(new int[] {nx,ny});
			}
			
			//물복사버그
			waterBug();
			//구름생성
			makeCloud();
		}
		
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				count += map[i][j];
			}
		}
		
		System.out.println(count);
		
	}
	
	static void waterBug() {
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			int x = p[0];
			int y = p[1];
			
			int count = 0;
		
			//대각선 확인후 물복사
			for (int d = 2; d <= 8; d+=2) {
				int nx = x +dx[d];
				int ny = y +dy[d];
				
				if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 0)	continue;
				
				count++;
			}
			
			map[y][x] += count;
		}
		
	}
	
	static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//2이상인 경우 구름 생성
				if(!isCloud[i][j] && map[i][j] >= 2) {
					map[i][j] -= 2;
					q.offer(new int[] {j, i});
				}
			}
		}
	}
}
