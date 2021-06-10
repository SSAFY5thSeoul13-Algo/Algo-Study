package week15.BOJ_1051_S3_숫자정사각형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_1051_S3_숫자정사각형 {
	static int max = 0, N, M;
	static int[][] map;
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st =new StringTokenizer(br.readLine()); 
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//직사각형 정보
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		//각 점을 기준으로 정사각형 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				check(i, j);
			}
		}
		
		//정사각형의 넓이 출력
		System.out.println((max+1)*(max+1));
	}
	
	static void check(int y, int x) {
		//해당 점을 기준으로 현재 max보다 길이가 큰 것을 찾아야 하기 때문에 거리가 max+1차이 나는 점부터 확인
		int yLen = y+max+1;
		int xLen = x+max+1;
		//현재 좌표에 있는 숫자
		int tgt = map[y][x];
		
		//새로 구할 최대 거리에 해당하는 점이 범위에 있으면 실행
		while(yLen < N && xLen < M) {
			
			//모든 꼭짓점이 같으면 그 때의 변의 길이 저장
			if(tgt == map[y][xLen] && tgt == map[yLen][xLen] && tgt == map[yLen][x]) {
				max = yLen-y;
			}
			
			//변의 길이를 늘림
			yLen++;
			xLen++;
		}
	}
}
