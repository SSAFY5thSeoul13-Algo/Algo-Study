## BOJ 1938 G2 통나무 옮기기
- 구현
- bfs
- gold2

<br>


### 🔍 문제 설명
https://www.acmicpc.net/problem/1938

위의 지형에서 길이 3인 통나무 BBB를 밀거나 회전시켜 EEE의 위치로 옮기는 작업을 하는 문제를 생각해 보자. BBB와 EEE의 위치는 임의로 주어진다. 단 문제에서 통나무의 길이는 항상 3이며 B의 개수와 E의 개수는 같다. 통나무를 움직이는 방법은 아래와 같이 상하좌우(Up, Down, Left, Right)와 회전(Turn)이 있다.

코드	의미
U	통나무를 위로 한 칸 옮긴다.
D	통나무를 아래로 한 칸 옮긴다.
L	통나무를 왼쪽으로 한 칸 옮긴다.
R	통나무를 오른쪽으로 한 칸 옮긴다.
T	중심점을 중심으로 90도 회전시킨다.

움직일 위치에 다른 나무, 즉 1이 없어야만 움직일 수 있다. 그리고 움직임은 한 번에 한 칸씩만 움직인다. 단 움직이는 통나무는 어떤 경우이든지 중간단계에서 한 행이나 한 열에만 놓일 수 있다. 그리고 회전의 경우에서는 반드시 중심점을 중심으로 90도 회전을 해야 한다. (항상 통나무의 길이가 3이므로 중심점이 있음)

그리고 이런 회전(Turn)이 가능하기 위해서는 그 통나무를 둘러싸고 있는 3*3 정사각형의 구역에 단 한 그루의 나무도 없어야만 한다.


#### 입력
첫째 줄에 주어진 평지의 한 변의 길이 N이 주어진다. (4<=N<=50) 주어진다. 이어서 그 지형의 정보가 0, 1, B, E로 이루어진 문자열로 주어진다. 한 줄에 입력되는 문자열의 길이는 N이며 입력 문자 사이에는 빈칸이 없다. 통나무와 최종 위치의 개수는 1개이다.

#### 출력
첫째 줄에 최소 동작 횟수를 출력한다. 이동이 불가능하면 0만을 출력한다.

###  💡 풀이

통나무의 현재 상태를 저장하는 클래스 `Node`를 만들어서 bfs를 실행했다. `Node`는 통나무 중심의 좌표와 가로/세로 상태를 나타내는 `state`를 저장한다. 

각각의 동작을 메소드로 만들어서 동작을 수행한 후의 `Node`를 반환하도록 했다.

`isVisited` 배열을 통해서 각 좌표에서의  가로/세로 상태에 따른 방문 체크를 한다

각 시간에 각 위치와 상태에서 수행 가능한 동작의 결과를 `q`에 저장하면서 목적지에 도달할 때 까지 bfs를 실행한다



<br><br>

