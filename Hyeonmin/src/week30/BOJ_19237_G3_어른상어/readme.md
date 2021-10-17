## BOJ 19237 G3 어른 상어
- 구현
- 시뮬레이션
- gold3

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/19237

청소년 상어는 더욱 자라 어른 상어가 되었다. 상어가 사는 공간에 더 이상 물고기는 오지 않고 다른 상어들만이 남아있다. 상어에는 1 이상 M 이하의 자연수 번호가 붙어 있고, 모든 번호는 서로 다르다. 상어들은 영역을 사수하기 위해 다른 상어들을 쫓아내려고 하는데, 1의 번호를 가진 어른 상어는 가장 강력해서 나머지 모두를 쫓아낼 수 있다.

N×N 크기의 격자 중 M개의 칸에 상어가 한 마리씩 들어 있다. 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다. 그 후 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고, 자신의 냄새를 그 칸에 뿌린다. 냄새는 상어가 k번 이동하고 나면 사라진다.

각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다. 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다. 이때 가능한 칸이 여러 개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다. 우선순위는 상어마다 다를 수 있고, 같은 상어라도 현재 상어가 보고 있는 방향에 따라 또 다를 수 있다. 상어가 맨 처음에 보고 있는 방향은 입력으로 주어지고, 그 후에는 방금 이동한 방향이 보고 있는 방향이 된다.

모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.

1번 상어만 격자에 남게 되기까지 몇 초가 걸리는지를 구하는 프로그램을 작성하시오.


#### 입력
첫 줄에는 N, M, k가 주어진다. (2 ≤ N ≤ 20, 2 ≤ M ≤ N2, 1 ≤ k ≤ 1,000)

그 다음 줄부터 N개의 줄에 걸쳐 격자의 모습이 주어진다. 0은 빈칸이고, 0이 아닌 수 x는 x번 상어가 들어있는 칸을 의미한다.

그 다음 줄에는 각 상어의 방향이 차례대로 주어진다. 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽을 의미한다.

그 다음 줄부터 각 상어의 방향 우선순위가 상어 당 4줄씩 차례대로 주어진다. 각 줄은 4개의 수로 이루어져 있다. 하나의 상어를 나타내는 네 줄 중 첫 번째 줄은 해당 상어가 위를 향할 때의 방향 우선순위, 두 번째 줄은 아래를 향할 때의 우선순위, 세 번째 줄은 왼쪽을 향할 때의 우선순위, 네 번째 줄은 오른쪽을 향할 때의 우선순위이다. 각 우선순위에는 1부터 4까지의 자연수가 한 번씩 나타난다. 가장 먼저 나오는 방향이 최우선이다. 예를 들어, 우선순위가 1 3 2 4라면, 방향의 순서는 위, 왼쪽, 아래, 오른쪽이다.

맨 처음에는 각 상어마다 인접한 빈 칸이 존재한다. 따라서 처음부터 이동을 못 하는 경우는 없다.

#### 출력
1번 상어만 격자에 남게 되기까지 걸리는 시간을 출력한다. 단, 1,000초가 넘어도 다른 상어가 격자에 남아 있으면 -1을 출력한다.

###  💡 풀이

1. 각 상어의 현재 위치를 `sharkArr`에 저장

```java
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				map[i][j] = new Node(num,0);
				
				if(num !=0) {
					sharkArr[num] = new Shark(i,j,num,0);
				}
			}
		}
```

2. 각 상어의 초기 방향과 우선순위를 저장


```java
		for (int i = 0; i <= M; i++) {
			priorityList.add(new ArrayList<List<Integer>>());
		}
		
		//상어의 초기 방향
		for (int i = 1; i <= M; i++) {
			Shark shark = sharkArr[i];
			shark.dir = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 5; j++) {
				priorityList.get(i).add(new ArrayList<Integer>());
			}
		}

		//각 상어의 우선순위 저장
		for (int i = 0; i < 4*M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = i/4+1;
			int dir = i%4+1;
			
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				priorityList.get(idx).get(dir).add(num);
			}
		}
```

3. 각 상어의 이동위치 확인 및 이동

```java
		while(deadCount < M - 1 && time <= 1000) {
			//각 상어 이동위치 탐색
			for (int i = 1; i <= M; i++) {
				Shark shark = sharkArr[i];
				
				if(shark.isDead)	continue;
				
				//주변에 비곳으로 이동
				boolean isFound = findEmpty(shark, i);
				
				//내 냄새가 있는 곳으로 이동
				if(!isFound)	findMySmell(shark, i);
			}
			
			time++;
			
			//각 상어 이동
			move();
		}
```

3-1. 상어 주변 빈 위치 확인

```java
	static boolean findEmpty(Shark shark, int idx) {
		List<Integer> dirList = priorityList.get(idx).get(shark.dir);
		
		for (Integer dir : dirList) {
			int ny = shark.y + delta[dir][0];
			int nx = shark.x + delta[dir][1];
			
			if(isIn(ny, nx)) {
				Node node = map[ny][nx];
				
				//냄새가 없는 경우
				if(time - node.time >= K || node.num == 0) {
					shark.y = ny;
					shark.x = nx;
					shark.dir = dir;
					
					sharkQ.offer(shark);
					
					return true;
				}
			}
		}
		
		return false;
	}
```

