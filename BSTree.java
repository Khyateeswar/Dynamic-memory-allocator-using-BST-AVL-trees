// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    {   
        BSTree t=new BSTree(address,size,key);
        BSTree y=this.getRootsen();
        if(y.key==-1 && y.address==-1 && y.size==-1){
            if(y.right!=null){
                BSTree z =y.right;
                BSTree s=y;
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
                return t;
            }else{
                y.right=t;
                t.parent=y;
                return t;
            }
        }
        return null;
    }

    public boolean Delete(Dictionary e)
    {
        BSTree z=this.getRootsen().getFirst();
        if(z==null){
            return false;
        }
        while(z!=null){
            if(z.key==e.key && z.address==e.address && z.size==e.size){
                break;
            }
            z=z.getNext();
        }
        if(z!=null){
            Del(z);
            return true;
        }
        return false;
    }
    private void Del(BSTree e){
        if(e.left==null && e.right==null){
            if(e.parent.left==e){
                e.parent.left=null;
                return ;
            }else{
                e.parent.right=null;
                return ;
            }
        }else{
            if(e.getNext()!=null){
                BSTree g= e.getNext();
                BSTree temp=new BSTree(g.address,g.size,g.key);
                temp.right=e.right;
                temp.left=e.left;
                temp.parent=e.parent;
                if(e.right==null){
                    e.left.parent=temp;
                }else if(e.left==null){
                    e.right.parent=temp;
                }else{
                    e.right.parent=temp;
                    e.left.parent=temp;
                }
                if(e.parent.right==e){
                    e.parent.right=temp;
                }else{
                    e.parent.left=temp;
                }
                Del(g);
            }else{
                e.parent.right=e.left;
                e.left.parent=e.parent;
                return ;
            }
        }

    }
        
    public BSTree Find(int key, boolean exact)//even though exact is false the best fit has too done****remainder
    {   
        if(this!=null){
            BSTree z=this.getRootsen().getFirst();
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

    public BSTree getFirst()
    {   BSTree z=this;
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

    public BSTree getNext()
    {   
        BSTree z=this;
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
    private BSTree getRootsen(){
        BSTree y =this;
        if(y!=null){
            while(y.parent!=null){
                y=y.parent;
            }
            return y;
        }
        return null;
    }

    public boolean sanity()
    {
        BSTree p= new BSTree();
        BSTree z=this.getRootsen().getFirst();
        while(z!=null){
            if(z.left!=null){
                if(z.left.key>z.key){
                    return false;
                }
            }
            if(z.right!=null){
                if(z.right.key<z.key){
                    return false;
                }
                if(z.right.key==z.key){
                    if(z.right.address<=z.address){
                        return false;
                    }
                }
            }
            if(p.Find(z.address,true)!=null){
                return false;
            }else{
                p.Insert(0,0,z.address);
            }
        }
        return true;
    }

}


 


