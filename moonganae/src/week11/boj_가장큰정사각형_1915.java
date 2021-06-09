package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_가장큰정사각형_1915 {

	/* 배열의 크기 */
	static int N, M;
	/* 배열 */
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int max = 0;	// 최대 정사각형의 길이
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) continue;
				
				int min = 0;	// 변의 길이
				if( i!=0 && j!=0) {
					/* 기준좌표 포함 모두가 1인지 확인 */
					if(map[i][j-1] == 0) continue;	// 좌
					if(map[i-1][j] == 0) continue;	// 상
					if(map[i-1][j-1] == 0) continue; //좌상
					
					min = Math.min(map[i-1][j], Math.min(map[i][j-1], map[i-1][j-1]));
					/* 최소길이 + 1 */
					map[i][j] = min + 1;
				}
				
				max = Math.max(max, map[i][j]);
			}
		}
		
		System.out.println(max * max);
	}
}
