package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13335_S1_트럭 {
	static int N, W, L;
	static int[] arr;
	static int[] weight;
	static int totalW, idx, cnt, time;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		//각 트럭의 무게를 저장할 배열 0이 가장 앞 트럭
		arr = new int[N];
		//다리 위 트럭의 무게를 저장할 배열 0 이 가장 처음 위치
		weight = new int[L];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		process();
		
		System.out.println(time);
		
	}
	
	static void process() {
		while(cnt != N) {
			//시간 증가
			time++;
			
			//이번 시간에 다리에서 내려가는 트럭이 있는 경우
			if(weight[L-1] != 0) {
				totalW -= weight[L-1];
				weight[L-1] = 0;
				cnt++;
			}
			
			//모든 트럭 1칸씩 이동
			for (int i = L-2; i >= 0; i--) {
				weight[i+1] = weight[i];
			}
			
			//다음 트럭이 존재하고 다리에 올라갈 수 있는 경우
			if(idx < N && W - totalW >= arr[idx]) {
				totalW += arr[idx];
				weight[0] = arr[idx++];
			}
			//다리에 올릴 트럭이 없는 경우
			else {
				weight[0] = 0;
			}
		}
	}
}
