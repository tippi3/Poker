public void trade () {
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