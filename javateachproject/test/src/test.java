public class test {
    public float discount(float x){
        float y = 0;
        if(x<=0) {
            System.out.println("re input");
        }else if(0<x && x<100){
            y = x;
        }else if(100<=x && x<1000 ){
            y = (float) (x*0.95);
        }else if(x>=1000){
            y = (float) (x*0.9);
        }
        return y;
    }
}
