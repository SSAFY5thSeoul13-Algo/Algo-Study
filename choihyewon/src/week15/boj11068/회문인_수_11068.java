package week15.boj11068;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class 회문인_수_11068 {
	static List<Integer> list;
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		boolean isTrue = false;
		for(int t=1; t<=T; t++) {
			int num = Integer.parseInt(br.readLine());
			
			for(int i=2; i<=64; i++) {
				// 숫자 num을 b진법으로 변환 
				isTrue = parse(num,i);
				
				// 회문이라면 더이상 알아볼 필요가 없다!
				if(isTrue) {
					break;
				}
				
			}
			
			if(isTrue) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
			
		}
	}

	private static boolean parse(int num, int b) {
		// list 초기화 
		list =  new ArrayList<Integer>();
		while(num>0) {
			list.add(num%b);
			num = num / b;
		}

		// 회문인지 아닌지 체크 
		for(int i=0; i<=list.size()/2; i++) {
			if(list.get(i) != list.get(list.size()-i-1)) {
				return false;
			}
		}

		return true;
	
	
	}

}
