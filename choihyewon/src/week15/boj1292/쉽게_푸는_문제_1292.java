package week15.boj1292;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 쉽게_푸는_문제_1292 {
	static int A,B,result;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		arr = new int[1001];
		
		int cnt = 1;
		for(int i=1; i<=1000; i++) {
			for(int j=0; j<i; j++) {
				if(cnt == 1001)	break;
				arr[cnt] = i;
				cnt++;
			}
		}
		
		for(int i=A; i<=B; i++) {
			result += arr[i];
		}
		
		System.out.println(result);
		
		
		
	}

}
