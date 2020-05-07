public void open () //手持ちを表示します
    {
        System.out.print("【 ");
        for (int i = 0; i < hands.size(); i++) {
            System.out.print(hands.get(i) + " ");
        }
        System.out.print("】");
    }