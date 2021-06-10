package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234_G5_인구이동 {
	static int N, L, R;
	static int[][] map;
	static int count;
	//상하 좌우
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		//나라 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		process();
		
		System.out.println(count);
	}
	
	//map 전체를 순회하며 bfs를 실행시키는 메소드
	static void process() {
		
		//더 이상 인구이동이 생기지 않는지 여부를 확인하는 변수
		boolean finished = false;
		
		while(true) {
			//인구 이동이 진행된 나라를 표시할 배열
			boolean[][] visited = new boolean[N][N];
			finished = false;
			
			//map 전체를 순회
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//아직 인구이동이 일어나지 않은 곳이면 bfs를 실행해서 인구이동을 시도한다
					if(!visited[i][j]) {
						//인구 이동이 있는 경우 finished에 true로 표시
						if(bfs(visited, i, j)) {
							finished = true;
						}
					}
				}
			}

			//인구 이동이 더 이상 없는 경우 종료
			if(!finished) {
				break;
			}
			
			//인구 이동을 한 경우 카운트 증가
			count++;
		}
	}
	
	static boolean bfs(boolean[][] visited, int y, int x) {
		//나라의 좌표를 저장할 큐
		Queue<int[]> queue = new LinkedList<int[]>();
		//나중에 인구값 조정을 위해서 해당 나라의 좌표를 저장할 배열
		List<int[]> list = new ArrayList<int[]>();
		
		//인구이동을 시작하는 나라를 큐에 저장
		queue.offer(new int[] {y,x});
		list.add(new int[] {y,x});
		
		//해당 방문 표시
		visited[y][x] = true;
		
		//인구 이동을 실행할 나라들의 인구의 합을 저장할 변수
		int sum=map[y][x];
		
		while(!queue.isEmpty()) {
			//현재 나라의 좌표를 읽어옴
			int[] data = queue.poll();
			
			//4방향에 대해서 실행
			for (int i = 0; i < 4; i++) {
				int ny = data[0] + dy[i];
				int nx = data[1] + dx[i];
				
				//map에 있는 나라이고 인구 이동이 진행되는 나라인 경우만 실행
				if(canGo(ny,nx, visited, map[data[0]][data[1]])) {
					queue.offer(new int[] {ny, nx});
					list.add(new int[] {ny, nx});
					visited[ny][nx] = true;
					sum+= map[ny][nx];
				}
			}	
		}
		
		//인구 이동을 실행한 나라들의 바뀔 인구수
		int average = sum/list.size();
		
		//해당하는 나라들의 인구를 변경
		for (int[] p : list) {
			map[p[0]][p[1]] = average;
		}
	
		//인구를 이동하지 않은 경우 리스트에 처음 나라만 들어감
		//인구 이동이 있었으면 true반환
		if(list.size()>1) {
			return true;
		}
		
		return false;
	}
	
	//map에 존재하고 인구 이동이 가능한지 확인하는 메소드
	static boolean canGo(int y, int x, boolean visited[][], int num) {
		if (y<0 || x<0 || y>=N || x>=N || visited[y][x]) 
			return false;
		
		if(Math.abs(map[y][x]-num) < L || Math.abs(map[y][x]-num) > R)
			return false;
		
		return true;
	}
}