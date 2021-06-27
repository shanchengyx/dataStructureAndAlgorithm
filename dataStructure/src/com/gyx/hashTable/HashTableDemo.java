package com.gyx.hashTable;

import java.util.Scanner;

/**
 * 实现基本的hash表
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        //实现一个菜单
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println();
            System.out.println("add:添加员工");
            System.out.println("select:查找员工");
            System.out.println("remove:删除员工");
            System.out.println("list:显示哈希表信息");
            System.out.println("exit:退出程序");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入员工ID:");
                    System.out.println("请输入员工名字:");
                    hashTable.add(new Employee(scanner.nextInt(), scanner.next()));
                    break;
                case "remove":
                    System.out.println("请输入要删除的员工ID");
                    try {
                        hashTable.remove(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "select":
                    System.out.println("请输入要查找的员工ID:");
                    try {
                        System.out.println(hashTable.selectEmployeeByID(scanner.nextInt()).toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("输入的命令有误");
                    break;
            }
        }
    }
}

/**
 * 哈希表
 */
class HashTable {
    private int size;
    private EmployeeLinkedList[] employeeLinkedListArray;//链表数组

    //构造方法
    public HashTable(int size) {
        this.size = size;
        employeeLinkedListArray = new EmployeeLinkedList[this.size];
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
    }

    //添加员工
    public void add(Employee employee) {
        int hashCode = hashFunction(employee.getID());
        employeeLinkedListArray[hashCode].add(employee);
    }

    public void remove(int ID) {
        employeeLinkedListArray[hashFunction(ID)].remove(ID);
    }

    //查找员工
    public Employee selectEmployeeByID(int ID) {
        return employeeLinkedListArray[hashFunction(ID)].select(ID);
    }

    //遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i].list(i);
        }
    }

    //散列函数
    public int hashFunction(int ID) {
        return ID % size;
    }
}

/**
 * 存储员工信息的链表结点
 */
class Employee {
    private int ID;
    private String name;
    private Employee next;

    //构造方法
    public Employee(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    public Employee getNext() {
        return next;
    }

    public String toString() {
        return "[" + ID + " " + name + "]";
    }
}

/**
 * 员工链表
 */
class EmployeeLinkedList {
    private Employee head;

    //添加员工
    public void add(Employee node) {
        //如果链表是空的
        if (head == null) {
            head = node;
            return;
        }
        //如果链表不为空，则在链表末尾添加员工结点
        Employee temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(node);
    }

    //删除员工信息
    public void remove(int ID) {
        //如果为空
        if (head == null) {
            throw new RuntimeException("要删除的员工不存在");
        }
        //如果只有一个结点
        if (head.getNext() == null) {
            head = null;
            return;
        }
        Employee temp = head;
        Employee preTemp = null;
        while (temp != null && temp.getID() != ID) {
            preTemp = temp;
            temp = temp.getNext();
        }
        if (temp != null) {
            preTemp.setNext(temp.getNext());
            temp = null;
        } else {
            throw new RuntimeException("要删除的员工不存在");
        }
    }

    //查找员工
    public Employee select(int ID) {
        //如果为空
        if (head == null) {
            throw new RuntimeException("没有找到该员工");
        }
        Employee temp = head;
        while (temp != null && temp.getID() != ID) {
            temp = temp.getNext();
        }
        if (temp != null) {
            return temp;
        } else {
            throw new RuntimeException("没有找到该员工");
        }
    }

    //遍历链表，显示员工信息
    public void list(int i) {
        //如果链表为空
        if (head == null) {
            System.out.println(i + ":" + null);
            return;
        }
        //如果链表不为空
        Employee temp = head;
        System.out.print(i + ":");
        while (temp != null) {
            System.out.print(temp.toString() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }
}