package week15.BOJ_1292_S5_쉽게푸는문제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1292_S5_쉽게푸는문제 {
	static int start, end, sum;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//구간의 시작
		start = Integer.parseInt(st.nextToken());
		//구간의 끝
		end = Integer.parseInt(st.nextToken());
		//수열을 저장할 배열
		arr = new int[end+1];
		
		//수열에 저장될 수
		int num = 1;
		//현재 수가 수열에 저장된 횟수
		int count = 0;
		
		//1부터 end까지의 배열에
		for (int i = 1; i <= end; i++) {
			arr[i] = num;
			//해당 숫자를 배열에 넣을 때 마다 카운트
			count++;
			//숫자의 값만큼 배열에 저장이 되었으면 다음 숫자로 가고 카운트 초기화
			if(num == count) {
				num++;
				count = 0;
			}
		}
		
		//원하는 범위만큼 해당 수를 더함
		for (int i = start; i <= end; i++) {
			sum += arr[i];
		}
		
		System.out.println(sum);
	}
}
