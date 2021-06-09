package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_스타트택시_19238 {
	/* 활동영역의 길이, 승객수, 남은연료, 사용한 연료 */
	static int N, M, K, use;
	/* 백준의 위치, 탑승승객 인덱스 */
	static int by, bx, target;
	/* 활동영역 */
	static int[][] map;
	/* 승객정보들 */
	static List<Passenger> passengers= new ArrayList<>();
	/* Delta Array */
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	static class Passenger{
		int sy, sx, ey, ex;

		public Passenger(int sy, int sx, int ey, int ex) {
			super();
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		by = Integer.parseInt(st.nextToken());
		bx = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			
			passengers.add(new Passenger(sy, sx, ey, ex));
			
			// 승객을 음수로 나타낸다. => 나중에 인덱스로 변환
			// 0번째 승객의 번호 == -1
			// 1번째 승객의 번호 == -2
			map[sy][sx] = -(i+1);
		}
		
		for(int i=0; i<M; i++) {
			
			
			// 가장 가까운 승객찾기
			if(!findPass()) {
				K = -1;
				break;
			}
			
			// 승객의 번호를 인덱스로 변환
			target = -(target+1);
			
			
			// 해당 승객의 목적지 이동여부
			use = 0;		// 사용연료
			if(!destination(passengers.get(target))) {
				K = -1;
				break;
			}
			// 사용한 연료의 2배인데, 빼지 않고 +K만 해준다.
			K += use;
		}
		
		System.out.println(K);
	}
	
	private static boolean destination(Passenger passenger) {
		
		boolean[][] vis = new boolean[N+1][N+1];
	
		Queue<Integer> q = new LinkedList<>();
		
		int sy = passenger.sy;
		int sx = passenger.sx;
		int ey = passenger.ey;
		int ex = passenger.ex;
		
		vis[sy][sx] = true;
		q.offer(sy);
		q.offer(sx);
		
		// 이동거리
		for(int d=1; d<=K; d++) {
			
			int size = q.size() / 2;
			for(int i=0; i<size; i++) {
				int y = q.poll();
				int x = q.poll();
				
				for(int z=0; z<4; z++) {
					int zy = y + dy[z];
					int zx = x + dx[z];
					
					if(zy<=0 || zx<=0 || zy>N || zx>N) continue;	// 범위확인
					if(vis[zy][zx]) continue;						// 방문확인
					if(map[zy][zx] == 1) continue;					// 벽확인
					
					// 목표지점 도달
					if(zy == ey && zx == ex) {
						use += d;				// 사용연료 기록
						by = zy;				// 택시위치 갱신
						bx = zx;
						return true;
					}
					
					vis[zy][zx] = true;
					q.offer(zy);
					q.offer(zx);
				}			
			}
		}
		
		// 연료를 다썼는데도 도달못했다면 이동불가
		return false;
	}

	static boolean findPass() {
		
		if(map[by][bx] < 0) {					// 현재 택시위치에 승객이 있다면
			target = map[by][bx];				// 개이득 ㅎㅎ 
			map[by][bx] = 0;
			return true;
		}
		
		boolean[][] vis = new boolean[N+1][N+1];
		
		Queue<Integer> q = new LinkedList<>();
		
		vis[by][bx] = true;
		q.offer(by);
		q.offer(bx);
		
		
		int ty = 21,  tx = 21;
		
		
		for(int d=1; d<=K; d++) {
			
			int size = q.size() / 2;
			for(int i=0; i<size; i++) {
				int y = q.poll();
				int x = q.poll();
				
				for(int z=0; z<4; z++) {
					int zy = y + dy[z];
					int zx = x + dx[z];
					
					if(zy<=0 || zx<=0 || zy>N || zx>N) continue; // 범위확인
					if(vis[zy][zx]) continue;					// 방문확인
					if(map[zy][zx] == 1) continue;				// 벽확인
					vis[zy][zx] = true;
					if(map[zy][zx] < 0 && zy <= ty ) {			// 승객이면서 행이 크지않을경우
						if(zy == ty && zx > tx) continue;		// 열이 크면 탑승승객이 아님
						
						ty = zy;
						tx = zx;
						continue;
					}
					
					q.offer(zy);
					q.offer(zx);
				}			
			}
			
			if(ty != 21) {
				K -= d;					// 연료 소모
				target = map[ty][tx];	// 승객번호
				map[ty][tx] = 0;		// 승객 암살
				return true;
			}
		}
		
		return false;
	}
}
