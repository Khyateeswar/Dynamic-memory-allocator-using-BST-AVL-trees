// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
	if(blockSize==0){
		return -1;
	}		
        Dictionary f=this.freeBlk.Find(blockSize,false);
        if(f!=null){
            this.allocBlk.Insert(f.address,blockSize,f.address);
            this.freeBlk.Delete(f);
            if(f.size!=blockSize){
                this.freeBlk.Insert(f.address+blockSize,f.size-blockSize,f.size-blockSize);
            }
            return f.address ;
        }
        return -1;
    } 
    
    public int Free(int startAddr) {
        Dictionary c = this.allocBlk.Find(startAddr,true);
        if(c!=null){
            this.allocBlk.Delete(c);
            this.freeBlk.Insert(c.address,c.size,c.size);
            return 0;
        }

        return -1;
    }
}