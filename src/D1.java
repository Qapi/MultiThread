/**
 * Created by ewd on 2017/2/17.
 */
public class D1 {
	public static void main (String args[]){
		Object obj = new Object();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++){
					synchronized (obj){
						for (int j = 0; j < 10; j++) {
							System.out.println(i+":sub print:"+j);
						}
						try {
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		for (int i = 0; i < 50; i++) {
			synchronized (obj){
				for (int j = 0; j < 100; j++) {
					System.out.println(i+":farther print:"+j);
				}
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
