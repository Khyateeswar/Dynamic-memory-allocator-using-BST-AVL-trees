// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        BSTree n=new BSTree();
        Dictionary z=this.freeBlk.getFirst();
        if(z==null){
            return;
        }
        while(z!=null){
            n.Insert(z.address,z.size,z.address);
            z=z.getNext();
        }
        Dictionary x=n.getFirst();
        while(x.getNext()!=null){
            if(x.getNext().address==x.address+x.size){
                Dictionary y=x;
                Dictionary v=x.getNext();
                y.key=x.size;
                v.key=x.getNext().size;
                n.Delete(x);
                n.Delete(x.getNext());
                this.freeBlk.Delete(y);
                this.freeBlk.Delete(v);
                x=n.Insert(x.address,v.size+x.size,x.key);
                y=this.freeBlk.Insert(y.address,y.size+v.size,y.size+v.size);
            }else{
                x=x.getNext();
            }
        }
        n=null;
        return ;
    }
}