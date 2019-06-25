
import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args) {
        //创建队列
        ArrayQueueStruct aqs = new ArrayQueueStruct(4);
        char key;//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中数据");
            System.out.println("h(head):查看队列的头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    aqs.showQueue();
                    break;
                case 'a':
                    System.out.println("please enter a num");
                    int value = scanner.nextInt();
                    aqs.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = aqs.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = aqs.headQueue();
                        System.out.printf("队列的头数据是：%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("exit....");
    }
}

//使用数组模拟队列
class ArrayQueueStruct {
    private int maxSize;//最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr; //存储的数组

    //创建队列的构造器
    public ArrayQueueStruct(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;//指向队列头部,分析出front 是指指向队列头的前一个位置，
        rear = 0;//指向队列尾，指向队列尾的数据
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("queue is full");
            return;
        }
        //移动队列尾指针
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常处理
            throw new RuntimeException("queue is empty");
        }
        //移动队列头指针
        int data = arr[front];
        front = (front + 1) % maxSize;
        return data;
    }

    //显示队列的所有数据
    public void showQueue() {
        //遍历
        if (isEmpty()) {
            System.out.println("empty queue");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]:%s\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列的头数据，注意不是取数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }
        return arr[front];
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}