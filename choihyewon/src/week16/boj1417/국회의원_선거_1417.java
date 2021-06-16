package week16.boj1417;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 국회의원_선거_1417 {
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
			
		});
		
		int dasom = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N-1; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int cnt =0;
		while(!pq.isEmpty()) {
			// 다솜이가 제일 많으면 break;
			if(dasom>pq.peek()) {
				break;
			}
			// 제일 많은 지지자를 가진 사람에게서 한명을 가져온다 
			pq.add(pq.poll()-1);
			dasom += 1;
			cnt++;
			
		}
		
		System.out.println(cnt);

	}

}
