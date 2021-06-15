package week16.BOJ_1764_S4_듣보잡;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_BOJ_1764_S4_듣보잡 {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		HashSet<String> set1 = new HashSet<>();
		TreeSet<String> set2 = new TreeSet<>();
		
		
		int N = Integer.parseInt(st.nextToken()); //듣도 못한 사람의 수
		int M = Integer.parseInt(st.nextToken()); //보도 못한 사람의 수
		
		
		for (int i = 0; i < N; i++) {
			set1.add(br.readLine());
		}
		
		
		for (int i = 0; i < M; i++) {
			String name = br.readLine();
			
			if(set1.contains(name)) {
				set2.add(name);
			}
		}
		
		//듣도보도못한사람의 수
		sb.append(set2.size()).append("\n");
		
		for (String string : set2) { //tree set -> 사전순
			sb.append(string).append("\n");
		}
		
		System.out.println(sb);
	}
}
