package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_S1_연산자끼워넣기 {
	static int N;
	static int[] arr;
	static int[] count = new int[4];
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//숫자 배열로 입력
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//연산자 정보를 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			int num = Integer.parseInt(st.nextToken());
			count[i] = num;
		}
		
		//값을 찾는 메소드
		find(arr[0], 1, count[0], count[1], count[2], count[3]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	//두 숫자를 연산하는 메소드
	static int calc(int num1, int num2, char c) {
		switch(c){
			case '+':
				return num1+num2;
			case '-':
				return num1-num2;
			case '*':
				return num1*num2;
			case '/':
				return num1/num2;
		}
		
		return 0;
	}
	
	//최소, 최대값을 찾는 메소드
	static void find(int num, int nextIdx, int plus, int minus, int multi, int div) {
		//연산이 끝난 경우
		if(nextIdx == N) {
			min = Math.min(min, num);
			max = Math.max(max, num);
			return;
		}
		int result = 0;
		
		//연산자가 남아있는 경우 모든 연산의 경우를 실행한다
		if(plus > 0) {
			result = calc(num, arr[nextIdx], '+');
			find(result, nextIdx+1, plus-1, minus, multi, div);
		}
		if(div > 0) {
			result = calc(num, arr[nextIdx], '/');
			find(result, nextIdx+1, plus, minus, multi, div-1);
		}
		if(minus > 0) {
			result = calc(num, arr[nextIdx], '-');
			find(result, nextIdx+1, plus, minus-1, multi, div);
		}
		if(multi > 0) {
			result = calc(num, arr[nextIdx], '*');
			find(result, nextIdx+1, plus, minus, multi-1, div);
		}
		
	}
}