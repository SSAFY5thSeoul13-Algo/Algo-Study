package week3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A에서B_16953 {
	static int A,B;
	static class Pair{
		long num;
		int cnt;
		public Pair(long num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		bfs(A,0);
		

	}
	// 최솟값을 구하는 문제이므로 bfs 이용 
	static void bfs(int a,int count) {
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(a,count));
		
		while(!queue.isEmpty()) {
			Pair p = queue.poll();
			
			// 기저조건
			if(p.num==B) {
				System.out.println(p.cnt+1);
				return;
			}
			
			if(p.num*10+1<=B) {
				queue.add(new Pair(p.num*10+1,p.cnt+1));
			}
			if(p.num*2<=B) {
				queue.add(new Pair(p.num*2,p.cnt+1));
			}
			
		}
		System.out.println(-1);
	}
	
	

}
