import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 利用读写锁ReadWriteLock 实现的缓存系统
 * 存放容器是map，读前先上读锁，如无数据则释放读锁，上写锁并再次判断有无数据，再去查询数据，查询后释放写锁再上读锁
 */
public class CacheDemo {

	private Map<String, Object> cache = new HashMap<String, Object>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public  Object getData(String key){
		rwl.readLock().lock();
		Object value = null;
		try{
			value = cache.get(key);
			if(value == null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					if(value==null){
						value = "aaaa";//ʵ��ʧȥqueryDB();
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
