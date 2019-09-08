import java.util.Scanner;

public class sort {
    public static void main(String[] args) {
        int len, temp;

        /*Scanner s = new Scanner(System.in);
        System.out.print("Enter no. of elements you want in array:");
        len = s.nextInt();

        int a[] = new int[len];
        System.out.println("Enter all the elements(intersect by space):");
        for (int i = 0; i < len; i++) {
            a[i] = s.nextInt();
        }*/
        int[] a={9,4,1,5,8,9,32,21,1,29};
        len=a.length;

        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

        System.out.print("Ascending Order:");
        for (int i = 0; i < len - 1; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.print(a[len - 1]);
    }
}