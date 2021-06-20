package week16.BOJ_10799_S3_실버;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10799_S3_쇠막대기 {
	static int count, result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int length = str.length();
		
		char prev_c = '(';
		
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			
			if(c == '(') {
				count++;
			}
			else if( c == ')' && prev_c == '(' ) {
				count--;
				result += count;
			}
			else {
				count--;
				result += 1;
			}
			
			prev_c = c;
		}
		
		System.out.println(result);
		
	}
}
