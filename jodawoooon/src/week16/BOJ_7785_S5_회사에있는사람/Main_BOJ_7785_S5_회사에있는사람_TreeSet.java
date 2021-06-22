package week16.BOJ_7785_S5_회사에있는사람;

import java.io.*;
import java.util.*;

public class Main_BOJ_7785_S5_회사에있는사람_TreeSet {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); //출입 기록 수
		
		TreeSet<String> set = new TreeSet<>(); //오름차순정렬
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String name = st.nextToken();
			String status = st.nextToken();
			
			if(status.equals("enter")) {
				set.add(name);
			}else {
				set.remove(name);
			}
		}
		
		Iterator<String> iter = set.descendingIterator();
		StringBuilder sb = new StringBuilder();
		while(iter.hasNext()) {
			sb.append(iter.next()).append("\n");
		}
		
		System.out.println(sb);
	}
}
