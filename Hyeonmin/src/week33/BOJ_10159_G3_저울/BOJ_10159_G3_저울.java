package week33.BOJ_10159_G3_저울;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10159_G3_저울 {
	static int[][] map, reverseMap;
	static final int INF = 10000;
	static int N, M;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		reverseMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = reverseMap[i][j] = INF;
				
			}
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken()) -1;
			int to = Integer.parseInt(st.nextToken()) -1;

			//정방향
			map[from][to] = 1;
			//역방향
			reverseMap[to][from] = 1;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(i==k)	continue;
				for (int j = 0; j < N; j++) {
					if(i==j || k == j)	continue;
					
					//정방향과 역방향 모두 확인
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					reverseMap[i][j] = Math.min(reverseMap[i][j], reverseMap[i][k]+reverseMap[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			int count = 1;
			
			for (int j = 0; j < N; j++) {
				if(i==j)	continue;
				
				if(map[i][j] != INF )	count++;
				if(reverseMap[i][j] != INF)	count++;
			}
			
			System.out.println(N - count);
		}

	}

}
