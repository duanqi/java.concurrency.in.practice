package lincoln.lab.java.concurrency.thisescape;

/**
 * ThisEscape����δ�������ʱ�����캯���������Ķ����Ѿ����Ի�ȡ��ʹ��this��
 * 
 * ��������һ���̣߳�����߳̾Ϳ���ͨ��thisʹ�øö�����ʱ�����û������ɣ����������ݳ���
 * 
 * @author geng.lin
 * 
 */
public class ThisEscape {

    private String status = "I";

    public ThisEscape() {
	(new Runnable() {

	    public void run() {
		System.out.println(this.toString());
	    }
	}).run();
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String toString() {
	return status;
    }

    public static void main(String[] args) {
	ThisEscape escape = new ThisEscape();
	System.out.println(escape);
    }
}
