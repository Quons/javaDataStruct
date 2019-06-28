import java.util.Stack;

public class DoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedListStruct heroList = new DoubleLinkedListStruct();
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero2 = new DoubleHeroNode(2, "卢俊义", "玉麒麟");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴勇", "智多星");
        DoubleHeroNode hero5 = new DoubleHeroNode(5, "鲁智深1", "花和尚");
        heroList.add(hero1);
        heroList.add(hero3);
        heroList.add(hero5);
        heroList.addByOrder(hero2);
        heroList.delete(hero5);
        //hero2.name = "刘恒越";
        //heroList.update(hero2);
        heroList.list();
//        heroList.addByOrder(hero5);
//        DoubleHeroNode updateDoubleHeroNode = new DoubleHeroNode(6, "鲁智笙ss", "丈二的和尚");
//        heroList.update(updateDoubleHeroNode);
//        DoubleHeroNode deleteDoubleHeroNode = new DoubleHeroNode(1, "鲁智笙", "丈二的和尚");
//        heroList.delete(deleteDoubleHeroNode);
//        heroList.list();
//        System.out.println(getLength(heroList.getHead()));
//        System.out.println(getLastNode(heroList.getHead(), 1));
//        reverseList(heroList.getHead());
//        heroList.list();
//        System.out.println("reverse print");
//        reversePrint(heroList.getHead());
//        System.out.println("combine list....");

        DoubleLinkedListStruct heroListNew = new DoubleLinkedListStruct();
        DoubleHeroNode hero4 = new DoubleHeroNode(4, "林冲", "豹子头");
        DoubleHeroNode hero6 = new DoubleHeroNode(6, "鲁智深", "花和尚");
        heroListNew.add(hero2);
        heroListNew.add(hero4);
        heroListNew.add(hero6);
    }

    // 获取双向链表的节点的个数，如果是带头结点对的链表，需求不统计头结点
    public static int getLength(DoubleHeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        DoubleHeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找双向链表的倒数第k个结点
    //1,编写一个方法，接受head节点，同事接收一个index
    //2，index 表示是倒数第index个结点
    //3,先把链表从头到尾遍历，得到链表的总的长度getLength
    //4，得到size后，我们从链表的第一个开始遍历（size-index）个，就可以到
    //5，如果找到了，则返回该节点，否则返回null；
    public static DoubleHeroNode getLastNode(DoubleHeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //第一个遍历得到链表的长度
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        DoubleHeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }
}

//定义SingleLinkedList 管理我们的英雄
class DoubleLinkedListStruct {
    //初始化一个头节点，头节点不要动，不存放具体的数据
    private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getHead() {
        return head;
    }

    public void setHead(DoubleHeroNode head) {
        this.head = head;
    }

    //添加节点到双向链表，1,先找到最后的节点,2:将最后这个节点的next指向新的节点，并将添加的节点的pre节点指向最后一个节点
    public void add(DoubleHeroNode doubleHeroNode) {
        //因为head节点不能动因此需要一个辅助遍历temp
        DoubleHeroNode temp = head;
        //遍历链表，找到最后
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = doubleHeroNode;
        doubleHeroNode.pre = temp;
    }

    public void update(DoubleHeroNode DoubleHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        DoubleHeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == DoubleHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = DoubleHeroNode.name;
            temp.nickname = DoubleHeroNode.nickname;
        } else {
            System.out.println("没有查询到该节点");
        }
    }

    //删除节点，找到要删除的节点，直接删除
    public void delete(DoubleHeroNode heroNode) {
        DoubleHeroNode temp = head.next;
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
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有查询到该节点");
        }
    }

    //添加排名到指定位置
    public void addByOrder(DoubleHeroNode heroNode) {
        DoubleHeroNode temp = head;
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
            //将heroNode.next指向temp.next,并且，如果temp.next不为空的话，将temp.next.pre指向heroNode
            heroNode.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = heroNode;
            }
            //将temp.next指向heroNode，并将heroNode.pre指向temp
            temp.next = heroNode;
            heroNode.pre = temp;
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
        DoubleHeroNode temp = head.next;
        while (true) {
            System.out.println(temp.toString());
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }
}

//定义DoubleHeroNode，每个DoubleHeroNode 对象就是一个节点
class DoubleHeroNode {
    public int no;
    public String name;
    public String nickname;
    public DoubleHeroNode next;
    public DoubleHeroNode pre;

    //构造器
    public DoubleHeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}