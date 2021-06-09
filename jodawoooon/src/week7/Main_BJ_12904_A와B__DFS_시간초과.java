package week7;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_12904_A와B__DFS_시간초과 {
	
	static String S,T;
	static boolean flag;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		
		StringBuilder sb = new StringBuilder(S);
		T = br.readLine();
		
		dfs(sb, 0, T.length());
		
		if(flag) System.out.println(1);
		else System.out.println(0);
	}
	
	private static void dfs(StringBuilder sb, int cnt, int length) {
		if(sb.toString().equals(T)) {
			flag=true;
			return;
		}
		
		if(cnt==length) {
			return;
		}
		
		sb.append("A");
		System.out.println(sb.toString());
		dfs(sb, cnt+1, length);
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
		sb.reverse().append("B");
		System.out.println(sb.toString());
		dfs(sb, cnt+1, length);
		sb.setLength(sb.length()-1);
		sb.reverse();
		System.out.println(sb.toString());
	}


}
