import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PermutationEfficient {

	public static void main(String[] args) {
		System.out.println("Enter number of elements");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.println("Enter elements");
		List<Integer> list = new ArrayList<>();
		for(int i =0;i<n;i++)
			list.add(scan.nextInt());
		System.out.println("Enter r of nPr");
		int r = scan.nextInt();
		scan.close();
		List<List<Integer>> permutations=new ArrayList<>();
		//findPermutationsNoRepetition(list,r,permutations);
		findPermutationsWithRepetition(list,r,permutations);
		System.out.println(permutations);
	}

	private static void findPermutationsWithRepetition(List<Integer> list, int r, List<List<Integer>> permutations) {
		Set<Integer> set = new TreeSet<Integer>();
		for(int ele : list){
			set.add(ele);
		}
		int input[] = new int[set.size()];
		int result[] = new int[r];
		int index=0;
		for(int ele:set)
			input[index++]=ele;
		findPermutationsWithRepetitionUtil(input,result,0,permutations,r);
	}

	private static void findPermutationsWithRepetitionUtil(int[] input, int[] result, int level,
			List<List<Integer>> permutations, int r) {
		if(level==r){
			List<Integer> p=new ArrayList<Integer>();
			for(int number:result)
				p.add(number);
			permutations.add(p);
			return;
		}
		for(int i=0;i<input.length;i++){
			result[level]=input[i];
			findPermutationsWithRepetitionUtil(input,result,level+1,permutations,r);
		}
	}

	public static void findPermutationsNoRepetition(List<Integer> list,int r, List<List<Integer>> permutations) {
		Map<Integer,Integer> freq = new TreeMap<>();
		for(int ele : list){
			freq.compute(ele, (key,val)->{
											return (val==null) ? 1 : 1+val;										
										 });
		}
		int input[] = new int[freq.size()];
		int eleCount[] = new int[freq.size()];
		int result[] = new int[r];
		int index=0;
		for(Entry<Integer,Integer> entry : freq.entrySet()){
			input[index]=entry.getKey();
			eleCount[index]=entry.getValue();
			index++;
		}
		findPermutationsNoRepetitionUtil(input,eleCount,result,0,permutations,r);
		
	}

	private static void findPermutationsNoRepetitionUtil(int[] input, int[] eleCount, int[] result, int level,
			List<List<Integer>> permutations,int r) {
		if(level==r){
			List<Integer> p=new ArrayList<Integer>();
			for(int number:result)
				p.add(number);
			permutations.add(p);
			return;
		}
		for(int i=0;i<input.length;i++){
			if(eleCount[i]==0)
				continue;
			result[level]=input[i];
			eleCount[i]--;
			findPermutationsNoRepetitionUtil(input,eleCount,result,level+1,permutations,r);
			eleCount[i]++;			
		}
	}

}
