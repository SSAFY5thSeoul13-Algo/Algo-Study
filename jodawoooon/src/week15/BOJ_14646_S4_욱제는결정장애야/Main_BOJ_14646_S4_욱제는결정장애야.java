package week15.BOJ_14646_S4_욱제는결정장애야;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14646_S4_욱제는결정장애야 {
	
	//돌림판에는 최대 몇개의 스티커가 붙어있을 수 있을까?
	//마지막에 붙어있는 스티커가 아니라
	//한번에 최대 붙어있을수 있는 스티커의 수..인듯
	
	static int N, ans;
	static boolean[] menu;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		menu = new boolean[N+1];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int cnt = 0;
		for (int i = 0; i < 2*N; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			
			if(!menu[cmd]) {
				menu[cmd]=true;
				cnt++;
			}else {
				cnt--;
			}
			
			ans = Math.max(cnt, ans);
			
		}
		

		System.out.println(ans);
		
		
		
	}
}
