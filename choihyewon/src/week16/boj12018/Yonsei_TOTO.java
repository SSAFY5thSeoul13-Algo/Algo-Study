package week16.boj12018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Yonsei_TOTO {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 성준이가 신청한 마일리지를 담는 pq 
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Integer> mileage = new PriorityQueue<>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					return o2-o1;
				}
				
			});
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<P; j++) {
				mileage.add(Integer.parseInt(st.nextToken()));
			}
		
			
			// 수강인원보다 신청한 사람이 더 많거나 같은경우 
			if(P>=L) {
				for(int l=0; l<L-1; l++) {
					mileage.poll();
				}
				pq.add(mileage.poll());
			}
			// 수강인원보다 신청한 사람이 더 적은 경우 
			else {
				pq.add(1);
			}
			
		}
		
		// 성준이가 듣는 마일리지 총합 
		int sum = 0;
		// 성준이가 듣는 과목 수 
		int cnt = 0;
		while(!pq.isEmpty()) {
			sum += pq.poll();
	
			// 주어진 마일리지보다 더 크면 break
			if(m<sum) {
				break;
			}else {
				cnt++;
			}	
		}
		System.out.println(cnt);
		
		
	}

}
