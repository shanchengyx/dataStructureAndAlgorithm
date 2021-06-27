package com.gyx.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码
 */
public class HuffmanCodeDemo {
    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();//哈夫曼树

        //压缩字符串
//        String content = "i like like like java do you like a java";//内容
//        byte[] contentBytes = content.getBytes();//内容数组
//        byte[] codedBytes = huffmanTree.zip(contentBytes);//得到编码后的字节数组
//        System.out.println();
//        byte[] result = huffmanTree.decode(codedBytes);//解压出来的结果
//        System.out.println(new String(result));

        //压缩文件
//        String srcFile = "d:\\风铃.jpg";
//        String dstFile = "d:\\风铃.zip";
//        huffmanTree.zipFile(srcFile, dstFile);

        //解压文件
        String srcFile = "d:\\风铃.zip";
        String dstFile = "d:\\风铃2.jpg";
        huffmanTree.unzipFile(srcFile, dstFile);
    }
}

//结点
class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    //构造器
    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    //比较方法
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node[" + "data=" + data + " weight=" + weight + "]";
    }
}

//哈夫曼树
class HuffmanTree {
    Node root;//根结点
    Map<Byte, String> codeList = new HashMap<>();

    /**
     * 获取结点数组
     *
     * @param contentBytes 内容数组
     * @return 结点数组
     */
    public List<Node> getNodeList(byte[] contentBytes) {
        List<Node> result = new ArrayList<>();
        Map<Byte, Integer> countMap = new HashMap<>();//用来记录每个字母出现的次数
        //利用HashMap统计各字母的次数
        for (int i = 0; i < contentBytes.length; i++) {
            byte b = contentBytes[i];//获取当前字母
            Integer count = countMap.get(b);//取出该字母的次数
            if (count == null) {//如果次数为0
                countMap.put(b, 1);
            } else {//如果不为零，就次数加1
                countMap.put(b, count + 1);
            }
        }
        //把统计好次数的字母依次放入数组
        for (Map.Entry<Byte, Integer> entry : countMap.entrySet()) {
            result.add(new Node(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    /**
     * 创建哈夫曼树
     *
     * @param contentBytes 字节数组
     * @return
     */
    public void createHuffmanTree(byte[] contentBytes) {
        List<Node> list = getNodeList(contentBytes);
        Collections.sort(list);//先排序
        //循环到只剩一个结点，即根结点
        while (list.size() > 1) {
            //先取出两个最小的结点
            Node left = list.get(0);
            Node right = list.get(1);
            //构建的父结点
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            //移出已经构建完父结点的结点
            list.remove(left);
            list.remove(right);
            //把构建的父结点加入数组
            list.add(parent);
            Collections.sort(list);//排序
        }
        this.root = list.get(0);
    }

    /**
     * 前序遍历哈夫曼树
     *
     * @param node 结点
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.toString() + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 重载前序遍历
     */
    public void preOrder() {
        if (root == null) {
            System.out.println("树是空的");
            return;
        }
        preOrder(root);
    }

    /**
     * 获取哈夫曼编码表
     *
     * @param node          当前结点
     * @param code          追加的编码，即左边为0，右边为1
     * @param stringBuilder 已经拼接的编码
     */
    public void getCodeList(Node node, String code, StringBuilder stringBuilder) {
        //StringBuilder是引用类型，想要不修改它，就需要创建一个新的对象，而不能用传过来的参数
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);//拼接编码
        if (node == null) {
            //如果node为空就什么都不用做
        } else {
            if (node.data == null) {//不是有数据的结点
                getCodeList(node.left, "0", stringBuilder2);//向左递归
                getCodeList(node.right, "1", stringBuilder2);//向右递归
            } else {
                codeList.put(node.data, stringBuilder2.toString());
                //因为有数据的结点都是叶子结点，所以不用往下递归了
            }
        }
    }

    /**
     * 重载获取哈夫曼编码表
     *
     * @return 哈夫曼编码表
     */
    public Map<Byte, String> getCodeList() {
        if (root == null) {
            System.out.println("树是空的");
        }
        StringBuilder stringBuilder = new StringBuilder();
        getCodeList(root, "", stringBuilder);
        return codeList;
    }

    /**
     * 用哈夫曼编码表压缩字节数组
     *
     * @param contentBytes 字节数组
     * @return 压缩后的字节数组
     */
    public byte[] zip(byte[] contentBytes) {
        createHuffmanTree(contentBytes);
        getCodeList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < contentBytes.length; i++) {
            stringBuilder.append(codeList.get(contentBytes[i]));
        }
        byte[] codedBytes;
        if (stringBuilder.length() % 8 == 0) {
            codedBytes = new byte[stringBuilder.length() / 8];
        } else {
            codedBytes = new byte[stringBuilder.length() / 8 + 1];
        }
        for (int i = 0, index = 0; i < stringBuilder.length(); i = i + 8, index++) {
            String str;
            if (i + 8 < stringBuilder.length()) {
                str = stringBuilder.substring(i, i + 8);
            } else {
                str = stringBuilder.substring(i, stringBuilder.length());
            }
            codedBytes[index] = (byte) Integer.parseInt(str, 2);
        }
        return codedBytes;
    }

    /**
     * 将字节数转换成二进制字符串
     *
     * @param isNeedHighBit 是否需要补高位，这是因为，之前压缩的时候，
     *                      最后的几个字符可能不满8位，是转换的时候补上去的，现在要去掉
     * @param b             字节数
     * @return 二进制字符串
     */
    public String byteToString(boolean isNeedHighBit, byte b) {
        int temp = (int) b;//先把字节数变成int
        // 与256按位或，这是因为，toBinaryString如果是正数，只会后面的位数，
        //如，toBinaryString(1)的结果是1，与256按位或，就能保证得到的字符串可以取到后面8位
        if (isNeedHighBit) {
            temp = temp | 256;
        }
        String str = Integer.toBinaryString(temp);//得到二进制字符串
        if (isNeedHighBit) {
            return str.substring(str.length() - 8);//取后面8位
        } else {
            return str;
        }
    }

    /**
     * 解压
     *
     * @param codedBytes 压缩后的字节数组
     * @return 解压后的字节数组
     */
    public byte[] decode(byte[] codedBytes) {
        StringBuilder codedString = new StringBuilder();//压缩后的二进制字符
        //先想办法得到字节数组对应的二进制字符串，有了字符串以后就可以根据哈夫曼编码表还原出原字符串
        for (int i = 0; i < codedBytes.length; i++) {
            if (i < codedBytes.length - 1) {//如果还没到最后一个数，需要补高位
                codedString.append(byteToString(true, codedBytes[i]));
            } else {//如果到了最后一个数，不需要补高位
                codedString.append(byteToString(false, codedBytes[i]));
            }
        }
        Map<String, Byte> decodeList = new HashMap<>();//解码表
        //由编码表得到解码表，也就是把编码表的key，value反过来
        for (Map.Entry<Byte, String> entry : codeList.entrySet()) {
            decodeList.put(entry.getValue(), entry.getKey());
        }
        //还原出字节数组
        List<Byte> resultList = new ArrayList<>();
        for (int i = 0; i < codedString.length(); ) {
            int subLen = 1;//记录截取的字符串长度
            Byte b = null;//还原出的字节数
            while (b == null) {//为空就继续循环
                String subStr = codedString.substring(i, i + subLen);//截取字符串
                b = decodeList.get(subStr);//从解码表中取出字节数
                if (b == null) {//如果为空，说明没匹配到，截取更长的字符串
                    subLen++;
                }
            }
            resultList.add(b);//将匹配到的字节数加入到结果数组
            i = i + subLen;//更新截取字符串的起点
        }
        byte[] reuslt = new byte[resultList.size()];//用来存储还原出的字节数
        //将resultList中的Byte放到resultByte中去
        for (int i = 0; i < resultList.size(); i++) {
            reuslt[i] = resultList.get(i);
        }
        return reuslt;
    }

    /**
     * 压缩文件
     *
     * @param srcFile 源文件
     * @param dstFile 压缩后的文件
     */
    public void zipFile(String srcFile, String dstFile) {
        FileInputStream input = null;
        OutputStream output = null;
        ObjectOutputStream ooutput = null;//对象输出流
        try {
            //读取文件
            input = new FileInputStream(srcFile);
            byte[] b = new byte[input.available()];
            input.read(b);
            //压缩
            createHuffmanTree(b);
            getCodeList();
            byte[] codedBytes = zip(b);
            //输出
            output = new FileOutputStream(dstFile);
            ooutput = new ObjectOutputStream(output);
            ooutput.writeObject(codedBytes);
            //以对象流的方式写入编码表，为了以后恢复文件时使用
            ooutput.writeObject(codeList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                input.close();
                output.close();
                ooutput.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    /**
     * 解压文件
     *
     * @param zipFile 要解压的文件
     * @param dstFile 目标文件，即要解压到哪里
     */
    public void unzipFile(String zipFile, String dstFile) {
        FileInputStream input = null;
        ObjectInputStream oinput = null;//对象输入流
        FileOutputStream output = null;
        try {
            //输入
            input = new FileInputStream(zipFile);
            oinput = new ObjectInputStream(input);
            byte[] codedBytes = (byte[]) oinput.readObject();//因为读出来的是object，需要强转类型
            this.codeList = (Map<Byte, String>) oinput.readObject();
            //解压
            byte[] result = decode(codedBytes);
            //输出，将解压后的字符串写入目标文件
            output = new FileOutputStream(dstFile);
            output.write(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                input.close();
                oinput.close();
                output.close();
            } catch (Exception e2) {
                System.out.println(e2.getCause());
            }
        }
    }
}