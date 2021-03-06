// CodeFights:Trees:restoreBinaryTree 
// AKA: Leetcode #105. Construct Binary Tree from Preorder and Inorder Traversal

// Note: Code is from LeetCode. Tree<T> is used on CodeFights.


/*
    Question: Given preorder (CLR) and inorder (LCR) traversal of a tree, construct the binary tree.
        Note: All numbers in tree are pairwise distinct.
*/


/*
    Algorithm: Notice that preorder gives you left nodes starting from root.
    - Right subtree detected when inorder[x] == preorder[y], where x can equal y. The first element 
        of inorder will be the leftmost element. If inorder[0]==preorder[0], this means there 
        is no left subtree. This logic can be used for subtrees as well. When inorder != preorder, 
        this means that there is a right subtree since preorder hits current node then left node while
        inorder finds leftmost node.
    - 1. Build left subtree with preorder until a match with inorder. If there is a match, add 
        the right node and repeat.
    - * Use a stack to keep track of nodes with potential right subtrees. When a match between
        stack.peek()(preorder) and inorder is found, then pop until inorder != stack.peek(), 
        essentially going up the tree until a node with a right subtree is found. 
        
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// refactored, clean, faster version
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;
        
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode toRight = null;
        int inIndex=0;
        
        TreeNode root = new TreeNode(preorder[0]);
        stack.addFirst(root);
        
        //Note: begin with index 1 to avoid adding root to stack again
        for(int preIndex=1; preIndex<preorder.length; preIndex++){
            //find node with right subtree
            if(!stack.isEmpty() && stack.peek().val == inorder[inIndex]){
                while(!stack.isEmpty() && stack.peek().val == inorder[inIndex]){
                    toRight=stack.poll();
                    inIndex++;
                }
                
                TreeNode rightNode = new TreeNode(preorder[preIndex]);
                toRight.right = rightNode;
                stack.addFirst(rightNode);
            }
            
            //build left subtree
            else{
                TreeNode leftNode = new TreeNode(preorder[preIndex]);
                stack.peek().left = leftNode;
                stack.addFirst(leftNode);
            }
        }
        
        return root;
    }
}


/*
    Complexity:
        Time: O(preorder.length)
        Space: O(d), d=depth of tree, max d = preorder.length
*/