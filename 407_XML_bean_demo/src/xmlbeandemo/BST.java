// BST : binary search tree 
// auteur: J.Kaldeway

package xmlbeandemo;

public class BST<E extends Comparable<E>>
{
    private BSTNode<E> root_ = null;

    public BST(){}
    
    public BSTNode<E> getRoot()
    {
    	return root_;
    }
    
    public void setRoot(BSTNode<E> root)
    {
    	root_ = root;
    }
    
    public BST(E[] a)
    {
    	if (a == null || a.length == 0)
    	   root_ = null;
    	else   
   		   root_ = new BSTNode<E>(a);
    }
    
    public String toString()
    {
        if (root_ == null)
           return "null-tree";
        else
           return root_.toString();  
    }
    
    public BSTNode<E> search(E e)
    {
    	if (e == null || root_ == null)
    	    return null;
    	else
    	    return root_.search(e);
    }
    
    public boolean insert(E e)
    {
    	if (e == null) return false;
    	if (root_ == null)
    	{
    	    root_ = new BSTNode<E>(e,null,null);
    	    return true;
    	}
    	else
    	    return root_.insert(e);
    	  
    }
    
    public boolean delete(E e)
    {
    	if (e == null || root_ == null)
    	    return false;
    	else
    	{
    		if (root_.element_.equals(e))
    		{
    			if (root_.left_ == null)
    				root_ = root_.right_;
    			else if (root_.right_ == null)
    				root_ = root_.left_;
    			else if (root_.right_.left_ == null)
    			{
    				root_.element_ = root_.right_.element_;
    				root_.right_ = root_.right_.right_;
    			}
    			else
    			{
    				BSTNode<E> node = root_.parentMinRightTree();
    				root_.element_ = node.left_.element_;
    				node.left_ = node.left_.right_;    				
    			}
    			return true;
    		}
    	   
    	    else return root_.delete(e);
    	} 
    }
        
    public static void main(String[] s)
    {
    	BST<Integer> /* b = new BST<Integer>(new Integer[]{1,2,3});
    	System.out.println(b);
        System.out.println("----------------");
    	b = new BST<Integer>(new Integer[]{1,2,3,4});
    	System.out.println(b);
        System.out.println("----------------");
    	b = new BST<Integer>(new Integer[]{1,2,3,4,5,6,7,8,9,10});
    	System.out.println(b);
        System.out.println("----------------");
*/    	b = new BST<Integer>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
    	System.out.println(b);
        BSTNode<Integer> node = b.search(new Integer(3));
        if (node != null) System.out.println(node.element_); 
        node = b.search(new Integer(4));
        if (node != null) System.out.println(node.element_); 
        node = b.search(new Integer(8));
        if (node != null) System.out.println(node.element_); 
        node = b.search(new Integer(11));
        if (node != null) System.out.println(node.element_); 
        node = b.search(new Integer(16));
        if (node != null) System.out.println(node.element_); 
        b.insert(new Integer(17));
    	System.out.println(b);
/*        System.out.println("----------------");
        System.out .println(b.insert(new Integer(10)));
        b = new BST<Integer>();
        for (int i = 1; i <=10; i++)
            b.insert(new Integer(i));
    	System.out.println(b);
*/        System.out.println("----------------");
                 
//    	b = new BST<Integer>(null);
//    	System.out.println(b);
//    	b = new BST<Integer>(new Integer[0]);
//    	System.out.println(b);
        b.delete(new Integer(14));
    	System.out.println(b);
        System.out.println("----------------");
        
        b = new BST<Integer>();
        b.insert(new Integer(3));
        b.insert(new Integer(2));
        b.insert(new Integer(10));
        b.insert(new Integer(11));
        b.insert(new Integer(9));
        b.insert(new Integer(6));
        b.insert(new Integer(7));
        b.insert(new Integer(8));
    	System.out.println(b);
        System.out.println("----------------");
        b.delete(new Integer(3));
    	System.out.println(b);
        System.out.println("----------------");
    }
}