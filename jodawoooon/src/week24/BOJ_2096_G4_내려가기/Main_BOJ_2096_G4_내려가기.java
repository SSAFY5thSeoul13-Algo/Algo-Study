package week24.BOJ_2096_G4_내려가기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 2096 내려가기
 * @Author : Daun JO
 * @Date : 2021. 8. 27. 
 * @Algorithm : DP
 *
 */
public class Main_BOJ_2096_G4_내려가기 {
	
	static int N, arr[][], max[][], min[][], maxAns, minAns;
	public static void main(String[] args)  throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		

		arr = new int[N][3];
		max = new int[N][3];
		min = new int[N][3];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=0; i<3;i++) {
			max[0][i] = arr[0][i];
			min[0][i] = arr[0][i];
		}
		
		for(int i=1;i<N;i++) {
			
			
			max[i][0] = arr[i][0] + Math.max(max[i-1][0], max[i-1][1]); //0열
			max[i][1] = arr[i][1] + Math.max(Math.max(max[i-1][0], max[i-1][1]), max[i-1][2]); //1열
			max[i][2] = arr[i][2] + Math.max(max[i-1][1], max[i-1][2]); //2열
			
			min[i][0] = arr[i][0] + Math.min(min[i-1][0], min[i-1][1]); //0열
			min[i][1] = arr[i][1] + Math.min(Math.min(min[i-1][0], min[i-1][1]), min[i-1][2]); //1열
			min[i][2] = arr[i][2] + Math.min(min[i-1][1], min[i-1][2]); //2열
		}
		
		maxAns = Math.max(Math.max(max[N-1][0], max[N-1][1]), max[N-1][2]);
		minAns = Math.min(Math.min(min[N-1][0], min[N-1][1]), min[N-1][2]);
		
		System.out.println(maxAns+" "+minAns);
	}
}
