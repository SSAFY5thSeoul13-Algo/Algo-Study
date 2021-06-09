package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_야구_17281 {
	
	/* 이닝 수, 정답 */
	static int N, max = -1;
	/* np를 위한 인덱스배열, 실제 타자순서 : batterIdx[i] : i번째 타자의 선수인덱스 */
	static int[] npIdx, batterIdx;
	/* 이닝별 타격 hit[i][j] : i번째 이닝에서 j번째 선수가 때린 타격 */
	static int[][] hit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		hit = new int[N][9];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			/* 타점기록 */
			for(int j=0; j<9; j++) {
				hit[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve();
	}
	static void solve() {
		
		npIdx = new int[]{0,1,2,3,4,5,6,7,8};
		do {
			batterIdx = npIdx.clone();
			
			for(int i=0; i<3; i++) {
				batterIdx[i] = batterIdx[i+1];
			}
			/*1번 선수를 4번타자 셋팅 */
			batterIdx[3] = 0;
			
			play();
		} while(np());
		
		System.out.println(max);
		
	}
	static void play() {
		int idx = 0, score=0;
		for(int i=0; i<N; i++) {
			int[] ru = new int[4];
			int outCnt = 0;
			while(outCnt < 3) {
				
				int power = hit[i][batterIdx[idx]];
				
				/* 안타 못쳤을 경우 outCnt 증가 */
				if(power == 0) {
					outCnt++;
				} else {
					/* 안타인경우 */
					int scoreLimit=0, moveLimit=0;
					ru[0] = 1;
					switch(power) {
					case 1:
						scoreLimit = 3;		//3루까지	점수
						moveLimit = 0;		
						break;
					case 2:
						scoreLimit = 2;		//2루까지 점수
						moveLimit = 1;		
						break;
					case 3:
						scoreLimit = 1;		//1루까지 점수
						moveLimit = 2;
						break;
					case 4:
						scoreLimit = 0;		// 홈까지 점수인정
						moveLimit = 3;
						break;
					}	
					
					/* 점수계산 */
					for(int j=3; j>=scoreLimit; j--) {
						score += ru[j];
						ru[j] = 0;
					}
					/* 주루이동 */
					for(int j=3; j>moveLimit; j--) {
						ru[j] = ru[j-power];		//
						ru[j-power] = 0;
					}
				}
				idx = (idx+1) % 9;
			}
		}
		
		max = Math.max(max,  score);
	}
	/* NP -> 0까지가 아니라 1까지 */
	static boolean np() {
		int i = 9-1;
		
		while(i>1 && npIdx[i-1] >= npIdx[i])
			i--;
		
		if(i==1) return false;
		
		
		int j = 9-1;
		while(npIdx[i-1] >= npIdx[j])
			j--;
		
		swap(i-1, j);
		
		int k = 9-1;
		while(i < k) {
			swap(i++, k--);
		}
		return true;
	}
	static void swap(int i, int j) {
		int tmp = npIdx[i];
		npIdx[i] = npIdx[j];
		npIdx[j] = tmp;
	}

}
