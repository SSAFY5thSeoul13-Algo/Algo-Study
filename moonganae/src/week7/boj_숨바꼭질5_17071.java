package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 사이트 : BOJ
 * 문제명 : 숨바꼭질5
 * 번호 : 17071
 * 난이도 : 골1
 * 풀이시간 : 1시간 40분
 * 사용 알고리즘 : BFS 
 */
public class boj_숨바꼭질5_17071 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/* 현 수빈이 위치 */
		int N = Integer.parseInt(st.nextToken());
		/* 현재 동생위치 */
		int K = Integer.parseInt(st.nextToken());
		/* 방문한 시간
		 * visTime[0][i] : i번째 위치를 방문한 짝수시간
		 * visTime[1][i] : i번째 위치를 방문한 홀수시간  
		 * */
		int[][] visTime = new int[2][5000001];
		
		// 0은 미방문으로 하기 위해 수빈이의 현재 위치를 1로 설정
		visTime[0][N] = 1;
		
		
		Queue<Integer> q = new LinkedList<>();
		int time = 1;
		if(N==K) {					// 시작부터 같다면
			time=0;					// 0초
		} else {
			q.offer(N);
			
			while(true) {
				
				/* 동생 걷기 */
				K += time;
				
				
				/* 수빈이 이동(BFS) */
				int size = q.size();
				
				// 현재 홀수짝수
				int curOdd = time%2;
				// 이전 홀수짝수
				int prevOdd = (time+1)%2;
				for(int i=0; i<size; i++) {
					
					int cur = q.poll();
					
					// X+1 이동
					if(cur+1 < 500001 && visTime[curOdd][cur+1] == 0) {
						visTime[curOdd][cur+1] = visTime[prevOdd][cur]+1;
						q.offer(cur+1);
					}
					// X-1 이동
					if(cur-1 > 0 &&visTime[curOdd][cur-1] == 0) {
						visTime[curOdd][cur-1] = visTime[prevOdd][cur]+1;
						q.offer(cur-1);
					}
					// X*2 이동
					if(cur*2 < 500001 && visTime[curOdd][cur*2] == 0) {
						visTime[curOdd][cur*2] = visTime[prevOdd][cur]+1;
						q.offer(cur*2);
					}	
				}
				// 시간증가
				time++;
				
				// 만약 동생이 범위를 벗어났다면
				if(K>500000) {
					// 실패
					time = -1;
					break;
				}
				// 동생과 마주쳤다면
				if(visTime[curOdd][K] > 0) {
					// 1초로 시작하였기때문에 -1해줌
					--time;
					break;
				}
			}
		}

		System.out.println(time);
	}
}
