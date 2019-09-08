
public class testTest {
    public static void main(String[] args) {
        test a = new test();

        float x = a.discount(2);
        if(x != 2){
            System.out.println("error");
        }

        x = a.discount(10000);
        if(x != 10000*0.9){
            System.out.println("error");
        }
    }


}
