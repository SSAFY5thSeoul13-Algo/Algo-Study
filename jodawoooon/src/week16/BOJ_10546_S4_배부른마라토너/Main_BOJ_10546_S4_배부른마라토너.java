package week16.BOJ_10546_S4_배부른마라토너;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_BOJ_10546_S4_배부른마라토너 {
	
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //참가자 수
		
		HashMap<String, Integer> map = new HashMap<>();
		
		//Programmers 해시 level1 문제와 같은 문제
		//완주하지 못한 한 명을 찾는다
		
		//N명의 참가자 정보를 HashMap에 넣는다
		for (int i = 0; i < N; i++) {
			String name = br.readLine(); //참가자
			
			if(map.get(name)==null) { 
				//참가자 이름을 넣는다
				map.put(name, 1);
			}else {
				//동명이인인 경우
				map.put(name, map.get(name)+1);
			} 
		}
		
		//완주자의 정보를 확인한다.
		for (int i = 0; i < N-1; i++) {
			String name = br.readLine(); //완주자
			map.put(name, map.get(name)-1);
		}
		
		//완주하지 못한 한명을 찾는다.
		for (String name : map.keySet()) {
			if(map.get(name)!=0) {
				System.out.println(name);
				break;
			}
		}
	}
}
