import java.util.Set;
import java.util.TreeSet;

/*
 * @author : Deepa G
 * Case :
 *  Permutation of string where source string has no repetition.
 *  Target string is of same length as source string
 *  
 *  Analysis : Here use this algorithm if you do not have repetitions in your source string.
 *  You could use this even if repetitions are there but unnecessary calculations lead to inefficiency
 *  
 *  Complexity :Time :  O(nPr) Space : O(nPr)
 *  
 *  
 */
public class PermutateStringBasic {
	public static void main(String[] args) {
		String s = "aabb";
		Set<String> result = new TreeSet<String>();
		int n = s.length();
		int r = 3;
		permutate(s,"",n,r,result);
		System.out.println(result);
		System.out.println(result.size());
	}
	public static void permutate(String input,String prefix,int n,int r,Set<String> result){
		if(r==0){
			result.add(prefix);
			return;
		}
		for(int i=0;i<input.length();i++){
			String newPrefix = prefix + input.charAt(i);
			String newInput = input.substring(0, i) + input.substring(i+1);
			//instead of input just use input if repetetions are allowed 
			permutate(newInput,newPrefix,n,r-1,result);
			
		}
	}
}
