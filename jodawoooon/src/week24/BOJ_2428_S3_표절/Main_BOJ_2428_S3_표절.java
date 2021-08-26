package week24.BOJ_2428_S3_표절;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 2428 표절
 * @Author : Daun JO
 * @Date : 2021. 8. 26
 * @Algorithm : Sliding Window
 *
 */


public class Main_BOJ_2428_S3_표절 {
	
	
	static int N,files[];
	static long ans;
	public static void main(String[] args) throws Exception {
		
		Queue<Integer> queue = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //솔루션의 개수
	
		files = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			files[i] = Integer.parseInt(st.nextToken());
		}


		
		/* 1. 먼저 files를 오름차순 정렬한다. 
		/* 첫 번째 조건 : size(Fi) ≤ size(Fj) */
		Arrays.sort(files);
		
		
		int left = 0;
		int right = 0; //부분 배열의  인덱스를 가리키는 left, right(0으로 초기화)
		
		while(left < N) {
			
			/* 2.  size(Fi) ≥ 0.9 × size(Fj) */
			while(true) {
				if(right>=N-1) break; //범위 벗어나면 break
				
				int Fi = files[left];
				int Fj = files[right+1];
				if(Fi < Fj*0.9) break; //조건 만족하지 않으면 break
				else right++;
			}

			ans += right-left; //검사해야하는 파일의 수
			left++;
		}
		
		System.out.println(ans);
		
		
		
	}
}
