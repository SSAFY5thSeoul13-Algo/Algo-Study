package week5.boj1157;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 단어_공부_1157 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 알파벳의 수 만큼 배열 생성 
		int[] Alphabet = new int[26];
		
		// 출력은 대문자로 하기때문에 대문자로 바꾸어 준다. 
		String str = br.readLine().toUpperCase(); 
		
		for(int i=0; i<str.length(); i++) {
			Alphabet[str.charAt(i)-'A']++;
		}
		
		int max = -1;
		char result = 0;
		for(int i=0; i<Alphabet.length; i++) {
			if(max < Alphabet[i]) {
				max = Math.max(max, Alphabet[i]);
				result = (char) (i+65);
			}else if(max == Alphabet[i]){
				result = '?';
			}
		}
		
		System.out.println(result);
		
		
		

	}

}
