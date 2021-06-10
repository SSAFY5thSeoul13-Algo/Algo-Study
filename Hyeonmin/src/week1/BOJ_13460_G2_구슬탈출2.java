package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460_G2_구슬탈출2 {
	static int N, M, result = -1;	//N: 세로(y), M: 가로(x)
	static char[][] board;
	static int[][] red = new int[1][2];	//[0][0]: y 좌표, [0][1]: x좌표
	static int[][] blue = new int[1][2];
	static int[][] hole = new int[1][2];
	//상 하 좌 우
	static int[] deltaX = {0, 0, -1, 1};
	static int[] deltaY = {-1, 1, 0, 0};
	//너비 우선 탐색을 위한 큐
	static Queue<Data> queue = new LinkedList<Data>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//세로, 가로 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		
		//보드 정보 입력
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j] == 'R') {
					red[0][0] = i;
					red[0][1] = j;
				}
				else if(board[i][j] == 'B') {
					blue[0][0] = i;
					blue[0][1] = j;
				}
				else if(board[i][j] == 'O') {
					hole[0][0] = i;
					hole[0][1] = j;
				}
			}
		}

		//초기 R, B 좌표 정보를 큐에 입력
		queue.offer(new Data(1, red[0][1], red[0][0], blue[0][1], blue[0][0]));
		
		//큐가 빌때까지 반복
		do {
			//큐에 저장된 입력을 꺼내옴
			Data d = queue.poll();
			//꺼내온 좌표를 기준으로 기울이기 실행
			move(d.cnt, d.rx, d.ry, d.bx, d.by);
		}while(!queue.isEmpty() && result == -1);
		
		//출력
		System.out.println(result);
		
	}
	
	//너비 우선
	static void move(int cnt, int rX, int rY, int bX, int bY) {
		//10회 이상의 실행이면 중지
		if(cnt == 11) {
			return;
		}
		
		//결과가 나왔으면 중지
		if(result != -1)
			return;
		
		//각 방향으로 기울이기를 실행
		for (int i = 0; i < 4; i++) {
			//각각 빨간 공, 파란 공의 (x,y)좌표
			int rx = rX;
			int ry = rY;
			int bx = bX;
			int by = bY;
			
			//기울인 방향으로 벽을 만날 때 까지 파란 공을 이동
			while(board[by+deltaY[i]][bx+deltaX[i]] != '#') {
				bx += deltaX[i];
				by += deltaY[i];
				
				//파란 공이 구멍에 빠지면 중지
				if(board[by][bx] == 'O') {
					break;
				}
			}
			
			
			//기울인 방향으로 벽을 만날 때 까지 빨간 공을 이동
			while(board[ry+deltaY[i]][rx+deltaX[i]] != '#') {
				rx += deltaX[i];
				ry += deltaY[i];
				
				//빨간 공이 구멍에 빠지면 중지
				if(board[ry][rx] == 'O') {
					break;
				}
			}
			
			//파란공이 빠졌으면 다른 방향으로 이동한 경우를 실행
			if(board[by][bx] == 'O') {
				continue;
			}
			
			//빨간공이 빠졌으면 결과를 저장하고 중단
			if(board[ry][rx] == 'O') {
				result = cnt;
				break;
			}
			
			//빨간공과 파란공이 이동중에 만났을 경우 같은 좌표가 된다
			if(by == ry && bx == rx) {
				//방향에 따라서 겹쳐진 공의 위치를 조정
				switch(i) {
				//상
				case 0:
					//파란공이 아래에 있었을 경우 파란공의 위치를 조정
					if(rY < bY) {
						by++;
					}
					//빨간공이 아래에 있었을 경우 빨간공의 위치를 조정
					else {
						ry++;
					}
					break;
				//하
				case 1:
					//파란공이 위에 있었을 경우 빨간공의 위치를 조정
					if(rY < bY) {
						ry--;
					}
					//빨간공이 위에 있었을 경우 파란공의 위치를 조정
					else {
						by--;
					}
					break;
				//좌
				case 2:
					//파란공이 오른쪽에 있었을 경우 파란공의 위치를 조정
					if(rX < bX) {
						bx++;
					}
					//빨간공이 오른쪽에 있었을 경우 빨간공의 위치를 조정
					else {
						rx++;
					}
					break;
				//우
				case 3:
					//파란공이 오른쪽에 있었을 경우 빨간공의 위치를 조정
					if(rX < bX) {
						rx--;
					}
					//빨간공이 오른쪽에 있었을 경우 파란공의 위치를 조정
					else {
						bx--;
					}
					break;
				}
			}

			//정상적으로 이동을 마쳤을 경우의 빨간, 파란 공의 좌표정포를 큐에 입력
			queue.offer(new Data(cnt+1, rx, ry, bx, by));
		}
	}
	
	static class Data{
		//빨간공의 좌표
		int rx;
		int ry;
		//파란공의 좌표
		int bx;
		int by;
		//단계 정보
		int cnt;
		
		public Data(int cnt, int rx, int ry, int bx, int by) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
}