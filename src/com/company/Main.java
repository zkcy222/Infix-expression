package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("请输入表达式：");
        Scanner input = new Scanner(System.in);

        //读取中缀表达式
        String zhongZhui = input.next();
//        String zhongZhui = "++";
//        String zhongZhui = "5+6+9-7.77++";
//        zhongZhui = "2+3*(7-4)+8/4";
//        zhongZhui = "(2+3*(7-4)+8/4)+++(2+3*(7-4)+8/4)";
//        zhongZhui = "1+((2+3)*4)-5";


        //转为后缀表达式
        String houZhui = Proc.transfor(zhongZhui);
        //计算
        System.out.println("计算结果为：" + Proc.compute(houZhui));

        System.out.println("程序结束");
        return;
    }


}
