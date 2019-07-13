
public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-3";
        //创建两个栈，数栈，符号栈
        CalculatorStack numStack = new CalculatorStack(10);
        CalculatorStack operStack = new CalculatorStack(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1;
        int num2;
        int oper = 0;
        int res;
        String keepNum = "";
        char ch;//将每次扫描到得到的char 保存到ch
        //开始while循环的扫描expression
        while (true) {
            //一次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)) {
                //如果是运算符
                //判断当前的符号栈是否为空
                if (operStack.isEmpty()) {
                    //为空，直接入栈
                    operStack.push(ch);
                } else {
                    //如果符号栈中有符号，如果当前的操作符小于等于栈顶的操作符，就出栈两个，进行运算，计算完再入栈，再入栈当前符号
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把计算的结果如数栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //大于栈顶操作符，直接入栈
                        operStack.push(ch);
                    }
                }
            } else {
                keepNum += ch;
                //如果ch已经是最后一位，直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断后一个是不是操作符
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            //index + 1,并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        numStack.list();
        operStack.list();
        //当表达式扫描完毕，就顺序的从数栈和符号中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中就只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //把计算的结果如数栈
            numStack.push(res);
        }
        //将数栈中的最后一个数pop出来就是最后的结果
        System.out.printf("表达式%s=%d\n", expression, numStack.pop());
    }
}


class CalculatorStack {
    private int maxSize;//栈的大小
    private int[] stack;
    private int top = -1;

    public CalculatorStack(int maxSize) {
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

    //返回运算符的优先级,返回的数字越大，优先级越大
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有+，-，*，/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }

    //获取栈元素
    public int peek() {
        return stack[top];
    }
}

