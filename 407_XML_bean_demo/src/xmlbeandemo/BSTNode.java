//BSTNode.java : binary search tree node , recursief
//auteur: J.Kaldeway

package xmlbeandemo;

public class BSTNode<E extends Comparable<E>>
{
    E element_;
    BSTNode<E> left_;
    BSTNode<E> right_;
    
       
    public E getElement_() {
		return element_;
	}

	public void setElement_(E element_) {
		this.element_ = element_;
	}

	public BSTNode<E> getLeft_() {
		return left_;
	}

	public void setLeft_(BSTNode<E> left_) {
		this.left_ = left_;
	}

	public BSTNode<E> getRight_() {
		return right_;
	}

	public void setRight_(BSTNode<E> right_) {
		this.right_ = right_;
	}

	public BSTNode()
	{}
	
	
	BSTNode(E element, BSTNode<E> left, BSTNode<E> right)
    {
        element_ = element;
        left_ = left;
        right_ = right;
    }
 
    BSTNode(E[] a)
    {
   	    // eis: a != null && a.length != 0
   	    
   	    int len = a.length;
   	    int mid = len/2;
   	    element_ = a[mid];
   	    left_ = null;
   	    right_ = null;
   	    if (mid > 0)
   	      insert(a,0,mid-1);
   	    if (len-1 > mid)
   	      insert(a,mid+1,len-1);   
    }
    
    private void insert(E[] a, int low, int high)
    {
    	int mid = (low+high+1)/2;
    	insert(a[mid]);
    	if (mid > low)
    	   insert(a,low,mid-1);
        if (high > mid)
    	   insert(a,mid + 1,high);
    }
    
    private String toString(int nspaces)
    {
        String s1 = "";
        String s2 = "";
        String s3 = "";
        if (right_ != null) 
            s1 = right_.toString(nspaces + 3);
        for (int i = 0; i < nspaces ; i++ ) s2 = s2 + ' ';
        s2 = s2 + element_.toString() + '\n';
        if (left_ != null)
            s3 = left_.toString(nspaces + 3); 
        return s1 + s2 + s3;
    }
    
    public String toString()
    {
    	return toString(0);
    }
    
    BSTNode<E> search(E e)
    {
    	// eis: e != null    	
    	
    	BSTNode<E> current = this;
    	boolean found = false;
    	while (!found && current != null)
    	{
    	   	if (current.element_.compareTo(e) < 0)
    			current = current.right_;
    	    else if (current.element_.compareTo(e) > 0)
    			current = current.left_;
			else found = true;
        }
        if (found)    	
    	   return current;
    	else return null;
    }
    
    BSTNode<E> search2(E e)
    {
    	// eis: e != null    	
    	
    	if (element_.equals(e)) return this;
        BSTNode<E> parent = parent(e);
    	if (parent == null)  return null;
    	if (parent.element_.compareTo(e)<0)
    	     return parent.right_;
    	return parent.left_; 
    }
    	
    boolean insert(E e)
    {
    	// eis: e != null
    	
    	BSTNode<E> parent = this;
    	BSTNode<E> current = null;
    	boolean found = false;
    	
    	if (parent.element_.compareTo(e) < 0)
    		current = parent.right_;
    	else if (parent.element_.compareTo(e) > 0)
    		current = parent.left_;
		else found = true;

    	while (!found && current != null)
    	{
    		parent = current;
	    	if (parent.element_.compareTo(e) < 0)
	    		current = parent.right_;
	    	else if (parent.element_.compareTo(e) > 0)
	    		current = parent.left_;
			else found = true;
        } 
        if (!found)
        {
	    	if (parent.element_.compareTo(e) < 0)
	    		parent.right_ = new BSTNode<E>(e,null,null);
	    	else parent.left_ = new BSTNode<E>(e,null,null); 
        }
           	
    	return !found;
    }
    
    private BSTNode<E> parent(E e)
    {
    	// eis: e != null
    	// eis: element.equals(e) != 0
    	
    	BSTNode<E> parent = this;
    	BSTNode<E> current = null;
    	boolean found = false;
    	
    	if (parent.element_.compareTo(e) < 0)
    		current = parent.right_;
    	else if (parent.element_.compareTo(e) > 0)
    		current = parent.left_;
		else return null;

    	while (!found && current != null)
    	{
    		if (current.element_.equals(e)) 
    		    found = true;
    		else
    		{
    			parent = current;
    			if (current.element_.compareTo(e) < 0)
	    		current = current.right_;
	    	    else current = current.left_;
	    	}
		} 
        if (found)
        	return parent;
        else return null;
	} 
    
    boolean delete(E e)
    {
    	// eis: e != null
    	// eis: element.equals(e) != 0
    	
    	BSTNode<E> parent = parent(e);
    	BSTNode<E> current;
    	
    	if (parent == null) return false;
    	if (parent.element_.compareTo(e) < 0)
    	{
    		current = parent.right_;
    	    if (current.left_ == null)
    	    {
    	        parent.right_ = parent.right_.right_;
    	        return true;
    	    }
    	    else if (current.right_ == null)
    	    {
    	        parent.right_ = parent.right_.left_;
    	        return true;
    	    }    
     	}
    	else 
    	{   
    	    current = parent.left_;
    	    if (current.left_ == null)
    	    {
    	        parent.left_ = parent.left_.right_;
    	        return true;
    	    }
    	    else if (current.right_ == null)
    	    {
    	        parent.left_ = parent.left_.left_;
    	        return true;
    	    }    
    	}
        if (current.right_.left_ == null)
        {
         	current.element_ = current.right_.element_;
         	current.right_ = current.right_.right_;
         	return true;
        }
    	BSTNode<E> node = current.parentMinRightTree();
    	current.element_ = node.left_.element_;
    	node.left_ = node.left_.right_;
    	return true; 
    }
    
   	BSTNode<E> parentMinRightTree()
   	{
   		// eis right.left != null

   		BSTNode<E> parent = right_;
   		BSTNode<E> current = parent.left_;
   		while (current.left_ != null)
   		{
   			parent = current;
   			current = current.left_;
   		}
   		
   		return parent;
   	}

}
