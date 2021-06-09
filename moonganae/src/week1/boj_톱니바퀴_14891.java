package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_톱니바퀴_14891 {
	
	static int sawMAX = 4;							// 톱니바퀴 갯수
	static int toothMAX = 8;						// 톱니수
	static int[][] saw = new int[sawMAX][toothMAX];	// 톱니바퀴배열
	static int[] directArr = new int[sawMAX];		// 각톱니바퀴별 회전방향
	
	
	static int S = 1;								// S극
	static int LEFT = 6, RIGHT = 2; 				// 톱니의 왼쪽번호, 오른쪽번호
	static int Clock = 1, CounterClock = -1;		// 시계방향, 반시계방향
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 톱니바퀴 초기화 */
		for(int i=0; i<sawMAX; i++) {
			String input = br.readLine();
			for(int j=0; j<toothMAX; j++) {
				saw[i][j] = input.charAt(j) - '0';
			}
		}
		
		/* 톱니바퀴 회전 */
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			// 방향설정
			setDirect(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			// 톱니바퀴 회전 
			rotate();
		}
		
		System.out.println(getScore());
		
	}
	
	/*
	 * 각 톱니바퀴 회전방향 결정
	 */
	static void setDirect(int sawNum, int direct) {
		
		for(int i=0; i<sawMAX; i++) {
			directArr[i] = 0;
		}
		directArr[sawNum-1] = direct;
		
		// 현재 톱니바퀴부터 오른쪽 방향으로  바퀴의 회전 방향설정
		for(int i=sawNum-1; i<sawMAX-1; i++) {
			if(saw[i][RIGHT] != saw[i+1][LEFT]) {		// 극이 다르면
				directArr[i+1] = directArr[i] * -1;		// 회전방향 반대
			}
		}
		// 현재 톱니바퀴부터 왼쪽 방향으로  바퀴의 회전 방향설정
		for(int i=sawNum-1; i>0; i--) {
			if(saw[i][LEFT] != saw[i-1][RIGHT]) {		//극이 다르면
				directArr[i-1] = directArr[i] * -1;		// 회전방향 반대
			}
		}
	}
	/*
	 * 각 톱니바퀴를 회전시키는 함수
	 */
	static void rotate() {
		for(int i=0; i<sawMAX; i++) {
			
			if(directArr[i] == Clock) {					// 시계방향 돌리기
				int tmp = saw[i][toothMAX-1];			
				for(int j=toothMAX-1; j>0; j--)			// RIGHT Shift
					saw[i][j] = saw[i][j-1];
				saw[i][0] = tmp;						
			} else if(directArr[i] == CounterClock) {	// 반시계방향 돌리기
				int tmp = saw[i][0];					
				for(int j=0; j<toothMAX-1; j++)			// LEFT Shift
					saw[i][j] = saw[i][j+1];
				saw[i][toothMAX-1] = tmp;
			}
		}
	}
	/*
	 * 톱니바퀴의 점수계산
	 */
	static int getScore() {
		int result = 0;
		for(int i=0; i<sawMAX; i++) {
			if(saw[i][0] == S)			// 12시방향이 S극이라면
				result += 1<<i;			// 점수획득 (1->2->4>8)
		}
		
		return result;
	}
}
