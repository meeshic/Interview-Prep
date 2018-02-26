// CodeFights:Trees:kthSmallestInBST 
// AKA: Leetcode #230


/*
    Question: Given a binary search tree t, find the kth smallest element in it.
*/




/*
    Algorithm: Traverse tree in-order (L, C, R)
*/




// Recursive Solution: Save BST in-order to a list and return index k-1

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
int kthSmallestInBST(Tree<Integer> t, int k) {
    List<Integer> list = new ArrayList<Integer>();
    
    inOrder(t, list);
    
    return list.get(k-1);
}

private void inOrder(Tree<Integer> curr, List<Integer> list){
    if(curr.left != null)
        inOrder(curr.left, list);
    
    list.add(curr.value);
    
    if(curr.right != null)
        inOrder(curr.right, list);
}

/*
Complexity:
    Time: O(N), N = # nodes in BST
    Space: O(depth + N), depth=log(N) for max size on call stack & list length of N is created
*/





// Iterative solution: Using deque(as stack), keep track of # of pops which
//  which correlate directly to k


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
int kthSmallestInBST(Tree<Integer> t, int k) {
    int kth = Integer.MIN_VALUE;
    
    //Stack
    Deque<Tree<Integer>> dq = new ArrayDeque<>();
    
    dq.addFirst(t);
    
    // In-order storage of tree (L, C, R)
    // Keep track of x-th smallest number by number of pops
    while(!dq.isEmpty() && k > 0){
        // add left nodes first to stack
        if(dq.peek().left != null){
            dq.addFirst(dq.peek().left);
        }
        // then add right nodes to stack
        else{
            // loop until node with non-null right child is found
            // or until kth smallest value is found
            while(!dq.isEmpty()){
                // if num of pops == k
                if(--k == 0){
                    kth = dq.peek().value;
                    break;
                }
                
                Tree<Integer> curr = dq.poll();
                
                // valid right child found
                if(curr.right != null){
                    dq.addFirst(curr.right);
                    break;
                }
            }
        }
    }
    
    return kth;
}


/*
Complexity:
    Time: O(N), N = # nodes in BST
    Space: O(depth), depth = log(N) for max size on stack
*/
