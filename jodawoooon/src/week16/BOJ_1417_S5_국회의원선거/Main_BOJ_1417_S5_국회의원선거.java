package week16.BOJ_1417_S5_국회의원선거;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_BOJ_1417_S5_국회의원선거 {
	
	//다른 모든 사람의 득표수 보다 많은 득표수를 가질 때, 그 사람이 국회의원에 당선
	//다솜이(기호1번)가 매수해야하는 사람의 최솟값
	
	static int N, M, dasom, ans;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
			
		}); //내림차순 PriorityQueue
		
		N = Integer.parseInt(br.readLine());
		
		int dasom = Integer.parseInt(br.readLine()); //다솜 투표 수
		for (int i = 1; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		while(!pq.isEmpty()) {
			int tgt = pq.poll(); //다른 후보 투표수
			
			if(dasom>tgt) { //다솜 당선 시 break
				break;
			}
			
			ans++; //매수 1명 증가
			dasom++; //다솜 투표수 1개 증가
			pq.add(tgt-1); //다른 후보 투표수 1개 감소
		}
		
		System.out.println(ans);
		
	}
}
