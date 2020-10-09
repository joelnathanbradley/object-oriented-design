package assignmentOne;

/**
 * This class represents a FIFO queue, using a circular doubly linked list data structure
 * Data found in this queue are the pointers to the head and tail nodes, as well as integers size and capacity
 */
public class Queue {

    private int size;
    private int capacity;
    private Node head;
    private Node tail;

    /**
     * Queue constructor sets size to zero, but the capacity to two empty nodes, one being the head node, the other the
     * tail node
     */
    public Queue() {
        this.size = 0;
        this.capacity = 2;
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.prev = tail;
        this.head.next = tail;
        this.tail.prev = head;
        this.tail.next = head;
    }

    /**
     * This adds data to the queue using FIFO.  It doubles the capacity of the queue if the queue is full.
     * @param data This is the ProcessObject being added to the queue
     */
    public void add(ProcessObject data) {
        // double capacity of queue if full
        if(this.size == this.capacity) {
            this.increaseCapacity();
        }
        // check head, then tail nodes first since queue starts with two empty nodes
        if(this.head.data == null) {
            this.head.data = data;
        } else if(this.tail.data == null) {
            this.tail.data = data;
        } else {
            // add data to empty node following tail, and point tail to it
            this.tail.next.data = data;
            this.tail = this.tail.next;
        }
        this.size += 1;
    }

    /**
     * This removes data from the queue using FIFO. The capacity is never decreased.
     * @return The ProcessObject being removed from the queue
     */
    public ProcessObject remove() {
        // only remove data if there is actually data to remove
        if(this.size == 0) {
            return null;
        }
        // pop off data in head node, and point head to next node
        ProcessObject data = this.head.data;
        this.head.data = null;
        this.head = this.head.next;
        this.size -= 1;
        // if head is pointing at same node as tail, move tail forward to empty node
        if(this.size == 1 || this.size == 0) {
            this.tail = this.tail.next;
        }
        return data;
    }

    /**
     * This returns the data that is up next to be removed from the queue, but doesn't actually remove it
     * @return The ProcessObject that is next to be removed using FIFO
     */
    public ProcessObject peek() {
        // only return data if there is data to return
        if(this.size == 0) {
            return null;
        }
        // don't remove data from queue, only return it
        return this.head.data;
    }

    /**
     * This prints the contents of the queue in ascending order of a chosen ProcessObject attribute, without changing
     * the order of the queue.
     * @param processAttributeOption Integer to pick which process object attribute to sort by in ascending order.
     *                               1 - Sort by Name
     *                               2 - Sort by Owner
     *                               3 - Sort by PID
     *                               4 - Sort by Number of Threads
     *                               5 - Sort by Percentage of CPU being used
     *                               6 - Sort by Total time used by CPU
     */
    public void print(int processAttributeOption) {
        // in order to keep order in queue, load a raw array with queue data
        ProcessObject[] processes = new ProcessObject[this.size];
        Node trackerNode = this.head;
        int index = 0;
        while(trackerNode.data != null) {
            processes[index] = trackerNode.data;
            trackerNode = trackerNode.next;
            index += 1;
        }
        // use merge sort to sort data in raw array by attributes found in the process object
        processes = this.mergeSort(processes, 0, this.size - 1, processAttributeOption);
        // once sorted, print each process object in raw array
        for(ProcessObject i : processes) {
            System.out.println(i.toString());
        }
    }

    /**
     * This doubles the capacity of the queue if it is full
     */
    private void increaseCapacity() {
        // double capacity of queue by adding empty nodes in between head and tail in a circular doubly linked list
        Node trackerNode = this.tail;
        while(this.size > 0) {
            Node newNode = new Node(null);
            newNode.prev = trackerNode;
            trackerNode.next = newNode;
            trackerNode = trackerNode.next;
            this.size -= 1;
        }
        trackerNode.next = this.head;
        this.head.prev = trackerNode;
        this.size = this.capacity;
        this.capacity = 2*(this.capacity);
    }

