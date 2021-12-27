package com.company;

import java.util.Date;

public class Proc {

//    中缀表达式a + b*c + (d * e + f) * g，其转换成后缀表达式则为a b c * + d e * f  + g * +。
//    转换过程需要用到栈，具体过程如下：
//            1）如果遇到操作数，我们就直接将其输出。
//            2）如果遇到操作符，则我们将其放入到栈中，遇到左括号时我们也将其放入栈中。
//            3）如果遇到一个右括号，则将栈元素弹出，将弹出的操作符输出直到遇到左括号为止。注意，左括号只弹出并不输出。
//            4）如果遇到任何其他的操作符，如（“+”， “*”，“（”）等，
//                  从栈中弹出元素直到遇到发现更低优先级的元素(或者栈为空)为止。
//                  弹出完这些元素后，才将遇到的操作符压入到栈中。有一点需要注意，只有在遇到" ) "的情况下我们才弹出" ( "，其他情况我们都不会弹出" ( "。
//            5）如果我们读到了输入的末尾，则将栈中所有元素依次弹出。

    //    中缀表达式转化后缀表达式
    public static String transfor(String zhongzhui) {
        //清除头尾空格
        String item = "";
        String houzhui = "";
        OpStack opStack = new OpStack();
        while (!zhongzhui.equals("")) {
            zhongzhui = zhongzhui.trim();
            item = getFirstNumOrOperator(zhongzhui);
//            System.out.println(item);
            if (item.equals("error")) {
                System.out.println("出现错误，请检查表达式");
                return "error";
            }
            if (item.charAt(0) >= '0' && item.charAt(0) <= '9') {
                houzhui += item;
                houzhui += ' ';
            }
            if (item.equals("++") || item.equals("--")) {
                houzhui += item;
                houzhui += ' ';
            }
            if (item.equals("(") || item.equals(")") || item.equals("+")
                    || item.equals("-") || item.equals("*") || item.equals("/")) {
                //出栈
                if (item.equals("(")) {
                    opStack.push('(');
                }
                if (item.equals(")")) {
                    while (!opStack.empty() && opStack.peek() != '(') {
                        houzhui += opStack.pop();
                        houzhui += ' ';
                    }
                    if (opStack.peek() == '(') {
                        opStack.pop();
                    }
                }
                if (item.equals("+") || item.equals("-")) {
                    while (!opStack.empty() && opStack.peek() != '(') {
                        houzhui += opStack.pop();
                        houzhui += ' ';
                    }
                    opStack.push(item.charAt(0));
                }
                if (item.equals("*") || item.equals("/")) {
                    while (opStack.peek() != '$' && (opStack.peek() == '*' || opStack.peek() == '/')) {
                        houzhui += opStack.pop();
                        houzhui += ' ';
                    }
                    opStack.push(item.charAt(0));
                }
            }
            zhongzhui = zhongzhui.substring(item.length());
            zhongzhui = zhongzhui.trim();
        }
        while (!opStack.empty()) {
            houzhui += opStack.pop();
            houzhui += ' ';
        }
        System.out.println("后缀表达式:" + houzhui);
        return houzhui;
    }

    //计算后缀表达式
    public static double compute(String houzhui) {
        if (houzhui.equals("error")) {
            System.out.println("错误");
            return 0;
        }

        double output = 0;
        NumStack numStack = new NumStack();
        String item = "";
        while (!houzhui.equals("")) {
            houzhui = houzhui.trim();
            item=getFirstNumOrOperator(houzhui);

            if (item.equals("error")) {
                System.out.println("出现错误，请检查表达式");
                return -1;
            }
            if (item.charAt(0) >= '0' && item.charAt(0) <= '9') {
                numStack.push(new Double(item));
            }
            if (item.equals("++")) {
                numStack.push(numStack.pop() + 1);
            }
            if (item.equals("--")) {
                numStack.push(numStack.pop() - 1);
            }
            if (item.equals("+")) {
                numStack.push(numStack.pop() + numStack.pop());
            }
            if (item.equals("-")) {
                numStack.push(-numStack.pop() + numStack.pop());
            }
            if (item.equals("*")) {
                numStack.push(numStack.pop() * numStack.pop());
            }
            if (item.equals("/")) {
                numStack.push(1 / numStack.pop() * numStack.pop());
            }
            houzhui = houzhui.substring(item.length());
            houzhui = houzhui.trim();
        }
        return numStack.pop();
    }

    //取式子中下一个运算符或数字
    private static String getFirstNumOrOperator(String str) {
//        System.out.println("字符串：" + str);
        str = str.trim();
        if (str.equals("")) {
            return "error";
        }
        String item = "";
        int i = 0;
        char ch = str.charAt(i);
        if (ch >= '0' && ch <= '9') {
            for (i = 0; i < str.length(); i++) {
                ch = str.charAt(i);
                if ((ch >= '0' && ch <= '9') || ch == '.') {
                    item += ch;
                } else {
                    break;
                }
            }
            int nofdot = 0;
            for (int i1 = 0; i1 < item.length(); i1++) {
                if (item.charAt(i1) == '.') {
                    nofdot++;
                }
            }
            if (nofdot > 1) {
                return "error";
            }
            return item;
        }
        if (ch == '+' || ch == '-') {
            if (str.length() > 1 && str.charAt(1) == ch) {
                item += ch;
                item += ch;
                return item;
            }
        }
        if (ch == '(' || ch == ')'
                || ch == '*' || ch == '/'
                || ch == '+' || ch == '-') {
            item += ch;
            return item;
        }
        return item;
    }
}
