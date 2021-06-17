package week16.BOJ_17479_S3_정식당;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 풀이시간 : 32분
 */
public class Main {

	public static void main(String[] args) throws Exception{
		/**
		 * 정식당만의 특별한 음식주문법
		 * 일반메뉴 A개 
		 * 특별메뉴 B개
		 * 서비스메뉴 C개
		 * 
		 * 특별메뉴는 일반메뉴에서 총 20,000원 이상을 주문해야 주문할 수 있다.
		 * 서비스메뉴는 일반메뉴와 특별메뉴에서 총 50,000원 이상을 주문해야 주문할 수 있따.
		 * 서비스메뉴는 단 하나만 주문할 수 있다.
		 * 
		 * [풀이방법]
		 * 1. 해쉬자료구조를 이용하여 메뉴이름을 key, 가격, type을 value로 저장한다.
		 * 2. 타입별로 가격을 계산함. 
		 * 
		 * [막힌점]
		 * 1. 메뉴를 하나씩 선택하면서 메뉴선택제한을 벗아나는지 확인했는데, 끝가지 다 받고 제한했어야했음.
		 * 2. 메뉴의 가격이 높아 int대신 long으로 해서 풀었어야 했음.
		 * 
		 */
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] menuCnt = new int[3];
		menuCnt[0] = Integer.parseInt(st.nextToken());
		menuCnt[1] = Integer.parseInt(st.nextToken());
		menuCnt[2] = Integer.parseInt(st.nextToken());
		
		HashMap<String, Menu> menu = new HashMap<>();
		
		// A:일반메뉴 , B:특별메뉴 , C:서비슨메뉴
		final int A=0, B=1, C=2;
		
		/* 일반 특별 서비스 메뉴 순서대로 */
		for(int i=0; i<3; i++) {
			for(int j=0; j<menuCnt[i]; j++) {
				st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				
				// 서비스 메뉴인 경우 가격이 없어 -1로 셋팅
				int price = -1;
				try {
					price = Integer.parseInt(st.nextToken());
				} catch(Exception e) {
					
				}
				
				menu.put(name, new Menu(price, i));
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		long[] prices = new long[3];
		
		/* 주문받기 */
		for(int i=0; i<N; i++) {
			String name = br.readLine();
			Menu cur = menu.get(name);
			prices[cur.type] += cur.price;
		}
		
		// 특별메뉴를 주문했는데 일반메뉴가 20000원 미만인경우 -> No
		if(prices[B] > 0 && prices[A] < 20000) {
			System.out.println("No");
		}
		// 서비스메뉴를 주문했는데, 2개이상 주문했거나, 일반+특별메뉴가 50000원 미만인경우 -> No
		else if(prices[C] < -1 || (prices[C] == -1 && prices[A]+prices[B] < 50000)) {
				System.out.println("No");
		}
		// 아니라면 Okay
		else {
			System.out.println("Okay");
		}
		
	}
	
	static class Menu{
		int price, type;

		public Menu(int price, int type) {
			super();
			this.price = price;
			this.type = type;
		}
	}

}
