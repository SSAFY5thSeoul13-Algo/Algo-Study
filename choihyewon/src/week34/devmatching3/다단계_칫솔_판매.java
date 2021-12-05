package week34.devmatching3;

import java.util.*;

public class 다단계_칫솔_판매 {

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String,String> parents = new HashMap<>();
        HashMap<String,Integer> order = new HashMap<>();
        
        for(int i=0; i<enroll.length; i++){
            String child = enroll[i];
            String parent = referral[i];
            parents.put(child,parent);
            order.put(child,i);
        }
        
        for(int i=0; i<seller.length; i++){
            String child = seller[i];
            String parent = (String)parents.get(child);
            int money = amount[i]*100;
            while(true){
                int index = order.get(child);
                answer[index] += money-money/10;
                
                if(parent.equals("-")){
                    break;
                }
                
                child = parent;
                parent = (String)parents.get(child);
                money = money/10; 
 
                if(money<1){
                    break;
                }
            }
        }
        return answer;
    }

}
