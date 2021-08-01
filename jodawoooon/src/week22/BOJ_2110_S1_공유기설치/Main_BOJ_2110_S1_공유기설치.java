package week22.BOJ_2110_S1_공유기설치;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2110_S1_공유기설치 {
	
	static int N, C;
	static int[] map;
	static int left, right, mid, ans; //인접한 두 공유기 사이의 최대 거리
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[N]; //집의 좌표

		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(map);
		
		
		left = 1;
		right = map[N-1]-map[0];
		
		
		while(left<=right) {
			mid = (left+right)/2; //공유기 사이 간격

			//mid 간격 기준으로 설치
			// C개 이상 설치 가능하면 간격 늘린다, 아니면 줄인다
			if(canInstall(mid)) { //C개 이상 설치 가능하면
				ans = Math.max(ans, mid);
				left = mid + 1;
			}else right = mid - 1;
		}
		
		System.out.println(ans);
		
	}
	
	private static boolean canInstall(int distance) {
		
		int cur = map[0];
		int cnt = 1; //0번집 설치
		
		for (int i = 1; i < N; i++) {
			if(map[i] - cur >= distance) {
				cnt++;
				cur = map[i];
			}
		}

		
		if(cnt>=C) return true; //C개 이상 설치 가능하면
		return false;

	}

}
