package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685_G4_드래곤커브 {
	//N: 드래곤 커프 수, count: 정사각형 개수
	static int N, count;
	//드래곤 커브에 포함된 점을 표시할 배열
	static boolean[][] map = new boolean[101][101];
	
	//우 상 좌 하
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			//드래곤 커브 만들기
			makeCurve(x,y,d,g);
		}

		//만든 드래곤 커브들에서 정사각형 찾기
		checkSquare();
		
		System.out.println(count);
	}
	
	static void makeCurve(int x, int y, int d, int g) {
		//0세대 추가
		int nx = x+dx[d];
		int ny = y+dy[d];
		
		List<Integer> dir = new ArrayList<Integer>();

		//0세대에 해당하는 점을 저장
		map[y][x] = true;
		map[ny][nx] = true;
		
		//회전하고 난 후에 실행될 방향을 설정해서 리스트에 넣음
		d = (d+1)%4;
		dir.add(d);
		
		//세대만큼 반복
		for (int i = 0; i < g; i++) {
			//리스트에 있는 수만큼 반복. 리스트의 개수가 지금까지 그어진 선의 개수이다.
			//마지막 점에서 90도를 회전시킨 커브를 붙인다는 것이 지금까지 그은 커브의 역순으로 새로운 세대의 커브가 그려진다는 것
			for (int j = dir.size()-1; j >=0; j--) {
				d = dir.get(j);
				x = nx;
				y = ny;
				nx = nx+dx[d];
				ny = ny+dy[d];
				
				//새로 그은 커브에 해당하는 점을 저장
				map[y][x] = true;
				map[ny][nx] = true;
				
				//해당 커브가 다음 세대에 그어질 방향을 지정하고 리스트에 추가
				d = (d+1)%4;
				dir.add(d);
			}
		}
	}
	
	//네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수 구하는 메소드
	static void checkSquare() {
		//전체 탐색. 사각형 모양으로 탐색을 할 거라서 맨 위의 행은 탐색 시작점에서 제외
		for (int i = 1; i <= 100; i++) {
			//위와 동일한 이유로 맨 우측 열은 탐색 시작점에서 제외
			for (int j = 0; j <= 99; j++) {
				int x=j,y=i;
				int nx=j,ny=i;
				boolean check = true;
				//시작점 기준으로 4각형 모양으로 탐색
				for (int d = 0; d < 4; d++) {
					x = nx;
					y = ny;
					nx += dx[d];
					ny += dy[d];
					
					//드래곤 커브에 해당하지 않는 점이 있으면 check를 false로
					if(!map[y][x] || !map[ny][nx]) {
						check = false;
						break;
					}
				}
				//모든 꼭지점이 드래곤 커브에 해당하면 카운트
				if(check) {
					count++;
				}
			}
		}
	}
}
