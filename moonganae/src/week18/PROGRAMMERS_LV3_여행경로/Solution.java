package week18.PROGRAMMERS_LV3_여행경로;

import java.util.*;
class Solution {
    List<String> path;
    List<String> tmp = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        // ICN부터 여행 시작
        tmp.add("ICN");
        
        dfs(0,0,"ICN", tickets);
        
        answer = path.toArray(new String[path.size()]);
        return answer;
    }
    /**
     * 
     * @param num : 고른 티켓의 수
     * @param select : 현재 고른 티켓의 bit mask
     * @param cur : 현재 시작경로
     * @param tickets : 티겟정보
     */
    public void dfs(int num,int select,String cur, String[][] tickets){
        // 모든 티겟을 사용하였다면
        if(num == tickets.length){
            if(path == null || check()){
                path = new ArrayList<>(tmp);
            }
            return;
        }
        
        for(int i=0; i<tickets.length; i++){
            if( (select & 1<<i) != 0) continue;
            // 현재 시작경로가 맞다면
            if( tickets[i][0].equals(cur) ){
                tmp.add(tickets[i][1]);
                dfs(num+1, select|1<<i, tickets[i][1], tickets);
                tmp.remove(tmp.size()-1);
            }
        }
    }
    /**
     * 
     * @return tmp와 path중 알파벳 순서가 앞서는 경로는?
     * true : tmp가 앞섬 -> path = tmp
     * false : path가 앞섬 -> continue;
     */
    public boolean check(){
        int limit = Math.min(path.size(), tmp.size());
        
        for(int i=0; i<limit; i++){
            int result = tmp.get(i).compareTo(path.get(i));
            if(result == 0 ) continue;
            else if(result > 0){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}

