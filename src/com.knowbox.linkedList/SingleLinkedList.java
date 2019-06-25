
public class SingleLinkedList {
    public static void main(String[] args) {
        SingleLinkedListStruct heroList = new SingleLinkedListStruct();
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴勇", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "鲁智深1", "花和尚");
        HeroNode hero6 = new HeroNode(6, "鲁智深", "花和尚");
        heroList.add(hero1);
        heroList.add(hero2);
        heroList.add(hero3);
        heroList.add(hero4);
        heroList.addByOrder(hero6);
        heroList.addByOrder(hero5);
        HeroNode updateHeroNode = new HeroNode(6, "鲁智笙", "丈二的和尚");
        heroList.update(updateHeroNode);
        heroList.list();
    }
}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedListStruct {
    //初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

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
        HeroNode temp = head.next;
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