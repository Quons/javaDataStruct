import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        hashTable hashTable = new hashTable(7);

        //写一个简单的菜单
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit：退出系统");
            System.out.println("find：查找雇员");
            System.out.println("del：删除雇员");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入ID");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入要查找的Id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的Id");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                default:
                    System.out.println("错误指令");
            }
        }
    }
}


//创建hashtable，管理多条链表
class hashTable {
    private EmpLinkList[] empLinkedListArray;
    private int size;

    public hashTable(int size) {
        this.size = size;
        this.empLinkedListArray = new EmpLinkList[size];
        //初始化每一个链表
        for (int i = 0; i < this.empLinkedListArray.length; i++) {
            this.empLinkedListArray[i] = new EmpLinkList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表,哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //编写一个散列函数，使用一个简单的取模法
    public int hashFun(int id) {
        return id % size;
    }

    //根据输入的id查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到该雇员id=%d,name:%s\n", empLinkedListNo + 1, id, emp.name);
        } else {
            System.out.println("没有找到该雇员");
        }
    }

    //根据输入的id查找雇员
    public void delete(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        empLinkedListArray[empLinkedListNo].delete(id);
    }
}

//雇员结构
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList ，表示链表
class EmpLinkList {
    //头指针，执行的一个Emp，因此我们这个链表的head是直接指向第一个Emp；
    private Emp head;//默认null

    //添加雇员到链表
    //说明
    //1，假定当添加雇员时，id是自增长，即id的分配总是从小到大
    //因此我们将该雇员直接加入到本链表的最后一个即可
    public void add(Emp emp) {
        //如果添加的第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员需要使用一个辅助指针，帮助定位到最后一个
        Emp curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        //退出时直接将emp加入链表
        curr.next = emp;
    }

    //遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + no + " 条当前链表为空");
            return;
        }
        System.out.println("第 " + no + " 条当前链表的信息为：");
        Emp curr = head;
        while (curr != null) {
            System.out.print("id:" + curr.id + " name:" + curr.name + ";  ");
            curr = curr.next;
        }
    }

    //根据id查找雇员
    //如果找到就返回Emp，如果没有找打，就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curr = head;
        while (curr != null) {
            if (curr.id == id) {
                break;//这时curr就是指向要查找的雇员
            }
            curr = curr.next;
        }
        return curr;
    }

    //删除雇员
    public void delete(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        if (head.id == id) {
            head = head.next;
            return;
        }

        Emp curr = head;
        while (curr.next != null) {
            if (curr.next.id == id) {
                //找打了
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }
}