import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution3 {
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
        int[][] minMax = new int[N][2];
        int index = 0;

        while(N > 0 && scanner.hasNextLine()){
          System.out.println(N + "ith line");
          String line = scanner.nextLine();
          System.out.println("line:" + line);
          String[] str = line.split("\\s+");
          
          ArrayList<Integer> list = new ArrayList<>();
          int min = Integer.MAX_VALUE;
          int max = Integer.MIN_VALUE;
          for(int i=1; i<str.length; i++){
            int num = Integer.parseInt(str[i]);
            if(num<min) min = num;
            if(num>max) max = num;
            list.add(num);
          }
          
          minMax[index][0] = min;
          minMax[index][1] = max;
          
          System.out.println(list);
          input.add(list);
          
          index++;
          N--;
        }
        
        ArrayList<Integer> output = new ArrayList<>();
        countPossibleKth(input, output, minMax, K);
        System.out.println("output for test " + numTests + ": " + output.size());
        
        numTests--;
      }
      
      scanner.close();
    
    } catch(NoSuchElementException e){
      System.out.println("Input pattern not found");
      e.printStackTrace();
    }
  }
  
  public static void countPossibleKth(ArrayList<ArrayList<Integer>> input, ArrayList<Integer> output, int[][] minMax, int k){
    int smaller = k-1;
    int bigger = input.size()-k;

    for(ArrayList<Integer> currList : input){
      for(Integer i : currList){
        int s = 0;
        int b = 0;
        int index = 0;
        for(ArrayList<Integer> list: input){
          //larger than biggest number in list
          if(i > minMax[index][1]) s++;
          //smaller than smallest number in list
          if(i < minMax[index][0]) b++;
          index++;
        }
        if(s <= smaller && b <= bigger) output.add(i);
      }
    }
  }
}
