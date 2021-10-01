package week29.BOJ_1590_S1_캠프가는영식;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_1590_S1_캠프가는영식 {
	static int N, T;
	static long ans;
	static ArrayList<Long> busTimes;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //버스의 개수
		T = Integer.parseInt(st.nextToken()); //영식 도착 시간
		
		busTimes = new ArrayList<>();
		
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			long start = Long.parseLong(st.nextToken());
			long gap = Long.parseLong(st.nextToken());
			long n = Long.parseLong(st.nextToken());
			
			for(int j = 0 ; j < n ; j++) {
				busTimes.add(start + gap*j);
			}
			//모든 버스 시간 입력
		}
		
		Collections.sort(busTimes); //시간 순 정렬
		
		if(busTimes.get(0) >= T ) {
			System.out.println(busTimes.get(0) - T);
			return;
		} else if(busTimes.get(busTimes.size() - 1) < T) {
			System.out.println(-1);
			return;
		}
		
		//이분탐색
		long left = 0;
		long right = busTimes.size() -1;
		long mid = (left+right)/2;
		while(left<right) {
			mid = (left+right)/2;
			if(busTimes.get((int)mid)==T) {
				System.out.println(0);
				return;
			}
			else if(busTimes.get((int)mid)>T) right = mid;
			else   left = mid+1;
		}
		System.out.println(busTimes.get((int)right)-T);
		
	}

}
