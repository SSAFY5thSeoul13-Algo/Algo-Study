package week15.BOJ_14646_S4_욱제는결정장애야;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14646_S4_욱제는결정장애야 {
	static int N, count, max;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		arr = new int[N + 1];

		while (st.hasMoreTokens()) {
			int idx = Integer.parseInt(st.nextToken());

			//이미 스티커가 붙어있는 경우
			if (arr[idx] == 1) {
				arr[idx]--;
				count--;
			}
			//스티커가 안붙어있는 경우
			else {
				arr[idx]++;
				count++;
			}

			//count가 더 크면 max에 저장
			if (max < count)
				max = count;

		}

		System.out.println(max);
	}
}
