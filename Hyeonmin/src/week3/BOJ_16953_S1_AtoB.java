package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16953_S1_AtoB {
	static long num, target, min = Integer.MAX_VALUE;
	// 0: 숫자, 1: 카운트
	static Queue<long[]> queue  = new LinkedList<long[]>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		
		queue.offer(new long[] {num, 1});
		
		//입력한 숫자와 목표가 같으면 종료
		if(num == target) {
			System.out.println(1);
			return;
		}
		
		//큐에 넣은 숫자가 없을 때 까지
		while(!queue.isEmpty()) {
			//다음 숫자와 영산횟수 불러오기
			long[] next = queue.poll();
			
			//2를 곱하는 계산에서 목표를 얻은 경우
			if(multi(next)) {
				min = next[1];
				break;
			}
			
			//뒤에 1을 붙이는 계산에서 목표를 얻은 경우
			if(addOne(next)) {
				min = next[1];
				break;
			}
			
		}
		
		//결과 출력
		if(min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min+1);
	}
	
	//2를 곱하는 계산
	static boolean multi(long[] num) {
		long temp = num[0]*2;
		
		//타켓을 구했으면 true를 반환
		if(temp == target) {
			return true;
		}
		
		//타겟보다 숫자가 큰 경우 큐에 넣지 않고 종료
		if(temp > target)
			return false;
		
		//계산한 숫자와 카운트를 큐에 저장
		queue.add(new long[] {temp, num[1] +1});
		
		return false;
	}
	
	//뒤에 1을 붙이는 계산
	static boolean addOne(long[] num) {
		//숫자에 10을 곱하고 맨 1을 더해 맨 뒤에 1을 붙인것과 같음
		long temp = num[0]*10 +1;
		
		//타겟을 구했으면 true반환
		if(temp == target) {
			return true;
		}
		
		//타겟보다 숫자가 큰 경우 큐에 넣지 않고 종료
		if(temp > target)
			return false;
		
		//계산한 숫자와 카운트를 큐에 저장
		queue.add(new long[] {temp, num[1] +1});
		
		return false;
	}
}