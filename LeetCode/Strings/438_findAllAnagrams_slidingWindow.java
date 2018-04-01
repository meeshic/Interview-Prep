//LeetCode 438. Find All Anagrams in a String

/*
Question:
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.
*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> output = new ArrayList<Integer>();
        if(s == null || s.length() == 0 || p == null || p.length() == 0) return output;
        
        int begin=0;
        int end=0;
        //anagram indicator
        int count = p.length();
        
        int[] freq = new int[128];
        
        for(char c : p.toCharArray())
            freq[c-97]++;
        
        while(end < s.length()){
            char current = s.charAt(end);
            //match of p within s
            if(freq[current-97] > 0){
                count--;
            }
            
            freq[current-97]--;
            end++;
            
            if(count == 0) output.add(begin);
            
            //window size has exceeded p.length()
            if(end-begin == p.length()){
                int b = s.charAt(begin++)-97;
                if(freq[b] >= 0)
                    count++;
                freq[b]++;
            }
        }
        return output;
    }
}


/*
Clarifying question(s):
    - can null and/or empty strings be arguments?
Algorithm:
    - Naive brute force: O(M*N), M=s.length(), N=p.length(), and using hashmap to see if anagram
        > iterate through string s and use each character as the beginning of a potential anagram
        > check i...i+p.length()-1 to see if anagram
        > if yes, add to output
        > i++
    - Sliding window technique: O(N), N=s.length(), using hashmap to keep track of matched characters
        > use two 'pointers', begin and end that keep track of current potential anagram of p in s
        > if window size is p.length(), we found anagram of p
    - Optimized sliding window technique: O(N), using array to keep track of what's been seen in s
        > use count variable to check if an anagram has been found
        > the window size at end at of each iteration is <= p.length()
        > count is only changed when it applies to the current potential anagram
*/


/*
Naive Brute Force Approach:
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> output = new ArrayList<Integer>();
        if(s == null || s.length() == 0 || p == null || p.length() == 0) return output;
        
        int begin=0;
        int end=0;
        
        HashMap<Character, Integer> freq = new HashMap<>();
        for(char c : p.toCharArray()){
            int index = (freq.get(c) != null) ? freq.get(c) : 0;
            freq.put(c, ++index);
        }
        
        while(end < s.length()){
            char current = s.charAt(end);
            //found match
            if(freq.get(current) != null){
                if(freq.get(current) > 0){
                    freq.put(current, freq.get(current)-1);
                    end++;
                    
                    //found anagram
                    if(end-begin == p.length()){
                        output.add(begin);
                        if(freq.get(s.charAt(begin)) != null)
                            freq.put(s.charAt(begin), freq.get(s.charAt(begin))+1);
                        begin++;
                    }
                }
                else{
                    if(freq.get(s.charAt(begin)) != null)
                        freq.put(s.charAt(begin), freq.get(s.charAt(begin))+1);
                    begin++;
                }
            }
            //found no match
            else{
                //move begin to start of new potential anagram segment
                while(begin<=end){
                    if(freq.get(s.charAt(begin)) != null)
                       freq.put(s.charAt(begin), freq.get(s.charAt(begin))+1);
                    begin++; 
                }
                end++;
            }
        }
        return output;
    }
}
*/
