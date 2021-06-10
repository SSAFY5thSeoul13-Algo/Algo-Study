package week5.boj3085;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 사탕_게임_3085 {
	static char[][] map;
	static int N,max;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				// 좌우로 인접한 사탕 교환 
				swap(i,j,i,j+1);
				// 연속된 최대 길이를 구하는 과정 
				check();
				// 원래대로 되돌려 놓는 과정 
				swap(i,j,i,j+1);
				// 상하로 인접한 사탕 교환 
				swap(j,i,j+1,i);
				// 연속된 최대 길이를 구하는 과정 
				check();
				// 원래대로 되돌려 놓는 과정 
				swap(j,i,j+1,i);
			}
		}
		
		System.out.println(max);
		

	}
	private static void check() {
		for(int i=0; i<N; i++) {
			int len = 1;
			for(int j=1; j<N; j++) {
				// 가로로 연속된 부분의 길이를 잰다.  
				if(map[i][j-1] == map[i][j])	len++;
				else {
					max = Math.max(len, max);
					len = 1;
				}
			}
			max = Math.max(len, max);
		}
		
		for(int j=0; j<N; j++) {
			int len = 1;
			for(int i=1; i<N; i++) {
				// 세로로 연속된 부분의 길이를 잰다. 
				if(map[i-1][j] == map[i][j])	len++;
				else {
					max = Math.max(len, max);
					len = 1;
				}
			}
			max = Math.max(len, max);
		}
	}
	private static void swap(int i, int j,int k,int l) {
		char tmp = map[i][j];
		map[i][j] = map[k][l];
		map[k][l] = tmp;
		
	}

}
