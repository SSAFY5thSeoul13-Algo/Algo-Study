package week24.BOJ_2096_G4_내려가기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] maxDP, minDP;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		maxDP = new int[3];
		minDP = new int[3];
		

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<3; i++) {
			int data = Integer.parseInt(st.nextToken());
			maxDP[i] = data;
			minDP[i] = data;
		}
		
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int[] input = new int[3];
			
			for(int j=0; j<3; j++) {
				input[j] = Integer.parseInt(st.nextToken());
			}

			
			// 최대
			int tmp0 = maxDP[0], tmp2 = maxDP[2];
			maxDP[0] = Math.max(maxDP[0], maxDP[1]) + input[0];
			maxDP[2] = Math.max(maxDP[1], maxDP[2]) + input[2];
			maxDP[1] = Math.max(Math.max(tmp0, maxDP[1]), tmp2) + input[1];

			// 최소
			tmp0 = minDP[0]; tmp2 = minDP[2];
			minDP[0] = Math.min(minDP[0], minDP[1]) + input[0];
			minDP[2] = Math.min(minDP[1], minDP[2]) + input[2];
			minDP[1] = Math.min(Math.min(tmp0, minDP[1]), tmp2) + input[1];
		}
		
		int max = Math.max(Math.max(maxDP[0], maxDP[1]), maxDP[2]);
		int min = Math.min(Math.min(minDP[0], minDP[1]), minDP[2]);
		System.out.println(max + " " + min);
	}

}
