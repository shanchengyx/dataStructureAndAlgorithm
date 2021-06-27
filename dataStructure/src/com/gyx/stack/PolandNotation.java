package com.gyx.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class PolandNotation {
    public static void main(String args[]) {
//        String sufixxExpressionn = "4 5 * 8 - 60 + 8 2 / +";//逆波兰表达式
//        List<String> suffixList = getList(sufixxExpressionn);
        String infixExpression = "(1-4/2)*3+2*(6+3)";//中缀表达式
        List<String> suffixList = infixToSuffix(infixExpression);
        System.out.println(suffixList);
        int result = calculate(suffixList);
        System.out.println("结果为" + result);
    }

    //判断是不是运算符
    public static boolean isOper(String oper) {
        return (oper.equals("+") || oper.equals("-") || oper.equals("*") || oper.equals("/") || oper.equals("(") || oper.equals(")"));
    }

    //运算符的优先级
    public static int priority(String oper) {
        int result = 0;
        switch (oper) {
            case "(":
                result = 0;
                break;
            case ")":
                result = 0;
                break;
            case "+":
                result = 1;
                break;
            case "-":
                result = 1;
                break;
            case "*":
                result = 2;
                break;
            case "/":
                result = 2;
                break;
            default:
                throw new RuntimeException("输入的运算符不合法");
        }
        return result;
    }

    //中缀转后缀
    public static List<String> infixToSuffix(String infixExpression) {
        Stack<String> operStack = new Stack<>();//运算符栈
        Stack<String> numStack = new Stack<>();//数栈
        List<String> result = new ArrayList<>();
        for (int i = 0; i < infixExpression.length(); i++) {
            String cur = infixExpression.substring(i, i + 1);//当前字符
            //如果是运算符
            if (isOper(cur)) {
                if (operStack.isEmpty()) {//如果栈空
                    operStack.push(cur);//栈空直接入栈
                } else {//如果栈不空
                    if (cur.equals("(") || cur.equals(")")) {//如果是括号
                        if (cur.equals("(")) {//如果是左括号
                            operStack.push(cur);//左括号直接入栈
                        } else {//如果是右括号，取出元素压入数栈，直到遇见左括号
                            while (!operStack.peek().equals("(")) {
                                numStack.push(operStack.pop());
                            }
                            operStack.pop();
                        }
                    } else {//如果不是括号
                        //如果优先级高
                        if (priority(cur) > priority(operStack.peek())) {
                            operStack.push(cur);//优先级高直接入栈
                        } else {//如果优先级小于等于栈顶
                            numStack.push(operStack.pop());
                            while (!operStack.isEmpty() && priority(cur) <= priority(operStack.peek())) {
                                numStack.push(operStack.pop());
                            }
                            //直到优先级大于栈顶，入栈
                            operStack.push(cur);
                        }
                    }
                }
            } else {//如果不是运算符，即数字
                //数字可能是多位数，所以要一直扫描到不是数字为止
                while (i < infixExpression.length() - 1 && !isOper(infixExpression.substring(i + 1, i + 2))) {
                    cur = cur + infixExpression.substring(i + 1, i + 2);
                    i++;
                }
                numStack.push(cur);//数字直接入栈
            }
        }
        //将剩余元素取出
        while (!operStack.isEmpty()) {
            numStack.push(operStack.pop());
        }
        //将数栈中的元素放入符号栈中，实现逆序
        while (!numStack.isEmpty()) {
            operStack.push(numStack.pop());
        }
        while (!operStack.isEmpty()) {
            result.add(operStack.pop());
        }
        return result;
    }

    public static List<String> getList(String sufixxExpressionn) {
        String[] split = sufixxExpressionn.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        return list;
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("\\d+")) {
                stack.push(list.get(i));
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (list.get(i).equals("+")) {
                    result = num1 + num2;
                } else if (list.get(i).equals("-")) {
                    result = num1 - num2;
                } else if (list.get(i).equals("*")) {
                    result = num1 * num2;
                } else if (list.get(i).equals("/")) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("输入了非法运算符");
                }
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
