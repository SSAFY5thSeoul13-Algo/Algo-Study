package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1030_프렉탈평면 {
	static int s, N, K, R1, R2, C1, C2, size;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		s = Integer.parseInt(st.nextToken()); //시간
		N = Integer.parseInt(st.nextToken()); //흰색 정사각형이 나뉘어 지는 단위 정사각형 크기
		K = Integer.parseInt(st.nextToken()); //가운데 K*K 정사각형 검정색
		R1 = Integer.parseInt(st.nextToken());
		R2 = Integer.parseInt(st.nextToken());
		C1 = Integer.parseInt(st.nextToken());
		C2 = Integer.parseInt(st.nextToken()); //R1행 C1열부터 R2행 C2열 까지 출력
		
		
		
		//1초마다 N배씩 크기가 커지고, 가운데 K*K는 검은색으로 색칠됨
		//x,y좌표 값이 뭔지 찾는다
		
		size = (int)Math.pow(N,s);
		for (int i = R1; i <= R2; i++) {
			for (int j = C1; j <= C2; j++) {
				System.out.print(solve(i,j));
			}System.out.println();
		}
		
	}
	private static int solve(int i, int j) {
		int tmpSize = size;
		
		int nx = i;
		int ny = j;
		
		int tx = 0;
		int ty = 0;
		
		while(true) {
			tmpSize /= N;
			
			if((tmpSize)==0) break;
			
			
			tx = nx / tmpSize;
			ty = ny / tmpSize;
			
			if( ((N/2 - K/2) <= tx && tx < (N/2 - K/2)+K ) && ((N/2 - K/2) <= ty && ty < (N/2 - K/2)+K)) {

				return 1;
				
			}
			nx -= (tx * tmpSize);
			ny -= (ty * tmpSize);
			
		}
			
		return 0;
	}
}
