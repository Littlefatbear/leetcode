题目描述
Given a binary tree, return the postorder traversal of its nodes values.
For example:
Given binary tree{1,#,2,3},
   1
    \
     2
    /
   3

return[3,2,1].
Note: Recursive solution is trivial, could you do it iteratively?

思路：后序遍历，利用递归求解
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
public class Solution {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        postorder(root,list);
        return list;
    }
    public void postorder(TreeNode root,ArrayList<Integer> list)
    { 
        if(root!=null)
     {
        postorder(root.left,list);    
        postorder(root.right,list);
        list.add(root.val);
    }
        
    }
}
======非递归：


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
public class Solution {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
       ArrayList<Integer> lst = new ArrayList<Integer>();
 
        if(root == null)
            return lst; 
 
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
 
        TreeNode prev = null;
        while(!stack.empty()){
            TreeNode curr = stack.peek();
 
            // go down the tree.
            //check if current node is leaf, if so, process it and pop stack,
            //otherwise, keep going down
            if(prev == null || prev.left == curr || prev.right == curr){
                //prev == null is the situation for the root node
                if(curr.left != null){
                    stack.push(curr.left);
                }else if(curr.right != null){
                    stack.push(curr.right);
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }
 
            //go up the tree from left node    
            //need to check if there is a right child
            //if yes, push it to stack
            //otherwise, process parent and pop stack
            }else if(curr.left == prev){
                if(curr.right != null){
                    stack.push(curr.right);
                }else{
                    stack.pop();
                    lst.add(curr.val);
                }
 
            //go up the tree from right node 
            //after coming back from right node, process parent node and pop stack. 
            }else if(curr.right == prev){
                stack.pop();
                lst.add(curr.val);
            }
 
            prev = curr;
        }
 
        return lst;
       
    }
}
=========简单点的
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
 
    if(root==null) {
        return res;
    }
 
    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);
 
    while(!stack.isEmpty()) {
        TreeNode temp = stack.peek(); //
        if(temp.left==null && temp.right==null) //判断节点的是子叶还是节点
         {
            TreeNode pop = stack.pop();    //子叶就弹出  
            res.add(pop.val);
        }
        else {
            if(temp.right!=null) {             //右节点
                stack.push(temp.right);
                temp.right = null;   //标记已被轮训过得
            }
 
            if(temp.left!=null) {                  //左节点
                stack.push(temp.left);
                temp.left = null;
            }
        }
    }
 
    return res;
}