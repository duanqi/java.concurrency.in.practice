package lincoln.lab.java.concurrency.latch;

import java.util.concurrent.CountDownLatch;

/**
 * Latch ����������ʹ�÷�ʽ ����ά��һ��״̬���ڵ������״̬ǰ���߳��޷�ͨ����latch.await()��
 * 
 * CountDownLatch ���Ʊ������߳��޷�ͨ��countDownLatch.await()��ֱ��countDownLatch.countDown()
 * ��countDownLatch.getCount()==0
 * 
 * @author geng.lin
 * 
 */
public class TestHarness {

    public interface MyExecutor {
	void execute();
    }

    public long timeTasks(int threadsNo, final MyExecutor myExecutor)
	    throws InterruptedException {
	final CountDownLatch startLatch = new CountDownLatch(1);
	final CountDownLatch endLatch = new CountDownLatch(threadsNo);

	for (int i = 0; i < threadsNo; i++) {
	    Thread thread = new Thread() {
		public void run() {
		    try {
			try {
			    startLatch.await();
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
			myExecutor.execute();
		    } finally {
			endLatch.countDown();
		    }
		};

	    };
	    thread.start();
	}

	long start = System.currentTimeMillis();
	startLatch.countDown();
	endLatch.await();
	return System.currentTimeMillis() - start;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

	TestHarness harness = new TestHarness();
	try {
	    long timeTasks = harness.timeTasks(10, new MyExecutor() {

		public void execute() {

		    try {

			Thread.sleep(3);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    });
	    System.out.println("during:" + timeTasks);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
