package week22.BOJ_2805_S3_나무자르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805_S3_나무자르기 {
	static int M, N, result;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		//절단기의 최대 높이
		int right = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
			if(right < arr[i])
				right = arr[i];
		}
		
		//절단기의 최소 높이
		int left = 0;
		
		while(left <= right) {
			//설정할 절단기의 높이
			int middle = (left+right)/2;
			
			boolean isEnough = cutTree(middle);
			
			//M이상의 목재를 얻은 경우
			if(isEnough) {
				//더 높은 높이를 result에 저장
				if(result < middle)
					result = middle;
				
				left = middle + 1; 
			}
			else {
				right = middle -1;
			}
		}
		
		System.out.println(result);
	}
	
	//나무 자르기
	static boolean cutTree(int middle) {
		long length = 0;
		
		for (int i = 0; i < N; i++) {
			//설정한 높이보다 높은 나무의 잘라낸 길이를 더함
			if(arr[i] > middle)
				length += arr[i] - middle;
		}
		
		if(length >= M)
			return true;
		
		return false;
	}

}
