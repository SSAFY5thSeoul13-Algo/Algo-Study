package week16.boj17479;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 정식당_17479 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		String result = "Okay";
		
		HashMap<String, Long> general = new HashMap<>();
		HashMap<String, Long> special = new HashMap<>();
		HashSet<String> service = new HashSet<>();
		
		// 일반 메뉴 등록 
		for(int i=0; i<A; i++) {
			st = new StringTokenizer(br.readLine());
			String menu = st.nextToken();
			long price = Integer.parseInt(st.nextToken());
			
			general.put(menu, price);
		}
		// 특별 메뉴 등록 
		for(int i=0; i<B; i++) {
			st = new StringTokenizer(br.readLine());
			String menu = st.nextToken();
			long price = Integer.parseInt(st.nextToken());
			
			special.put(menu, price);
		}
		// 서비스 메뉴 등록 
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(br.readLine());
			String menu = st.nextToken();
			
			service.add(menu);
		}
		
		int N = Integer.parseInt(br.readLine());
		
		long gn_cost = 0;	// 손님이 주문한 일반 메뉴 총 가격 
		long sp_cost = 0;	// 손님이 주문한 스페셜 메뉴 총 가격 
		int sv_cnt = 0;		// 서비스 메뉴는 가격이 없으므로 몇 번 시켰는지 카운트 
		// 손님이 주문하는 음식 
		for(int i=0; i<N; i++) {
			String menu = br.readLine();

			// 일반 메뉴인 경우 
			if(general.containsKey(menu)) {
				gn_cost += general.get(menu);				
			}
			// 특별메뉴인 경우 
			else if(special.containsKey(menu)) {
				sp_cost += special.get(menu);
			}
			else if(service.contains(menu)) {
				sv_cnt++;
			}
		}
		
		// 조건 확인 
		// 특별메뉴는 일반메뉴에서 총 20,000원 이상을 주문해야 주문할 수 있다.
		if(sp_cost>0 && gn_cost<20000) {
			result = "No";
		}
		// 서비스 메뉴를 주문했다면 
		else if(sv_cnt >= 1) {
			// 서비스 메뉴는 두개 이상 주문을 할 수 없고, 일반메뉴와 특별메뉴에서 총 50,000원 이상을 주문해야 주문할 수 있다.
			if(sv_cnt > 1 || gn_cost + sp_cost < 50000) {
				result = "No";
			}
		}

		System.out.println(result);

	}

}
				
		