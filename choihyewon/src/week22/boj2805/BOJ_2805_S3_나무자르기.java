package week22.boj2805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805_S3_나무자르기 {
	static int N,M,result;
	static int[] tree;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tree);
		
		int left = 0;
		int right = 1000000000;
		
		while(left<=right) {
			int mid = (left+right)/2;
			long slice = 0;
			for(int i=0; i<N; i++) {
				if(tree[i] - mid > 0 ) {
					slice += tree[i] - mid;
				}
			}
			
			if(M<=slice) {
				left = mid + 1;
			}else if(M>slice){
				right = mid - 1;
			}
		}
		
		System.out.println(right);
		

	}

}
