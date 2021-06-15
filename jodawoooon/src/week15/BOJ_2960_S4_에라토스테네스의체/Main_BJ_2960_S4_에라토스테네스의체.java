package week15.BOJ_2960_S4_에라토스테네스의체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_2960_S4_에라토스테네스의체 {
	

/*	
	2부터 N까지 모든 정수를 적는다.
	아직 지우지 않은 수 중 가장 작은 수를 찾는다. 이것을 P라고 하고, 이 수는 소수이다.
	P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
	아직 모든 수를 지우지 않았다면, 다시 2번 단계로 간다.
	*/
	static int N, K, ans;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		//1. 2부터 N까지 모든 정수를 적는다.
		for (int i = 2; i <= N; i++) {
			list.add(i);
		}
		
		while(list.size()!=0) { //아직 모든 수를 지우지 않았다면 반복
			
			//2.아직 지우지 않은 수 중 가장 작은 수를 찾는다. 
			//이것을 P라고 하고, 이 수는 소수이다.
			
			int P = list.get(0);
			
			int tgt = P;
			
			list.remove(new Integer(tgt));
			ans++;
			if(ans==K) {
				System.out.println(tgt);
				return;
			}
			//P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
			
			
			while(tgt<=N) {
				tgt += P;
				
				if(list.contains(tgt)) {
					ans++;
					
					if(ans==K) {
						System.out.println(tgt);
						return;
					}else {
						list.remove(new Integer(tgt));
					}
				}
			}
			
		}
		
	}
}
