package week17.bruteforcing1;

public class 카펫 {
	static int brown = 24;
	static int yellow = 24;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] answer = solution(brown,yellow);
		
		for(int i=0; i<answer.length; i++) {
			System.out.print(answer[i] + " ");
		}

	}
	
	public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int area = brown + yellow;
        for(int i=3; i<=area; i++) {
        	if(area%i==0) {
        		int w = i;
        		int h = area/i;
        		if(w>=h && (w-2)*(h-2)==yellow) {
        			answer[0] = w;
        			answer[1] = h;
        			break;
        		}
        	}
        }
        
        return answer;
    }

}
