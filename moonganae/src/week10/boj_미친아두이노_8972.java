package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_미친아두이노_8972 {

	static int R, C;
	static char[][] map;
	static int[][] cnt;
	static int[] jong = new int[2];
	static char[] command;
	static Queue<Integer> madRobot = new LinkedList<>();
	
	
	static int dy[] = {0,1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int dx[] = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		cnt = new int[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'R') {
					madRobot.offer(i);
					madRobot.offer(j);
				} else if(map[i][j] == 'I') {
					jong[0] = i;
					jong[1] = j;
					map[i][j] = '.';
				}
			}
		}

		command = br.readLine().toCharArray();
		boolean isSucc = true;
		int idx= 0;
		for(int i=0 ;i<command.length; i++) {
			idx++;
			// 종수 아두이노 이동
			if(jongMove(command[i]) == false) {
				isSucc = false;
				break;
			}
			/* 미친 아두이노 이동 */
			if(madMove() == false) {
				isSucc = false;
				break;
			}
			
		}
		
		/* 종수가 안잡혔다면 : 성공 */
		if(isSucc) {
			map[jong[0]][jong[1]] = 'I';
			for(int j=0; j<R; j++) {
				for(int k=0; k<C; k++) {
					System.out.print(map[j][k]);
				}
				System.out.println();
			}
		}
		/* 게임도중에 실패했다면*/
		else {
			/* 이동한 횟수출력 */
			System.out.println("kraj "+ idx);
		}
		
	}
	private static boolean madMove() {
		
		Queue<Integer> remain = new LinkedList<>(), bomb= new LinkedList<>();
		while(!madRobot.isEmpty()) {
			int y = madRobot.poll();
			int x = madRobot.poll();
			map[y][x] = '.';
			
			/* 현재 종수위치부터 현재로봇의 위치빼기 */
			int subY = jong[0] - y;
			int subX = jong[1] - x;
			
			/* 종수가 로봇보다 아래에 있다면 */
			if(subY > 0)
				y++;
			/* 종수가 로봇보다 위에 있다면 */
			else if(subY < 0)
				y--;
			/* 종수가 로봇보다 오른쪽에 있다면 */
			if(subX > 0) 
				x++;
			/* 종수가 로봇보다 왼쪽에 있다면 */
			else if(subX < 0)
				x--;
			
			/* 종수가 있는곳이네? 개꿀딱스 */
			if(y == jong[0] && x == jong[1]) return false;
			
			cnt[y][x]++;
			if(cnt[y][x] == 1) {
				remain.offer(y);
				remain.offer(x);
			}else {
				bomb.offer(y);
				bomb.offer(x);
			}
		}
		
		/* 남아있는것들 폭파대상 다시한번 확인 */
		int size = remain.size();
		for(int i=0; i<size; i+=2) {
			int y = remain.poll();
			int x = remain.poll();
			/* 하나만 있다면 살아있는 로봇 */
			if(cnt[y][x] == 1) {
				remain.offer(y);
				remain.offer(x);
				map[y][x] = 'R';
			}
			/* 시체처리및 초기화 */
			cnt[y][x] = 0;
		}
		
		/* 폭파된 것들 시체처리하기 */
		while(!bomb.isEmpty()) {
			int y = bomb.poll();
			int x = bomb.poll();
			cnt[y][x] = 0;
		}
		
		madRobot = remain;
		
		return true;
	}
	static boolean jongMove(char comm) {
		int commIdx = comm-'0';
		int zy = jong[0] + dy[commIdx];
		int zx = jong[1] + dx[commIdx];

		if(map[zy][zx] == 'R') return false;
		
		jong[0] = zy;
		jong[1] = zx;
		return true;
	}
}
