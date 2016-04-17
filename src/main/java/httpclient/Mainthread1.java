package httpclient;

public class Mainthread1 {
	int task_num = 100;//任务数
	int thread_num = 10;//线程数
	public void test(){
		for(int i = 0; i < thread_num; i++){
			int start = i * task_num/thread_num + 1881000;
			int end = (i+1) * task_num/thread_num + 1882000;
			TestThread1 testThread1 = new TestThread1(start, end);
			testThread1.run();
		}
	}
	public static void main(String[] args){
		Mainthread1 mainThread1 = new Mainthread1();
		mainThread1.test();
	}
}
