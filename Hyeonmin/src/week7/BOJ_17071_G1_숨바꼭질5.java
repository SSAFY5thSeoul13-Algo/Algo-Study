package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17071_G1_숨바꼭질5 {
	static int N, K;
	//홀,짝 시간에서 해당 위치를 방문한 시간을 저장할 배열
	static int[][] time = new int[500001][2];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//시작 위치가 같은 경우
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		//방문 시간 저장할 배열을 -1로 초기화
		for (int i = 0; i < 500001; i++) {
			time[i][0] = time[i][1] = -1;
		}
		
		
		Queue<int[]> q = new LinkedList<int[]>();
		//수빈이의 첫 시작 위치를 큐에 저장. index 0:위치, 1: 홀짝여부
		q.add(new int[] {N, 0});
		time[N][0] = 0;
		
		//현재 시간
		int t = 0;
		
		while(!q.isEmpty()) {
			//현재 큐에 있는 데이터의 수
			int size = q.size();
			t++;
			
			//수빈이 위치를 갱신
			K += t;
            
			//수빈이의 위치가 범위를 벗어난 경우 중단
            if(K>500000)
				break;
			
            //현재 큐에 잇는 데이터 만큼 반복
			for (int i = 0; i < size; i++) {
				int[] d = q.poll();
				//수빈이가 이동할 수 있는 위치들
				for (int j : new int[]{d[0]-1, d[0]+1, 2*d[0]}) {
					//범위를 벗어나는 경우 다음으로
					if(j > 500000 || j < 0)
						continue;
					
					//아직 방문하지 않은 위치이면 값을 갱신하고 큐에 추가
					if(time[j][1-d[1]] == -1) {
						time[j][1-d[1]] = t;
						q.add(new int[] {j, 1-d[1]});
					}
				}
				
			}
			
			//동생의 현재 위치를 해당 홀짝 시간에 수빈이가 이미 방문한 경우 동생을 찾을 수 있다
			if(time[K][t%2] != -1) {
				System.out.println(t);
				return;
			}
			
		}
		
		//동생을 찾지 못한 경우
		System.out.println(-1);
	}
}