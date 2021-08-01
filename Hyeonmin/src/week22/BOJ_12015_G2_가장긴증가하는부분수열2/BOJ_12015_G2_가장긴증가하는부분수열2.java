package week22.BOJ_12015_G2_가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12015_G2_가장긴증가하는부분수열2 {
	static int N;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		//부분 수열을 저장할 배열
		numbers = new int[N+1];
		
		//부분 수열에서 교체될 숫자가 들어갈 인덱스
		int idx = 1;
		//부분 수열에서 새로 추가될 숫자가 들어갈 인덱스
		int max = 1;

		//배열의 앞 부분부터 숫자를 적절한 위치에 저장
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			//이분 탐색을 할 왼쪽 끝 범위
			int left = 0;
			//이분 탐색을 할 오른쪽 끝 범위
			int right = max;
			
			//새로 들어올 숫자가 부분 수열에 있는 모든 숫자보다 크면 뒤에 새로 저장한다
			if(num > numbers[max-1]) {
				numbers[max++] = num;
				continue;
			}
			
			int mid = (left+right)/2;
			
			boolean isSame = false;
			
			//이분탐색
			//부분 수열에서 num 숫자보다 큰 수중에 가장 작은 숫자가 있는 위치를 num으로 변경한다
			while(left <= right) {
				mid = (left+right)/2;
				
				//부분 수열에 같은 숫자가 있는 경우
				if(numbers[mid] == num) {
					isSame = true;
					break;
				}
				else if(numbers[mid] > num) {
					right = mid - 1;
				}
				else {
					left = mid + 1;
				}
			}
			
			if(isSame)
				continue;
			
			if(numbers[mid] < num)
				idx = mid+1;
			else
				idx = mid;
			
			numbers[idx++] = num;
		}
		
		System.out.println(max-1);
	}
}
