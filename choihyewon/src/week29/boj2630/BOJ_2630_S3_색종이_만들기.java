package week29.boj2630;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_S3_색종이_만들기 {
	static int N,white,blue;
	static int[][] arr;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(0,0,N);
		
		System.out.println(white);
		System.out.println(blue);

	}
	private static void divide(int r, int c, int len) {
		if(isSame(r,c,len)) {
			if(arr[r][c]==0) {
				white++;
			}else if(arr[r][c]==1){
				blue++;
			}
			return;
		}
		
		int division = len/2;
		
		divide(r,c,division);
		divide(r,c+division,division);
		divide(r+division,c,division);
		divide(r+division,c+division,division);
		
		
	}
	
	private static boolean isSame(int r, int c, int len) {
		int color = arr[r][c];
		
		for(int i=r; i<r+len; i++) {
			for(int j=c; j<c+len; j++) {
				if(arr[i][j]!=color) {
					return false;
				}
			}
		}
		
		return true;
	}

}
