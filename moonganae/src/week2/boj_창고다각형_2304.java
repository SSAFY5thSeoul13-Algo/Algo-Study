package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_창고다각형_2304 {

	static int N;
	static List<Point> list = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int max = 0;									// 기둥 중 가장 높은 높이
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int high = Integer.parseInt(st.nextToken());
			max = Math.max(max,  high);
			list.add(new Point(x,high));
		}
		
		/* 시작좌표 기준으로 정렬 */
		Collections.sort(list);
		
		int area = 0;
		/*좌측에서 max가 나올때까지 */
		Point lMax=list.get(0);
		for(int i=1; i<N; i++) {
			if(lMax.y < list.get(i).y) {
				
				area += lMax.y * (list.get(i).x-lMax.x);
				lMax = list.get(i);
			}
			if(list.get(i).y == max) {
				break;
			}
		}
		
		/* 우측에서 max가 나올때까지 */
		Point rMax = list.get(N-1);
		for(int i=N-2; i>=0; i--) {
			if(rMax.y < list.get(i).y) {
				
				area += rMax.y * (rMax.x-list.get(i).x);
				rMax = list.get(i);
			}
			if(list.get(i).y == max) {
				break;
			}
		}
		
		/* 가장 높은게 하나일때랑 다수일때가 다르기 때문에 필요한 보정
		 * 
		 * 				3					3		3
		 * 			2		2		vs			2	
		 * 		1				1		1				1
		 * */
		if(lMax == rMax) {							// 같은 높이를 가르키고 있는 경우 == 높은게 하나
			area += lMax.y;							// 가장 높은 영역 더하기
		} else {									// 높은게 다수일때
			area += lMax.y * (rMax.x+1 - lMax.x);	// 해당범위 영역 더하기
		}											// 왜 rMax.x + 1인가? => 기둥의 왼쪽면의 위치이기 때문에 +1해야 원하는 범위가 된다.
		
		System.out.println(area);

	}
	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}
		
	}
}
/*
4
1 1
3 2
5 4
7 2
=> 14

4
1 1
3 4
5 2
7 2
=> 14

4
1 11
2 5
3 5
4 10
=> 41

5
1 3
3 10
5 10
7 10
9 3
=> 62

5
1 3
3 10
5 5
7 10
9 3
=> 62

3
1 1
3 3
5 1
=> 7

3
1 3
3 1
5 3
=>

9
2 4
11 4
15 8
4 6
5 3
8 10
13 6
9 10
10 10
=> 102

4
1 1
3 1
5 3
7 3
=> 13

4
1 4
3 3
5 2
7 1
=>16
*/