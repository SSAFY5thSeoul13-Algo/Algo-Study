package week22.BOJ_2110_S1_공유기설치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 집의 수, 설치할 공유기 수
	static int N, C;
	// 집의 좌표
	static int[] house;
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
			
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		// 집위치 정렬
		Arrays.sort(house);
		
		// 아무리 작아도 1부터, 최대 집사이의 간격을 탐색 범위로 지정한다.
		System.out.println(binarySearch(1, house[N-1] - house[0]));
	}
	/**
	 * 이분탐색
	 * @param left : 최소 집 사이 간격
	 * @param right : 최대 집 사이 간격
	 * @return 두 공유기 사이의 최대간격
	 */
	public static int binarySearch(int left, int right) {
		
		int ans = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			
			// 공유기가 설치가능하다면 더 넓은 간격으로 탐색
			if(isPossible(mid)) {
				ans = mid;
				left = mid + 1;
			}
			// 아니라면 더 좁은 간격으로 탐색
			else {
				right = mid - 1;
			}
			
		}
		
		return ans;
	}
	/**
	 * 결정함수
	 * @param mid : 확인하려는 특정 간격
	 * @return 공유기 설치가능 유무
	 */
	public static boolean isPossible(int mid) {
		int start = house[0];
		int cnt = 1;
		for(int i=1; i<N; i++) {
			// 공유기를 설치할 두 집간의 간격
			int diff = house[i] - start;
			
			// 특정간격보다 크다면
			if(diff >= mid) {
				cnt++;				// 설치횟수 증가
				start = house[i];	// 다음 간격을 찾기 위해 시작위치 업데이트
			}
		}
		// C개 이상 설치할 수 있는지 확인
		return cnt >= C;
	}

}
