import java.util.concurrent.*;


public class demo01 {

    public static void main(String[] args) {

        //创建
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
            System.out.println("最后运行!!");
        }) ;

        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("-----------------------------"+Thread.currentThread().getName());
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            
        }
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
