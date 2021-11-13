## BOJ 2610 G2 회의준비
- 플로이드-와샬

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/2610

KOI 준비를 위해 회의를 개최하려 한다. 주최측에서는 회의에 참석하는 사람의 수와 참석자들 사이의 관계를 따져 하나 이상의 위원회를 구성하려고 한다. 위원회를 구성하는 방식은 다음과 같다.

서로 알고 있는 사람은 반드시 같은 위원회에 속해야 한다.
효율적인 회의 진행을 위해 위원회의 수는 최대가 되어야 한다.
이런 방식으로 위원회를 구성한 후에 각 위원회의 대표를 한 명씩 뽑아야 한다. 각 위원회의 대표만이 회의 시간 중 발언권을 가지며, 따라서 회의 참석자들이 자신의 의견을 말하기 위해서는 자신이 속한 위원회의 대표에게 자신의 의견을 전달해야 한다. 그런데 각 참석자는 자신이 알고 있는 사람에게만 의견을 전달할 수 있어 대표에게 의견을 전달하기 위해서는 때로 여러 사람을 거쳐야 한다. 대표에게 의견을 전달하는 경로가 여러 개 있을 경우에는 가장 적은 사람을 거치는 경로로 의견을 전달하며 이때 거치는 사람의 수를 참석자의 의사전달시간이라고 한다.

위원회에서 모든 참석자들의 의사전달시간 중 최댓값이 최소가 되도록 대표를 정하는 프로그램을 작성하시오.

예를 들어 1번, 2번, 3번 세 사람으로 구성되어 있는 위원회에서 1번과 2번, 2번과 3번이 서로 알고 있다고 하자. 1번이 대표가 되면 3번이 대표인 1번에게 의견을 전달하기 위해서 2번을 거쳐야만 한다. 반대로 3번이 대표가 되어도 1번이 대표인 3번에게 의견을 전달하려면 2번을 거쳐야만 한다. 하지만 2번이 대표가 되면 1번과 3번 둘 다 아무도 거치지 않고 대표에게 직접 의견을 전달 할 수 있다. 따라서 이와 같은 경우 2번이 대표가 되어야 한다.


#### 입력
첫째 중에 회의에 참석하는 사람의 수 N이 주어진다. 참석자들은 1부터 N까지의 자연수로 표현되며 회의에 참석하는 인원은 100 이하이다. 둘째 줄에는 서로 알고 있는 관계의 수 M이 주어진다. 이어 M개의 각 줄에는 서로 아는 사이인 참석자를 나타내는 두개의 자연수가 주어진다.

#### 출력
첫째 줄에는 구성되는 위원회의 수 K를 출력한다. 다음 K줄에는 각 위원회의 대표 번호를 작은 수부터 차례로 한 줄에 하나씩 출력한다. 한 위원회의 대표가 될 수 있는 사람이 둘 이상일 경우 그중 한 명만 출력하면 된다.

###  💡 풀이

플로이드 와샬로 의견을 전달할 수 있는 모든 사이를 구한다

모든 사람을 순회하며 그 사람과 의사 결정이 가능한 사람을 큐에 넣고 방문 체크를 한다

큐에 넣은 사람들을 하나씩 꺼내서 자신의 의사전달시간의 최대값 `temp`를 구한다

큐에 넣은 사람들중 의사전달시간의 최대값 중에서 가장 작은 값 `min`을 구한다

`min`을 얻어낸 사람의 번호 `minIdx`가 그 의원회의 대표가 된다


<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2610_G2_회의준비 {
	static int[][] map;
	static int N, M;
	static final int INF = Integer.MAX_VALUE/2;
	static boolean[] isVisited;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		isVisited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = INF;
			}
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from][to] = map[to][from] = 1;
		}
		
		floydWashall();
		
		//아직 위원회에 속하지 않은 사람의 위원회 리더 선출
		for (int i = 1; i <= N; i++) {
			if(isVisited[i])	continue;
			
			selectLeader(i);
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(list.size()).append("\n");
		
		for (int num : list) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void floydWashall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j)	continue;
					map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
				}
			}
		}
	}
	
	static void selectLeader(int idx) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(idx);
		
		//같은 위원회 사람을 큐에 저장
		for (int i = 1; i <= N; i++) {
			if(map[idx][i] != INF) {
				q.offer(i);
			}
		}
		
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		
		while(!q.isEmpty()) {
			int num = q.poll();
			
			isVisited[num] = true;
			
			int temp = 0;
			
			//해당 위원의 가장 큰 의사결정 거리 구하기
			for (int i = 1; i <= N; i++) {
				if(map[num][i] != INF) {
					temp = Math.max(temp, map[num][i]);
				}
			}
			
			if(min > temp) {
				min = temp;
				minIdx = num;
			}
			
		}
		
		list.add(minIdx);
	}
}




```


<br>



메모리|시간
--|--
12884 KB|120 ms