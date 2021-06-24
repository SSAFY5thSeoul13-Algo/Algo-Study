package week16.BOJ_10546_S4_배부른마라토너;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author moonseounguk
 * 풀이시간 : 13분
 */
public class Main {

	public static void main(String[] args) throws Exception{
		/**
		 * 마라톤 대회에 참가해 놓고 완주하지 못한 배부른 참가자를 구하라!
		 * 
		 * 참가자 수 : N
		 * N줄에 참가자 이름 입력 
		 * N-1줄에 완주한 사람 이름 입력
		 * 
		 * [풀이]
		 * N줄동안 Map에 push
		 * N-1줄 동안 Map에서 remove
		 * 
		 * [막힌점]
		 * 동명이인 조건을 놓침
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			
			String name = br.readLine();
			
			if(map.containsKey(name)){
				int cnt = map.get(name);
				map.put(name, cnt+1);
			}else {
				map.put(name, 1);
			}
			
		}
		
		for(int i=1; i<N; i++) {
			String name = br.readLine();
			
			int cnt = map.get(name) - 1;
			
			if(cnt == 0) {
				map.remove(name);
			}else {
				map.put(name, cnt);
			}
			
		}
		
		List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		System.out.println(list.get(0).getKey());
	}

}