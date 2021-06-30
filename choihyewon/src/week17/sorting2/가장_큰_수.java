package week17.sorting2;

import java.util.Arrays;
import java.util.Comparator;

public class 가장_큰_수 {
	static int[] numbers = {6,10,2};
	//static int[] numbers = {3,30,34,5,9};
	public static void main(String[] args) {
		String answer = solution(numbers);
		
		System.out.println(answer);
		
	}
	static String solution(int[] numbers) {
        String answer = "";
        
        // int형인 배열을 String형으로 다시 저장 
        String[] str = new String[numbers.length];     
        for(int i=0; i<str.length; i++) {
        	str[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(str, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return (o2+o1).compareTo(o1+o2);
			}
        });
        
         if(str[0].equals("0")) {
        	return str[0];
        }
        
        for(int i=0; i<str.length; i++) {
        	answer += str[i];
        }
        
        return answer;
    }

}
