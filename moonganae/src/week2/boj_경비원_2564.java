package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_경비원_2564 {

	static int C, R;
	static int N;
	static int NORTH=1, SOUTH=2, WEST=3, EAST=4;
	static List<Point> storeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		
		storeList = new ArrayList<>();
		Point dong;
		
		/* 상점정보 입력 */
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int offset = Integer.parseInt(st.nextToken());
			
			storeList.add(dir2Point(dir,offset)); 
		}
		
		/* 동현이 위치입력 */
		st = new StringTokenizer(br.readLine());
		dong = dir2Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		int reverseDong = reverse(dong.dir);
		
		/* 최단거리의 합 구하기 */
		int sum = 0;
		for(Point store : storeList) {
			
			if(reverseDong == store.dir) {	// 마주볼경우
				int xSum = dong.x + store.x;
				int ySum = dong.y + store.y;
				sum += Math.min(xSum, 2*C-xSum) + Math.min(ySum, 2*R - ySum);
			} else { 						// 나란하거나 직각일경우
				sum += Math.abs(dong.y - store.y) +Math.abs(dong.x - store.x);
			}
		}
		System.out.println(sum);
	}
	/* 반대방향 */
	static int reverse(int dir) {
		if(dir == NORTH) {
			return SOUTH;
		} else if(dir == SOUTH) {
			return NORTH;
		}else if(dir == WEST) {
			return EAST;
		} else if(dir == EAST) {
			return WEST;
		}
		return dir;
	}
	/* 방향과 거리를 입력하면 좌표객체 반환 */
	static Point dir2Point(int dir, int offset) {
		Point p = new Point(dir,0,0);
		if(dir == NORTH) {
			p.y = 0;
			p.x = offset;
		} else if(dir == SOUTH) {
			p.y = R;
			p.x = offset;
		}else if(dir == WEST) {
			p.y = offset;
			p.x = 0;
		} else if(dir == EAST) {
			p.y = offset;
			p.x = C;
		}
		return p;
	}
	static class Point{
		int dir, y, x;
		public Point(int dir, int y, int x) {
			super();
			this.dir = dir;
			this.y = y;
			this.x = x;
		}
	}

}
