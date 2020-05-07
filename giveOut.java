class Card
{
    
    private static String[ ] deck = {
         "♠︎ 1","♠︎ 2","♠︎ 3","♠︎ 4","♠︎ 5","♠︎ 6","♠︎ 7","♠︎ 8","♠︎ 9","♠︎ 10","♠︎ J10","♠︎ Q10","♠︎ K10",
         "❤︎ 1","❤︎ 2","❤︎ 3","❤︎ 4","❤︎ 5","❤︎ 6","❤︎ 7","❤︎ 8","❤︎ 9","❤︎ 10","❤︎ J10","❤︎ Q10","❤︎K 10",
         "♣︎ 1","♣︎ 2","♣︎ 3","♣︎ 4","♣︎ 5","♣︎ 6","♣︎ 7","♣︎ 8","♣︎ 9","♣︎ 10","♣︎ J10","♣︎ Q10","♣︎ K10",
         "♦︎ 1","♦︎ 2","♦︎ 3","♦︎ 4","♦︎ 5","♦︎ 6","♦︎ 7","♦︎ 8","♦︎ 9","♦︎ 10","♦︎ J10","♦︎ Q10","♦︎ K10"
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