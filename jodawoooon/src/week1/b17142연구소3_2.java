package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class b17142연구소3_2 {
	
	/*
	 * 바이러스 - 활성상태, 비활성상태
	 * 활성 상태 바이러스는 상하좌우 인접한 모든 빈칸으로 "동시에"복제. 1초걸림
	 * 승원이는 연구소의 바이러스 M개를 활성상태로 변경하고자 한다.
	 * 
	 * 연구소는 N*N 정사각형
	 * 빈칸, 벽, 바이러스로 이루어짐
	 * 0 : 빈칸 / 1 : 벽 / 2 : 바이러스
	 * 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활성 바이러스가 활성으로 변함
	 * 
	 * !! 출력 = 모든 빈칸에 바이러스를 퍼뜨리는 "최소시간" min
	 * 최소시간은 즉 N*N배열에 있는 숫자 중 max 숫자이다.
	 * 바이러스를 모두 퍼트릴 수 없다면 -> -1 출력
	 * 
	 * M = 놓을 수 있는 바이러스의 개수.
	 * 
	 * 즉 M개의 활성 바이러스 "조합"을 만들고 바이러스를 퍼트린 뒤 "max"를 구한 뒤 그 중 " min을 구한다.
	 * 
	 * 
	 */
	
	static List<int[]> virus = new ArrayList<>();

	static boolean flag;
	static boolean[] select;
	static int N,M,ans, max, count, virusNum;
	static int[] dr = {-1,1,0,0};  //상하좌우
	static int[] dc = {0,0,-1,1};
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = Integer.MAX_VALUE;


		arr = new int[N][N];
		
		//연구소의 상태 세팅
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) virus.add(new int[]{i,j});

			}
		}

		virusNum= virus.size();
		select = new boolean[virusNum];
		
		//M개의 바이러스를 놓아서 0을 모두 바이러스로 퍼뜨리고. 그 최소 시간을 출력해야한다.
		//=> 연구소의 상태의 2 중에서 M개를 고른다 => 조합
		
		comb(0, 0);
		
		//바이러스를 모두 퍼트릴 수 없다면 계속 break문에 걸려서 ans를 갱신할 수 없다 => ans == Integer.Max 
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		
	}
	private static void comb(int cnt, int start) { //조합
		//M개의 바이러스를 뽑으면
		if(cnt==M) {
			
			int[][] map = new int[N][N];
			List<int[]> tgtVirus = new ArrayList<>();
			flag = false; max = 0;
		//	System.out.println(Arrays.toString(select));			
			
			//바이러스를 퍼뜨린다.
		
			
			//기본 map 셋팅
			for (int i = 0; i < N; i++) { //기본 map 배열을 모두 -1로 셋팅한다
				for (int j = 0; j < N; j++) {
					map[i][j] = -1;
				}
			}
			
			//선택된 활성화 바이러스 맵에 표시
			for (int i = 0; i < virusNum; i++) { //선택된 바이러스는 활성바이러스이므로 0으로 표시
				if(select[i]) {
					tgtVirus.add(virus.get(i));
					map[virus.get(i)[0]][virus.get(i)[1]] = 0; 
				}	
			}
			
//			System.out.println("======================");		
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(arr[i][j]+" ");
//				}System.out.println();
//			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j]+" ");
//				}System.out.println();
//			}
//			

			
			
			//퍼지는 시간 체크
			for (int i = 0; i < tgtVirus.size(); i++) { //선택된 tgtVirus에서 상하좌우로 이동하면서 빈칸을 찾아 바이러스를 퍼트린다

				int r = tgtVirus.get(i)[0];
				int c = tgtVirus.get(i)[1];
				
				for (int j = 0; j < 4; j++) {
					int nr = r + dr[j];
					int nc = c + dc[j];
					
					if(nr>=0&&nr<N&&nc>=0&&nc<N&& arr[nr][nc]!=1 && map[nr][nc]==-1) {
						//인덱스 범위 안이고, 벽이 아니고, 초기 활성화 바이러스 아니면
						map[nr][nc] = map[r][c]+1; //방문 할때마다 1씩 증가
						if(arr[nr][nc]==2) {
							tgtVirus.add(new int[]{nr,nc}); //활성바이러스가 비활성바이러스가 있는 곳으로 가면 비활성바이러스가 활성으로 변한다.
						}
						
					}
				}
			}

			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}System.out.println();
			}
			System.out.println("======================");	
//			
//			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(arr[i][j]==0) {//원래 벽이 아닌애들 중에서 => 라고하면 안됨. 원래 빈칸인 애들중에서만 비교해야함
						if(map[i][j]!=-1) {//-1이 아니라면 바이러스가 퍼진것이다.
							max = Math.max(max, map[i][j]);
							
						}else if(map[i][j]==-1){

							//-1이 남아있다면 => 다 퍼지지 못했다. -1 출력
							//여기서 ans=-1을 처리해버리니까 다른 케이스에서는 가능하더라도 -1로 그냥 출력이 되버린다.
							
							flag = true;

							break;
						}
						
					}
				}

			}
			//System.out.println(time);
			if(!flag) ans = Math.min(ans, max);


		}
		
		
			for (int i = start; i < virusNum; i++) {
				select[i] = true;
				comb(cnt+1, i+1);
				select[i] = false;
			}

	}

}
