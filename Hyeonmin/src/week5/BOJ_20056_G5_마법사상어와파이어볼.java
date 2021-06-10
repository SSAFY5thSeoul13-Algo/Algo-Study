package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20056_G5_마법사상어와파이어볼 {
	static int N, M, K;
	static int result;
	//맵에서 파이어볼의 정보를 저장할 자료구조
	static List<Ball>[][] ballList;
	//델타
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	
	static class Ball{
		int m;
		int s;
		int d;
		
		public Ball(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//각 맵에 파이어볼을 저장할 리스트를 생성
		ballList = new ArrayList[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				ballList[i][j] = new ArrayList<Ball>();
			}
			
		}
		
		//해당 하는 자표의 리스트에 볼을 집어 넣음
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			ballList[y][x].add(new Ball(m,s,d));
		}
		
		//파이어볼 이동 실행
		move();
		
		//남은 파이어볼 질량 총합 계산
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(ballList[i][j].size()>0) {
					
					for (Ball ball : ballList[i][j]) {
						result += ball.m;
					}
				}
			}
		}
		
		System.out.println(result);
		
	}
	
	static void move() {
		
		//k만큼 이동 실행
		for (int k = 0; k < K; k++) {
			//이동한 파이어볼의 정보를 저장할 자료구조
			List<Ball>[][] nextList = new ArrayList[N+1][N+1];
			
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= N; j++) {
					nextList[i][j] = new ArrayList<Ball>();
				}
				
			}
			
			//맵을 순회
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					//해당 위치에 파이어볼이 존재하는 경우
					if(ballList[i][j].size()>0) {
						//그 위치에 있는 모든 파이어볼을 이동
						for (Ball ball : ballList[i][j]) {
							int y = i+dy[ball.d]*(ball.s%N);
							int x = j+dx[ball.d]*(ball.s%N);
							
							if(y <= 0) {
								y += N;
							}
							else if(y > N) {
								y -= N; 
							}
							
							if(x <= 0) {
								x += N;
							}
							else if(x >N) {
								x -= N; 
							}
							//이동한 위치에 파이어볼 저장
							nextList[y][x].add(new Ball(ball.m, ball.s, ball.d));
						}
					}
				}
			}
			
			//이동이 모두 끝난 파이어볼들의 정보를 갱신
			ballList = nextList;
			
			//같은 위치에 있는 파이어볼의 분할
			divide();
		}
	}
	
	static void divide() {
		//맵을 순회
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				//한개의 위치에 2개 이상의 파이어볼이 있는 경우
				if(ballList[i][j].size() >=2) {
					int totalM = 0;
					int totalS = 0;
					int d1 = 0;
					int d2 = 0;
					int count = 0;
					
					for (Ball ball : ballList[i][j]) {
						//질량의 합
						totalM += ball.m;
						//속도의 합
						totalS += ball.s;
						
						//방향이 짝수인 것과 홀수인 것의 숫자를 체크
						if(ball.d%2 == 0) {
							d1++;
						}
						else {
							d2++;
						}
						count++;
						
					}
					
					//분할될 파이어볼의 질량과 속도
					int m = totalM/5;
					int s = totalS/count;
					
					//분할될 파이어볼의 방향을 지정할 변수
					int st = 1;
					
					//파이어볼의 방향이 모두 홀수 이거나 모두 짝수인 경우 
					if(d1 == 0 || d2 == 0) {
						st=0;
					}
					
					//해당 위치에 있던 파이어볼을 모두 지움
					ballList[i][j].clear();
					
					//분할된 파이어볼의 질량이 0이면 그대로 다음으로 이동
					if(m == 0) {
						continue;
					}
					
					//질량이 0이 아니면 4개의 파이어볼을 생성
					for (int k = st; k < 8; k+=2) {
						ballList[i][j].add(new Ball(m, s, k));
					}
				}
				
			}
		}
	}

}