package lincoln.lab.java.concurrency.interrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * �����жϵķ�����
 * 
 * 1.���ж��쳣IterruptedException���������ֳ����ͷ���Դ�ȣ�Ȼ���׸����÷�����
 * 2.����������������߳��в���IterruptedException
 * �����Ҫ�жϣ���ͨ��Thread.currentThread().interrupt()�жϵ���
 * 
 * @author geng.lin
 * 
 */
public class DeliverInterruptedExceptionToCurrentThread implements Runnable {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

    /**
     * @param args
     */
    public static void main(String[] args) {
	Thread thread = new Thread(
		new DeliverInterruptedExceptionToCurrentThread());

	System.out.println("thread id in main:"
		+ Thread.currentThread().getId());
	thread.start();

	thread.interrupt();

	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    System.out.println("e when sleep:" + e);
	    e.printStackTrace();
	}

	System.out.println(Thread.currentThread().isInterrupted());
    }

    public void run() {
	System.out
		.println("thread id in run:" + Thread.currentThread().getId());
	try {
	    for (;;) {
		System.out.println(queue.take());
	    }
	} catch (InterruptedException e) {
	    System.out.println("e in run:" + e);
	    Thread.currentThread().interrupt();
	}
    }
}
