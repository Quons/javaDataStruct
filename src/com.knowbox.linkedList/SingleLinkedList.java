import javax.sound.midi.Soundbank;
import java.util.Stack;

public class SingleLinkedList {
    public static void main(String[] args) {
        SingleLinkedListStruct heroList = new SingleLinkedListStruct();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "吴勇", "智多星");
        HeroNode hero5 = new HeroNode(5, "鲁智深1", "花和尚");
        heroList.add(hero1);
        heroList.add(hero3);
        heroList.add(hero5);
//        heroList.addByOrder(hero5);
//        HeroNode updateHeroNode = new HeroNode(6, "鲁智笙ss", "丈二的和尚");
//        heroList.update(updateHeroNode);
//        HeroNode deleteHeroNode = new HeroNode(1, "鲁智笙", "丈二的和尚");
//        heroList.delete(deleteHeroNode);
//        heroList.list();
//        System.out.println(getLength(heroList.getHead()));
//        System.out.println(getLastNode(heroList.getHead(), 1));
//        reverseList(heroList.getHead());
//        heroList.list();
//        System.out.println("reverse print");
//        reversePrint(heroList.getHead());
//        System.out.println("combine list....");

        SingleLinkedListStruct heroListNew = new SingleLinkedListStruct();
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "鲁智深", "花和尚");
        heroListNew.add(hero2);
        heroListNew.add(hero4);
        heroListNew.add(hero6);
        SingleLinkedListStruct resultList = combineLists(heroList.getHead(), heroListNew.getHead());
        resultList.list();
    }

    // 获取单链表的节点的个数，如果是带头结点对的链表，需求不统计头结点
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表的倒数第k个结点
    //1,编写一个方法，接受head节点，同事接收一个index
    //2，index 表示是倒数第index个结点
    //3,先把链表从头到尾遍历，得到链表的总的长度getLength
    //4，得到size后，我们从链表的第一个开始遍历（size-index）个，就可以到
    //5，如果找到了，则返回该节点，否则返回null；
    public static HeroNode getLastNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一个遍历得到链表的长度
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //反转链表
    //1，创建一个反转头结点
    //2,遍历所有的节点，并添加到反转链表的最前端
    public static void reverseList(HeroNode head) {
        //判断如果链表为空，返回null
        if (head.next == null || head.next.next == null) {
            return;
        }
        //第一个遍历得到链表的长度
        HeroNode cur = head.next;
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表的最前端
        while (cur != null) {
            next = cur.next;//暂时保存当前节点的下一个节点，因为后面需要用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将当前节点连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    //逆序打印链表
    //利用栈先进后出的特点，逆序打印
    public static void reversePrint(HeroNode head) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点都压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

    //合并两个有序的链表
    public static SingleLinkedListStruct combineLists(HeroNode first, HeroNode second) {
        //创建合并之后的链表
        SingleLinkedListStruct result = new SingleLinkedListStruct();
        HeroNode resultHead = new HeroNode(0, "", "");
        //判断如果链表为空，返回null
        if (first.next == null) {
            result.setHead(second);
            return result;
        } else if (second.next == null) {
            result.setHead(first);
            return result;
        }
        //遍历两个链表
        HeroNode firstCur = first.next;
        HeroNode secondCur = second.next;
        //只是作为一个临时标记
        HeroNode resultCur = new HeroNode(0, "", "");
        resultHead.next = resultCur;
        HeroNode tmpNode;
        while (true) {
            if (firstCur == null) {
                resultCur.next = secondCur;
                break;
            }
            if (secondCur == null) {
                resultCur.next = firstCur;
                break;
            }
            if (firstCur.no > secondCur.no) {
                //如果first大于second ,就吧second的节点变成
                tmpNode = secondCur;
                secondCur = secondCur.next;
            } else {
                tmpNode = firstCur;
                firstCur = firstCur.next;
            }
            resultCur.next = tmpNode;
            resultCur = tmpNode;
        }
        result.setHead(resultHead.next);
        return result;
    }
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedListStruct {
    //初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //添加节点到单向链表，1,先找到最后的节点,2:将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动因此需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("没有查询到该节点");
        }
    }

    public void delete(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("没有查询到该节点");
        }
    }

    //获取最后一个元素
    public HeroNode getLast() {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                return temp;
            }
            temp = temp.next;
        }
    }

    //添加排名到指定位置
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("不能添加，已存在");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //遍历链表
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            System.out.println(temp.toString());
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }
}

//定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}