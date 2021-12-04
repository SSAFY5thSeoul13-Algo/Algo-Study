package week33.BOJ_18428_S1_감시피하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18428_S1_감시피하기 {
	/*
	 * 장애물 3개 설치
	 * 선생 위치에서 4방향 탐색
	 * 중간에 학생이 있으면 No
	 * 범위 밖으로 나가거나 장애물을 만나면 그 방향 종료 
	 * */
	
	static int N;
	static int ccc;
	static List<int[]> teacherList = new ArrayList<>();
	static List<int[]> obstaclePointList = new ArrayList<>();
	static char[][] map;
	static int[][] delta = {
			{-1,0},
			{1,0},
			{0,-1},
			{0,1}
	};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				
				if(map[i][j] == 'T') {
					teacherList.add(new int[] {i,j});
				}
			}
		}
		
		verifyResult();
		
		findObstaclePoint();

		setObstacle(0,0);
		
		System.out.println("NO");

	}
	
	static void findObstaclePoint() {
		boolean[][] isVisited = new boolean[N][N];
		
		//각 선생의 4방향에 해당하는 위치에만 장애물을 설치
		for(int[] p : teacherList) {
			for (int d = 0; d < 4; d++) {
				List<int[]> tempList = new ArrayList<>();
				boolean isStudentExist = false;
				
				int ny = p[0];
				int nx = p[1];
				
				while(true) {
					ny += delta[d][0];
					nx += delta[d][1];
					
					if(isIn(ny,nx)) {
						if(!isVisited[ny][nx] && map[ny][nx] == 'X') {
							tempList.add(new int[] {ny, nx});
						}
						else if(map[ny][nx] == 'S') {
							isStudentExist = true;
							break;
						}
						else if(map[ny][nx] == 'T') {
							break;
						}
					}
					else {
						break;
					}
				}
				
				//장애물 설치가 유의미한 경우
				if(isStudentExist) {
					for(int[] point:tempList) {
						if(isVisited[point[0]][point[1]])	continue;
						
						isVisited[point[0]][point[1]] = true;
						obstaclePointList.add(new int[] {point[0],point[1]});
					}
				}
			}
		}
	}
	
	static boolean isIn(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N)	return false;
		
		return true;
	}
	
	
	//장애물 설치
	static void setObstacle(int num, int count) {
		verifyResult();
		
		if(count == 3) {
			
			return;
		}
		
		if(num >= obstaclePointList.size()) {
			return;
		}
		
		for(int i = num; i < obstaclePointList.size(); i++) {
			int[] p = obstaclePointList.get(i);
			int y = p[0];
			int x = p[1];
			
			map[y][x] = 'O';
			setObstacle(i+1, count+1);
			map[y][x] = 'X';
			setObstacle(i+1, count);
		}
	}
	
	static void verifyResult() {
		boolean isPossible = true;
		
		for(int[] p : teacherList) {
			
			for (int d = 0; d < 4; d++) {
				int ny = p[0];
				int nx = p[1];
				
				while(true) {
					ny += delta[d][0];
					nx += delta[d][1];
					
					if(isIn(ny,nx)) {
						if(map[ny][nx] == 'S') {
							isPossible = false;
						}
						else if(map[ny][nx] == 'O') {
							break;
						}
					}
					else {
						break;
					}
				}
			}
		}
		
		//감시를 피하는 경우
		if(isPossible) {
			System.out.println("YES");
			System.exit(0);
		}
	}

}
