import java.util.NoSuchElementException;
import java.util.ArrayList;
public class Heap{
    /* Parent at index p, child nodes at 2p+1(left) and 2p+2(right) in array
        P > 0, 0th node is considered max(or min) node.
     */
    private ArrayList<Integer> heap = new ArrayList<>();
    private Integer size = 0;

    public Heap(){
    }

    public void insert(Integer x){
        heap.add(x);
        siftup();
    }

    private void siftup(){
        int k = size - 1;
        while(k > 0){
            int p = (k-1)/2;

            int item = heap.get(k);
            int parent = heap.get(p);

            if(item > parent){
                // Swap elements
                heap.set(k, parent);
                heap.set(p, item);

                k = p;
            }
            else break;
        }
    }

    private void siftdown(){
        int k = 0;
        int l = 2*k + 1;
        while(l < size){
            int max = l;
            int r = l + 1;
            if (r < size){
                if (heap.get(r) > heap.get(l)) max++;

            }
            if(heap.get(k) < heap.get(max)){
                int kth_elem = heap.get(k);
                heap.set(k, heap.get(max));
                heap.set(max, kth_elem);

                k = max;
                l = 2*k + 1;
            }
            else break;
        }
    }

    public Integer delete() throws NoSuchElementException{
        if (size == 0) throw new NoSuchElementException();
        if(size == 1) return heap.remove(0);

        Integer i = heap.get(0);
        heap.set(0, heap.remove(size - 1));
        siftdown();
        return i;
    }


}


