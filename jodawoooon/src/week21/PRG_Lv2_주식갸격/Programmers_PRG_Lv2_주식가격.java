package week21.PRG_Lv2_주식갸격;

public class Programmers_PRG_Lv2_주식가격 {
	public int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        
        for(int i = 0 ; i < len ; i++){
            int cur = prices[i]; //현재 가격
            int time = 0; //가격이 떨어지지 않은 기간
            
            for (int j = i+1 ; j <len ; j++){
            	time++;
                if(cur>prices[j]) break;
            }
            answer[i] = time;
        }
        return answer;
    }
}
