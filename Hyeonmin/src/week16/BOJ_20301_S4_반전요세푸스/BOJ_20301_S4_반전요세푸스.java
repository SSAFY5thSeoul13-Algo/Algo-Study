package week16.BOJ_20301_S4_반전요세푸스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_20301_S4_반전요세푸스 {
	static int N, K, M;
	static Deque<Integer> deque = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//덱에 값 넣기
		for (int i = 1; i <= N; i++) {
			deque.add(i);
		}
		
		//방향이 바뀌고 나서 누적 삭제된 사람 수
		int count = 0;
		//정방향 여부
		boolean isForward = true;
		StringBuilder sb = new StringBuilder();
		
		//덱에 숫자가 없을 때 까지
		while(!deque.isEmpty()) {
			//정방향으로 가는 경우
			if(isForward) {
				//덱에서 K-1번째 사람 까지 넘김
				for (int i = 1; i < K; i++) {
					deque.addLast(deque.removeFirst());
				}
				
				//K번째에 해당하는 사람삭제
				sb.append(deque.removeFirst()).append("\n");
				//삭제한 사람수 증가
				count++;
			}
			//역방향으로 가는 경우
			else {
				//덱에서 K-1번째 사람 까지 넘김
				for (int i = 1; i < K; i++) {
					deque.addFirst(deque.removeLast());
				}
				
				//K번째에 해당하는 사람삭제
				sb.append(deque.removeLast()).append("\n");
				//삭제한 사람수 증가
				count++;
			}
			
			//M명의 사람이 제거되었으면 0으로 초기화하고 방향을 바꿈
			if(count == M) {
				count = 0;
				isForward = !isForward;
			}
		}
		
		System.out.println(sb.toString());
	}
}
