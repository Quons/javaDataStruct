import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList csl = new CircleSingleLinkedList();
        csl.addBoy(125);
        csl.showBoy();
        csl.countBoy(10, 20, 125);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，单签没有编号
    private Boy first = null;

    //添加小孩节点，create a circle linked list
    public void addBoy(int num) {
        //check num
        if (num < 1) {
            System.out.println("invalid node num");
            return;
        }
        //create curBoy node to help build a list
        Boy curBoy = null;
        //create linked list by loop
        for (int i = 1; i <= num; i++) {
            //create boy node by NO
            Boy boy = new Boy(i);
            //handle first boy
            if (i == 1) {
                first = boy;
                first.setNext(first);//build a single node loop
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //iterate linked list
    public void showBoy() {
        //check empty
        if (first == null) {
            System.out.println("empty list");
            return;
        }
        //iterate list with a tmpBoy node
        Boy curBoy = first;
        while (true) {
            System.out.printf("boy no:%d\n", curBoy.getNo());
            curBoy = curBoy.getNext();
            if (curBoy == first) {
                break;
            }
        }
    }

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //校验数据
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("param err");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy starter = first;
        //事先指向链表指定的位置
        while (true) {
            if (starter.getNo() == startNo) {
                break;
            }
            starter = starter.getNext();
        }
        Boy helper = starter;
        //将helper移动到开始前的第一个
        while (true) {
            if (helper.getNext() == starter) {
                break;
            }
            helper = helper.getNext();
        }
        while (true) {
            if (helper == starter) {
                System.out.printf("No:%d\n", starter.getNo());
                break;
            }
            //移动countNum-1
            for (int i = 0; i < countNum - 1; i++) {
                helper = helper.getNext();
                starter = helper.getNext();
            }
            //出队列
            System.out.printf("No:%d\n", starter.getNo());
            helper.setNext(starter.getNext());
            starter = helper.getNext();
        }

    }
}

//创建一个boy类，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

