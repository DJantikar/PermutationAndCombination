import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Combination {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int r = scan.nextInt();
		List<Integer> nums = new ArrayList<>();
		for(int i=0;i<n;i++){
			nums.add(scan.nextInt());
		}
		scan.close();
		List<List<Integer>> combinations = new ArrayList<>();
		findCombinationsNoRepetitions(n,r,nums,combinations);
		System.out.println(combinations);
	}

	private static void findCombinationsNoRepetitions(int n, int r, List<Integer> nums,
			List<List<Integer>> combinations) {
		Map<Integer,Integer> f = new TreeMap<>();
		for(int num : nums){
			f.compute(num, (key,value) -> {return value==null ? 1 : 1+value;});
		}
		int[] input = new int[f.size()];
		int[] eleCount = new int[f.size()];
		int index = 0;
		for(Entry<Integer,Integer> entry : f.entrySet()){
			input[index] = entry.getKey();
			eleCount[index] = entry.getValue();
			index++;
		}
		int[] result = new int[r];
		findCombinationsWithoutRepetitionsUtil(input,eleCount,result,combinations,0,0,r);
	}

	private static void findCombinationsWithoutRepetitionsUtil(int[] input,int[] eleCount, int[] result,
			List<List<Integer>> combinations, int pos, int level, int r) {
		if(level==r){
			List<Integer> l = new ArrayList<>();
			for(int resNum : result){
				l.add(resNum);
			}
			combinations.add(l);
			return;
		}
		for(int i=pos;i<input.length;i++){
			if(eleCount[i]==0)
				continue;
			result[level]=input[i];
			eleCount[i]--;
			findCombinationsWithoutRepetitionsUtil(input,eleCount,result,combinations,i,level+1,r);
			eleCount[i]++;
		}
	}
}
