package week22.BOJ_2110_S1_공유기설치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110_S1_공유기설치 {
	static int N, C, result;
	static int[] houses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		houses = new int[N];
		
		//각 집의 좌표 저장
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}

		//집의 좌표를 오름차순으로 정렬
		Arrays.sort(houses);

		//공유기 설치 거리를 이분탐색하기 위한 범위
		int left = 0;
		int right = houses[N - 1] - houses[0];

		while (left <= right) {
			//공유기를 설치할 거리의 기준
			int mid = (left + right) / 2;
			
			//설치한 공유기의 수
			int count = calc(mid);

			if (count >= C) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(result);
	}

	static int calc(int distance) {
		int now = houses[0];
		int count = 1;
		//공유기 사이의 최단 거리
		int minDistance = Integer.MAX_VALUE;

		for (int i = 1; i < houses.length; i++) {
			int num = houses[i] - now;

			// 공유기 설치 한 경우
			if (num >= distance) {
				count++;
				now = houses[i];
				minDistance = Math.min(minDistance, num);
			}
		}

		if (count >= C) {
			result = Math.max(result, minDistance);
		}

		return count;
	}
}
