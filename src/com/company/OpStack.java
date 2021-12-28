//OpStack.java
package com.company;


public class OpStack {
    char[] stack = new char[100];
    int length = 0;

    //    判断是否为空
    public boolean empty() {
        if (length == 0) return true;
        return false;
    }

    //    取栈顶值（不出栈）
    public char peek() {
        if (length == 0) {
            return ' ';
        }
        return stack[length - 1];
    }

    //    进栈
    public char push(char op) {
        stack[length] = op;
        length++;
        return op;
    }

    //    出栈
    public char pop() {
        if (length == 0) {
            return ' ';
        }
        length--;
        char op = stack[length];
        return op;
    }

}
