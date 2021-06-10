package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1915_G5_가장큰정사각형 {
	static int[][] map;
	static int N, M, max;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		char[] arr = new char[M];
		
		for (int i = 1; i <= N; i++) {
			arr = br.readLine().toCharArray();
			for (int j = 1; j <= M; j++) {
				int num = Character.getNumericValue(arr[j-1]);
				map[i][j] = num;
			}
		}
		
		//2번째 행 2번쨰 열 부터 시작
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				//해당 좌표가 0이 아니고 그 이전 위치들을 오른쪽아래 끝점으로 하는 정사각형들이 모두 있을 때 해당 길이가 1 더 긴 정사각형이 생긴다
				if(map[i][j] != 0 && map[i-1][j] != 0 && map[i][j-1] != 0 && map[i-1][j-1] != 0) {
					//이전 좌표들까지의 정사각형 중에서 가장 길이가 짧은 정사각형보다 1더 긴 정사각형이 만들어진다.
					map[i][j] = Math.min(map[i-1][j], Math.min(map[i][j-1], map[i-1][j-1])) + 1 ;
				}
				
				//새로 구한 정사각형이 기존 정사각형의 최대길이보다 긴 경우 저장
				if(map[i][j] > max) {
					max = map[i][j];
				}
			}
		}
		
		//넓이 출력
		System.out.println(max*max);
		
	}
}
