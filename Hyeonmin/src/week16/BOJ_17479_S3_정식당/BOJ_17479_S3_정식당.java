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

		//일반 메뉴의 수
		A = Integer.parseInt(st.nextToken());
		//특별 메뉴의 수
		B = Integer.parseInt(st.nextToken());
		//서비스 메뉴의 수
		C = Integer.parseInt(st.nextToken());

		//일반 메뉴 저장
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			mapA.put(st.nextToken(), Long.parseLong(st.nextToken()));
		}

		//특별 메뉴 저장
		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			mapB.put(st.nextToken(), Long.parseLong(st.nextToken()));
		}

		//서비스 메뉴 저장
		for (int i = 0; i < C; i++) {
			mapC.add(br.readLine());
		}

		N = Integer.parseInt(br.readLine());

		//일반 메뉴 주문액
		long priceA = 0;
		//특별 메뉴 주문액
		long priceB = 0;
		//서비스 메뉴 주문 수
		int countC = 0;

		for (int i = 0; i < N; i++) {
			//주문한 메뉴
			String menu = br.readLine();

			//일반 메뉴인 경우
			if (mapA.containsKey(menu)) {
				priceA += mapA.get(menu);
			}
			//특별 메뉴인 경우
			else if (mapB.containsKey(menu)) {
				priceB += mapB.get(menu);
			}
			//서비스 메뉴인 경우
			else if (mapC.contains(menu)) {
				countC++;
			}
		}
		
		//주문 가능 여부
		boolean isPossible = true;
		
		//서비스 메뉴를 2개 이상 주문한 경우
		if(countC >1) {
			isPossible = false;
		}
		//서비스 메뉴를 주문 했는데 주문 조건이 안되는 경우
		else if(countC == 1 && priceA + priceB < 50000) {
			isPossible = false;
		}
		//특별 메뉴 주문을 했는데 주문 조건이 안되는 경우
		else if(priceB > 0 && priceA < 20000 ) {
			isPossible = false;
		}
		
		System.out.println(isPossible ? "Okay" : "No");
	}
}
