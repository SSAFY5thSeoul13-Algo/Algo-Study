package week28.boj1038;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1038_G5_감소하는_수 {
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 한자리수인 경우 자기 자신을 출력 
		if(N>=0 && N<=9) {
			System.out.println(N);
		}
		// 최대 감소하는 수는 9876543210로, 1022번째 감소하는 수 이므로 1023이상의 값이 나올 수 없다.
		else if(N>1022){
			System.out.println("-1");
		}else {
			for(int i=0; i<10; i++) {
				solution(i,1);
			}
			Collections.sort(list);
			System.out.println(list.get(N));
		}

	}
	private static void solution(long num, int idx) {
		if(idx>10) {
			return;
		}
		
		list.add(num);
		for(int i=0; i<num%10; i++) {
			solution((num*10)+i,idx+1);
		}
		
	}

}
