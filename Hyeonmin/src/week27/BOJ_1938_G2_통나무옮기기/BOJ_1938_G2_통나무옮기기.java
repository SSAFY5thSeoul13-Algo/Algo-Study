package week27.BOJ_1938_G2_통나무옮기기;

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
