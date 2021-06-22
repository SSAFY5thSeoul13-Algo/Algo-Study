package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_7579_앱 {
	
	/*
	 * dp - knapsack
	 * 배낭채우기 문제와 유사한 방식으로 풀었습니다.
	 * 
	 * - 과정 
	 * 이 문제에서의 가치는 m이고, 그에 따른 비용은 c입니다.
	 * 
	 * 필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용을 구해야 하므로 2차원 dp배열을 선언하고, 
	 * dp[i][j]에서 i는 앱의 번호, j는 비용입니다.
	 * 
	 * 따라서 dp[N][j]는 1번앱~N번앱까지 모두 고려했을때, j비용으로 얻을 수 있는 최대 메모리가 됩니다. 
	 * 비용은 N과 C의 최댓값인 100*100을 최댓값으로 두었습니다.
	 * 
	 * 
	 * 
	 * 2중 for문을 돌면서 순서대로 앱을 고려하면서 j비용으로 얻을 수 있는 최대 메모리를 구했습니다
	 * 
	 * 1. i번째 앱을 지울 수 없다면 (c[i]>j) 
	 * dp는 i-1번째 앱까지 고려하는 경우에서의 최대메모리값이므로 이전값인 dp[i-1][j]를 가집니다
	 * 
	 * 2. i번째 앱을 지울 수 있다면 
	 * i번째 앱을 지우거나, 안지우는 경우 중 최대가치값(최대메모리값)을 선택합니다 
	 * i번째 앱을 안 지우는경우 : dp[i-1][j] i번째 
	 * 앱을 지우는 경우 : m[i] + dp[i-1][j-c[i]] 
	 * (i번째 앱의 메모리가치(m[i]) + 이전 행에서 j-c[i] 비용으로 얻을 수 있는 최대가치값 (dp[i-1][j-c[i])) 위 두 값중 큰 값을 찾아 dp에 저장합니다.
	 * 
	 * 
	 * 위 과정을 마친 후에는 dp[N][j]배열에 j비용으로 얻을 수 있는 최대 메모리값이 저장됩니다 
	 * 따라서 j비용을 최솟값(1)부터 증가시키며 dp[N][j]가 M바이트를 확보하면 
	 * 그 j 값이 M바이트를 확보한 앱 비활성화의 최소 비용이므로
	 *  해당 j값을 출력하고 종료합니다.
	 * 
	 * 
	 * - 막혔던 부분 
	 * 문제가 낯설어서 비슷한 문제인 boj-12865(평범한배낭) 문제를 먼저 풀고 해당 문제를 풀었습니다.
	 * 
	 * 
	 * - 결과 
	 * 메모리 16104 시간 124
	 * 
	 */
	
	/*
	 * 앱의 비활성화 문제를 스마트하게 해결하기 위한 프로그램
	 * m ; 앱 Ai이 사용하는 메모리
	 * c ; 앱을 비활성화한 후에 다시 실행하고자 할 경우, 추가적으로 들어가는 비용(시간 등)을 수치화 한 것을
	 * 사용자가 새로운 앱 B를 실행하고자 하여, 추가로 M 바이트의 메모리가 필요하다고 하자. 
	 * 
	 *  필요한 메모리 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용
	 */
	
	static int N, M;
	static int m[], c[], dp[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //N 앱의 갯수
		M = Integer.parseInt(st.nextToken()); //M 필요한 메모리
		
		m = new int[N+1];
		c = new int[N+1];
		//배열 선언
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		//앱 A1~AN이 사용중인 바이트 수 m배열 
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}
		//앱 A1~AN을 비활성화 했을 경우의 비용 c배열
		

		//앱 비활성화의 최소의 비용을 계산하라 => dp
		
		//M : 확보해야 하는 메모리의 크기 => 배낭의 크기
		//m[] ; 메모리 사용량(가치)
		//c[] ; 재 실행 비용(비용)
		
		
		dp = new int[N+1][100*100+1];
		//dp 값 : 메모리 사용량
		//dp[i][j] ; i는 앱의 번호, j는 비용
		//dp[N][j] ; 1번앱~N번앱까지 모두 고려하였을때 j비용 이하로 얻을 수 있는 최대 메모리
		//비용은 N과 C의 최댓값인 100*100을 최댓값으로 두었다.
		
		
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 100*100; j++) {
				
				if(c[i]>j) { //1. i번째 앱을 지울 수 없다면
					
					//비용보다 i번째 앱을 지우는 비용이 크므로 i번째 앱을 지울 수 없다.
					//dp[i][j]는 i-1번째 앱까지 선택하는 경우에서의 최대값 => dp[i-1][j]
					dp[i][j] = dp[i-1][j];
					
				}else { //2. i번째 앱을 지울 수 있다면
					
					/*
					i번째 앱을 안지운다면 : dp[i-1][j]
					i번째 앱을 지운다면 : m[i] + dp[i-1][j-c[i]];
						i번째 앱의 메모리가치(m[i]) + 이전 행에서 j-c[i] 비용으로 얻을 수 있는 최대가치값 (dp[i-1][j-c[i])
					
					둘 중 큰 값을 dp[i][j]에 저장
					 * 
					 */
					dp[i][j] = Math.max(dp[i-1][j], m[i] + dp[i-1][j-c[i]]);
				}

			}
		} 
		
		for (int j = 1; j <= 100*100; j++) {
			//dp배열에는 j 비용으로 얻을 수 있는 최대 메모리바이트가 저장되어있다.
			//따라서 j비용을 최솟값부터 증가시키면서, dp[N][j]가 M바이트를 확보했다면
			//그 j 값이 M 바이트를 확보하기 위한 앱 비활성화의 최소의 비용이므로
			//j값을 출력하고 종료한다.
			if(dp[N][j]>=M) {
				System.out.println(j);
				break;
			}
		}
		
	}
}
