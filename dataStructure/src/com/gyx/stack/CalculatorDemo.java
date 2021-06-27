package com.gyx.stack;

/**
 * 逆波兰表达式实现计算器
 */
public class CalculatorDemo {
    public static void main(String args[]) {
        CalculatorStack numStack = new CalculatorStack(20);//数栈
        CalculatorStack operStack = new CalculatorStack(10);//符号栈
        String expression = "1-20+30*2";//表达式
        for (int i = 0; i < expression.length(); i++) {
            int cur = expression.charAt(i);//当前数字或符号
            if (operStack.isOper(cur)) {//如果是操作符
                try {
                    //判断优先级
                    if (operStack.priority(cur) <= operStack.priority(operStack.peek())) {
                        //优先级小于等于栈顶元素，取出栈顶元素，再入栈，再取两个数出来计算
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int oper = operStack.pop();
                        operStack.push(cur);
                        int res = operStack.cal(num1, num2, oper);
                        numStack.push(res);//计算结果入数栈
                    } else {//优先级大于栈顶元素，直接入栈
                        operStack.push(cur);
                    }
                } catch (Exception e) {//有异常，说明栈是空的，直接入栈
                    operStack.push(cur);
                }
            } else {//如果不是操作符，即数字，就入栈
                int num = cur - '0';
                while (i < expression.length() - 1 && !operStack.isOper(expression.charAt(i + 1))) {//如果后面也是数字
                    num = num * 10 + expression.charAt(i + 1) - '0';
                    i++;
                }
                numStack.push(num);
            }
        }
        //整个表达式都已入栈，逐个取出计算
        while (!numStack.isEmpty() && !operStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int oper = operStack.pop();
            int res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        int res = numStack.pop();//取出计算结果
        System.out.println("计算结果为:" + res);
    }
}

//计算器
class CalculatorStack {
    //结点
    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    private int maxSize;//栈的大小
    private int length = 0;//有效数据的长度
    private Node head;//头结点

    //构造方法
    public CalculatorStack(int maxSize) {
        this.maxSize = maxSize;
        head = new Node(0);
    }

    //栈是否空
    public boolean isEmpty() {
        return (length == 0);
    }

    //栈是否满
    public boolean isFull() {
        return (length == maxSize);
    }

    //入栈
    public void push(int val) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈已满，不能再放入数据");
            return;
        }
        Node node = new Node(val);
        //如果栈是空的，要特殊处理
        if (isEmpty()) {
            head.next = node;
            length++;
            return;
        }
        Node temp = head.next;
        head.next = node;
        node.next = temp;
        length++;
    }

    //出栈
    public int pop() {
        //先判断是否栈满
        if (isEmpty()) {
            throw new RuntimeException("栈是空的，没有元素可以出栈");
        }
        //如果栈中只有一个元素，要特殊处理
        if (length == 1) {
            int val = head.next.val;
            head.next = null;
            length--;
            return val;
        }
        Node temp = head.next;
        int val = temp.val;
        head.next = temp.next;
        temp.next = null;
        temp = null;
        length--;
        return val;
    }

    //查看栈顶元素
    public int peek() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈是空的");
        }
        return head.next.val;
    }

    //显示栈的数据
    public void show() {
        //先判断是否栈满
        if (isEmpty()) {
            System.out.println("栈是空的");
            return;
        }
        System.out.println("栈的数据如下:");
        Node temp = head.next;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

    //判断是不是运算符
    public boolean isOper(int oper) {
        return (oper == '+' || oper == '-' || oper == '*' || oper == '/');
    }

    //判断运算符优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//计算结果
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;//后出栈的数是被减数
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;//和减法同理
                break;
            default:
                break;
        }
        return res;
    }
}