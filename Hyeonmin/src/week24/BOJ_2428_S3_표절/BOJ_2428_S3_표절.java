package week24.BOJ_2428_S3_표절;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2428_S3_표절 {
	static int N;
	static int[] sizes;
	static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sizes = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//각 파일 사이즈
		for (int i = 0; i < N; i++) {
			sizes[i] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(sizes);
		
		for (int i = 0; i <N-1; i++) {
			result += search(i) - i;
		}
		
		System.out.println(result);
		
	}
	
	//이분 탐색
	static int search(int idx) {
		int left = idx+1;
		int right = N-1;
		int middle = 0;
		int num = 0;
		
		//검사할 쌍이 없는 경우
		if(sizes[idx] < 0.9*sizes[idx+1]) {
			return idx;
		}
		
		while(left <= right) {
			middle = (left+right)/2;
			
			//middle이 인덱스인 파일이 검사 대상이 아닌 경우
			if(sizes[middle] *0.9 > sizes[idx]) {
				right = middle -1;
			}
			//middle이 인덱스인 파일이 검사 대상인 경우 그 인덱스 값을 저장
			else {
				num = middle;
				left = middle +1;
			}
		}
		
		return num;
	}

}
