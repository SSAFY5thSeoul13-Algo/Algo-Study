package week27.boj_17779;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779_G4_게리맨더링2 {
	static int N,total;
	static int ans = Integer.MAX_VALUE;
	static int[][] arr;
 	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				total += arr[i][j];
			}
		}
		
		// 기준점 
		for(int x=1; x<=N; x++) {
			for(int y=1; y<=N; y++) {
				// 경계의 길이 
				for(int d1=1; d1<=N; d1++) {
					for(int d2=1; d2<=N; d2++) {
						// 유효성 검사 
						if(x+d1+d2<=N && y-d1>=1 && y+d2<=N) {
							solution(x,y,d1,d2);
						}
						
					}
				}
			}
		}
		
		System.out.println(ans);
		

	}
	private static void solution(int x, int y, int d1, int d2) {
		boolean[][] border = new boolean[N+1][N+1];
		
		// 경계선 세팅 
		for(int i=0; i<=d1; i++) {
			border[x+i][y-i] = true;
			border[x+d2+i][y+d2-i] = true;
		}
		
		for(int i=0; i<=d2; i++) {
			border[x+i][y+i] = true;
			border[x+d1+i][y-d1+i] = true;
		}
		
		int[] population = new int[5];
		
		// 1번 
		for(int i=1; i<x+d1; i++) {
			for(int j=1; j<=y; j++) {
				if(border[i][j]) break;
				population[0] += arr[i][j];
			}
		}
		
		// 2번 
		for(int i=1; i<=x+d2; i++) {
			for(int j=N; j>y; j--) {
				if(border[i][j]) break;
				population[1] += arr[i][j];
			}
		}

		// 3번 
		for(int i=x+d1; i<=N; i++) {
			for(int j=1; j<y-d1+d2; j++) {
				if(border[i][j]) break;
				population[2] += arr[i][j];
			}
		}
		
		// 4번 
		for(int i=x+d2+1; i<=N; i++) {
			for(int j=N; j>=y-d1+d2; j--) {
				if(border[i][j]) break;
				population[3] += arr[i][j];
			}
		}
		
		
		population[4] = total; 
		
		for(int i=0; i<population.length-1; i++) {
			population[4] -= population[i];
		}
		
				
		Arrays.sort(population);
		
		ans = Math.min(ans,(population[4]-population[0]));

 		
	}

}
