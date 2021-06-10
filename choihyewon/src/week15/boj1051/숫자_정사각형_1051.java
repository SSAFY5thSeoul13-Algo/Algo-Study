package week15.boj1051;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 숫자_정사각형_1051 {
	static int[][] map;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int min = Math.max(N, M);
		int max = 0; // 최대로 구할 수 있는 정사각형의 넓이 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<min; k++) {
					if(i+k < N && j+k < M) {
						if(map[i][j] == map[i][j+k] && map[i][j] == map[i+k][j] && map[i][j] == map[i+k][j+k]) {
							int tmp = (k+1)*(k+1);
							max = Math.max(max, tmp);
						}
					}
				}
			}
		}
		
		System.out.println(max);
		

	}

}
