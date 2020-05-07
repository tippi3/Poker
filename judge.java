public int judge () {
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