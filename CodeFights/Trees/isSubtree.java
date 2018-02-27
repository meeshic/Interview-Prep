// CodeFights:Trees:isSubtree
// AKA: Leetcode #572


/*
    Question:
    Given two binary trees t1 and t2, determine whether the 
    second tree is a subtree of the first tree. A subtree for 
    vertex v in a binary tree t is a tree consisting of v and 
    all its descendants in t. Determine whether or not there 
    is a vertex v (possibly none) in tree t1 such that a subtree 
    for vertex v (possibly empty) in t1 equals t2.
*/

/*
    Algorithm:
        - Iterate over t1 and if there is a hit/match with t2:
            check if that if the starting node of subtree t2 in t1 as seen below
            (t1.left, t2.left) && (t1.right, t2.right)
        - To check for all cases (if starting node is lower in the tree),
            check left and right subtree with t2's root
*/


//
// Definition for binary tree:
// class Tree<T> {
//   Tree(T x) {
//     value = x;
//   }
//   T value;
//   Tree<T> left;
//   Tree<T> right;
// }
boolean isSubtree(Tree<Integer> t1, Tree<Integer> t2) {
    if(t2 == null) return true;
    
    return validateSubtree(t1, t2, t2);
}

private boolean validateSubtree(Tree<Integer> t1, Tree<Integer> t2, Tree<Integer> t2Root){
    if(t1==null && t2==null) return true;
    if(t1==null || t2==null) return false;
    
    boolean hit = false;
    
    if(t1.value.equals(t2.value)){
        hit = validateSubtree(t1.left, t2.left, t2Root) && validateSubtree(t1.right, t2.right, t2Root);
    }
    
    return hit || validateSubtree(t1.left, t2Root, t2Root) || validateSubtree(t1.right, t2Root, t2Root);
}
