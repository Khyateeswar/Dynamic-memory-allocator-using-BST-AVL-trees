// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    {
        AVLTree t= new AVLTree(address,size,key);
        AVLTree y=this.getRoot();
        if(y.right!=null){
            AVLTree z =y.right;
            AVLTree s=y;
            int f=0;
            while(z!=null){
                if(t.key<z.key){
                    s=z;
                    z=z.left;
                    f=0;
                }else if(t.key==z.key){
                    if(t.address>z.address){
                        s=z;
                        z=z.right;
                        f=1;
                    }else if(t.address==z.address){
                        return null;
                    }else{
                        s=z;
                        z=z.left;
                        f=0;
                    }
                }else{
                    s=z;
                    z=z.right;
                    f=1;
                }
            }
            t.parent=s;
            if(f==0){
                s.left=t;
            }else{
                s.right=t;
            }
        }else{
            y.right=t;
            t.parent=y;
        }
        AVLTree x=t;
        while(t.parent!=null ){
            if(checkNode(t)!=true){
                break;
            }else{
                adjH(t);
            }
            t=t.parent;

        }
        if(t.parent!=null){
            reBal(t);
        }
        return x;
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree z=this.getRoot().getFirst();
        if(z==null){
            return false;
        }
        while(z!=null){
            if(z.key==e.key && z.address==e.address && z.size==e.size){
                break;
            }else{
                z=z.getNext();
            }
        }
        if(z!=null){
            z=Del(z);
            while(z.parent!=null){
                if(checkNode(z)!=true){
                    reBal(z);
                }
                adjH(z);
                z=z.parent;
            }
            return true;
        }
        return false;
    }
    private AVLTree Del(AVLTree z){
        if(z.parent==null){
            return null;
        }
        int f;
        if(z.parent.left==z){
            f=0;
        }else{
            f=1;
        }
        if(z.left==null && z.right==null){
            if(f==0){
                z.parent.left=null;
            }else{
                z.parent.right=null;
            }
            return z.parent;
        }else if(z.left==null && z.right!=null){
            if(f==0){
                z.parent.left=z.right;
                z.right.parent=z.parent;
            }else{
                z.parent.right=z.right;
                z.right.parent=z.parent;
            }
            return z.parent;
        }else if(z.left!=null && z.right==null){
            if(f==0){
                z.parent.left=z.left;
                z.left.parent=z.parent;
            }else{
                z.parent.right=z.left;
                z.left.parent=z.parent;
            }
            return z.parent;
        }else{
            AVLTree temp= new AVLTree(z.address,z.size,z.key);
            AVLTree x=z.getNext();
            temp.address=x.address;
            temp.size=x.size;
            temp.key=x.key;
            temp.parent=z.parent;
            temp.right=z.right;
            temp.left=z.left;
            z.right.parent=temp;
            z.left.parent=temp;
            if(f==0){
                z.parent.left=temp;
            }else{
                z.parent.right=temp;
            }
            return Del(x);
        }
    }
    private boolean checkNode(AVLTree z){
        if(z.left==null || z.right==null){
            if(z.left==null && z.right==null){
                if(z.height!=0){
                    return false;
                }else{
                    return true;
                }
            }
            if(z.left==null && z.right!=null){
                if(z.right.height!=0){
                    return false;
                }else{
                    if(z.height!=1){
                        return false;
                    }else{
                        return true;
                    }
                }
            }
            if(z.left!=null && z.right==null){
                if(z.left.height!=0){
                    return false;
                }else{
                    if(z.height!=1){
                        return false;
                    }
                    return true;
                }
            }
        }
        int x=(z.left.height-z.right.height);
        if(x<-1 || x>1){
            return false;
        }else{
            return true;
        }
    }
    private boolean reBal(AVLTree z){
        AVLTree y=z;
        AVLTree x=z;
        if(z.left==null && z.right!=null){
            if(z.right.height==0){
                z.height=1;
            }else{
                if(z.right.left==null && z.right.right!=null){
                    Rot(z,z.right);
                }else if(z.right.left!=null && z.right.right==null){
                    Rot(z.right,z.right.left);
                    Rot(z,z.right);
                }else{
                    if(z.right.left.height>=z.right.right.height){
                        Rot(z.right,z.right.left);
                        Rot(z,z.right);
                    }else{
                        Rot(z,z.right);
                    }
                }
            }
        }else if(z.left==null && z.right==null){
            z.height=0;
        }else if(z.left!=null && z.right==null){
            if(z.left.height==0){
                z.height=1;
            }else{
                if(z.left.left==null && z.left.right!=null){
                    Rot(z.left,z.left.right);
                    Rot(z,z.left);
                }else if(z.left.left!=null && z.left.right==null){
                    Rot(z,z.left);
                }else{
                    if(z.left.left.height>=z.left.right.height){
                        Rot(z,z.left);
                    }else{
                        Rot(z.left,z.left.right);
                        Rot(z,z.left);
                    }
                }
            }
        }else{
            if(z.left.height>z.right.height){
                x=z.left;
                if((x.left!=null && x.right==null) || (x.left!=null && x.right!=null && x.left.height>x.right.height)){
                    y=x.left;
                    Rot(z,x);
                }else{
                    y=x.right;
                    Rot(x,y);
                    Rot(z,y);
                }
            }else{
                x=z.right;
                if((x.left!=null && x.right!=null) || (x.left!=null && x.right!=null && x.left.height>x.right.height)){
                    y=x.left;
                    Rot(x,y);
                    Rot(z,y);
                }else{
                    y=x.right;
                    Rot(z,x);
                }
            }
        }
        return true;
    }
    public AVLTree Find(int key, boolean exact)//even though exact is false the best fit has too done****remainder
    {   
        if(this!=null){
            AVLTree z=this.getRoot().getFirst();
            if(z!=null){
                while(z!=null && z.key<key){
                    z=z.getNext();
                }
                if(z==null){
                    return null;
                }else{
                    if(exact==true){
                        if(z.key==key){
                            return z;
                        }else{
                            return null;
                        }
                    }
                    return z;
                }
            }
        }
        return null;
    }

    private AVLTree getRoot(){
        AVLTree x=this;
        if(this!=null){
            while(x.parent!=null){
                x=x.parent;
            }
            return x;
        }
        return null;
    }
    public AVLTree getFirst()
    {   AVLTree z=this;
        if(z!=null){
            if(z.parent==null){
                if(z.right!=null){
                    z=z.right;
                }else{
                    return null;
                }
            }
            while(z.left!=null){
                z=z.left;
            }
            return z;
        }
        return null;
    }

    public AVLTree getNext()
    {   
        AVLTree z=this;
        if(z.parent==null){
            return null;
        }
        if(z!=null){
            if(z.right!=null){
                z=z.right;
                while(z.left!=null){
                    z=z.left;
                }
                return z;
            }else{
                while( z.parent.parent!=null && z.parent.right==z){
                    z=z.parent;
                }
                if(z.parent.parent!=null){
                    return z.parent;
                }
            }
        }
        return null;
    }
    private void Rot(AVLTree x,AVLTree y){//remember x should be parent of y ,i.e the pointers should be given in that order
        if(x==null || y==null){
            return;
        }
        y.parent=x.parent;
        if(x.parent.left==x){
            x.parent.left=y;
        }
        if(x.parent.right==x){
            x.parent.right=y;
        }
        x.parent=y;
        if(x.left==y){
            if(y.right==null){
                x.left=null;
            }else{
                x.left=y.right;
                y.right.parent=x;   
            }
            y.right=x;
        }else{
            if(y.left==null){
                x.right=null;
            }else{
                x.right=y.left;
                y.left.parent=x;
            }
            y.left=x;
        }
        adjH(x);
        adjH(y);
        return;  
    }
    private void adjH(AVLTree z){
        if(z.left==null && z.right==null){
            z.height=0;
        }else if(z.left!=null && z.right==null){
            z.height=z.left.height+1;
        }else if(z.left==null && z.right!=null){
            z.height=z.right.height+1;
        }else{
            if(z.left.height>=z.right.height){
                z.height=z.left.height+1; 
            }else{
                z.height=z.right.height+1;
            }
        }
        return;
    }


    public boolean sanity()
    {

        return false;
    }
}


