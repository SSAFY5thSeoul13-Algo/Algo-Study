package week30.BOJ_13558_G2_등차수열의개수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13558_G2_등차수열의개수 {
	static int N, max;
	static long count;
	static int[] arr;
	static long[] rightNumbers, leftNumbers;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		//기준 숫자 우측에 있는 수 카운트
		rightNumbers = new long[30001];
		//기준 숫자 좌측에 있는 수 카운트
		leftNumbers = new long[30001];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
			rightNumbers[arr[i]]++;
		}
		
		rightNumbers[arr[1]]--;
		
		//중간 숫자 수만큼 반복
		for (int i = 2; i <= N-1; i++) {
			leftNumbers[arr[i-1]]++;
			rightNumbers[arr[i]]--;
			
			count += leftNumbers[arr[i]]*rightNumbers[arr[i]];
			
			for (int j = 1; (j <= arr[i]-1) && (arr[i] + j <= max); j++) {
				//중간 숫자를 기준으로 좌,우 차이가 같은 수 카운트의 곱만큼 더함
				count += leftNumbers[arr[i] - j]*rightNumbers[arr[i] + j];
				count += leftNumbers[arr[i] + j]*rightNumbers[arr[i] - j];
			}
		}
		
		System.out.println(count);
	}
}
