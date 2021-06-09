package week15.BOJ_1292_S5_쉽게푸는문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1292_S5_쉽게푸는문제 {
	/*
	
	 1 2 2 3 3 3 4 4 4 4 5 .. 
	 이러한 수열을 만들고 어느 일정한 구간을 주면 그 구간의 합을 구해야 한다.
	 */
	static int A,B,sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		int cnt = 0; //몇번째 숫자인지 카운트할 변수

		for (int i = 1; i <= B; i++) { //반복할 숫자
			for (int j = 0; j < i; j++) { //해당 숫자 크기만큼 반복한다.
				cnt++;
				
				if(cnt>=A) { //A번째 숫자부터 합을 구한다.
					sum += i;
				}
				if(cnt==B) { 
					//B번째 숫자까지 합을 더했으면 해당 sum을 출력 후 종료
					System.out.println(sum);
					return;
				}
			}
		}
		
	}
}
