// CycleNode.java
// Auteur: J. Kaldeway 

package xmlbeandemo;

public class CycleNode
{
    private int i_;
    private CycleNode next_;

    public CycleNode(int i,CycleNode next)
    {
        i_ = i;
        next_ = next;
    }
    
    public CycleNode()
    {
        i_ = 0;
        next_ = null;
    }
    
    public void setI(int i)
    {
        i_ = i;
    }
    
    public int getI()
    {
        return i_;
    }

    public void setNext(CycleNode next)
    {
        next_ = next;
    }
    
    public CycleNode getNext()
    {
        return next_;
    }

//    public String toString()
//    {
//        CycleNode current = this;
//
//        String s = new String(""  + current.i_ + " ");
//        current = current.next_;
//
//        while (current != this) 
//        {
//            s = s + current.i_ + " ";
//            current = current.next_;
//        }
//        return s;
//    }
}