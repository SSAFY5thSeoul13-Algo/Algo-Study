package week18.PROGRAMMERS_LV3_단어변환;

class Solution {
    int answer = 51;    // words의 범위가 50개 이하이므로 범위를 넘어서는 값은 51
    public int solution(String begin, String target, String[] words) {
        
        dfs(0, 0, begin, target, words, 0);
        if(answer == 51) answer = 0;    // 51이라는 것은 target을 찾지 못했다!
        return answer;
    }
    /**
     * num : 확인한 단어의 수
     * select : 선택한 단어 bitmask
     * target : target 단어
     * words : 단어의 집합
     * stage : 현재 단계
     */
    public void dfs(int num, int select, String cur, String target, String[] words,int stage){
        // 원하는 단어를 찾았다면
        if(cur.equals(target)){
            // 최소 단계업데이트
            answer = Math.min(answer, stage);
            return;
        }
        // 기저조건 : 끝까지 단어를 뒤졌는가?
        if(num == words.length){
            return;
        }
        
        for(int i=0; i<words.length; i++){
            // 선택한 단어 제외
            if( (select & 1<<i) != 0) continue;
            // 만약 한글자 바꿀수 있다면?
            if(diff(cur, words[i])){
                dfs(i+1, select|1<<i, words[i], target, words, stage+1);
            }
        }
    }
    // 한글자 변경이 가능한지 확인
    public boolean diff(String a, String b){
        int cnt = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                cnt++;
                if(cnt>1) return false;
            }
        }
        return true;
    }
}