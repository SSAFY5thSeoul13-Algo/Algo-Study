package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11559_G5_PuyoPuyo {
	//연쇄 횟수
	static int count;
	//뿌요가 터졌는지 체크하는 변수
	static boolean check;
	//뿌요 배치
	static char[][] map = new char[12][6];
	//상 하 좌 우
	static int[]dy = {-1, 1, 0, 0};
	static int[]dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//뿌요 정보 입력
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		
		while(true) {
			//해당 사이클에 뿌요가 터졌는지 확인할 변수
			check = false;
			//모든 위치에 대해서
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					//뿌요가 있으면 bfs실행
					if(map[i][j] != '.')
						bfs(i, j);
				}
			}
			//터진 뿌요가 있으면 연쇄 횟수 증가
			if(check)
				count++;
			//터진 뿌요가 없으면 중단
			else
				break;
			
			//터진 뿌요 위에 있던 뿌요들 위치 조정
			replace();
		}
		
		System.out.println(count);
	}
	
	static void bfs(int y, int x) {
		if(map[y][x] == '.')
			return;
		
		Queue<int[]> q = new LinkedList<int[]>();
		
		boolean[][] visited = new boolean[12][6];
		
		//시작 뿌요의 색깔
		char color = map[y][x];
		
		q.offer(new int[] {y,x});
		
		//연결된 뿌요 수
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int py = p[0];
			int px = p[1];
			char ncolor = map[py][px];
			
			//시작 뿌요와 색이 다른 뿌요는 제외
			if(ncolor =='.' || ncolor != color)
				continue;
			
			//연쇄 뿌요 수 증가
			cnt++;
			//방문 체크
			visited[py][px] = true;
			
			//4방향
			for (int d = 0; d < 4; d++) {
				int ny = py + dy[d];
				int nx = px + dx[d];
				
				//범위 이내이고 색깔이 같고 아직 방문하지 않은 뿌요의 위치를 큐에 저장
				if(canGo(ny, nx) && map[ny][nx] == color && !visited[ny][nx]) {
					q.add(new int[] {ny, nx});
				}
			}
		}
		
		//연쇄된 뿌요가 4개 이상이면
		if(cnt >= 4) {
			//뿌요가 터졌다는 것을 체크
			check = true;
			//터진 뿌요들을 '.'으로 변경
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(visited[i][j])
						map[i][j] ='.';
				}
			}
		}
	}
	
	static void replace() {
		//각 열에 대해서
		for (int j = 0; j < 6; j++) {
			//가장 아래 빈 공간의 y값을 저장할 변수
			int idx = -1;
			
			//가장 아래에 있는 빈공간 확인
			for (int i = 11; i >= 0; i--) {
				if(map[i][j] == '.') {
					idx = i;
					break;
				}
			}
			
			//뿌요 위치 변경
			for (int i = idx-1; i >= 0; i--) {
				//빈 공간이 없으면 중단
				if(idx < 0)
					break;
				
				//뿌요가 있는 경우 가장 아래있는 빈공간으로 해당 뿌요를 이동시킴
				if(map[i][j] != '.') {
					map[idx][j] = map[i][j];
					map[i][j] = '.';
					idx--;
				}
			}
		}
		
	}
	
	static boolean canGo(int y, int x) {
		if(y < 0 || x < 0 || y >= 12 || x >= 6) 
			return false;
		
		return true;
	}
}