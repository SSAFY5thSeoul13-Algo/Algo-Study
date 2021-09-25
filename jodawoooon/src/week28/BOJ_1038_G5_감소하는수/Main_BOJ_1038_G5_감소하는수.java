package week28.BOJ_1038_G5_감소하는수;

import java.io.*;
import java.util.*;

/**
 * 
 * 
 * ✨ Algorithm ✨
 * @Problem : BOJ 1038 G5 감소하는수
 * @Author : Daun JO
 * @Date : 2021. 9. 26. 
 *
 */
public class Main_BOJ_1038_G5_감소하는수 {
	static int N,ans,cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		cnt = -1;
		
		/*
		 * 감소하는 수
		 * 0~10
		 * 10 .. 20... 21.... 90...
		 */
		
		if(N < 10) { 								//1의 자리
			System.out.println(N);
			System.exit(0);
		}
		if(N == 1022) {
			System.out.println("9876543210");
			System.exit(0);
		}
		if(N > 1022) {
			System.out.println(-1);
			System.exit(0);
		}
		
		for(int l =0 ; l <= 10 ; l++) {				// ㅣ = 감소하는 수의 길이. 10자리를 넘어갈 수 없다
			for(int i =0 ; i< 10 ; i++) {			// i = 감소하는 수의 첫번째 자리수
				getDecNum(i,1,l);					// (감소하는 수 , 수의 자릿수, 만들려는 자리수)
				if(cnt>=N) {
					System.out.println(ans);
					System.exit(0);
				}
			}
		}
		
		
		
	}
	private static void getDecNum(int n, int idx, int len) {
		if(idx==len) {
			cnt++; 									//감소하는 수 갯수 증가
			if(cnt==N) ans = n;						//N번째 감소하는 수
			return;
		}

		
		for(int i = 0 ; i< n%10 ; i++) {			//현재 감소하는 수의 맨 끝 자릿수보다 작은 수
			getDecNum(n*10+i, idx+1, len);				//감소하는 수 n의 자리수 높이기 (*10), 감소하는수 + i하여 그 다음 감소하는 수 구하기
		}
	}
	
	
}
