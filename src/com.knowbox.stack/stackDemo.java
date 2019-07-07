
import java.util.Scanner;

public class stackDemo {
    public static void main(String[] args) {
        LinkedStack arrayStack = new LinkedStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈");
            System.out.println("pop:出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        HeroNode res = arrayStack.pop();
                        System.out.printf("出栈的数据是:%d\n", res.no);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
    }
}


class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //判断是否已满
        if (isFull()) {
            System.out.println("stack is full");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        int result = stack[top];
        top--;
        return result;
    }

    //遍历栈
    public void list() {
        //判空
        if (isEmpty()) {
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}

class LinkedStack {
    private int maxSize;//栈的大小
    private SingleLinkedListStruct linkedList;
    private HeroNode top = new HeroNode(-1, "", "");
    private int len;//栈元素

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
        linkedList = new SingleLinkedListStruct();
    }

    //栈满
    public boolean isFull() {
        return len == maxSize;
    }

    //栈空
    public boolean isEmpty() {
        return top.no == -1;
    }

    //入栈-push
    public void push(int value) {
        //判断是否已满
        if (isFull()) {
            System.out.println("stack is full");
            return;
        }
        HeroNode heroNode = new HeroNode(value, "", "");
        top.next = heroNode;
        top = heroNode;
        len++;
    }

    //出栈-pop
    public HeroNode pop() {
        if (isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        //找出最后一个元素
        HeroNode lastNode = linkedList.getLast();
        linkedList.delete(lastNode);
        top = linkedList.getLast();
        len--;
        return lastNode;
    }

    //遍历栈
    public void list() {
        //判空
        if (isEmpty()) {
            return;
        }
        SingleLinkedList.reversePrint(linkedList.getHead());
    }
}