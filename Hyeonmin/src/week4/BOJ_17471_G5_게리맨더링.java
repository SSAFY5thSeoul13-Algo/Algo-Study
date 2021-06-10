package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_G5_게리맨더링 {
	static int N;
	//인구 수를 저장할 배열
	static int[] arr;
	//연결 상태를 저장할 배열
	static boolean[][] link;
	//조합을 만들기 위한 배열
	static boolean[] select;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[N+1];
		link = new boolean[N+1][N+1];
		select = new boolean[N+1];
		
		//구역별 인구 저장
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//구역별 연결 상태를 저장
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			link[i][i] = true;
			for (int j = 0; j < num; j++) {
				int end = Integer.parseInt(st.nextToken());
				link[i][end] = true;
			}
		}
		
		//선거구 나눔
		subset(1, 0);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	static void subset(int idx, int cnt) {
		if(idx == N+1) {
			//구역이 없는 선거구가 있는 경우
			if(cnt == 0 || cnt == N) {
				return;
			}
			
			//각 선거구의 구역 번호를 저장하는 배열
			int[] area1 = new int[cnt];
			int[] area2 = new int[N-cnt];
			
			int idx1 = 0;
			int idx2 = 0;
			
			//각 선거구의 번호를 그룹에 따라서 배열에 저장
			for (int i=1; i <= N ; i++) {
				if(select[i]) {
					area1[idx1++] = i;
				}
				else {
					area2[idx2++] = i;
				}
			}
			
			//각 선거구의 인구수 합
			int sum1 = 0;
			int sum2 = 0;
			
			//같은 선거구끼리 연결이 되어 있는지 확인
			if(checkLink(area1) && checkLink(area2)) {
				//선거구1의 인구수 총합
				for (int i : area1) {
					sum1 += arr[i];
				}

				//선거구2의 인구수 총합
				for (int i : area2) {
					sum2 += arr[i];
				}

				//두 선거구의 인구수 차이
				int result = Math.abs(sum1-sum2);				
				
				min = Math.min(result, min);
			}
			
			
			return;
		}
		
		select[idx] = true;
		subset(idx+1, cnt+1);
		select[idx] = false;
		subset(idx+1, cnt);
	}
	
	//선거구가 연결되어 있는지 확인하는 메소드
	static boolean checkLink(int[] area) {
		int length = area.length;
		//연결 여부를 저장하는 배열
		boolean[] visit = new boolean[length];
		
		//area의 인덱스를 저장할 큐
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//선거구에 첫번째 구역의 인덱스를 큐에 넣고 연결 저장
		queue.offer(0);
		visit[0] =true;
		
		//연결된 선거구 수
		int cnt= 0;
		
		while(!queue.isEmpty()) {
			int place = queue.poll();
			cnt++;
			
			//area에 있는 모든 선거구와 연결 확인
			for (int i=0 ; i < length ;i++) {
				//아직 연결 확인이 안되었는데 서로 인접한 구역인 경우
				if(!visit[i] && link[area[place]][area[i]]) {
					//연결되었다고 표시하고 큐에 넣음
					visit[i] = true;
					queue.offer(i);
				}
			}
		}
		
		//서로 연결된 구역의 수와 전체 구역의 수가 같으면 true
		if(cnt == length)
			return true;
		
		return false;
	}
}
