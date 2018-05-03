import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution1 {
  public static void main(String[] args) {
    try{
      int numTests = 0;
      Scanner scanner = new Scanner(System.in);

      if(scanner.hasNextInt()) numTests = scanner.nextInt();

      while(numTests > 0){
        System.out.println("test number " + numTests);
        int N = 0;
        int K = 0;

        if(scanner.hasNextLine()){
          N = scanner.nextInt();
          K = scanner.nextInt();
          scanner.nextLine();
        }

        System.out.println("N, K: " + N + ", " + K);
        
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        while(N > 0 && scanner.hasNextLine()){
          System.out.println(N + "ith line");
          String line = scanner.nextLine();
          System.out.println("line:" + line);
          String[] str = line.split("\\s+");
          
          ArrayList<Integer> list = new ArrayList<>();
          for(int i=1; i<str.length; i++)
            list.add(Integer.parseInt(str[i]));
          
          System.out.println(list);
          input.add(list);
          
          N--;
        }
        
        HashSet<Integer> output = new HashSet<>();
        countPossibleKth(input, new ArrayList<Integer>(), output, K, 0);
        System.out.println("output for test " + numTests + ": " + output.size());
        
        numTests--;
      }
      
      scanner.close();
    
    } catch(NoSuchElementException e){
      System.out.println("Input pattern not found");
      e.printStackTrace();
    }
  }
  
  public static void countPossibleKth(ArrayList<ArrayList<Integer>> input, ArrayList<Integer> currList, HashSet<Integer> output, int k, int curr){
    if(curr == input.size()){
      Integer[] list = currList.toArray(new Integer[0]);
      Arrays.sort(list);
      System.out.println(Arrays.toString(list));
      //k-th largest
      output.add(list[list.length-k]);
      
      //k-th smallest
      //output.add(list[k-1]);
      return;
    }
    
    for(int i=0; i<input.get(curr).size(); i++){
      currList.add(input.get(curr).get(i));
      countPossibleKth(input, currList, output, k, curr+1);
      currList.remove(currList.size()-1);
    }
  }
}
