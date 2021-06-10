package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_G4_스타트택시 {
	//count: 목적지까지 데려다준 승객의 수, sum: 승객을 태우고 목적지까지 이동할 때 사용한 연료
	static int N, M, E, count, sum;
	static int[][] map;
	//다음 승객 혹은 목적지의 좌표를 저장할 배열
	static int[] target = new int[2];
	//각 승객의 목적지 좌표
	static int[][] goal;
	//택시의 현재 좌표
	static int[] now = new int[2];
	//상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		//승객 수만큼 목적지 배열 생성
		goal = new int[M][2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//시작 위치
		st = new StringTokenizer(br.readLine());
		now[0] = Integer.parseInt(st.nextToken());
		now[1] = Integer.parseInt(st.nextToken());
		
		//맵에 승객위치 표시, 각 승객의 목적지 좌표 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int ty = Integer.parseInt(st.nextToken());
			int tx = Integer.parseInt(st.nextToken());
			int gy = Integer.parseInt(st.nextToken());
			int gx = Integer.parseInt(st.nextToken());
			
			//목적지 좌표 저장
			goal[i][0] = gy;
			goal[i][1] = gx;
			
			//승객의 위치. 1이 벽이기 때문에 0번 승객을 2로 저장
			map[ty][tx] = i+2; 
		}
		
		process();
		
		//전체 승객수와 목적지에 데려다준 승객의 수가 같은 경우
		if(M==count)
			System.out.println(E);
		//승객이 남아있는 경우
		else
			System.out.println(-1);
	}
	
	static void process() {
		while(true) {
			//승객을 태우기 전 사용한 연료 초기화
			sum = 0;
			//bfs에서 목표 승객을 찾은 경우
			if(bfs()) {
				//목표 승객의 번호를 저장
				int num = map[target[0]][target[1]]-2;
				//택시의 위치를 승객을 태운 위치로 변경
				now[0] = target[0];
				now[1] = target[1];
				//승객을 태웠으니 해당 위치를 0으로 변경
				map[target[0]][target[1]] = 0;
				
				//승객을 목적지까지 데려다 준 경우
				if(move(num)) {
					//현재 위치를 목적지로 변경
					now[0] = target[0];
					now[1] = target[1];
				}
				//승객을 데려다 주기 전에 연료를 모두 사용한 경우
				else {
					break;
				}
			}
			//승객을 찾기 전에 연료가 다 닳은 경우
			else {
				break;
			}
			
			//데려다준 승객과 전체 승객의 수가 같은 경우 반복 중단
			if(count==M)
				break;
		}
	}
	
	static boolean bfs() {
		//시작위치에 승객이 있는 경우 해당 승객을 목표로 지정
		if(map[now[0]][now[1]]>1) {
			target[0] = now[0];
			target[1] = now[1];
			
			return true;
		}
		
		//현재 위치를 큐에 저장
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {now[0], now[1]});
		
		//방문 체크를 할 배열
		boolean[][] visited = new boolean[N+1][N+1];
		
		//목표를 찾지 못한 경우를 위한 값 지정
		target[0] = N+1;
		target[1] = N+1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				//다음 위치정보를 큐에서 꺼냄
				int[] p = q.poll();
				int y = p[0];
				int x = p[1];
				
				//이미 방문한 위치인 경우
				if(visited[y][x])
					continue;
				
				//방문 체크
				visited[y][x] = true;

				//4방향에 대해서
				for (int d = 0; d < 4; d++) {
					int ny = y+dy[d];
					int nx = x+dx[d];
					
					//범위 이내이고 벽이 아닌 경우
					if(canGo(ny, nx) && map[ny][nx] != 1) {
						//해당 위치에 승객이 있는 경우
						if(map[ny][nx] > 1) {
							//같은 거리인 경우 y좌표가 작은 것을 선택
							if(target[0] > ny) {
								target[0] = ny;
								target[1] = nx;
							}
							//같은 거리이고 y좌표가 같은 경우 x좌표가 작은 것은 선택 
							else if(target[0] == ny && target[1] > nx) {
								target[0] = ny;
								target[1] = nx;
							}
						}
						//해당 위치에 승객이 없는 경우
						else {
							//다음 탐색을 위해 큐에 현재 위치 저장
							q.offer(new int[] {ny, nx});
						}
					}
				}
			}
			
			//시간이 1 지나면 연료를 1사용
			E--;
			
			//더 이상 연료가 없는 경우
			if(E <= 0)
				return false;
			
			//목표 승객을 찾은 경우
			if(target[0] != N+1) {
				return true;
			}
		}
		
		//갈 수 있는 모든 위치를 가도 승객을 태울 수 없는 경우
		return false;
	}
	
	static boolean move(int num){
		//탐승한 승객의 목표지점 좌표
		target[0] = goal[num][0];
		target[1] = goal[num][1];
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {now[0], now[1]});
		//승객을 목표에 대려다 준 것을 체크
		boolean check = false;
		
		boolean[][] visited = new boolean[N+1][N+1];
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				int[] p = q.poll();
				int y = p[0];
				int x = p[1];
				
				if(visited[y][x])
					continue;
				
				visited[y][x] = true;
				
				//4방향
				for (int d = 0; d < 4; d++) {
					int ny = y+dy[d];
					int nx = x+dx[d];
					
					if(canGo(ny, nx) && map[ny][nx] != 1) {
						//해당 위치가 승객의 목적지인 경우
						if(ny == target[0] && nx == target[1]) {
							check = true;
							break;
						}
						else {
							q.offer(new int[] {ny, nx});
						}
					}
				}
				
				//목적지에 승객이 온 경우
				if(check)
					break;
			}
			
			//시간이 1지날 때 마다 연료 1 사용
			E--;
			sum++;
			
			//연료를 모두 사용했는데도 승객이 아직 목적지에 도착을 못한 경우
			if(E < 0)
				return false;
			
			//승객이 목적지에 도착한 경우 데려다준 승객 수 1 증가. 연료 회복.
			if(check) {
				count++;
				E += sum*2;
				return true;
			}
		}
		return false;
	}
	
	//범위 체크 메소드
	static boolean canGo(int y, int x) {
		if(y<1||x<1||y>N||x>N)
			return false;
		
		return true;
	}
}
