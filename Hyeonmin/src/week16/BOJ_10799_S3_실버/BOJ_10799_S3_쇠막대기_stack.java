package week16.BOJ_10799_S3_실버;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10799_S3_쇠막대기_stack {
	static int result;
	static Stack<Character> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int length = str.length();
		
		char prev_c = '(';
		
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			
			if(c == '(') {
				stack.push(c);
			}
			else if( c == ')' && prev_c == '(' ) {
				stack.pop();
				result += stack.size();
			}
			else {
				stack.pop();
				result += 1;
			}
			
			prev_c = c;
		}
		
		System.out.println(result);
		
	}
}
