import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //要完成将一个中缀表达式转换成后缀表达式的算法
        //说明
        //1，1+（（2+3）*4）-5   => 1 2 3 + 4 * + 5 -
        //2，因为直接对str进行操作，不方便，因此先将表达式转成一个中缀的list，
        //即 "1+((2+3)*4)-5" =>ArrayList
        String expression = "1+((2+3)*4)-5";
        ArrayList<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        //中缀表达式转后缀表达式
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);
        //计算后缀表达式
        System.out.println(calculate(suffixExpressionList));
        //先定义逆波兰表达式 (3+4)*5-6
        // 4*5 -8 +60 +8/2 => 4 5 * 8 - 60 + 8 2 / +
        //String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        // 先将表达式放到arrayList中
        // 将arrayList 传递给一个方法，遍历ArrayList ，配合栈 完成计算
        List<String> list = getListString(suffixExpression);
        int res = calculate(list);
        System.out.println(res);
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明，因为s2这个栈再整个转换过程中没有pop操作，而且后面还要逆序输出，直接使用list替代s2
        //Stack<String> s2 = new Stack<>();//储存中间结果的栈
        List<String> s2 = new ArrayList<>();//储存中间结果的list2
        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                //如果是一个左括号，入符号栈
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出符号栈顶的运算符，并压入list2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（ 弹出符号栈，消除括号
            } else {
                //当item的优先级小于等于栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到（4.1）与s1中新的栈顶运算符比较
                //问题；缺少一个比较运算符优先级的方法
                while (s1.size() != 0 && !s1.peek().equals("(") && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符一次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放在list中，所以按顺序输出就是逆波兰表达式
    }

    //将表达式转换成一个arrayList
    public static ArrayList<String> toInfixExpressionList(String s) {
        //定义一个list，存放中缀表达式对应的内容
        ArrayList<String> ls = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str; //对多位数的拼接
        char c;//每遍历一个字符，就放入c中
        do {
            c = s.charAt(i);
            //如果是一个非数字，我们就需要加入到ls中，
            if (c < 48 || c > 57) {
                ls.add("" + c);
                i++;//i后移
            } else {// 如果是一个数，需要考虑多位数的问题
                str = "";//先将str 置空
                while (i < s.length()) {
                    c = s.charAt(i);
                    if (c >= 48 && c <= 57) {
                        str += c;//拼接
                    } else {
                        break;
                    }
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式，一次将数据和运算符放入到arrayList 中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分隔
        String[] split = suffixExpression.split(" ");
        return Arrays.asList(split);
    }

    //完成对逆波兰表达式的运算
    // 1，从左至右扫描，将3和4压入堆栈
    // 2, 遇到+ 运算符，因此弹出4和3（4位栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈
    // 3，将5入栈
    // 4, 接下来是x运算符，因此弹出5 和7 ，计算出7 x5=35，将35入栈；
    // 5, 将6入栈
    // 6, 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取整
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("invalid operator");
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}


//编写一个类Operation，可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，放回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("invalid operator!!");
                break;
        }
        return result;
    }
}

/*
 *
 * 中缀表达式转后缀表达式
 * 1）初始化两个栈：运算符栈s1和储存中间结果的栈s2
 * 2）从左至右扫描中缀表达式
 * 3) 遇到操作数时，比较其与s1栈顶运算符的优先级；
 * 4）遇到运算符是，比较其与s1栈顶运算符的优先级：
 *   1，如果s1位空，或栈顶运算符为左括号"（"，则直接将此运算符入栈
 *   2，否则，若优先级比栈顶运算符的高，也将运算符压入s1；
 *   3，否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1,与s1中新的栈顶运算符相比较；
 * 5）遇到括号是：
 *   1，如果是左括号"（"，则直接入栈
 *   2，如果是右括号"）"，则一次弹出s1栈顶的运算符，并压入s2，知道遇到左括号为止，此时将这一对括号丢弃
 * 6）重复步骤2至5，知道表达式的最右边
 * 7）将s1中剩余的运算符一次弹出并压入s2
 * 8）一次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 *
 * */