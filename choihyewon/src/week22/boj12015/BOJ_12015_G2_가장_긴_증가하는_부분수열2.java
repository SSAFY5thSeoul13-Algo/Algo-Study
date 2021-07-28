package week22.boj12015;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12015_G2_가장_긴_증가하는_부분수열2 {
	static int N;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		// 수열의 최솟값은 1이므로 비교를 위해 0을 넣어준다.
		list.add(0);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num, left, right, mid;
		for(int i=0; i<N; i++) {
			num = Integer.parseInt(st.nextToken());
			// list의 맨 마지막값보다 크다면 list에 넣는다.
			if(num>list.get(list.size()-1)) {
				list.add(num);
			}else{
				left = 0;
				right = list.size()-1;
				// 이분탐색 시작 
				while(left<right) {
					mid = (left+right) / 2;
					if(num<=list.get(mid)) {
						right = mid;
					}else {
						left = mid + 1;
					}
				}
				list.set(right, num);
			}
		}
		
		System.out.println(list.size()-1);
		
	

	}

}
