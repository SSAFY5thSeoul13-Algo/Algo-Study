package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_체스판여행1_16959 {

	static int N;
	static char[] piece;
	
	public static void main(String[] args) throws Exception{
		setDoubleNight();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		List<Point> list = new ArrayList<>();
		list.add(new Point(-1,-1,0));
		
		// List에 넣기
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				list.add(new Point(i,j,num));
			}
		}
		
		// num 순으로 오름차순정렬
		Collections.sort(list, (o1, o2) -> o1.d - o2.d);

		piece = new char[list.size()];
		/* 번호끼리 갈때 사용해야하는 기물설정 */
		for(int i=1; i<list.size()-1; i++) {
			piece[i+1] = setPiece(list.get(i), list.get(i+1));
		}

//		for(char c : piece) {
//			System.out.print(c + " ");
//		}
//		System.out.println();

		int time = 1;
		char prev = piece[2];
		for(int i=2; i<list.size()-1; i++) {
			char cur = piece[i];
			
			if(cur == AUTO) {
				time += 2;
//				System.out.println("AUTO => 2증가" + time);
				continue;
			} else if(cur == piece[i+1]) {
				time++;
//				System.out.println("같아서 => 1증가" + time);
				continue;
			}
			// i vs i+1이 다르면
			else {
				// 만약 auto인데, 갈수없다면
				if(piece[i+1] == AUTO && canDoubleGo(cur, list.get(i), list.get(i+1)) == false) {
					piece[i+1] = ROOK;
					time++;
				}
				else if(i+2<=list.size()-1 &&cur == piece[i+2]) {
					
					if( piece[i+1] == AUTO || (cur == ROOK || piece[i+1] == ROOK)) {
//						System.out.print("연결가능 => 1증가  ");
						time++;
						i++;
					}
					
				}
				
				time += 2;
//				System.out.println("달라서 => 2증가" + time);
				
			}
		}
		
		System.out.println(time);
	}
	static final char NIGHT='N', ROOK='R', BISHOP='B', AUTO='A'; 
	static char setPiece(Point a, Point b) {
	
		if(isRook(a, b)) return ROOK;
		else if(isBishop(a,b)) return BISHOP;
		else if(isNight(a,b)) return NIGHT;
		else return AUTO;
		
	}
	
	static boolean isRook(Point a, Point b) {
		if(a.y == b.y || a.x == b.x) return true;
		return false;
	}
	static boolean isBishop(Point a, Point b) {
		if(Math.abs(a.y - b.y) == Math.abs(a.x - b.x)) return true;
		return false;
	}
	static boolean isDoubleBishop(Point a, Point b) {
		
		if(isBishop(a,b) == false && (Math.abs(a.y-b.y) + Math.abs(a.x-b.x)) % 2 == 0 ) {
			return true;
		}
		return false;
	}
	static int[] nx = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] ny = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	static List<Integer> doubleNight = new ArrayList<>();
	static boolean[][] vis = new boolean[9][9];
	static void setDoubleNight() {
		Queue<Integer> oneNight = new LinkedList<>();
		vis[4][4] = true;
		for(int i= 0; i<8; i++) {
			
			oneNight.offer(4 + nx[i]);
			oneNight.offer(4 + ny[i]);
		}
		
		while(!oneNight.isEmpty()) {
			int x = oneNight.poll();
			int y = oneNight.poll();
			
			for(int i=0; i<8; i++) {
				int dx = x + nx[i];
				int dy = y + ny[i];
				if(vis[dy][dx]) continue;
				vis[dy][dx] = true;
				doubleNight.add(dx);
				doubleNight.add(dy);
			}
		}
	}
	static boolean isNight(Point a, Point b) {
		
		int subX = Math.abs(a.x - b.x);
		int subY = Math.abs(a.y - b.y);
		
		if( (subX == 1 && subY == 2) || (subX==2 && subY == 1)) return true;
		
		return false;
	}
	static boolean isDoubleNight(Point a, Point b) {
		
		for(int i=0; i<doubleNight.size(); i+=2) {
			
			int dx = a.x + doubleNight.get(i);
			int dy = a.y + doubleNight.get(i+1);
			
			if(b.x == dx && b.y == dy) return true;
		}
		return false;
	}
	static boolean canDoubleGo(char c, Point a, Point b) {
		if(c==ROOK) return true;
		else if(c==BISHOP) {
			if(isDoubleBishop(a, b)) return true;
		}else {
			if(isDoubleNight(a, b)) return true;
		}
		return false;
	}
	
	
	static class Point{
		int y, x, d;

		public Point(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
