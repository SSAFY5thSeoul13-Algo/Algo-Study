package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1242_소풍_큐_메모리초과 {
	static int N,K,M,cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //사람 수
		//1번부터 N번까지 시계방향으로 원형으로 앉았다
		
		K = Integer.parseInt(st.nextToken());  
		//KIN 게임 : 1번부터 K까지 센다. K를 말하면 퇴장장한다.
		//다음 자리부터 다시 1부터 센다.
		
		M = Integer.parseInt(st.nextToken()); 
		//동호는 M번 학생이다. 동호가 몇번으로 퇴장당하는지 구해라.
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		int tgt = 0;
		
		while(!queue.isEmpty()) {
			int idx = 0;
			for (int i = 1; i < K; i++) {
				//1번부터 K-1번까지 센다.
				idx = queue.poll();
				queue.add(idx); //원형구조
			}
			
			//K번을 세면 아예 퇴장
			idx = queue.poll();
			//퇴장당한 cnt 증가
			cnt++;
			
			//방금퇴장당한애가 동호이면 return;
			if(idx==M) {
				System.out.println(cnt);
				return;
			}
			
		}
		
	}
}
