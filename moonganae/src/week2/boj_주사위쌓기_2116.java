package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_주사위쌓기_2116 {

	static int[][] dice;
	static int N;
	static int max;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dice = new int[N][6];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			dice[i][0] = Integer.parseInt(st.nextToken());	// A
			dice[i][1] = Integer.parseInt(st.nextToken());	// B
			dice[i][2] = Integer.parseInt(st.nextToken());	// C
			
			/* 3과 4의 위치가 다른 이유 
			 * 		현재 주사위는 A <-> F, B<->D, C<->E 가 각각 반대면으로 구성된다.
			 * 		그냥 주어진대로 넣으면 0<->5, 1<->3, 2<->4 의 인덱스를 가지게 되는데
			 * 		이를 D(3), E(4)의 위치를 바꾸어 넣게 되면 반대편 면의 인덱스를 찾을 때 5-index로 편하게 찾을 수 있다.
			 * 		A(0)<->F(5), B(1)<->D(4), C(2)<->E(3)
			 * 
			 * => dice[i] = A, B, C, E, D, F 순서로 숫자를 넣는다.
			 * 
			 * */
			dice[i][4] = Integer.parseInt(st.nextToken());	// E
			dice[i][3] = Integer.parseInt(st.nextToken());	// D
			dice[i][5] = Integer.parseInt(st.nextToken());	// F
		}
		
		solve();
		
		System.out.println(max);
	}
	
	static void solve() {
		
		/*   주사위 제일 바닥을 0번째부터 5번째까지 순서대로 돌려보기
		 * */
		for(int bIndex=0; bIndex<6; bIndex++) {
			int tIndex= 5-bIndex;
			
			recur(0, dice[0][tIndex], 0);
		}
	}
	
	/*
	 * index : 현재주사위
	 * bottom : 현재주사위의 바닥면 숫자 ( 즉, 이전 주사위의 윗면 숫자 )
	 * sum : 한 옆면 숫자의 합
	 */
	static void recur(int index, int bottom, int sum) {
		// 기저조건 : 모든 주사위를 쌓았다면
		if(index == N) {
			max = Math.max(max, sum);		// 최댓값 최신화
			return;
		}
		
		/* 현재 주사위의 윗면 숫자를 찾기. */
		int top = 0;
		for(int i=0; i<6; i++) {
			if(bottom == dice[index][i]) {		// 바닥면의 숫자라면
				top = dice[index][5-i];			// 위면의 숫자!
				break;
			}
		}
		
		/* 6부터 4까지 순회
		 * 		옆면으로 사용할 수 있는건 윗면, 아랫면의 2개의 숫자이다.
		 *  	따라서 옆면의 최댓값은 6, 5, 4 중 하나를 반드시 선택할 수 있다.
		 * */
		for(int num=6; num>=4; num--) {
			if(num == bottom || num == top) continue;
			// 다음주사위, 현재 윗면숫자, 현재 옆면 최댓값 더하기
			recur(index+1, top, sum+num);	
			break;
		}
	}
}
