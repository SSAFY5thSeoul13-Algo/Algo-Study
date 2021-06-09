package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1915_가장큰정사각형 {
/*	
	한 정점을 기준으로 인접한 3곳(좌,상,좌상)이 모두 0이 아닐경우
	인접한 3곳(좌,상,좌상)의 최솟값에 1을 더한값이 가장 큰 정사각형의 한 변의 길이가 됩니다.

	검사 할 위치에서 인접한 3곳(좌,상,좌상)을 검사하여
	정사각형일 경우(0이 아닌경우) 3곳의 min값+1로 검사 위치의 값을 갱신했습니다.

	- 어려웠던 부분
	접근법을 찾는것이 어려웠습니다.
	n중 for문으로 탐색하다가 시간초과가 발생하여
	다른 사람들의 풀이에서 아이디어를 참고했습니다.

	- 결과
	메모리 21904KB	시간 236ms
	*/
	static int N, M, map[][], ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				
				int x = 0;
				
				if(map[i][j]==0) continue; //정사각형이 될 수 없다

				//현 좌표값이 0이아니라면
				
				if(i!=0&&j!=0) { //인접한 3곳(좌,상,좌상)을 검사할 수 있다면
					//인접한 3곳(좌,상,좌상)이 모두 0이 아닐경우.
					//인접한 3곳(좌,상,좌상)의 최솟값에 1을 더한값이 가장 큰 정사각형의 한 변의 길이
					if(map[i-1][j-1]==0) continue;
					x = map[i-1][j-1];
					
					if(map[i][j-1]==0) continue;
					x = Math.min(x, map[i][j-1]);
					
					if(map[i-1][j]==0) continue;
					x = Math.min(x, map[i-1][j]);
					
					
					map[i][j] = x+1;
					
				}
				
				ans = Math.max(ans, map[i][j]);
			}
		}
		

		System.out.println(ans*ans);

	}

}
