package week20.hash3;

import java.util.*;

public class Programmers_위장 {
	public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        for(int i=0; i<clothes.length; i++) {
        	String kind = clothes[i][1];
        	// 의상의 종류별로 각 몇개인지 map에 put한다.
        	if(map.containsKey(kind)) {
        		map.put(kind, map.get(kind)+1);
        	}else {
        		map.put(kind, 1);
        	}
        }
        
        for (String key : map.keySet()) {
        	// 각 옷별로 선택할지 안할지 여부를 곱해준다. 
			answer *= (map.get(key) + 1);
		}
        
        // 아무도 선택하지 않을 경우를 빼준다. 
        answer = answer - 1;
        
        return answer;
    }

}
