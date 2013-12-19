package lincoln.lab.java.concurrency.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * javac �Ὣfor��������Ϊʹ�õ�����Iterator����������
 * 
 * �ڵ��������У���������ļ��������޸ģ������next��hasNextʱ���׳�java.util.
 * ConcurrentModificationException
 * 
 * @author geng.lin
 * 
 */
public class ChangeCollectionWhenIterating {

    private static List<Integer> list = new ArrayList<Integer>();

    /**
     * @param args
     */
    public static void main(String[] args) {

	list.add(1);
	list.add(2);
	list.add(3);

	for (int tem : list) {
	    doInThread(new Executor() {
		public void execute() {
		    list.add(8);
		}
	    });
	    System.out.println(tem);
	}

    }

    private static void doInThread(final Executor executor) {
	(new Thread(new Runnable() {

	    public void run() {
		executor.execute();
	    }
	})).start();
    }

    interface Executor {
	void execute();
    }

}
