package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_기차가어둠을헤치고은하수를_15787 {

	static int N, M;
	static int[] trains;
	static boolean[] vis = new boolean[1<<21];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trains = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int com = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
		
			if(com == 1) {
				int x = Integer.parseInt(st.nextToken());
				
				// idx 기차 x번 좌석 승객 탑승 
				trains[idx] |= 1<<x;
				
			} else if(com == 2) {
				int x = Integer.parseInt(st.nextToken());
				
				// idx 기차 x번 좌석 승객 하차
				trains[idx] &= ~(1<<x);
			} else if(com == 3) {
				trains[idx] = trains[idx] << 1;
				
				// 기존 20번 승객 하차
				trains[idx] = trains[idx] &= ~(1<<21);
			} else if(com == 4) {
				trains[idx] = trains[idx] >> 1;
				
				// 기존 1번 승객 하차
				trains[idx] = trains[idx] &= ~1;
			}
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(vis[trains[i]]) continue;
			vis[trains[i]] = true;
			cnt++;
		}
		
		
		System.out.println(cnt);
		
	}
	


}
