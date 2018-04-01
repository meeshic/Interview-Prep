//Leetcode #3. Longest Substring Without Repeating Characters


class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 1) return 1;
        
        HashMap<Character, Integer> seen = new HashMap<>();
        for(char c : s.toCharArray()){
            if(seen.get(c) == null)
                seen.put(c, 0);
        }
        
        int begin=0;
        int end=0;
        int maxLength=0;
        int dupCount=0;
        
        while(end<s.length()){
            char current = s.charAt(end);
            int freq = seen.get(current)+1;
            seen.put(current, freq);
            
            //duplicate found
            if(freq >= 2)
                dupCount++;
            
            end++;
            
            if(dupCount > 0){
                char b = s.charAt(begin);
                int count = seen.get(b)-1;
                seen.put(b, count);
                if(count >= 1)
                    dupCount--;
                begin++;
            }
            
            if(dupCount == 0 && end-begin>maxLength)
                maxLength = end-begin;
        }
        return maxLength;
    }
}

/*
Clarifying questions:
    - can an empty and/or null string be passed in as arguments?
    - will the string consist only of lower case English alphabet letters?
    
Algorithm:
    - Naive Brute Force: O(N^2), N=s.length() with count array/hashmap
        > iterate through the string and use each character as the beginning of a potential substring
        > find the longest substring using current character
        > move onto next character
    - Sliding window technique: O(N), N=s.length() with count array/hashmap
        > use begin and end index
        > update seen.get(end) so that when checking to see if dupCount-- is needed, it is accurate
        > increment end until a duplicate is found (= found substring with no duplicates)
            >> update int longestSubstring if needed
            >> end++
        > if there exists duplicates in window substring, begin++
        
Edge/special cases:
    - all duplicate string (i.e. "aaaa...a") -> begin and end move together and count stays at 0
    - find a duplicate, but moving begin++ does not get rid of it, so while there exists a duplicate you must begin++
*/

//7:38-8:25
