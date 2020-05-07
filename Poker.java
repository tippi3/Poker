import java.util.*;

class Poker
{
    public static void main(String[ ] args)
    {
        Scanner sc = new Scanner(System.in);
        
        //オブジェクトを作成
        Card player = new Card(); 
        Card cpu = new Card(); 
        int playerPoint = 0;
        int cpuPoint = 0;
        
        //カードを5枚ずつ交互に配る
        player.giveOut();
        cpu.giveOut();
        player.giveOut();
        cpu.giveOut();
        player.giveOut();
        cpu.giveOut();
        player.giveOut();
        cpu.giveOut();
        player.giveOut();
        cpu.giveOut();
        
        //手札の役を表示する
        player.open();
        playerPoint = player.judge();
        
        //手札を交換する
        player.trade();
        
        //手札を表示する
        System.out.print("あなた:");
        player.open();
        playerPoint = player.judge();
        System.out.print("CPU:");
        cpu.open();
        cpuPoint = cpu.judge();
        
        //結果を表示する
        System.out.println("ーーー結果ーーー");
        if (playerPoint == cpuPoint) {
            System.out.println("引き分け");
        } else if (playerPoint < cpuPoint) {
            System.out.println("CPUの勝利");
        } else {
            System.out.println("プレイヤーの勝利");
        }
    }
}
        


class Card
{
    Scanner sc = new Scanner(System.in);
    
    private static String[ ] deck= {
         "♠︎1","♠︎2","♠︎3","♠︎4","♠︎5","♠︎6","♠︎7","♠︎8","♠︎9","♠︎10","♠︎11","♠︎12","♠︎13",
         "❤︎ 1","❤︎ 2","❤︎ 3","❤︎ 4","❤︎ 5","❤︎ 6","❤︎ 7","❤︎ 8","❤︎ 9","❤︎ 10","❤︎ 11","❤︎ 12","❤︎ 13",
         "♣︎1","♣︎2","♣︎3","♣︎4","♣︎5","♣︎6","♣︎7","♣︎8","♣︎9","♣︎10","♣︎11","♣︎12","♣︎13",
         "♦︎1","♦︎2","♦︎3","♦︎4","♦︎5","♦︎6","♦︎7","♦︎8","♦︎9","♦︎10","♦︎11","♦︎12","♦︎13"
             };
             
    private ArrayList<String> hands = new ArrayList<String>();
    
    public void giveOut () //カードを一枚配ります。
    {
        int count = 0;
        while (true) {
            int n = (int) (Math.random() * 52);
            if (Integer.parseInt(deck[n].replaceAll("[^0-9]", "")) != 0) {
                hands.add(deck[n]);
                deck[n] = "0";
                return;
            }
        }
    }
    public void open () //手持ちを表示します
    {
        System.out.print("【 ");
        for (int i = 0; i < hands.size(); i++) {
            System.out.print(hands.get(i) + " ");
        }
        System.out.print("】");
    }
    public void trade () { //手持ちを指定の枚数交換します
        System.out.println("手札を何枚交換しますか？");
        int n = sc.nextInt();
        int[ ] changeNum = new int[n];
    
        for (int i = 0; i < n ; i++) {
            System.out.println((i + 1) + "枚目に交換するカード番号を入力してください...");
            changeNum[i] = sc.nextInt();
        }
        
        int count = 0;
        for (int i = 0; i < n ; i++) {
            count = 0;
            do {
                count = 0;
                int n1 = (int) (Math.random() * 52);
                if (Integer.parseInt(deck[n1].replaceAll("[^0-9]", "")) != 0) {
                    hands.set((changeNum[i] - 1), deck[n1]);
                    deck[n1] = "0";
                    count++;
                }
            } while(count == 0);
        }
    }
    public int judge () { //手札の役を表示して、得点を戻り値として返します
    int[ ] numCount  = new int[13];
    for (int i = 0; i < 13; i++) {
        numCount[i] = 0;
    }
    
    for (int i = 0; i < 5; i++) {
        int num = Integer.parseInt(hands.get(i).replaceAll("[^0-9]", ""));
        
        if (num == 1) {
            numCount[0]++;
        } else if (num == 2) {
            numCount[1]++;
        }else if (num == 3) {
            numCount[2]++;
        } else if (num == 4) {
            numCount[3]++;
        } else if (num == 5) {
            numCount[4]++;
        } else if (num == 6) {
            numCount[5]++;
        } else if (num == 7) {
            numCount[6]++;
        } else if (num == 8) {
            numCount[7]++;
        } else if (num == 9) {
            numCount[8]++;
        } else if (num == 10) {
            numCount[9]++;
        } else if (num == 11) {
            numCount[10]++;
        } else if (num == 12) {
            numCount[11]++;
        } else if (num == 13) {
            numCount[12]++;
        }
    }
        
        int four = 0;
        int three = 0;
        int two = 0;
        for (int i = 0; i < 13; i++) {
            if (numCount[i] == 4) {
                four++;
            } else if (numCount[i] == 3) {
                three++;
            } else if (numCount[i] == 2) {
                two++;
            }
        }
        
        int point = 0;
        if (four != 0) {
            System.out.println("フォーカード！！！！");
            point += 5;
        } else if ((three != 0) && (two != 0)) {
            System.out.println("フルハウス！！！");
            point += 4;
        } else if (three != 0) {
            System.out.println("スリーカード");
            point += 3;
        } else if (two == 2) {
            System.out.println("ツーペア");
            point += 2;
        } else if (two == 1) {
            System.out.println("ワンペア");
            point += 1;
        } else {
            System.out.println("ノーペア");
        }
        return point;
    }
}