###  💡 소스코드
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1938_G2_통나무옮기기 {
	static int N;
	static int[][] map;
	static Node begin, end;
	static boolean[][][] isVisited;
	
	static class Node{
		int y;
		int x;
		int state;
		
		public Node(int y, int x, int state) {
			super();
			this.y = y;
			this.x = x;
			this.state = state;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		isVisited = new boolean[N][N][2];
		
		int[][] bArr = new int[3][2];
		int[][] eArr = new int[3][2];
		
		int startIdx = 0;
		int endIdx = 0;
		
		for (int i = 0; i < N; i++) {
			char[] charArr = br.readLine().toCharArray();
			for (int j = 0; j < charArr.length; j++) {
				if(charArr[j] == '1') {
					map[i][j] = 1;
				}
				else if(charArr[j] == 'B') {
					bArr[startIdx][0] = i;
					bArr[startIdx++][1] = j;
				}
				else if(charArr[j] == 'E') {
					eArr[endIdx][0] = i;
					eArr[endIdx++][1] = j;
				}
			}
		}
		
		//세로방향
		if(bArr[0][1] == bArr[2][1]) {
			begin = new Node(bArr[1][0], bArr[1][1], 0);
		}
		//가로방향
		else {
			begin = new Node(bArr[1][0], bArr[1][1], 1);
		}
		
		//세로방향
		if(eArr[0][1] == eArr[2][1]) {
			end = new Node(eArr[1][0], eArr[1][1], 0);
		}
		//가로방향
		else {
			end = new Node(eArr[1][0], eArr[1][1], 1);
		}
		
		isVisited[begin.y][begin.x][begin.state] = true;
		
		Queue<Node> q = new LinkedList<>();
		
		q.offer(begin);
		
		int time = 0;
		boolean isFinished = false;
		
		Loop:while(!q.isEmpty()) {
			int size = q.size();
			time++;
			
			for (int i = 0; i < size; i++) {
				Node wood = q.poll();
				
				Node up = up(wood);
				Node down = down(wood);
				Node left = left(wood);
				Node right = right(wood);
				Node rotate = rotate(wood);
				
				//위쪽 이동한 경우
				if(up != null) {
					if(!isVisited[up.y][up.x][up.state]) {
						isVisited[up.y][up.x][up.state] = true;
								
						if(checkWood(up)) {
							isFinished = true;
							break Loop;
						}
						
						q.offer(up);
					}
				}
				
				//아래쪽 이동한 경우
				if(down != null) {
					if(!isVisited[down.y][down.x][down.state]) {
						isVisited[down.y][down.x][down.state] = true;
						
						if(checkWood(down)) {
							isFinished = true;
							break Loop;
						}
						q.offer(down);
					}
				}
				
				//왼쪽 이동한 경우
				if(left != null) {
					if(!isVisited[left.y][left.x][left.state]) {
						isVisited[left.y][left.x][left.state] = true;
						
						if(checkWood(left)) {
							isFinished = true;
							break Loop;
						}
						q.offer(left);
					}
				}
				
				//오른쪽 이동한 경우
				if(right != null) {
					if(!isVisited[right.y][right.x][right.state]) {
						isVisited[right.y][right.x][right.state] = true;
						
						if(checkWood(right)) {
							isFinished = true;
							break Loop;
						}
						q.offer(right);
					}
				}
				
				//회전한 경우
				if(rotate != null) {
					if(!isVisited[rotate.y][rotate.x][rotate.state]) {
						isVisited[rotate.y][rotate.x][rotate.state] = true;
						
						if(checkWood(rotate)) {
							isFinished = true;
							break Loop;
						}
						q.offer(rotate);
					}
				}
			}
		}
		
		if(isFinished)
			System.out.println(time);
		else
			System.out.println(0);

	}
	
	//위로 이동
	static Node up(Node wood) {
		Node newWood = new Node(wood.y, wood.x, wood.state);
		int x = newWood.x;
		int y = newWood.y;
		
		if(newWood.state == 0) {
			if((y-1 == 0) || map[y-2][x] == 1)	return null;
			
			newWood.y -= 1;
		}
		else {
			if((y == 0) || map[y-1][x] == 1 || map[y-1][x-1] == 1 || map[y-1][x+1] == 1)	return null;
			
			newWood.y -= 1;
		}
		
		return newWood;
	}
	
	//아래 이동
	static Node down(Node wood) {
		Node newWood = new Node(wood.y, wood.x, wood.state);
		int x = newWood.x;
		int y = newWood.y;
		
		if(newWood.state == 0) {
			if((y+1 == N-1) || map[y+2][x] == 1)	return null;
			
			newWood.y += 1;
		}
		else {
			if((y == N-1) || map[y+1][x] == 1 || map[y+1][x-1] == 1 || map[y+1][x+1] == 1)	return null;
			
			newWood.y += 1;
		}
		
		return newWood;
	}
	
	//왼쪽 이동
	static Node left(Node wood) {
		Node newWood = new Node(wood.y, wood.x, wood.state);
		int x = newWood.x;
		int y = newWood.y;
		
		if(newWood.state == 0) {
			if((x == 0) || map[y][x-1] == 1 || map[y-1][x-1] == 1 || map[y+1][x-1] == 1)	return null;
			
			newWood.x -= 1;
		}
		else {
			if((x-1 == 0) || map[y][x-2] == 1)	return null;
			
			newWood.x -= 1;
		}
		
		return newWood;
	}
	
	//오른쪽 이동
	static Node right(Node wood) {
		Node newWood = new Node(wood.y, wood.x, wood.state);
		int x = newWood.x;
		int y = newWood.y;
		
		if(newWood.state == 0) {
			if((x == N-1) || map[y][x+1] == 1 || map[y-1][x+1] == 1 || map[y+1][x+1] == 1)	return null;
			
			newWood.x += 1;
		}
		else {
			if((x+1 == N-1) || map[y][x+2] == 1)	return null;
			
			newWood.x += 1;
		}
		
		return newWood;
	}
	
	//90도 회전
	static Node rotate(Node wood) {
		Node newWood = new Node(wood.y, wood.x, wood.state);
		int x = newWood.x;
		int y = newWood.y;
		
		if(x-1 < 0 || x+1 >= N || y-1<0 || y+1 >= N)
			return null;
		
		for (int i = y-1; i <= y+1; i++) {
			for (int j = x-1; j <= x+1; j++) {
				if(map[i][j] == 1)
					return null;
			}
		}
		
		newWood.state = (newWood.state+1)%2;
		
		return newWood;
	}
	
	//이동 가능 위치인지 확인
	static boolean canGo(int y, int x) {
		if(y<0 || x<0 || y>=N || x>=N || map[y][x] == 1)	return false;
		
		return true;
	}
	
	//위치에 도착인지 확인
	static boolean checkWood(Node wood) {
		if((wood.x == end.x) && (wood.y == end.y) && (wood.state == end.state)) {
			return true;
		}
		
		return false;
	}

}





```


<br>



메모리|시간
--|--
56540 KB|420 ms