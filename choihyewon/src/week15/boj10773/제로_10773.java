package week15.boj10773;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 제로_10773 {
	static int K,result;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i=0; i<K; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num!=0) {
				stack.push(num);
			}else {
				stack.pop();
			}
		}
		
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		
		System.out.println(result);

	}

}
