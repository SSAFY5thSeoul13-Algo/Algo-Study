package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_문자열폭발_9935 {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열
		char[] str = br.readLine().toCharArray();
		// 폭발문자열
		char [] bomb = br.readLine().toCharArray();
		
		// 폭발문자열의 마지막 문자 인덱스
		int bLast = bomb.length - 1;
		// 스택에 들어갈 인덱스
		int inIdx = 0;
		// 스택
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<str.length; i++) {
			
			char cur = str[i];
			
			// 문자가 폭탄의 마지막 문자라면
			if(cur == bomb[bLast]) {
				
				int idx = bLast - 1;
				
				boolean suc = true;
				
				if(inIdx - bLast >= 0) {
					int limit = inIdx - bLast;
					// 폭발문자열 과 같은지 확인 
					for(int j=inIdx-1; j>=limit; j--) {
						if( sb.charAt(j) != bomb[idx--]) {
							suc = false;
							break;
						}
					}
					
					// 같다면
					if(suc) {
						// 크기 줄이기
						inIdx -= bLast;
						sb.setLength(inIdx);
						continue;
					}
				}
			}
			
			// 문자열 넣기
			++inIdx;
			sb.append(cur);
		}
		// 비어있다면
		if(inIdx == 0) {
			System.out.println("FRULA");
		}else {
			System.out.println(sb.toString());
		}
	}

}
