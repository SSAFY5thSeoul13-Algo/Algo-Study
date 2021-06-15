package week5.boj7568;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 덩치_7568 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// 몸무게와 키를 담을 배열 생성 
		int[][] arr = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			int rank = 1;
			
			for(int j=0; j<N; j++) {
				// 자기 자신과는 비교하지 않는다. 
				if(i==j)	continue;
				if(arr[i][0]<arr[j][0] && arr[i][1]<arr[j][1]) {
					rank++;
				}
			}
			
			sb.append(rank + " ");
			
		}
		
		System.out.println(sb.toString());

	}

}
