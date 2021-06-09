package week15.BOJ_11068_S5_회문인수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_BJ_11068_S5_회문인수 {
/*	
	양의 정수를 입력받았을 때, 
	이 수가 어떤 B진법 (2 ≤ B ≤ 64)으로 표현하면 
	회문이 되는 경우가 있는지 알려주는 프로그램을 작성하시오.
	
*/	
	
	static int input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			input = Integer.parseInt(br.readLine());
			
			//B진법으로 표현가능한 회문인지 구분해보자
			//계속 B로 나눠주면된다.
			
			//input은 64이상 1000000이하의 정수
			
			
			boolean flag = false;
			for (int i = 2; i <= 64; i++) {
				if(solve(input, i)) { //회문이면 true
					flag = true; //회문맞으면 바로 break
					break;
				}
				
			}
			
			if(!flag) {
				System.out.println(0);
			}else {
				System.out.println(1);
			}
			
		}
	
	}
	private static boolean solve(int num, int b) {
		ArrayList list = new ArrayList<>();
		
		while(num/b != 0) {
			list.add(num%b);
			num/=b;
		}
		list.add(num%b);
		
		boolean flag = true;
		for (int i = 0; i <= list.size()/2; i++) {
			if(list.get(i)!=list.get(list.size()-i-1)) {
				//회문이 아니라면 바로 break
				flag=false;
				break;
			}
		}
		
		if(flag) return true;
		else return false;
	}
	
}
