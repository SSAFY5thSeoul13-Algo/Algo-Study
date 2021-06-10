package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_8972_G4_미친아두이노 {
	static int R, C;
	//보드의 상태
	static char[][] map;
	//종수의 위치
	static int[] start = new int[2];
	static int[] dy = {1000, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dx = {1000, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		//보드 정보 입력
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				//종수의 위치를 따로 저장
				if(map[i][j] == 'I') {
					start[0] = i;
					start[1] = j;
				}
			}
		}
		
		//종수가 움직일 순서
		String order = br.readLine();
		
		//중간에 끝나지 않으면 보드 정보를 출력
		if(move(order)) {
			print();
		}
	}
	
	//아두이노 이동
	static boolean move(String order) {
		//미친 아두이노 위치들을 저장할 리스트
		List<int[]> list = new ArrayList<int[]>();
		
		//종수의 이동 횟수
		int size = order.length();
		
		//종수 이동 횟수만큼 반복
		for (int i = 0; i < size; i++) {
			//종수가 이동할 방향
			int dir = Character.getNumericValue(order.charAt(i));
			
			//미친 아두이노를 탐색해 리스트에 저장
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(map[r][c] == 'R')
						list.add(new int[] {r,c});
				}
			}
			
			//종수 현재 위치 삭제
			map[start[0]][start[1]] = '.';
			
			//종수 이동
			start[0] += dy[dir];
			start[1] += dx[dir];
			
			//종수가 이동한 위치에 아두이노가 있으면 중단
			if(map[start[0]][start[1]] == 'R') {
				System.out.println("kraj "+ (i+1));
				return false;
			}
			//없으면 종수 위치를 갱신
			else {
				map[start[0]][start[1]] = 'I';
			}
			
			//각 위치로 이동한 아두이노의 개수를 저장할 배열
			int[][] visited = new int[R][C];
			
			//각 아두이노 이동
			for (int[] p : list) {
				
				//종수의 위치를 기준으로 hor: 가로, ver: 세로
				//왼쪽 위 좌표를 0,0 으로 두고서 풀이
				int hor = start[1] - p[1];
				int ver = start[0] - p[0];
				
				//아두이노 현재 위치 삭제
				map[p[0]][p[1]] = '.';
				
				//아두이노가 종수보다 좌측에 있는 경우
				if(hor > 0) {
					p[1]++;
				}
				//아두이노가 종수보다 우측에 있는 경우
				else if(hor < 0) {
					p[1]--;
				}
				
				//아두이노가 종수보다 위에 있는 경우
				if(ver > 0) {
					p[0]++;
				}
				//아두이노가 종수보다 아래에 있는 경우
				else if(ver < 0) {
					p[0]--;
				}
				
				//이동한 위치에 종수가 있는 경우 중단
				if(map[p[0]][p[1]] == 'I') {
					System.out.println("kraj "+ (i+1));
					return false;
				}
				
				//이동한 아두이노 위치에 아두이노 개수 추가
				visited[p[0]][p[1]]++;
			}
			
			//모든 아두이노가 이동을 끝나면 현재 아두이노 위치를 보드에 갱신
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					//1보다 큰 경우는 여러 아두이노가 있는 것이므로 폭발로 처리
					if(visited[r][c] == 1)
						map[r][c] = 'R';
				}
			}
			
			//아두이노 위치 리스트 초기화
			list.clear();
		}
		
		return true;
	}
	
	//보드 출력
	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		
		System.out.println(sb.toString());
	}
}