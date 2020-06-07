package ds.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LeftRotation {
    public static void main(String[] args) throws IOException {
        final Solver solver = new Solver();
        StringBuilder stringBuilder = new StringBuilder();
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //   final int n=Integer.parseInt(br.readLine());
        final String[] str = br.readLine().split("\\s");
        final int d = Integer.parseInt(br.readLine());
        final int[] a = new int[str.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(str[i]);
        }
//         other ways to do same
//         1.
//         int[] arr =Arrays.stream(br.readLine().split("\\s")).mapToInt(s->Integer.parseInt(s)).toArray();
//            or
//         int[] arr =Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

//          2. Using StringTokenizer class
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for(int i=0;st.hasMoreTokens();i++){
//            a[i]=Integer.parseInt(st.nextToken());
//        }
        stringBuilder.append(Arrays.toString(solver.solve1(a, d))).append("\n");
        stringBuilder.append(Arrays.toString(solver.solve2(a, d))).append("\n");
        stringBuilder.append(Arrays.toString(solver.solve3(a, d))).append("\n");
        System.out.println(stringBuilder);
    }
}

class Solver {

    // reversal algo
    public int[] solve1(int[] a, int d) {
        final int n = a.length;
        d = d % n; // to handle d>= n
        if (d == n) {
            return a;
        }
        reverse(a, 0, d - 1);
        reverse(a, d, n - 1);
        reverse(a, 0, n - 1);
        return a;
    }

    // Utility Function to reverse an array from index si to index di
    public void reverse(int[] a, int si, int di) {
        int temp;
        while (si < di) {
            temp = a[si];
            a[si] = a[di];
            a[di] = temp;
            si++;
            di--;
        }
    }

    // juggling algo
    public int[] solve2(int[] a, int d) {
        final int n = a.length;
        final int g_c_d = gcd(n, d);
        //     System.out.println(" gcd = "+g_c_d);
        d = d % n; // to handle d>= n
        int i, j, k, temp;
        for (i = 0; i < g_c_d; i++) {
            temp = a[i];
            j = i;
            while (true) {
                k = j + d;
                if (k >= n) {
                    k -= n;
                }
                if (k == i) {
                    break;
                }
                a[j] = a[k];
                j = k;
            }
            a[j] = temp;
        }
        return a;
    }

    //         Utility Function to find gcd of a and b
//         a>=b
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // block swap algo
    public int[] solve3(int[] a, int d) {
        final int n = a.length;
        d = d % n; // to handle d>= n
        int i = d, j = n - d;
        while (i != j) {
            if (i < j) {
                swap(a, d - i, d + j - i, i);
                j -= i;
            } else {
                swap(a, d - i, d, j);
                i -= j;
            }
            //     System.out.println(Arrays.toString(a));
        }
        swap(a, d - i, d, i);
        return a;
    }

    // Utility function to swap
    // This function swaps d elements starting at index si with d elements
    // starting at index di

    private void swap(int[] a, int si, int di, final int d) {
        int i, temp;
        for (i = 0; i < d; i++) {
            temp = a[si];
            a[si] = a[di];
            a[di] = temp;
            si++;
            di++;
        }
    }
}