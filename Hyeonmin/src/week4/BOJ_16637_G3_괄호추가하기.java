package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_16637_G3_괄호추가하기 {
	static int N;
	//수식
	static String str;
	//처음 시작 숫자들
	static int[] numbers;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N/2+1];
		
		//수식을 읽어옴
		str = br.readLine();
		
		//숫자 부분을 배열에 저장
		for (int i = 0; i < str.length(); i++) {
			if(i%2 == 0) {
				numbers[i/2] = Character.getNumericValue(str.charAt(i));
			}
		}

		//숫자가 1개인 경우 그대로 출력
		if(N == 1) {
			System.out.println(numbers[0]);
			return;
		}
		
		//연산시작
		process(1, 0, numbers[0]);
		
		//결과 출력
		System.out.println(max);
	}
	
	// idx: 연산자의 인덱스, count: 연산을 진행한 횟수, num: 현재까지 연산한 결과
	static void process(int idx, int count, int num) {
		//모든 연산이 끝난 경우
		if(count == N/2) {
			max= Math.max(max, num);
					
			return;
		}
		
		//a+b인 경우 현재 연산자의 인덱스에서 앞 뒤 숫자를 더하고 다음으로 넘어간다
		int next = calc(str.charAt(idx), num, numbers[(idx+1)/2]);
		process(idx+2, count+1, next);
		//a+(b+c) 경우 현재 연산자의 다음 연산자를 먼저 연산하고 현재 연산자 연산을 수행한다
		if(idx+3 <N) {
			next = calc(str.charAt(idx+2), numbers[(idx+1)/2], numbers[(idx+3)/2]);
			next = calc(str.charAt(idx), num, next);
			//2번의 연산을 수행 했으니 count+2를 넘긴다
			process(idx+4, count+2, next);
		}
	}
	
	//연산 수행 메소드
	static int calc(char oper, int num1, int num2) {
		switch(oper) {
		case '+':
			return num1+num2;
		case '-':
			return num1-num2;
		case '/':
			return num1/num2;
		case '*':
			return num1*num2;
		}
		
		return 0;
	}
}
