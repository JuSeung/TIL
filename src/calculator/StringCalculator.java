package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	int add(String text){
		if(isBlank(text)){
			return 0;
		}
		return sum(ToInt(Split(text)));
	}
	private boolean isBlank(String text) {
		return text == null || text.isEmpty();
	}
	
	private int sum(int[] numbers){
	    int sum = 0;
		for (int number : numbers) {
			sum += number;
		}
		return sum;
	}
	private int[] ToInt(String[] valuse ){
			int[] numbers = new int[valuse.length]; 
			for(int i =0; i < valuse.length; i++){
				numbers[i] = Integer.parseInt(valuse[i]);
			}
		return numbers; 
	}
	private String[] Split(String text){
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
		if(m.find()){
			String customDelimeter = m.group(1);
			return m.group(2).split(customDelimeter);
		}
		return text.split(",|:");
	}
	
}
