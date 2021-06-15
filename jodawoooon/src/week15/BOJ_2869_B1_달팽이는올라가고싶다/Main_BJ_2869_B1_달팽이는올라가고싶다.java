package week15.BOJ_2869_B1_달팽이는올라가고싶다;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2869_B1_달팽이는올라가고싶다 {
	static int A,B,V,ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken()); //낮에 올라갈 수 있는 A미터
		B = Integer.parseInt(st.nextToken()); //밤에 미끄러지는 B미터
		V = Integer.parseInt(st.nextToken()); //높이가 V미터인 나무
		
		//달팽이가 나무 막대를 모두 올라가는데 며칠이 걸리는지 
		//1. A
		//2. A-B+A
		//3. A-B+A-B+A
		//4. A-B+A-B+A-B+A
		// ....
		//meter(day) = (A-B)*(day-1)+A
		
		// (A-B)*(day-1)+A >= V이려면
		// day-1 >= (V-A)/(A-B)
		// day >= (V-A)/(A-B) + 1
		
		ans = (int)Math.ceil((double)(V-A)/(double)(A-B)) +1;
		System.out.println(ans);
	}
	
}
