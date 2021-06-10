package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_괄호추가하기_16637 {
	
	/* 연산자숫자, 정답 */
	static int M, max = Integer.MIN_VALUE;
	/* 피연산자 배열 */
	static int[] operand;
	/* 연산자 배열 */
	static char[] operator;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/* 수식의 길이 */
		int N = Integer.parseInt(br.readLine());
		M = N/2;				// 연산자 수 = 수식의 길이/2
		
		/* 피연산자 수는 연산자 수보다 1개 많음. */
		operand = new int[M+1];
		operator = new char[M];
		
		int randIdx = 0;
		int ratorIdx = 0;
		
		/* 연산자,피연산자 구분에서 배열에 저장 */
		String str = br.readLine();
		for(int i=0; i<N; i++) {
			char cur = str.charAt(i);
			if('0' <= cur && cur <= '9') {
				operand[randIdx++] = cur - '0';
			}else {
				operator[ratorIdx++] = cur;
			}
		}
		
		/* 연산할게 없으면 그대로 출력 */
		if(N == 1) {
			max = operand[0];
		}else {
			/* 첫번째 연산자를 선택X(0)*/
			comb(1, 1, 0);
			/* 첫번째 연산자를 선택(0)*/
			comb(1, 1, 1);
		}
		
		System.out.println(max);
		
	}
	
	/* num : 순회한 연산자의 수
	 * start : for문의 시작점
	 * mask : bitCount -> 조합의 선택 flag
	 * */
	static void comb(int num, int start, int mask) {
		
		if(num == M) {
			cal(mask);
			return;
		}
		
		/* 		괄호가 겹치면 안된다 => 연속적으로 괄호를 사용할수없다!
		 * */
		for(int i=start; i<M; i++) {
			if((mask & (1<<i)) != 0) continue;	// 이미 선택한경우
			else if( (mask & (1<<i-1)) == 0) {	// 한칸 이전 연산자에 괄호를 사용하지 않았다면
				comb(num+1, i+1, mask | 1<<i);		// 괄호 사용 
			}
			comb(num+1, i+1, mask);				// 괄호 사용 X
			
		}
	}

	static void cal(int mask) {
		int sum = operand[0];
		for(int i=0; i<M;i++){
			
			/* 다음연산자에 괄호가 있다면 */
			if(i!=M-1 && (mask & (1<<i+1)) != 0) {
				/* 다음 연산부터 실시 */
				int nextIdx = i+1;
				int nextResult = operation(operand[nextIdx], operand[nextIdx+1], operator[nextIdx]);
				
				/* 다음연산후 결과와 연산 */
				sum = operation(sum, nextResult, operator[i]);
				i++;
			} else {
				/* 현재 연산자 연산 */
				sum = operation(sum, operand[i+1], operator[i]);
			}
		}
		/* 최댓값 갱신 */
		max = Math.max(max, sum);
	}
	/* a : 피연산자 1
	 * b : 피연산자 2
	 * oper : 연산자
	 * */
	static int operation(int a, int b, int oper) {
		int result = 0;

		switch(oper) {
		case'+':
			result = a+b;
			break;
		case'-':
			result = a-b;
			break;
		case'*':
			result = a*b;
			break;
		}
		
		return result;
	}
}

