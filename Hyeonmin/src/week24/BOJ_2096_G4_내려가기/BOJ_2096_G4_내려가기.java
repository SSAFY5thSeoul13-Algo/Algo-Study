package week24.BOJ_2096_G4_내려가기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096_G4_내려가기 {
	static int[][] map, maxDp, minDp;
	static int[] dx = {-1,0,1};
	static int[] dy = {1,1,1};
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][3];
		maxDp = new int[N][3];
		minDp = new int[N][3];
		
		//숫자 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minDp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		//첫 번 째 숫자 dp배열에 입력
		for (int i = 0; i < 3; i++) {
			maxDp[0][i] = minDp[0][i] = map[0][i];
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < 3; j++) {
				//아래에 있는 3방향에 대해서
				for (int d = 0; d < 3; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					
					//범위 밖이면 스킵
					if(!canGo(ny, nx))	continue;
					
					//더 큰 수를 ny, nx위치에 저장
					maxDp[ny][nx] = Math.max(maxDp[ny][nx], maxDp[i][j] + map[ny][nx]);
					//더 작은 수를 ny, nx위치에 저장
					minDp[ny][nx] = Math.min(minDp[ny][nx], minDp[i][j] + map[ny][nx]);
				}
			}
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < 3; i++) {
			max = Math.max(max, maxDp[N-1][i]);
			min = Math.min(min, minDp[N-1][i]);
		}
		
		System.out.println(max+" "+min);
	}
	
	static boolean canGo(int y, int x) {
		if(y<0 || x<0 || x>=3 || y>=N)
			return false;
		
		return true;
	}
}
