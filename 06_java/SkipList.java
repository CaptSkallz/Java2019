import java.util.*;

public class SkipList {
    private SkipNode        head;
    private SkipNode        tail;
    private int             maxHeight;
    private int             curHeight;
    
    public SkipList(int m) {
        curHeight   = 1;
        maxHeight   = m;
        head = new SkipNode(SkipNode.MinNodeValue,maxHeight);
        tail = new SkipNode(SkipNode.MaxNodeValue,maxHeight);
        for ( int x = 1; x <= maxHeight; x++ )
            head.Nodes[x] =  tail;
    } 
    public boolean insert(int k) {
        int        level = 0;
        int        h = 0;
        SkipNode[] update = new SkipNode[maxHeight+1];
        SkipNode   tmp = head;
        for ( h = curHeight; h >= 1; h-- ) {
            while ( tmp.Nodes[h].key < k ) 
                tmp    = tmp.Nodes[h];
            update[h] = tmp;
        }
        tmp    = tmp.Nodes[1];
        if ( tmp.key == k )
            return false;
        else {
            level = 1;
            while ((Math.random() < 0.5) && (level < maxHeight))
                level++;
            if ( level > curHeight ) {
                for ( int i = curHeight + 1; i <= level; i++ )
                    update[i] = head;
                curHeight = level;
            }
        }
        tmp = new SkipNode(k,level);
        for ( int i = 1; i <= level; i++ ) {
            tmp.Nodes[i] = update[i].Nodes[i];
            update[i].Nodes[i] = tmp;
        }
        return true;
    }
    public int find(int key)  {
        int h = 0;
        SkipNode   tmp = head;
        for ( h = curHeight; h >= 1; h-- ) {
            while ( tmp.Nodes[h].key < key )        
                tmp    = tmp.Nodes[h];
            if (tmp.Nodes[h].key == key)
            	return key;
        }
        return SkipNode.MinNodeValue - 1;
    }
    public void traverse() {
        SkipNode tmp;
        tmp = head;
        while ( tmp != tail ) {
            visit(tmp);
            tmp = tmp.Nodes[1];
        }
        visit(tail);
    }
        public void visit(SkipNode n) {
            int k;
            k = n.key;          
            if (k < 0)
                System.out.print("h");
            else
                System.out.print(n.key);
            for(int i=0;i<maxHeight;i++) {
                if (i > n.nodeHeight)
                    System.out.print(".");
                else
                    System.out.print("v");
            }
            System.out.println();
        }

    public static void main(String[] args) {
    	int sum = 0;
        SkipList s = new SkipList(7);
        for(int i=0; i<300; i++) {
        	sum+=(i*17+13)%313;
            s.insert((i*17+13)%313);
        }
        s.traverse();
        for(int i=0; i<313; i++)
            if (s.find(i) != -1) sum -= i;
        System.out.println(sum);
    }
}


