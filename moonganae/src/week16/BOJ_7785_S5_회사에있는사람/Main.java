package week16.BOJ_7785_S5_회사에있는사람;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * @author moonseounguk
 * 풀이시간 : 15분
 */
public class Main {

	public static void main(String[] args) throws Exception{
		/*
		 * 직원들은 자기가 원할때 출근하고, 원할때 퇴근한다.
		 * 
		 * 출입기록을 통해 현재 회사에 있는 모든 사람을 구하라.
		 * 사람의 이름을 사전 순의 역순으로 한줄에 한명씩 출력
		 * 
		 * [풀이]
		 * 해쉬자료 구조를 이용하여 구하고, 사전순으로 정렬한다.
		 */
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Set log = new HashSet();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String name = st.nextToken();
			String action = st.nextToken();
			
			if("enter".equals(action)) {
				log.add(name);
			}else if("leave".equals(action)){
				log.remove(name);
			}
		}
		
		List<String> logName = new ArrayList<String>(log);
		
		Collections.sort(logName,Collections.reverseOrder());
		
		for(String name : logName) {
			System.out.println(name);
		}
	}

}
