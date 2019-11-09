
public class test {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            public void run(){
                while (true){
                    System.out.println("t1");
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                while (true){
                    System.out.println("t2");
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();

    }
}
