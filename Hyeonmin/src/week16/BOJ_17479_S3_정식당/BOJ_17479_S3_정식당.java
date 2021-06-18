package week16.BOJ_17479_S3_정식당;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_17479_S3_정식당 {
	static int A, B, C, N;
	static Map<String, Long> mapA = new HashMap<>();
	static Map<String, Long> mapB = new HashMap<>();
	static Set<String> mapC = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			mapA.put(st.nextToken(), Long.parseLong(st.nextToken()));
		}

		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			mapB.put(st.nextToken(), Long.parseLong(st.nextToken()));
		}

		for (int i = 0; i < C; i++) {
			mapC.add(br.readLine());
		}

		N = Integer.parseInt(br.readLine());

		long priceA = 0;
		long priceB = 0;

		int countC = 0;

		for (int i = 0; i < N; i++) {
			String menu = br.readLine();

			if (mapA.containsKey(menu)) {
				priceA += mapA.get(menu);
			}
			else if (mapB.containsKey(menu)) {
				priceB += mapB.get(menu);
			}
			else if (mapC.contains(menu)) {
				countC++;
			}
		}
		
		boolean isPossible = true;
		
		if(countC >1) {
			isPossible = false;
		}
		else if(countC == 1 && priceA + priceB < 50000) {
			isPossible = false;
		}
		else if(priceB > 0 && priceA < 20000 ) {
			isPossible = false;
		}
		
		System.out.println(isPossible ? "Okay" : "No");
	}
}
