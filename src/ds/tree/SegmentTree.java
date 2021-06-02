package ds.tree;

/*
    author : saurabh singh dangi
    java program to show segment tree construction
    query and update
*/

public class SegmentTree {
    int[] st;  // array which stores segment tree nodes.

    /* constructor to construct segment tree from given array.
       it allocates memory for segment tree and calls constructSTUtil() to fill allocated memory.
     */
    public SegmentTree(int[] arr) {
        int n = arr.length;
        int bit = (int) Math.ceil(Math.log(n) / Math.log(2));  // height of the segment tree
        int size = 2 * (int) Math.pow(2, bit) - 1;             // max size of segment tree
        st = new int[size];
        constructSTUtil(arr, 0, n - 1, 0);
    }

    /* A recursive utility function which creates segment tree for arr [ss ... se]
       si is the current index of segment tree
     */
    private int constructSTUtil(int[] arr, int ss, int se, int si) {
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }
        int mid = getMid(ss, se);
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) + constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }

    // A utility function to get the middle index from corner indexes.
    private int getMid(int ss, int se) {
        return ss + (se - ss) / 2;
    }

    /* A function to get sum of elements from index qs to qe
     it uses getSumUtil() function to get sum
     */

    public int getSum(int n, int qs, int qe) {
        if (qs > qe || qs < 0 || qe > n - 1) {
            System.out.println("Invalid input");
            return -1;
        }
        return getSumUtil(0, n - 1, qs, qe, 0);
    }

    /*
       A Recursive function to update nodes in segment which have given index in there ranges.
       The parameters for this function are  ->
       si -> index of current node in segment tree .
       ss & se -> starting and ending index of segment represented by current node si .
       qs & qe  ->  starting and ending index of query range .
     */

    private int getSumUtil(int ss, int se, int qs, int qe, int si) {
        if (ss >= qs && se <= qe) {
            return st[si];
        }
        if (se < qs || qe < ss) {
            return 0;
        }
        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, si * 2 + 1) + getSumUtil(mid + 1, se, qs, qe, si * 2 + 2);
    }

    /*
       A function to update value in input array and segment tree.
       it uses updateUtil() function to update value in segment tree
     */

    public void updateValue(int[] arr, int n, int index, int new_val) {
        if (index < 0 || index > n - 1) {
            System.out.println("Invalid index");
            return;
        }
        int diff = new_val - arr[index];
        arr[index] = new_val;
        updateUtil(0, n - 1, index, diff, 0);
    }

    /*
       A Recursive function to update nodes in segment which have given index in there ranges.
       The parameters for this function are  ->
       ss , se and si is same as getSumUtil() method .
       index -> index to be updated in input array.
       diff -> value to be updated in all nodes which have index in there range .
     */

    private void updateUtil(int ss, int se, int index, int diff, int si) {
        if (index < ss || index > se) {
            return;
        }
        st[si] += diff;
        if (ss != se) {
            int mid = getMid(ss, se);
            updateUtil(ss, mid, index, diff, 2 * si + 1);
            updateUtil(mid + 1, se, index, diff, 2 * si + 2);
        }
    }
}

