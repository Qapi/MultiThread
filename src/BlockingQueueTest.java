import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 支持两个附加操作的 Queue
 * 这两个操作是：获取元素时等待队列变为非空，以及存储元素时等待空间变得可用。
 * 有1 抛异常  2 返回布尔值  3 阻塞等待  4 超时 等对应不同的存取方法
 */
public class BlockingQueueTest {
	public static void main(String[] args) {
		final BlockingQueue queue = new ArrayBlockingQueue(3);
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep((long)(Math.random()*1000));
							System.out.println(Thread.currentThread().getName() + "׼��������!");
							queue.put(1);
							System.out.println(Thread.currentThread().getName() + "�Ѿ��������ݣ�" +
										"����Ŀǰ��" + queue.size() + "������");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}

			}.start();
		}

		new Thread(){
			public void run(){
				while(true){
					try {
						//���˴���˯��ʱ��ֱ��Ϊ100��1000���۲����н��
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + "׼��ȡ����!");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "�Ѿ�ȡ�����ݣ�" +
								"����Ŀǰ��" + queue.size() + "������");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
	}
}
