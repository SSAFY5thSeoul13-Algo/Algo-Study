package week20.hash1;

import java.util.HashMap;

public class Programmers_완주하지_못한_선수 {
	public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<String,Integer>();
        for(int i=0; i<participant.length; i++) {
        	String person = participant[i];
        	
        	if(!hm.containsKey(person)) {
        		hm.put(person, 1);
        	}else {
        		hm.put(person, hm.get(person)+1);
        	}
        }
        
       for(int i=0; i<completion.length; i++) {
        	String person = completion[i];
        	hm.put(person, hm.get(person)-1);       	
        }
        
        for (String person : hm.keySet()) {
			if(hm.get(person) != 0) {
				answer = person;
			}
		}
        
        return answer;
    }

}
