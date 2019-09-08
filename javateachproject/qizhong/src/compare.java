import java.util.Scanner;

class compare {
    public static void main(String args[])
    {
        String s1, s2;
        Scanner in = new Scanner(System.in);

        //System.out.println("Enter the first string");
        //s1 = in.nextLine();
        s1 =("hello everybody");

        //System.out.println("Enter the second string");
        //s2 = in.nextLine();
        s2 =("hello world");

        if (s1.compareTo(s2) < 0)
            System.out.println("The first string is bigger: " + s1);
        else if (s1.compareTo(s2) > 0)
            System.out.println("The second string is bigger: " + s2);
        else
            System.out.println("Both the strings are equal.");
    }
}