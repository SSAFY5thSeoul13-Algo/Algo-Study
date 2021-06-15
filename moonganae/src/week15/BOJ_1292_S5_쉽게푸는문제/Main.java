package week15.BOJ_1292_S5_쉽게푸는문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int cnt = 1;
		int idx = 2;
		
		// A에 해당하는 숫자 구하기
		while(true) {
			if(cnt >= A) break;
			cnt += idx++;
			
		}
		
		// A와 같은 수들을 모두 더하기
		int sum = (cnt-A+1) * (idx-1);
		
		// B에 해당하는 숫자와 B까지의 합 구하기
		while(true) {
			if(cnt >= B) break;
			sum += idx*idx;
			cnt += idx++;
		}
		
		// B를 벗어난 합 빼기
		sum -= (cnt-B) * (idx-1);
		
		System.out.println(sum);

	}

}
