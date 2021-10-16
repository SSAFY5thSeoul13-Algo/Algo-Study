package week29.BOJ_1590_S1_캠프가는영식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1590_S1_캠프가는영식 {
	static int N, T, min = 1000000001;
	static boolean isPossible = false;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int interval = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			
			int end = start + interval*(count-1);
			
			//마지막 차를 못타는 경우
			if(end < T) {
				continue;
			}
			
			//마지막 차와 같은 시간에 도착
			if(end == T) {
				isPossible = true;
				min = 0;
				break;
			}
			
			//기다리기 시작하는 시간
			int startTime = T - start;
			
			//아직 첫차가 오기 전
			if(startTime <= 0) {
				isPossible = true;
				min = Math.min(min, startTime*-1);
				continue;
			}
			
			//가장 최근 떠난 버스로부터 지난 시간
			int waitTime = startTime%interval;
			
			if(waitTime == 0) {
				isPossible = true;
				min = 0;
				break;
			}
			else {
				isPossible = true;
				min = Math.min(min, interval - waitTime);
			}
		}
		
		System.out.println(isPossible ? min : -1);
	}

}
