package week17.PRG_Lv2_H_Index;

import java.util.*;

public class Solution_PRG_Lv2_H_Index {
	public static void main(String[] args) {
		int[] citations = new int[] {3,0,6,1,5};
		System.out.println(solution(citations));
	}
	
	public static int solution(int[] citations) {
		
		//n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 h-index
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : citations) {
			list.add(i);
		}
        
        //내림차순 정렬
        Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
			
				return o2-o1;
			}
        	
        });
        
       
        int max = list.get(0);
        int n = list.size();
        //max : 논문 인용 최댓값, n : 논문 개수
        
        int hIndex = 0;
        for (int i = max ; i>=0; i--) {
        	//hIndex찾기
        	hIndex = i; 
        	
        	int cnt = 0;
			for (int num : list) {
				//hIndex번 이상 인용된 논문 개수 cnt
				if(num>=hIndex) cnt++;
				else break;
			}
			
			//h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면
			if(cnt>=hIndex&&(n-cnt)<=hIndex) {
				break;
			}
		}
        return hIndex;
    }
}
