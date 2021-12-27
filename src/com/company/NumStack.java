package com.company;


public class NumStack {
    double[] stack = new double[100];
    int length = 0;

    //    判断是否为空
    public boolean empty() {
        if (length == 0) return true;
        return false;
    }

    //    取栈顶值（不出栈）
    public double peek() {
        if (length == 0) {
            return ' ';
        }
        return stack[length - 1];
    }

    //    进栈
    public double push(double num) {
        stack[length] = num;
        length++;
        return num;
    }

    //    出栈
    public double pop() {
        if (length == 0) {
            return ' ';
        }
        length--;
        double num = stack[length];
        return num;
    }
}
