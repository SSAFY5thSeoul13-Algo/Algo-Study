package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_13335_트럭2 {
	
/*	
	트럭의 무게를 저장하는 배열(truck), 
	다리 위의 상태를 저장하는 배열(bridge)
	총 2가지 배열을 사용했습니다.

	현재 다리 위에 올려야 하는 타겟 트럭 무게 tgtW에 대해서
	다리의 최대 하중을 만족하면서, 다리 위에 올릴 수 있을 때 까지 while문을 돌렸습니다.
	while문 안에서는 time을 1초씩 증가시키면서 트럭을 한 칸씩 앞으로 이동시켰습니다.
	다리 위에 올릴 수 있다면, 맨 뒤에 트럭을 집어넣고 while문을 종료합니다.
	위와 같은 과정을 모든 트럭에 대해 반복합니다.

	- 결과
	메모리 11872KB	시간 100ms
	
	*/
	
	static int N, W, L, truck[], bridge[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //트럭의 개수
		W = Integer.parseInt(st.nextToken()); //다리 위에는 단지 w 대의 트럭만 동시에 올라갈 수 있다.
		L = Integer.parseInt(st.nextToken()); //다리의 최대하중
		
		//다리의 길이는 w 단위길이
		//각 트럭들은 하나의 단위시간(unit time)에 하나의 단위길이만큼만 이동할 수 있다고 가정한다
		truck = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			truck[i] = (Integer.parseInt(st.nextToken()));
		}
		
		bridge = new int[W];
		
		int time = W;
		
		for (int i = 0; i < N; i++) {
			int tgtW = truck[i]; //타겟 트럭 무게
			
			while(true) { //bridge에 트럭을 올릴 수 있을 때 까지 while문 안에서 트럭이 이동하며 1초씩 증가
				int sum = 0; //현재 다리위의 트럭 무게 합
				
				time++;
				//시간1초증가
				
				
				for (int idx = 0; idx < W-1 ; idx++) {
					bridge[idx] = bridge[idx+1];
					sum += bridge[idx]; //현재 다리 위의 트럭 무게 저장
				}
				bridge[W-1] = 0;
				//1초 증가 할 때마다 bridge의 트럭들이 한칸씩 이동
				//앞에서부터 빠져나감
				
				if(sum+tgtW <= L) { //bridge에 들어갈수 있으면
					bridge[W-1] = tgtW; //bridge에 트럭을 넣는다
					break; //while문종료
				}
				
			}
		}
		
		System.out.println(time);
		
	}
}
