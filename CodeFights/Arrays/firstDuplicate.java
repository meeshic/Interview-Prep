//CodeFights:Arrays:firstDuplicate

/*
Problem: 
Given an array a that contains only numbers in the range from 1 to a.length, 
find the first duplicate number for which the second occurrence has the minimal 
index. In other words, if there are more than 1 duplicated numbers, return the 
number for which the second occurrence has a smaller index than the second 
occurrence of the other number does. If there are no such elements, return -1.
*/

/*
Thoughts:
    - Since the range is 1-a.length, that indicates we can use the array indices.
    - We need a way to mark the elements that have been seen.
        > Turn seen elements to -1 (ok)
        > Turn seen elements to -element (good, preserves necessary information)
    - To mark the element a[i] as seen, mark array index of a[i] (= a[a[i]])
    - Since we are looking at elements left to right, when we see a duplicate, it
        is guaranteed to be the duplicate with the minimal second occurrence.
Algorithm:
    - for(i: 0-n)
        - if duplicate (a[a[i]] == -1)
            return a[i]
        - else, mark as seen (a[a[i]] = -1)
*/


int firstDuplicate(int[] a) {
    for(int i=0; i<a.length; i++){
        if(a[Math.abs(a[i])-1] < 0)
            return Math.abs(a[i]);
            
        a[Math.abs(a[i])-1] *= -1;
    }
    return -1;
}



/*
Complexity:
    Time: O(N), N=length of input array
    Space: O(1)
*/