package week15.BOJ_3085_S4_사탕게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_BJ_3085_S4_사탕게임 {

	
	static int  N, ans;
	static char arr[][];
	
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
	
		arr = new char[N][N];
		
		for (int i = 0; i <N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-1; j++) {
				
				//가로검사
				//사탕의 색이 다른 인접한 두칸을 고른다
				if(arr[i][j]!=arr[i][j+1]) { 
					
					//그 다음 그 고른칸에 들어있는 사탕을 서로 교환한다.
					char tmp = arr[i][j];
					arr[i][j] = arr[i][j+1];
					arr[i][j+1] = tmp;
					
					//모두 같은 색으로 이루어져 있는 가장 긴 연속 부분을 고른 다음 그 사탕을 모두 먹는다.
					int cnt = 1;
					int max = 0;
					
					for (int r = 0; r < N; r++) {
						for (int c = 0; c < N-1; c++) {
							if(arr[r][c]==arr[r][c+1]) {
								cnt++;
							}else {
								max =  Math.max(cnt, max); //최대치 찾기
								cnt = 1; //cnt 리셋
							}
						}
						max =  Math.max(cnt, max);
						
						cnt = 1; //리셋
					}
					
					for (int c = 0; c < N; c++) {
						for (int r = 0; r < N-1; r++) {
							if(arr[r][c]==arr[r+1][c]) {
								cnt++;
							}else {
								max =  Math.max(cnt, max); //최대치 찾기
								cnt = 1; //cnt 리셋
							}
						}
						max =  Math.max(cnt, max);
						
						cnt = 1;
					}
					
					//원상복구
					tmp = arr[i][j];
					arr[i][j] = arr[i][j+1];
					arr[i][j+1] = tmp; 
					
					ans = Math.max(max, ans);
					
				}
				
				
				//세로검사
				//사탕의 색이 다른 인접한 두칸을 고른다
				if(arr[j][i]!=arr[j+1][i]) { 
					
					//그 다음 그 고른칸에 들어있는 사탕을 서로 교환한다.
					char tmp = arr[j][i];
					arr[j][i] = arr[j+1][i];
					arr[j+1][i] = tmp;
					
					//모두 같은 색으로 이루어져 있는 가장 긴 연속 부분을 고른 다음 그 사탕을 모두 먹는다.
					int cnt = 1;
					int max = 0;
					
					for (int r = 0; r < N; r++) {
						for (int c = 0; c < N-1; c++) {
							if(arr[r][c]==arr[r][c+1]) {
								cnt++;
							}else {
								max =  Math.max(cnt, max); //최대치 찾기
								cnt = 1; //cnt 리셋
							}
						}
						max =  Math.max(cnt, max);
						cnt = 1;
					}
					
					for (int c = 0; c < N; c++) {
						for (int r = 0; r < N-1; r++) {
							if(arr[r][c]==arr[r+1][c]) {
								cnt++;
							}else {
								max =  Math.max(cnt, max); //최대치 찾기
								cnt = 1; //cnt 리셋
							}
						}
						max =  Math.max(cnt, max);
						cnt = 1;
					}
					
					tmp = arr[j][i];
					arr[j][i] = arr[j+1][i];
					arr[j+1][i] = tmp;
					ans = Math.max(max, ans);
					
				}
			}
		}
		
		System.out.println(ans);
	
	}

	
}
