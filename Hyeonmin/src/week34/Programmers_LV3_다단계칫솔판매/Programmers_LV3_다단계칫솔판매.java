package week34.Programmers_LV3_다단계칫솔판매;

import java.util.*;

class Solution {
    static Map<String, String> referralMap = new HashMap<>();
    static Map<String, Integer> benifitMap = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {        
        for(int i =0; i < enroll.length; i++){
            String name = enroll[i];
            String ref = referral[i];
            
            referralMap.put(name, ref);
            benifitMap.put(name, 0);
        }
        
        for(int i =0; i < seller.length; i++){
            calcBenifit(seller[i],amount[i]*100);
        }
        
        int[] answer = new int[enroll.length];
        
        for(int i =0; i < enroll.length; i++){
            answer[i] = benifitMap.get(enroll[i]);
        }
        
        
        return answer;
    }
    
    static void calcBenifit(String seller, int price){
        int tenPer = price/10;
        
        if(!benifitMap.containsKey(seller)){
            return;
        }
        else{
            String nextName = referralMap.get(seller);
            benifitMap.put(seller, benifitMap.get(seller)+price-tenPer);
            
            if(tenPer > 0)
                calcBenifit(nextName, tenPer);
        }
    }
}