package week15.BOJ_2869_B1_달팽이는올라가고싶다;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 풀이시간 : 27분
 */
public class Main {

	public static void main(String[] args) throws Exception{
		/*
		 * 높이 V인 나무막대를 올라간다.
		 * 낮에는 A미터 올라가고 밤에는 B미터 미끄러진다. (정상에서는 미끄러지지 않음)
		 * 달팽이가 나무 막대를 모두 올라가려면, 며칠이 걸리는가?
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		
		// 하루 낮에 올라갈 수 있는 높이 : A
		// 하루 밤에 올라갈 수 있는 높이 : A-B
		
		// 2 1 5
		// q = 5 / 1 = 5
		// remain  5%1 = 0
		
		// 3 1 5
		// q = 5 / 2 = 2
		// remain = 5%2 = 1
		
		// 5 1 6
		// q = 6 / 4 = 1
		// remain = 6%4 = 2
		
		// 100 99 10000 00000
		// q = 10000 00000
		// remain = 0
		
		// tmpV : 목표지점에 도달한 날에는 미끄러지면 안되니 V-B에 도달하면 됨
		int tmpV = V - B;
		
		// V-B에 도달하는 날짜
		int day = tmpV / (A-B);
		
		// day동안 움직였을때 현재간접목표(tmpV)까지 남은 거리
		int remain = tmpV % (A-B);
		
		// 아직 높이가 남았으면 하루만 더가면 됨
		if(remain > 0)
			day++;
	
		System.out.println(day);
	}

}
