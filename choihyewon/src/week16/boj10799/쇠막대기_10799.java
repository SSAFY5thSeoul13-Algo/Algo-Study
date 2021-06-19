package week16.boj10799;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 쇠막대기_10799 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		
		String str = br.readLine();
		
		int iron = 0;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='(') {
				stack.add('(');
			}else if(str.charAt(i)==')') {
				stack.pop();
				if(str.charAt(i-1)=='(') {
					iron += stack.size();
				}else if(str.charAt(i-1)==')') {
					iron += 1;
				}
			}
		}
		
		System.out.println(iron);
		
		

	}

}
