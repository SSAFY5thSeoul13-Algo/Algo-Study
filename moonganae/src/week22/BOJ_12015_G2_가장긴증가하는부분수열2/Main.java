package week22.BOJ_12015_G2_가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	static List<Integer> LIS = new ArrayList<>();
 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		LIS.add(Integer.parseInt(st.nextToken()));
		for(int i=1; i<N; i++) {
			int data = Integer.parseInt(st.nextToken());
			
			// LIS 최대보다 크면 뒤에 그냥 붙인다.
			if(data > LIS.get(LIS.size()-1)) {
				LIS.add(data);
			}
			// 아니라면 업데이트할 인덱스를 찾는다.
			else {
				int pos = bSearch(0, LIS.size()-1, data);
				LIS.set(pos, data);
			}
		}
		
		System.out.println(LIS.size());
	}
 	
 	/**
 	 * 
 	 * @param left : 시작 인덱스
 	 * @param right : 끝 인덱스
 	 * @param el : 비교할 엘리먼트
 	 * @return : 넣을 위치
 	 */
 	public static int bSearch(int left, int right, int el) {
 		
 		while(left < right) {
 			int mid = (left + right) / 2;
 			
 			// 엘리먼트가 크면, 범위를 올린다.
 			if(el > LIS.get(mid)) left = mid + 1;
 			else right = mid;
 		}
 		return right;
 	}

}
