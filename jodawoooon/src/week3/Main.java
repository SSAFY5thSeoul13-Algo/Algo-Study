package week3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;	// 피연산자 수, 연산자 수
	static List<Integer> operand = new ArrayList<>();
	static List<Integer> operator = new ArrayList<>();
	
	static int[] tgt;	// 순열 타겟배열
	static final int PLUS=0, MINUS=1, MUL=2, DIV=3;
	
	static int min, max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = N-1;
		tgt = new int[M];
		
		/* 피연산자 입력 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			operand.add(Integer.parseInt(st.nextToken()));
		}
		
		/* 연산자 입력 */
		st = new StringTokenizer(br.readLine());
		for(int oper=0; oper<4; oper++) {
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				operator.add(oper);
			}
		}
		
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		// 순열시작
		perm(0,0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	/* 연산자를 순열로 정렬 */
	static void perm(int num, int mask) {
		
		if(num == M) {
			System.out.println(Arrays.toString(tgt));
			cal();
			return;
		}
		
		for(int i=0; i<M; i++) {
			if( (mask & 1<<i) != 0) continue;
			tgt[num] = operator.get(i); 
			perm(num+1, mask|1<<i);
		}
	}

	/* 순열로 정해진 연산자를 이용해서 계산 */
	static void cal() {
		
		int sum=operand.get(0);
		
		for(int i=0; i<M; i++) {
			int oper = tgt[i];
			
			int operand2 = operand.get(i+1);
			
			switch(oper) {
			case PLUS:
				sum += operand2;
				break;
			case MINUS:
				sum -= operand2;
				break;
			case MUL:
				sum *= operand2;
				break;
			case DIV:
				sum /= operand2;
				break;
			}
		}
		max = Math.max(max, sum);
		min = Math.min(min, sum);
	}
}