import java.util.ArrayList;
public class Heap {
    private ArrayList<Post> heap;

    // Constructor to initialize the heap
    public Heap() {
        heap = new ArrayList<>();
    }

    // Add an element to the priority queue
    public void add(Post value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }


    public Post poll() {
        if (heap.isEmpty()) {
            return null;
        }
        Post root = heap.get(0); // Save the root to return it later
        Post last = heap.remove(heap.size() - 1); // Remove the last element
        if (!heap.isEmpty()) {
            heap.set(0, last); // Replace the root with the last element
            heapifyDown(0); // Restore the heap property
        }
        return root;
    }

    // Peek the highest-priority element without removing it
    public Post peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty");
        }
        return heap.get(0);
    }

    // Check if the priority queue is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }


    // Maintain the heap property after adding an element
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex))<=0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // Maintain the heap property after removing the root
    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int largest  = index;
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            if (leftChild < size && heap.get(leftChild).compareTo(heap.get(largest))>0) {
                largest  = leftChild;
            }
            if (rightChild < size && heap.get(rightChild).compareTo(heap.get(largest))>0){
                largest  = rightChild;
            }
            if (largest  == index) {
                break;
            }
            swap(index, largest );
            index = largest ;
        }
    }

    // swapping two elements of heap
    private void swap(int i, int j) {
        Post temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


}
