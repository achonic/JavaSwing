package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


class Award{
    public int yixiuCount, erjuCount, sanhongCount,sijinCount,duitangCount,zhuangyuanCount;
    public int playerCount,LiuBeiHongPlayerID;
    public boolean isGameOver = false,isLiuBeiHong = false;
    public int NowZhuangYuanLevel = 0,NowZhuangYuanPlayer;

    // 0 一秀 ， 1 二举， 2 四进 ，3 三红， 4 对堂
    // 5 状元， 6 状元（五子登科） ，7 六杯红 ，8 状元插金花
    public int[][] PlayerAwardCount = new int[7][9];
    public String[] AwardName={"一秀","二举","四进","三红","对堂","状元","状元（五子登科）","六杯红","状元插金花"};





    public void setAwardCount(int yixiuCount,int erjuCount,int sanhongCount,int sijinCount,int duitangCount,int zhuangyuanCount)
    {
        this.yixiuCount = yixiuCount;
        this.erjuCount = erjuCount;
        this.sanhongCount = sanhongCount;
        this.sijinCount = sijinCount;
        this.duitangCount = duitangCount;
        this.zhuangyuanCount = zhuangyuanCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    private void Throwtouzi(int playerID){
        int[] buk = new int[7];
        String result = "";
        for(int i = 1;i <= 6;i++) buk[i] = 0;
        for(int i = 1;i <= 6;i++)
        {
            int point = (int)(Math.random() * 6 + 1);
            buk[point]++;
            result += String.valueOf(point)+',';
        }

        //对堂
        if((buk[1] == 1)&&(buk[2] == 1)&&(buk[3] == 1)&&(buk[4] == 1)&&(buk[5] == 1)&&(buk[6] == 1)){
            if(duitangCount > 0) {
                PlayerAwardCount[playerID][4]++;
                duitangCount--;
                System.out.println("玩家" + playerID + ":" + result + "    对堂，恭喜");
            }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");
        }
        //六杯红
        else if(buk[4] == 6){

            System.out.println("玩家" + playerID + ":" + result + "    六杯红，恭喜");
            isLiuBeiHong = true;
            isGameOver = true;
            LiuBeiHongPlayerID = playerID;
        }
        //状元插金花
        else if(buk[4] == 4&&buk[1] == 2){
            if(8 > NowZhuangYuanLevel&&zhuangyuanCount == 0) {  //追饼成功
                PlayerAwardCount[NowZhuangYuanPlayer][NowZhuangYuanLevel]--;
                PlayerAwardCount[playerID][8]++;
                NowZhuangYuanLevel = 8;
                NowZhuangYuanPlayer = playerID;
                System.out.println("追饼成功！玩家" + playerID + ":" + result + "    状元插金花，恭喜");
            }
            else if(zhuangyuanCount > 0){
                System.out.println("玩家" + playerID + ":" + result + "    状元插金花，恭喜");
                PlayerAwardCount[playerID][8]++;
                zhuangyuanCount--;
                if(zhuangyuanCount == 0) {
                    NowZhuangYuanLevel =8;
                    NowZhuangYuanPlayer = playerID;
                }
            }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");
        }
        //状元  是最小的状元 没有追饼
        else if(buk[4] == 4){
            if(zhuangyuanCount > 0){  //状元还未分完
                System.out.println("玩家" + playerID + ":" + result + "    状元，恭喜");
                PlayerAwardCount[playerID][5]++;
                zhuangyuanCount--;
                if(zhuangyuanCount == 0) {
                    NowZhuangYuanLevel = 5;
                    NowZhuangYuanPlayer = playerID;
                }
            }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");


        }
        //状元（五子登科）
        else if(buk[3] == 5){
            if(6 > NowZhuangYuanLevel&&zhuangyuanCount == 0) {  //追饼成功
                PlayerAwardCount[NowZhuangYuanPlayer][NowZhuangYuanLevel]--;
                PlayerAwardCount[playerID][6]++;
                NowZhuangYuanLevel = 6;
                NowZhuangYuanPlayer = playerID;
                System.out.println("追饼成功！玩家" + playerID + ":" + result + "    状元（五子登科），恭喜");
            }
            else if(zhuangyuanCount > 0){   //状元还未分完
                System.out.println("玩家" + playerID + ":" + result + "    状元（五子登科），恭喜");
                PlayerAwardCount[playerID][6]++;
                zhuangyuanCount--;
                if(zhuangyuanCount == 0) {
                    NowZhuangYuanLevel = 6;
                    NowZhuangYuanPlayer = playerID;
                }
            }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");

        }
        //三红
        else if(buk[4] == 3) {
            if (sanhongCount > 0){
                PlayerAwardCount[playerID][3]++;
                sanhongCount--;
                System.out.println("玩家" + playerID + ":" + result + "    三红，恭喜");
             }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");
        }
        //四进
        else if(buk[3] == 4)
        {
            if(sijinCount > 0) {
                PlayerAwardCount[playerID][2]++;
                sijinCount--;
                System.out.println("玩家" + playerID + ":" + result + "    四进，恭喜");
            }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");
        }
        //二举
        else if(buk[4] == 2)
        {
            if(erjuCount > 0) {
                PlayerAwardCount[playerID][1]++;
                erjuCount--;
                System.out.println("玩家" + playerID + ":" + result + "    二举，恭喜");
            }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");
        }
        //一秀
        else if(buk[4] == 1)
        {
            if(yixiuCount > 0) {
                PlayerAwardCount[playerID][0]++;
                yixiuCount--;
                System.out.println("玩家" + playerID + ":" + result + "    一秀，恭喜");
            }
            else
                System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");
        }
        else
        {
            System.out.println("玩家" + playerID + ":" + result + "    无奖，继续努力");
        }

    }
    public void PrintAwardRecord(){
        //六杯红 得到六杯红的获得所有奖品
        System.out.println("得奖情况：");
        if(isLiuBeiHong){
            System.out.println("六杯红！"+"玩家"+LiuBeiHongPlayerID+":"+"夺得所有奖品！");
        }
        else{
            for(int i = 1;i <= playerCount;i++)
            {
                System.out.println("玩家"+i+"的得奖情况：");
                for(int j = 0;j <= 8;j++)
                {
                    System.out.println(AwardName[j]+"获得数："+PlayerAwardCount[i][j]);
                }
            }
        }
    }

    public void gamePlay() {
        int remainCount = yixiuCount + erjuCount + sanhongCount + sijinCount + duitangCount + zhuangyuanCount;
        while(remainCount > 0) {
            for (int i = 1; i <= playerCount; i++) {

                char in = 'n';
                System.out.print(i + "号玩家请掷骰子：");
                while (in != 'y') {
                    if(in == '\n')
                        System.out.print(i + "号玩家请掷骰子：");
                    try {
                        in = (char) System.in.read();

                    } catch (IOException e) {
                    }
                }
                try {
                    in = (char) System.in.read();
                    } catch (IOException e) {
                }


                Throwtouzi(i);
                remainCount = yixiuCount + erjuCount + sanhongCount + sijinCount + duitangCount + zhuangyuanCount;
                if(remainCount == 0 || isGameOver == true) break;
            }
            if(isGameOver == true) break;
        }

        PrintAwardRecord();
    }
}


public class Main {

    public static void main(String[] args) {
	// write your code here
        Award award = new Award();

        //设置奖品,玩家人数
        award.setAwardCount(32,16,8,4,0,1);
        award.setPlayerCount(6);

        //投掷甩子
        award.gamePlay();


    }
}
