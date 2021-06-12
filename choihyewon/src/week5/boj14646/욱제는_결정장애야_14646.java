package week5.boj14646;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욱제는_결정장애야_14646 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		
		int sum = 0;
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*N; i++) {
			int num = Integer.parseInt(st.nextToken());
			// 선택되지 않았다면 스티커를 붙여준다.
			if(arr[num]==0) {
				sum++;
				arr[num]++;
			}else {
				sum--;
			}
			if(sum>max) {
				max = sum;
			}
			
			
		}
		System.out.println(max);
		

	}

}
