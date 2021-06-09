package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 난이도 : 골5
 * 시간 : 2시간 4분 + 1시간 42분 = 3시간 46분
 */
public class boj_마법사상어와파이어볼 {

	/* 격자크기, 초기 파이어볼수, 마법사 이동명령횟수 */
	static int N, M, K;
	
	/* 파이볼이 이동후의 상태배열들 */
	static int[][] weight;	// 질량배열
	static int[][] speed;	// 속도배열
	static int[][] cnt;		// 갯수배열
	static int[][] dir;		// 방향배열 (bitCount 로 변환하여 저장)
	
	/* delta array 
	 * 7 0 1
	 * 6 x 2
	 * 5 4 3
	 * */
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	
	/* 모두 홀수 혹은 짝수이면 divDir[0] = 1, 3, 5, 7
	 * 아니라면 divDir[1] = 0, 2, 4, 6  를 사용*/
	static int[][] divDir = {{1,3,5,7}, {0,2,4,6}};
	
	/* 생성된 파이어볼을 저정할 장소*/
	static List<FireBall> balls = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		weight = new int[N+1][N+1];
		speed = new int[N+1][N+1];
		cnt = new int[N+1][N+1];
		dir = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			balls.add(new FireBall(r,c,m,s,d));
		}
		
		while(K-- > 0) {
			move();
			division();
		}
		
		int sum = 0;
		for(FireBall cur : balls) {
			sum += cur.m;
		}

		System.out.println(sum);
	}
	/* 파이어볼이 저장된 멤버변수에 따라 좌표이동 */
	static void move() {
		
		for(FireBall cur : balls) {
			int zr = cur.r + dr[cur.dir]*cur.speed;
			int zc = cur.c + dc[cur.dir]*cur.speed;
			
			
			/* 범위를 벗어났을경우 환산해줌!
			 * (1과 N은 서로 연결되어있음을 주의한다!!!) 
			 * */
			if(zr < 1 ) {
				zr %= N;
				zr += N;
			}
			else if(zr > N) {
				zr %=N;
				zr = zr == 0 ? N : zr;
			}
			
			if(zc < 1 ) {
				zc %= N;
				zc += N;
			}
			else if(zc > N) {
				zc %=N;
				zc = zc == 0 ? N : zc;
			}
			
			weight[zr][zc] += cur.m;
			speed[zr][zc] += cur.speed;
			
			// 막힌점 : 방향을 다합하고 홀짝을 비교햇는데 1 2 3 4는 홀2 짝 2이지만 짝수취급함.
			// 막힌점 : 기존의 방향을 잃어버림
			/* 방향은 모두 홀수 혹은 모두 짝수일경우를 판단하기 위해 bitCount를 사용하여 구분한다.
			 * -> division 함수내에서 따로 설명함
			 * */
			dir[zr][zc] |= 1<<cur.dir;
			cnt[zr][zc]++;
		}
		balls.clear();
	}
	static void division() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				
				/* 파이어볼이 1개인 경우 */
				if(cnt[i][j] == 1) {
					
					// 원래 방향 다시 복원 -> log2(dir[i][j])
					// 하지만, log2함수가 없기때문에 log(dir[i][j] / log(2)로 계산한다.
					int d = dir[i][j];
					d = (int) (Math.log(d) / Math.log(2)); // 복원된 방향
					
					// 다시 그대로 파이어볼 생성
					balls.add(new FireBall(i,j,weight[i][j], speed[i][j], d));
					
					// 막힌점 파이어볼 이동, 나머지 값들 처리안함
					weight[i][j] = 0;
					speed[i][j] = 0;
					cnt[i][j] = 0;
					dir[i][j] = 0;
				}
				else if(cnt[i][j] > 1) {
					
					int m = weight[i][j] / 5;
					// 막힌점 질량처리안함
					
					// 질량이 0보다 크다면
					if(m > 0) {
						// 속도계산
						int s = speed[i][j] / cnt[i][j];
						
						/* 방향이 모두 홀수 혹은 짝수였는지 확인한다. 
						 * */
						int evenCnt = 0, oddCnt = 0;
						for(int z=0; z<8; z+=2) {
							if( (dir[i][j] & 1<<z) != 0) evenCnt++;
							if( (dir[i][j] & 1<<(z+1)) != 0) oddCnt++;
						}
						int allSame = 0;
						
						/* 짝수 혹은 홀수의 수가 0, 즉 홀수만 나왔거나 짝수만 나왔다면 */
						if(evenCnt == 0 || oddCnt == 0) {
							allSame = 1;
						}
						
						// 파이어볼이 4방향으로 분리
						for(int d=0; d<4; d++) {
							balls.add(new FireBall(i,j,m,s, divDir[allSame][d]));
						}
					}
					// 값 초기화
					weight[i][j] = 0;
					speed[i][j] = 0;
					cnt[i][j] = 0;
					dir[i][j] = 0;
				}
			}
		}
		
	}

	static class FireBall{
		int r, c, m, dir, speed;

		public FireBall(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.dir = d;
			this.speed = s;
		}

		@Override
		public String toString() {
			return "FireBall [r=" + r + ", c=" + c + ", m=" + m + ", dir=" + dir + ", speed=" + speed + "]";
		}
		
	}
}
