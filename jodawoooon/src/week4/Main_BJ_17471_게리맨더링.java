package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17471_게리맨더링 {
	
	
	//백준시 : N개 구역, 1~N번 번호
	//N개 구역을 2개의 선거구로 나눈다. 
	//선거구는 구역을 적어도 하나 포함. 
	//한 선거구의 포함된 구역은 모두 연결
	
	//두 선거구에 포함된 인구의 차이를 최소로 하자.
	// 인구 차이의 최솟값 구하기
	
	
	/*부분집합으로 각 구역을 2개의 그룹으로 나눈 후 tgt이 true인 인덱스는 tgtGroup1에, 
	tgt이 false인 인덱스는 tgtGroup에 add하여 두 개의 선거구 리스트(tgtGroup1, tgtGroup2)를 구했습니다.

	그리고 BFS로 두 선거구가 모두 연결이 되어있는지 확인했습니다.

	그리고 두 그룹 모두 연결이 되어있다면 두 선거구의 인구수의 gap을 구해 최솟값을 계속 갱신시켰습니다.
	두 선거구를 아예 연결할 수 없다면, 즉 단 한번도 모두 연결된 선거구 부분집합을 구할 수 없다면 -1을 출력해야하므로
	단 한번이라도 모두 연결된 선거구 부분집합을 구하면 flag를 true로 변경해주었습니다.


	BFS 안에서 visited 배열의 크기를 임의의 수로 잘못 설정해서 array index error가 계속 났었습니다 ㅜㅜ


	메모리 : 13516kb	시간 : 108ms*/
	
	static int N, arr[], ans;
	static boolean area[][], tgt[], flag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //구역의 개수
		
		ans = Integer.MAX_VALUE;
		arr = new int[N+1];
		area = new boolean[N+1][N+1];
		tgt = new boolean[N+1];
		
		//각 구역의 인구
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		//인접구역 정보
		//첫번째 정수는 그 구역과 인접한 구역의 수. 
		//이후 인접한 구역의 번호가 주어진다
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int adjNum = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < adjNum; j++) {
				int areaNum = Integer.parseInt(st.nextToken());
				area[i][areaNum] = true;
				area[areaNum][i] = true;
			}
		}
		
		//팀을 나누고
		
		tgt = new boolean[N+1];
		subset(1);
		
		if(!flag) System.out.println(-1); //두개구역으로 절대 나눌수 없다면
		else System.out.println(ans);
	}
	private static void subset(int cnt) {
		
		if(cnt==N+1) {
//			System.out.println(teamNum);
//			System.out.println(Arrays.toString(tgt));
//			
			
			
			//두개의 선거구로 나누어진 구역번호를 각각의 선거구 List (tgtGroup1,2)에 add한다.
			ArrayList<Integer> tgtGroup1 = new ArrayList<>();
			ArrayList<Integer> tgtGroup2 = new ArrayList<>();

			for (int i = 1; i <= N; i++) {
				if(tgt[i]) {
					tgtGroup1.add(i);
				}else {
					tgtGroup2.add(i);
				}
			}
			
			//한 선거구가 적어도 한 구역을 포함하지 않는다면 return
			if(tgtGroup1.size()==0||tgtGroup2.size()==0||tgtGroup1.size()==N||tgtGroup2.size()==N) {
				return;
			}
			
			
			//System.out.println(Arrays.toString(tgtGroup1.toArray()));
			//System.out.println(Arrays.toString(tgtGroup2.toArray()));
			
			
			
			//tgt 그룹별로 다 연결되어있는지 확인한다
			boolean connect1 = bfs(tgtGroup1, tgtGroup1.get(0)); //임의로 첫번째 인덱스 값을 넣어주었다.
			boolean connect2 = bfs(tgtGroup2, tgtGroup2.get(0));
			
			//두 선거구 모두 연결이 되어있으면
			if(connect1&&connect2) {
				flag = true; //여러 케이스 중 한번이라도 두 선거구 모두 연결이 된다면 flag는 true값으로 종료된다.
				int num1 = 0;
				int num2 = 0;
				
				//두 선거구의 인구수(num1, num2)를 구한다.
				for (int i = 1; i <= N; i++) {
					if(tgt[i]) {
						//첫번째 선거구 인구
						num1 += arr[i];
					}else {
						//두번째 선거구 인구
						num2 += arr[i];
					}
				}
				
				int gap = Math.abs(num1-num2);
				//선거구 gap의 min값을 ans에 저장한다.
				ans = Math.min(gap, ans);
				
			}
			
			return;
		}
		
		tgt[cnt] = true;
		subset(cnt+1);
		tgt[cnt] = false;
		subset(cnt+1);
	}
	
	private static boolean bfs(ArrayList tgt, int v) {
		boolean visited[] = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();

		
		int cnt = 0;
		
		visited[v] = true;
		queue.add(v);
		
		while(true) {
			if(queue.isEmpty()) break;
			cnt++;
			
			int curNode = queue.poll();
			for (int i = 0; i < tgt.size(); i++) {
				int idx = (int) tgt.get(i);
				if(area[curNode][idx]&&!visited[idx]) {
					//선거구가 연결이 되어있고, visited이 false이면
					queue.add(idx);
					visited[idx]=true;
				}
			}
			
		}
		
		if(cnt!=tgt.size()) {
			return false;
		}
		return true;
		
	}
	
	
}
