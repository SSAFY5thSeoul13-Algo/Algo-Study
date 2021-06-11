package week15.BOJ_3085_S4_사탕게임;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	/* 보드의 크기 */
	static int N;
	/* 보드 */
	static char[][] map;
	/* 초기상태, 변경 이후 상태의 최댓값 */
	static int initMax, max;
	/* 최대값에서의 좌표값들 저장 */
	static List<Point> initList = new ArrayList<>();	// (i, -1) : i행에서 최댓값이 나옴, (-1. j) : j행에서 최댓값이 나옴 => 1행씩 
	static List<Point> list = new ArrayList<>();		// 2개의 Point를 묶어서 봐야한다. list[0] 와 list[1]을 swap해서 최댓값이 나옴.
	static class Point{
		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		/* 1. 초기상태에서 먹을 수 있는 최대 갯수 및 위치 저장 */
		int initMax = 1;
		max = 1;
		
		for(int i=0; i<N; i++) {
			// 가로 탐색
			int cnt = checkCol(i);
			// 최댓값확인
			if(cnt > initMax) {
				initList.clear();
				initMax = cnt;
				initList.add(new Point(i, -1));
			}else if(initMax == cnt) {
				initList.add(new Point(i, -1));
			}
			
			// 세로 탐색
			cnt = checkRow(i);
			
			if(cnt > initMax) {
				initList.clear();
				initMax = cnt;
				initList.add(new Point(-1, i));
			}else if(initMax == cnt) {
				initList.add(new Point(-1, i));
			}
		}
		
		/* 2. 모든 좌표를 탐색 
		 	2-1. 인접한 다른 사탕 찾기
		 	2-2. 사탕의 위치를 바꾸고 3개의 영역에서 최대갯수 탐색
		 	 
		 	 	1 2 3 4
		 	 1	* % % *
		 	 2	* % % *
		 	 3	% X Y %
		 	 4	* % % *
		 	 => X, Y를 Swap 했을때, 3행, 2열, 3열 의 3개의 영역(%가 입력된 영역)을 탐색해야함
		 */
		
		for(int i=0; i<N-1; i++) {
			for(int j=0; j<N; j++) {
				
				// 가로
				if(j+1 < N && map[i][j] != map[i][j+1]) {
					
					swap(i,j, i, j+1);
					
					int localMax = checkCol(i);
					localMax = Math.max(localMax, checkRow(j));
					localMax = Math.max(localMax, checkRow(j+1));
					
					if(localMax > max) {
						list.clear();
						max = localMax;
						list.add(new Point(i,j));
						list.add(new Point(i,j+1));
					} else if(localMax == max) {
						list.add(new Point(i,j));
						list.add(new Point(i,j+1));
					}
					
					swap(i,j, i, j+1);
				}
				
				// 세로
				if(map[i][j] != map[i+1][j]) {
					
					swap(i,j, i+1, j);
					
					int localMax = checkRow(j);
					localMax = Math.max(localMax, checkCol(i));
					localMax = Math.max(localMax, checkCol(i+1));
					
					if(localMax > max) {
						list.clear();
						max = localMax;
						list.add(new Point(i,j));
						list.add(new Point(i+1,j));
					} else if(localMax == max) {
						list.add(new Point(i,j));
						list.add(new Point(i+1,j));
					}
					
					swap(i,j, i+1, j);
				}
			}
		}
		
		/* 3. 1번과 2번에서 구한것을 비교한다.
		 	3-1. 1번 > 2번이면
		 		3-1-1. 초기위치가 바꾼 두점에 영향을 받는다면 2번
		 		3-1-2. 아니라면 1번
		 	3-2. 1번 < 2번이면 2번
		*/
		if(initMax > max) {
			if(isPossible()) {
				System.out.println(initMax);
			}else {
				System.out.println(max);
			}
		} else {
			System.out.println(max);
		}
		
		
		/*
		 ! - 1번에서의 최대 갯수가 유지될 수 있는가?
		 	=> 2번 에서 구할때 최대 갯수 및 바꾼 두점의 좌표를 구해서  마지막에 비교한다.
		 ! - 1번에서 같은 max가 여러개가 있다면?
		 	=> 위치를 여러개 저장해서 나중에 비교
		*/
	}
	/* 2번에서의 변경점들이 1번에 영향을 미치는지 확인 */
	private static boolean isPossible() {
		
		for(Point initP : initList) {
			
			for(int i=0; i<list.size(); i+=2){
				boolean isY = initP.y != list.get(i).y && initP.y != list.get(i+1).y;
				boolean isX = initP.x != list.get(i).x && initP.x != list.get(i+1).x;
				
				// y좌표와 x좌표 모두 영향이 없어야 1번에서 구한것을 사용할 수 있다.
				if(isY && isX) return true;
			}
		}
		
		return false;
	}
	/* 위치 변경 */
	static void swap(int y1, int x1, int y2, int x2) {
		char tmp = map[y1][x1];
		map[y1][x1] = map[y2][x2];
		map[y2][x2] = tmp;
	}
	// i행에서 최댓값 반환
	static int checkCol(int i) {
		int cnt = 1;
		int tmpMax = 1;
		for(int j=0; j<N-1; j++) {
			if(map[i][j] == map[i][j+1]) {
				cnt++;
			}else {
				tmpMax = Math.max(tmpMax,  cnt);
				cnt=1;
			}
		}
		tmpMax = Math.max(tmpMax,  cnt);
		return tmpMax;
	}
	// j열에서 최댓값 반환
	static int checkRow(int j) {
		int cnt = 1;
		int tmpMax = 1;
		for(int i=0; i<N-1; i++) {
			if(map[i][j] == map[i+1][j]) {
				cnt++;
			}else {
				tmpMax = Math.max(tmpMax,  cnt);
				cnt=1;
			}
		}
		tmpMax = Math.max(tmpMax,  cnt);
		return tmpMax;
	}
}
