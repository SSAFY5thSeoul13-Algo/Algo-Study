package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_8972_미친아두이노 {
/*	
	시뮬레이션
	
	- 조건
	종수의 아두이노는 미친 아두이노를 피해야한다.
	1. 종수가 아두이노를 8가지 방향(수직,수평,대각선)으로 이동시키거나, 그 위치에 그대로 놔둔다.
	2. 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되며, 종수는 게임을 지게 된다.
	3. 미친 아두이노는 8가지 방향 중에서 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동한다. 
		즉, 종수의 위치를 (r1,s1), 미친 아두이노의 위치를 (r2, s2)라고 했을 때, 
		|r1-r2| + |s1-s2|가 가장 작아지는 방향으로 이동한다.
	4. 미친 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되고, 종수는 게임을 지게 된다.
	5. 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.
	
	- 출력
	중간에 게임이 끝나는 경우에는 "kraj X"를 출력한다. 
	X는 종수가 게임이 끝나기 전 까지 이동한 횟수이다. 
	 그 외의 경우에는 보드의 상태를 입력과 같은 형식으로 출력한다.
	 
	*/

	static int R, C, cnt;
	static char map[][];
	static String command;
	
	static int[] dr = {0,1,1,1,0,0,0,-1,-1,-1};
	static int[] dc = {0,-1,0,1,-1,0,1,-1,0,1}; //1-9까지 주어진 조건에 맞는 방향
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//보드의 크기 R*C
		
		map = new char[R][C];
		
		int jongR=0;
		int jongC=0;
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				
				if(map[r][c]=='I') { //종수의 시작위치
					jongR = r;
					jongC = c;
				}
			}
		}
		
		cnt = 0;
		command = br.readLine(); //종수가 움직이려고 하는 방향
		
		//명령대로 중수의 아두이노를 이동시킨다.
		for (int i = 0; i < command.length(); i++) {
			
			int d = command.charAt(i) - '0'; //움직일 방향
			
			//1. 종수가 아두이노를 이동시킨다.
			int nr = jongR + dr[d];
			int nc = jongC + dc[d];
			
			cnt++; //종수가 아두이노를 움직인 횟수 증가
			
			if(map[nr][nc]=='R') {
				//종수의 아두이노가 미친 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되며, 종수는 게임을 지게 된다.
				System.out.println("kraj "+cnt);
				return;
			}
				
			map[jongR][jongC] = '.';
			map[nr][nc] = 'I'; //아두이노 이동
			
			jongR = nr; //아두이노의 위치 저장
			jongC = nc;
			
			
			
			
			//2. 미친 아두이노를 종수의 아두이노와 가장 가까워 지는 방향으로 한 칸 이동한다.
				int[][] change = new int[R][C];
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(map[r][c]=='R') { //미친아두이노
						
						
						int minR = 0; //종수의 아두이노와 가장 가까워지는 방향의 r값
						int minC = 0; //종수의 아두이노와 가장 가까워지는 방향의 c값
						int minDis = Integer.MAX_VALUE; //종수의 아두이노와 가장 가까워지는 거리값
						
						for (int dir = 1; dir <= 9; dir++) { //모든 방향을 탐색하며
							if(dir==5) continue; //그대로 있지않음
							int nnr = r + dr[dir];
							int nnc = c + dc[dir];
							if(nnr<0||nnc<0||nnr>=R||nnc>=C) continue;
							
							int dis = getDistance(jongR, jongC, nnr, nnc); //종수의 아두이노와의 거리값을 구한다
							if(dis<minDis) { //min값을 찾으면
								minDis = dis; //변수들을 갱신한다.
								minR = nnr;
								minC = nnc;
							}
							
						}
						
						
						//각각의 미친 아두이노는 최종적으로 종수의 아두이노와 가장 가까워지는 방향으로 이동
						
						map[r][c] = '.'; //기존 위치 빈칸으로 초기화
						
						if(map[minR][minC]=='I') {
							//미친 아두이노가 종수의 아두이노가 있는 칸으로 이동한 경우에는 게임이 끝나게 되고, 종수는 게임을 지게 된다.
							System.out.println("kraj "+cnt);
							return;
						}
						
						//아두이노의 이동 위치 표시
						change[minR][minC]++;

						
					}
				}
			}
			
			//change 배열에 표시해 놓은 위치들로 미친아두이노를 이동시킨다.
			//이 때 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸에 있는 아두이노는 모두 파괴된다.
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if(change[r][c]==1) {
						map[r][c]='R'; //미친 아두이노 이동
					}else if(change[r][c]>=2) {
						map[r][c]='.'; //여러 개 있으면 폭발
					}
				}
			}

			//printMap();
			//System.out.println("-------------------");
//			
		
		}
		
		
		//보드의 상태를 입력과 같은 형식으로 출력
		printMap();
		
	}

	private static void printMap() { //map print 메소드
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(map[r][c]);
			}sb.append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	
	private static int getDistance(int startR, int startC, int nnr, int nnc) {
		//종수 아두이노에서의 거리
		return Math.abs(startR - nnr) + Math.abs(startC - nnc);
	}


}