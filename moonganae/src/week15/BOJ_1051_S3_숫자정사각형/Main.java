package week15.BOJ_1051_S3_숫자정사각형;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/* 직사각형 세로, 가로, 정답정사각형 한변의 길이, 제한길이 */
	static int N, M, k, limit;

	static char[][] map;
	public static void main(String[] args) throws Exception{
		
//		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray(); 
		}
		
		k=0;
		limit = Math.min(N, M);
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				// 현재 정답 정사각형 길이보다 긴범위만 확인
				for(int z=k+1; z<=limit; z++) {
					if(!isGo(i,j,z)) continue;
					
					k = z;
				}
				
			}
		}
		
		k++;
		// 정사각형의 넓이 출력
		System.out.println(k*k);
	}
	/* 기준좌표를 기준으로 정사각형인지 확인 */
	static boolean isGo(int y, int x, int offset) {
		// 범위 확인
		if(y+offset >=N || x+offset >=M ) return false;
		
		char c = map[y][x];
		// 모두가 같은 숫자인지 확인
		if(map[y][x+offset] == c && map[y+offset][x] == c && map[y+offset][x+offset] == c) 
			return true;
		
		return false;
	}
	
}