    /**
     * This sorts the contents of the queue in a raw array to prepare for printing using a recursive merge sort
     * @param processes The raw array of queue data to be sorted.  This is not sorting the actual queue
     * @param first The index pointing to the beginning of the first chunk of the array to be merged
     * @param last The index pointing to the end of the second chunk of the array to be merged
     * @param processAttributeOption The integer value representing which process object attribute to sort by
     * @return This returns the same raw array passed in the first parameter, but now sorted
     */
    private ProcessObject[] mergeSort(ProcessObject[] processes, int first, int last, int processAttributeOption) {
        // recursive merge sort
        if(first < last) {
            int middle = (first + last)/2;
            processes = this.mergeSort(processes, first, middle, processAttributeOption);
            processes = this.mergeSort(processes, middle+1, last, processAttributeOption);
            processes = this.merge(processes, first, middle, last, processAttributeOption);
        }
        return processes;
    }

    /**
     * This is the the function that does the actual merging once the raw array has been broken down recursively
     * @param processes The raw array of queue data to be sorted.  This is not sorting the actual queue
     * @param first The index pointing to the beginning of the first chunk of the array to be merged
     * @param middle The index pointing to the beginning of the second chunk of the array to be merged
     * @param last The index pointing to the end of the second chunk of the array to be merged
     * @param processAttributeOption The integer value representing which process object attribute to sort by
     * @return This returns the same raw array passed in the first parameter, but now sorted
     */
    private ProcessObject[] merge(ProcessObject[] processes, int first, int middle, int last, int processAttributeOption) {
        // merge two chunks of raw array together in ascending order by using a temporary raw array
        ProcessObject[] tempSpace = new ProcessObject[last-first+1];
        int tempSpaceIndex = 0;
        int firstHalfPointer = first;
        int secondHalfPointer = middle + 1;
        // merge the two chunks of raw array until one of the chunks is empty
        while((firstHalfPointer < (middle + 1)) && (secondHalfPointer <= last)) {
            if(attributeCompare(processes[firstHalfPointer], processes[secondHalfPointer], processAttributeOption)) {
                tempSpace[tempSpaceIndex] = processes[firstHalfPointer];
                firstHalfPointer += 1;
            } else {
                tempSpace[tempSpaceIndex] = processes[secondHalfPointer];
                secondHalfPointer += 1;
            }
            tempSpaceIndex += 1;
        }
        // if second chunk was empty, finish merging chunks by filling in the rest with the first chunk
        // otherwise, if first chunk was empty, finish merging chunks by filling in the rest with the second chunk
        if(firstHalfPointer != (middle + 1)) {
            while(firstHalfPointer < (middle + 1)) {
                tempSpace[tempSpaceIndex] = processes[firstHalfPointer];
                firstHalfPointer += 1;
                tempSpaceIndex += 1;
            }
        } else {
            while(secondHalfPointer <= last) {
                tempSpace[tempSpaceIndex] = processes[secondHalfPointer];
                secondHalfPointer += 1;
                tempSpaceIndex += 1;
            }
        }
        // replace actual raw array with now sorted data from the now sorted temporary raw array
        tempSpaceIndex = 0;
        while(tempSpaceIndex <= last) {
            processes[tempSpaceIndex] = tempSpace[tempSpaceIndex];
            tempSpaceIndex += 1;
        }
        return processes;
    }

    /**
     * This compares a chosen attribute in two process objects and returns true if the first comes before the second
     * @param first The first process object for comparison
     * @param second The second process object for comparison
     * @param processAttributeOption The integer value representing which process object attribute to sort by
     * @return True if the first process object attribute comes before the second process object attribute. False otherwise.
     */
    private boolean attributeCompare(ProcessObject first, ProcessObject second, int processAttributeOption) {
        // based off of which process object attribute will be sorted, return true first process object comes before
        // second process object, in ascending order.  Otherwise, return false
        switch(processAttributeOption) {
            case 1:
                if(first.getName().compareTo(second.getName()) < 0) {
                    return true;
                }
                break;
            case 2:
                if(first.getOwner().compareTo(second.getOwner()) < 0) {
                    return true;
                }
                break;
            case 3:
                if(first.getPid() < second.getPid()) {
                    return true;
                }
                break;
            case 4:
                if(first.getNumberOfThreads() < second.getNumberOfThreads()) {
                    return true;
                }
                break;
            case 5:
                if(first.getPercentageOfCPU() < second.getPercentageOfCPU()) {
                    return true;
                }
                break;
            case 6:
                if(first.getTotalCPUTimeUsed() < second.getTotalCPUTimeUsed()) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }
}