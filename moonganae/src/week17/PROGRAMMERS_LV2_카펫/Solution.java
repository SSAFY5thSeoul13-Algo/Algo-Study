package week17.PROGRAMMERS_LV2_카펫;

class Solution {
    public int[] solution(int brown, int yellow) {
        
        /* 주어진 예제를 통해 식을 구하고,
         * 가로 또는 세로에 대한 이차방정식을 구한뒤 근의 공식을 사용하여 문제를 해결함
         * */
    	
    	/* 이차방징식 a*x^2 + bx + c 라 가정 */
        int b = (brown+4)/2;
        int c = brown + yellow;
        
        // 근의 공식에서 b^2 - 4ac (여기서, a=1)
        int last = (int) Math.sqrt(b*b -4*c);
        
        /* 가로의 길이 > 세로 길이 */
        int[] answer = {(b+last)/2, (b-last)/2};
        return answer;
    }
    
    
}