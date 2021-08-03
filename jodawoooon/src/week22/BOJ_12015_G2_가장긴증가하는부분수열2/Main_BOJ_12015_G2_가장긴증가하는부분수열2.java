package week22.BOJ_12015_G2_가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_12015_G2_가장긴증가하는부분수열2 {
	static int N, ans, arr[];
	static ArrayList<Integer> lis;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //수열의 크기
		arr = new int[N]; //수열
		lis = new ArrayList<>();
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//LIS의 길이 구하기

		//각 원소가 LIS에 들어갈 위치를 찾는다.
		//i보다 크거나 같은 값의 첫 번째 인덱스 리턴
		
		lis.add(0);
		
		for(int num : arr) {
			if(num > lis.get(lis.size()-1)) lis.add(num);
		
			else {
				int left = 0;
				int right = lis.size()-1;
				int mid = 0;
				
				while(left<right) {
					mid = (left+right)/2;
					if(lis.get(mid) < num) left = mid+1;
					else right = mid;
				}
				
				lis.set(right, num);
			}
		}
		
		System.out.println(lis.size()-1);

	}
	
	
}
