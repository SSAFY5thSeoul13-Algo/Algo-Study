package week15.BOJ_2960_S4_에라토스테네스의체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2960_S4_에라토스테네스의체 {
	static int N, K;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//범위에 해당하는 수 만큼 배열 생성
		arr = new int[N+1];
		
		//배열에 값 넣기
		for (int i = 2; i <= N; i++) {
			arr[i]=i;
		}
		
		int num = 2;
		int count = 0;
		
		while(true) {
			num = 0;
			
			//아직 남아 있는 수 중에 가장 작은 수를 찾음 
			for (int i = 2; i <= N; i++) {
				if(arr[i] != 0) {
					num = i;
					break;
				}
			}
			
			if(num == 0)
				break;
			
			//남은 수중에 아직 삭제 되지 않은 수를 찾음
			for (int i = num; i <= N; i+=num) {
				//해당 숫자가 삭제가 안되었으면 삭제하고 카운트
				if(arr[i] != 0) {
					arr[i] = 0;
					count++;
				}
				
				//카운트가 목표로 하는 번째에 도달했으면 현재의 숫자를 출력하고 종료
				if(count == K) {
					System.out.println(i);
					return;
				}
			}
		}
	}
}
