package week24.boj2096;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2096_G4_내려가기 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[3];
		int[] min = new int[3];
        int[] max = new int[3];

        for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				arr[j] = Integer.parseInt(st.nextToken());	
			}
			
			// 초기값 설정 
			if(i==0) {
				min[0] = max[0] = arr[0];
				min[1] = max[1] = arr[1];
				min[2] = max[2] = arr[2];
				continue;
			}

			// 바로 min배열에 최소값을 저장해버리면 다음 비교할때 영향을 주므로 변수 사용 
			int n1 = Math.min(min[0], min[1]) + arr[0];
			int n2 = Math.min(Math.min(min[0],min[1]),min[2]) + arr[1];
			int n3 = Math.min(min[1], min[2]) + arr[2];
			
			min[0] = n1;
			min[1] = n2;
			min[2] = n3;
			
			// 바로 max배열에 최대값을 저장해버리면 다음 비교할때 영향을 주므로 변수 사용
			int m1 = Math.max(max[0], max[1]) + arr[0];
			int m2 = Math.max(Math.max(max[0],max[1]),max[2]) + arr[1];
			int m3 = Math.max(max[1], max[2]) + arr[2];
			
			max[0] = m1;
			max[1] = m2;
			max[2] = m3;
			
		}
		
        Arrays.sort(min);
        Arrays.sort(max);
        
        System.out.println(max[2] + " " + min[0]);

	}

}
