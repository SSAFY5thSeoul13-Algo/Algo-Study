package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15787_S2_기차가어둠을헤치고은하수를 {
	//N: 기차수, M: 명령수, count: 은하수를 건넌 기차 수
	static int N, M, count;
	//각 기차의 좌석에 승객 여부
	static boolean[][] arr;
	//각 기차의 좌석 비트마스킹
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new boolean[N+1][21];
		
		//명령 입력
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int train = Integer.parseInt(st.nextToken());
			int seat = 0;
			
			//seat 정보가 있는 경우
			if(st.hasMoreTokens()) {
				seat = Integer.parseInt(st.nextToken());
			}
			
			//명령 수행
			process(command, train, seat);
		}
		
		//모든 기차에 대해서
		for (int i = 1; i <= N; i++) {
			//해당 기차 좌석 비트정보
			int bit = getBit(i);
			//이미 리스트 없는 경우 추가하고 카운트
			if(!list.contains(bit)) {
				list.add(bit);
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	static void process(int command, int train, int seat) {
		switch(command) {
		//탑승
		case 1:
			on(train, seat);
			break;
		//하차
		case 2:
			off(train, seat);
			break;
		//뒤로 한칸씩
		case 3:
			goBack(train);
			break;
		//앞으로 한칸씩
		case 4:
			go(train);
			break;
		}
	}
	
	static void on(int train, int seat) {
		if(!arr[train][seat]) {
			arr[train][seat] = true;
		}
	}
	static void off(int train, int seat) {
		if(arr[train][seat]) {
			arr[train][seat] = false;
		}
	}
	static void goBack(int train) {
		for (int i = 20; i > 1; i--) {
			arr[train][i] = arr[train][i-1];
		}
		
		arr[train][1] = false;
	}
	static void go(int train) {
		for (int i = 1; i < 20; i++) {
			arr[train][i] = arr[train][i+1];
		}
		
		arr[train][20] = false;
	}
	
	//기차에 앉아있는 승객 정보 비트로 반환
	static int getBit(int idx) {
		int bit = 0;
		
		for (int i = 1; i <= 20; i++) {
			if(arr[idx][i]) {
				bit += 1<<i;
			}
		}
		
		return bit;
	}
}
