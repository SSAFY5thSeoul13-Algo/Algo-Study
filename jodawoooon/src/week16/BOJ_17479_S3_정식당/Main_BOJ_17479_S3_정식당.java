package week16.BOJ_17479_S3_정식당;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BOJ_17479_S3_정식당 {
	
	static int A, B, C, N;
	static long nmSum, spSum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws Exception{
		
		st = new StringTokenizer(br.readLine());
			
		A = Integer.parseInt(st.nextToken()); //일반메뉴 수
		B = Integer.parseInt(st.nextToken()); //특별메뉴 수
		C = Integer.parseInt(st.nextToken()); //서비스메뉴 수
			
		//normal, special, service 메뉴판
		HashMap<String, Integer> normal = new HashMap<>();
		HashMap<String, Integer> special = new HashMap<>();
		HashSet<String> service = new HashSet<>();
		
		//일반메뉴
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int cost = Integer.parseInt(st.nextToken());
			normal.put(name, cost);
		}
		
		//특별메뉴
		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int cost = Integer.parseInt(st.nextToken());
			special.put(name, cost);
		}		
		//서비스메뉴
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			service.add(name);
		}
	
		N = Integer.parseInt(br.readLine()); //손님이 주문하는 음식 수
		boolean flag = false; //서비스 시켰는지 아닌지
		//주문 가격 더하기
		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			
			if(normal.get(name)!=null) {
				nmSum += normal.get(name);
			}else if(special.get(name)!=null) {
				spSum += special.get(name);
			}else if(service.contains(name)) {
				if(flag) { //서비스메뉴는 단 하나만 주문할 수 있다.
					System.out.println("No");
					return;
				}
				flag = true;
			}
		}
		
		//특별메뉴는 일반메뉴에서 총 20,000원 이상을 주문해야 주문할 수 있다.
		if(spSum>0) {
			if(nmSum<20000) {
				System.out.println("No");
				return;
			}
		}
		
		//서비스메뉴는 일반메뉴와 특별메뉴에서 총 50,000원 이상을 주문해야 주문할 수 있다.
		if(flag) {
			if(nmSum+spSum<50000) {
				System.out.println("No");
				return;
			}
		}
		
		System.out.println("Okay");
		
	}
	
}
