package week20.PRG_Lv2_전화번호목록;
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Arrays.sort(phone_book);
        //오름차순 정렬!!
        
        int len = phone_book.length;
        
        for(int i=0 ; i < len -1 ; i++){
            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        
        return true;
    }
}