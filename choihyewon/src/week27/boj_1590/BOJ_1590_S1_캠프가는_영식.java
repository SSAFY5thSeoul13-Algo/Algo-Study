package week27.boj_1590;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1590_S1_캠프가는_영식{
	static int N,T;
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			long startTime = Integer.parseInt(st.nextToken());
			long interval = Integer.parseInt(st.nextToken());
			long total = Integer.parseInt(st.nextToken());
			
			long time = startTime;
			for(int j=0; j<total; j++) {
				list.add(time);
				time += interval;
			}
		}
		
		Collections.sort(list);
		
		
		long ans = binarySearch();
		

		System.out.println(ans);
		
	}
	private static long binarySearch() {
		if(list.get(list.size()-1)<T)	return -1;
		if(list.get(0)>=T)	return list.get(0) - T;
		long start = 0;
		long end = list.size()-1;
		
		while(start<end) {
			long mid = (start+end)/2;
			
			if(list.get((int)mid) == T) {
				return 0;
			}
			else if(list.get((int)mid)>T) {
				end = mid;
			}else if(list.get((int)mid)<T){
				start = mid+1;
			}
			
		}
		
		
		return list.get((int)start) - T;
		
	}

}