3-2. 상어 주변 자기 냄새 위치 확인

```java
	static void findMySmell(Shark shark, int idx) {
		List<Integer> dirList = priorityList.get(idx).get(shark.dir);
		
		for (Integer dir : dirList) {
			int ny = shark.y + delta[dir][0];
			int nx = shark.x + delta[dir][1];
			
			if(isIn(ny, nx)) {
				Node node = map[ny][nx];
				
				//내 냄새가 있는 경우
				if(time - node.time < K && node.num == shark.num) {
					shark.y = ny;
					shark.x = nx;
					shark.dir = dir;
					
					sharkQ.offer(shark);
					
					return;
				}
			}
		}
	}
```

3-3. 상어 이동

```java
	static void move() {
		while(!sharkQ.isEmpty()) {
			Shark shark = sharkQ.poll();
			int y = shark.y;
			int x = shark.x;
			
			Node node = map[y][x];

			//죽음
			if(node.time == time && node.num != 0 && node.num < shark.num) {
				shark.isDead = true;
				deadCount++;
			}
			else {
				node.num = shark.num;
				node.time = time;
			}
		}
	}
```



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19237_G3_어른상어 {
	//각 위치의 정보
	static class Node{
		int num;
		int time;
		
		public Node(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
	}
	
	//상어의 정보
	static class Shark{
		int y;
		int x;
		int num;
		int dir; 
		boolean isDead = false;
		
		public Shark(int y, int x, int num, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
		}
	}
	
	static int N, M, K, time, deadCount;
	//이동 예정인 상어
	static Queue<Shark> sharkQ = new LinkedList<>();
	//상어 배열
	static Shark[] sharkArr;
	static Node[][] map;
	//각 상어의 방향에 따른 다음 이동 우선순위
	static List<List<List<Integer>>> priorityList = new ArrayList<>();
	static int[][] delta= {
			{0,0},
			{-1,0},
			{1,0},
			{0,-1},
			{0,1}
	};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Node[N][N];
		sharkArr = new Shark[M+1];
		sharkArr[0] = new Shark(0,0,0,0);
		
		//상어와 맵 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				map[i][j] = new Node(num,0);
				
				if(num !=0) {
					sharkArr[num] = new Shark(i,j,num,0);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i <= M; i++) {
			priorityList.add(new ArrayList<List<Integer>>());
		}
		
		//상어의 초기 방향
		for (int i = 1; i <= M; i++) {
			Shark shark = sharkArr[i];
			shark.dir = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 5; j++) {
				priorityList.get(i).add(new ArrayList<Integer>());
			}
		}

		//각 상어의 우선순위 저장
		for (int i = 0; i < 4*M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int idx = i/4+1;
			int dir = i%4+1;
			
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				priorityList.get(idx).get(dir).add(num);
			}
		}
		
		
		while(deadCount < M - 1 && time <= 1000) {
			//각 상어 이동위치 탐색
			for (int i = 1; i <= M; i++) {
				Shark shark = sharkArr[i];
				
				if(shark.isDead)	continue;
				
				//주변에 비곳으로 이동
				boolean isFound = findEmpty(shark, i);
				
				//내 냄새가 있는 곳으로 이동
				if(!isFound)	findMySmell(shark, i);
			}
			
			time++;
			
			//각 상어 이동
			move();
		}
		
		System.out.println(time > 1000 ? -1 : time);
	}
	
	//각 상어 이동
	static void move() {
		while(!sharkQ.isEmpty()) {
			Shark shark = sharkQ.poll();
			int y = shark.y;
			int x = shark.x;
			
			Node node = map[y][x];

			//죽음
			if(node.time == time && node.num != 0 && node.num < shark.num) {
				shark.isDead = true;
				deadCount++;
			}
			else {
				node.num = shark.num;
				node.time = time;
			}
		}
	}
	
	static boolean findEmpty(Shark shark, int idx) {
		List<Integer> dirList = priorityList.get(idx).get(shark.dir);
		
		for (Integer dir : dirList) {
			int ny = shark.y + delta[dir][0];
			int nx = shark.x + delta[dir][1];
			
			if(isIn(ny, nx)) {
				Node node = map[ny][nx];
				
				//냄새가 없는 경우
				if(time - node.time >= K || node.num == 0) {
					shark.y = ny;
					shark.x = nx;
					shark.dir = dir;
					
					sharkQ.offer(shark);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	static void findMySmell(Shark shark, int idx) {
		List<Integer> dirList = priorityList.get(idx).get(shark.dir);
		
		for (Integer dir : dirList) {
			int ny = shark.y + delta[dir][0];
			int nx = shark.x + delta[dir][1];
			
			if(isIn(ny, nx)) {
				Node node = map[ny][nx];
				
				//내 냄새가 있는 경우
				if(time - node.time < K && node.num == shark.num) {
					shark.y = ny;
					shark.x = nx;
					shark.dir = dir;
					
					sharkQ.offer(shark);
					
					return;
				}
			}
		}
	}
	
	static boolean isIn(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N)	return false;
		
		return true;
	}
}





```


<br>



메모리|시간
--|--
18896 KB|192 ms