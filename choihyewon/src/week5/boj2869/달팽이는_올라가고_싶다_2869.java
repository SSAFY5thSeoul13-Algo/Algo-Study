package week5.boj2869;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달팽이는_올라가고_싶다_2869 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		int tmp = (V-A)/(A-B) + 1;
		int day = (V-A)%(A-B);
		
		if(day==0) {
			System.out.println(tmp);
		}else{
			System.out.println(tmp+1);
		}
	}

}
