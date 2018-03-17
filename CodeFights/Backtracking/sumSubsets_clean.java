import java.util.*;

// AKA: LeetCode #40 (below code format)

/*
Problem:
Given a sorted array of integers arr and an integer num, find all possible unique 
subsets of arr that add up to num. Both the array of subsets and the subsets 
themselves should be sorted in lexicographical order.
*/

class sumSubsets_clean {
    
    public static void main(String[] args){
        int[] input = {1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15, 16, 16, 16, 19, 19, 20};
        //int[] input = {1,2,3,4,5,6,3,2,4,5,7,9,9,6,3,3,5,7,7,7,3,2,4,5,3,2,5,3,2,4,2,3,4,5,3,2,3,4,5,32,5,5,9,6,4,6,4,22,1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15, 16, 16, 16, 19, 19, 20};
        //int[] input = {1, 2,3,4,5};
        //int[] input = {1,2,3,4,5,6,3,2,4,5,7,9,9,6,3,3,5,7,7,7,3,2,4,5,3,2,5,3,2,4,2,3,4,5,3,2};
        int num = 20;
        Arrays.sort(input);
        long startTime = System.nanoTime();
        int[][] answer = sumSubsets(input, num);
        long endTime = System.nanoTime();
        
        long duration = (endTime - startTime)/1000000;
        System.out.println(duration);
        System.out.println("size:" + answer.length);
        
        /*for(int i=0; i<answer.length; i++){
            for(int j=0; j<answer[i].length; j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
            System.out.println("==================");
        }*/
    }

static int[][] sumSubsets(int[] arr, int num) { 
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    findSubsets(arr, 0, num, new ArrayList<Integer>(), result);
    
    int[][] answer = new int[result.size()][];
    int index = 0;
    for(List<Integer> l : result){
        answer[index++] = l.stream().mapToInt(i->i.intValue()).toArray();
    }
    
    return answer;
}

static void findSubsets(int[] arr, int curr, int target, List<Integer> path, List<List<Integer>> result){
    if(target == 0){
        result.add(new ArrayList(path));
        return;
    }
    
    if(target < 0 || curr > arr.length-1)
        return;
    
    for(int i=curr; i<arr.length; i++){
        if(i>curr && arr[i]==arr[i-1])
            continue;
        path.add(arr[i]);
        findSubsets(arr, i+1, target-arr[i], path, result);
        path.remove(path.size()-1);
    }
}
}

/*
Algorithm:
- search for all subsets -> do a DFS
- Function def: (int[] arr, int curr, int target, List<Integer> path, List<List<Integer>> result)
- Base case(s):
    1. if curr sum == target 
    2. if curr sum > target
- for (all elements in input array):
    if(curr element != prev element) // this prevents duplicate subsets: {1,1,...} -> {1, ..}, {1, ..}
        add current element to path and call recursive function
        take current element off
        
- Edge cases:
    1. num = 0
    2. empty input array (size = 0)
- Complexity:
    Time: O(2^N)
    Space: O(N), N=length of input array (depth of tree = max length of path)
*/