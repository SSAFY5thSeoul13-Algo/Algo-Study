package week16.BOJ_12018_S3_YonseiTOTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_12018_S3_YonseiTOTO {
	static int N, M, P, L, count;
	//각 과목의 각 사람이 넣을 마일리지를 저장하는 우선순위 큐
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	//내가 각 과목을 듣기위해 넣어야하는 마일리지를 저장할 우선순위 큐
	static PriorityQueue<Integer> myPq = new PriorityQueue<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		//과목수
		N = Integer.parseInt(st.nextToken());
		//내 마일리지
		M = Integer.parseInt(st.nextToken());

		//과목 수만큼 반복
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			//신청 인원
			P = Integer.parseInt(st.nextToken());
			//과목 수강 가능 인원
			L = Integer.parseInt(st.nextToken());
			//큐 초기화
			pq.clear();

			//초과 인원수
			int num = P - L;

			st = new StringTokenizer(br.readLine());
			
			//만약 강의 인원이 부족하면 1마일리지로 신청 가능
			if (num < 0) {
				myPq.offer(1);
			} 
			else {

				//신청한 사람들의 마일리지를 큐에 저장
				for (int j = 0; j < P; j++) {
					pq.offer(Integer.parseInt(st.nextToken()));
				}
				
				//초과하는 인원만큼 작은 크기의 마일리지 제거
				for (int j = 0; j < num; j++) {
					pq.poll();
				}
				
				//그 다음 마일리지만큼 넣으면 성준이는 수강이 가능
				myPq.offer(pq.poll());
			}
		}
		
		//우선순위 큐가 비거나 마일리지가 더이상 없을 때 까지
		while(M > 0 && !myPq.isEmpty()) {
			//현재 남은 수업중 수강 가능한 가장 작은 마일리지
			int mileage = myPq.poll();
			
			//현재 마일리지로 수강이 가능하면
			if(M - mileage >= 0) {
				//수강가능한 과목 수 증가
				count++;
				//마일리지 차감
				M -= mileage;
			}
			//더 이상 수강가능한 수업이 없으면 중단
			else {
				break;
			}
		}
		
		System.out.println(count);
	}

}
