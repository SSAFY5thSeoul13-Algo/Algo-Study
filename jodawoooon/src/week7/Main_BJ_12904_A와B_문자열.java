package week7;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_12904_A와B_문자열 {
	
	static String S,T;
	static boolean flag;
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		
		T = br.readLine();
		StringBuilder sbT = new StringBuilder(T);
		
		
		while(sbT.length()!=S.length()) {
			int idx = sbT.length()-1;
			
			if(sbT.charAt(idx)=='A') {
				sbT.setLength(idx);
			}else {
				sbT.setLength(idx);
				sbT.reverse();
			}
		}
		
		if(sbT.toString().equals(S)) System.out.println(1);
		else System.out.println(0);
	}



}
