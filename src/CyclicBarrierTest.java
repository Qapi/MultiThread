import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用场景：保证所有的线程同一时间开始执行相关的代码
 */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�1����ǰ����" + (cb.getNumberWaiting()+1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));						
						cb.await();
						
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�2����ǰ����" + (cb.getNumberWaiting()+1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();	
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�3����ǰ����" + (cb.getNumberWaiting() + 1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));						
						cb.await();						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
