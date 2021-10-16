package week29.BOJ__1590_S1_캠프가는영식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, T, ans=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int stime = Integer.parseInt(st.nextToken());
			int term = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			
			
			int result = getMinTime(stime, term, cnt);
			
			if(result != -1) ans = Math.min(ans, result);
		}
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}

	static int getMinTime(int stime, int term, int cnt) {
		
		/* 시작시간전에 이미 영식이가 도착했다면 해당 노선의 첫차시간 */
		if(T <= stime) return stime - T;
		
		
		/* 영식이 도착시간 이후에 제일 작은 시간구하기 */
		int diff = T - stime;
		
		
		// 필요한 해당 노선의 버스대수
		int bussCnt = diff/term;
		bussCnt += (diff%term != 0) ? 1 : 0; 
		
		/* 막힌점1 : 첫시간도 해당노선의 버스 1대가 사용된것이므로, 남은 대수를 계산할때는 1대를 빼주어야함
		 * 따라서, bussCnt <= cnt로 하면 틀린다! 
		 * */
		if(bussCnt < cnt) {
			return bussCnt*term - diff;
		}
		
		
		return -1;
	}
}
