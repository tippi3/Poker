import java.util.*;

class NewPoker {
    public static void main(String[] args) {
        PokerGame game = new PokerGame();
        while (!game.isOver()) {
            game.start();
        }
    }
}

class PokerGame {
    List<Card> playerHands;
    List<Card> dealerHands;
    Deck deck;
    boolean isOver;
    Scanner in;
    
    public PokerGame () {
        playerHands = new ArrayList<Card>();
        dealerHands = new ArrayList<Card>();
        deck = new Deck();
        deck.shuffle();
        in = new Scanner(System.in);
        isOver = false;
    }
    
    public void start() {
        initialDraw();
        playerTurn();
        dealerTurn();
        result();
        askContinueOrNot();
    }
    
    public boolean isOver() {
        return isOver;
    }
    
    private void initialDraw() {
        playerHands.clear();
        dealerHands.clear();
        
        playerHands.add(deck.draw()); //1枚目
        dealerHands.add(deck.draw());
        playerHands.add(deck.draw()); //2枚目
        dealerHands.add(deck.draw());
        playerHands.add(deck.draw()); //3枚目
        dealerHands.add(deck.draw());
        playerHands.add(deck.draw()); //4枚目
        dealerHands.add(deck.draw());
        playerHands.add(deck.draw()); //5枚目
        dealerHands.add(deck.draw());
    }
    
    private void playerTurn() { 
        int userInput = -1;
        System.out.println("あなたのカード:");
        printCards(playerHands);
        
        while ((userInput < 0) || (5 < userInput)) {
        System.out.println("何枚交換しますか...");
        userInput = in.nextInt();
        }
        
        int[] changeCard = new int[userInput];
        int num = 0;
        for (int i = 0; i < userInput; i++) {
            System.out.println((i + 1) + "枚目");
            num = in.nextInt();
            playerHands.remove(num - 1);  //ここで指定した場所の要素を消す
            playerHands.add(deck.draw()); //すぐに追加する
        }
    }
        
    private void dealerTurn() {
       int dealerScore = computeScore(dealerHands);
       
       if (dealerScore < 2) {
           dealerHands.clear();
           for (int i = 0; i < 5; i++) {
               dealerHands.add(deck.draw());
            }
        }
    }
    
    
    private int computeScore(List<Card> cards) {
        int score = 1;
        int[ ] numCount  = new int[13];
            for (int i = 0; i < 13; i++) {
            numCount[i] = 0;
        }
        
        for (Card c : cards) {
            numCount[c.getNum() - 1]++;
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
        
        if (four == 1) {
            score = 8;
        } else if (three == 1) {
            score = 4;
            if (two == 1) {
                score = 7;
            }
        } else if (two == 2) {
            score = 3;
        } else if (two == 1) {
            score = 2;
        }
        
        return score;
    }
    
    private void result() {
        int playerScore = computeScore(playerHands);
        int dealerScore = computeScore(dealerHands);
        System.out.println("あなたのカード");
        printCards(playerHands);
        System.out.print("あなた:");
        role(playerScore);
        System.out.print("CPU:");
        role(dealerScore);
        
        if (playerScore > dealerScore) {
            System.out.println("勝ちました");
        } else if (playerScore < dealerScore) {
            System.out.println("負けました");
        } else {
            System.out.println("引き分けです");
        }
    }
    
    private void role (int num) {
        num = num;
        
        if (num == 9) {
            System.out.println("ストレートフラッシュ");
        } else if (num == 8) {
            System.out.println("フォーカード");
        } else if (num == 7) {
            System.out.println("フルハウス");
        } else if (num == 6) {
            System.out.println("フラッシュ");
        } else if (num == 5) {
            System.out.println("ストレート");
        } else if (num == 4) {
            System.out.println("スリーカード");
        } else if (num == 3) {
            System.out.println("ツーペア");
        } else if (num == 2) {
            System.out.println("ワンペア");
        } else if (num == 1) {
            System.out.println("ノーペア");
        }
    }
            
    public void askContinueOrNot() {
        int userInput = 2;
        while (!(userInput == 0 || userInput == 1)) {
            System.out.printf("ゲームを続けますか？(1 = 続ける, 0 = 続けない): ");
            userInput = in.nextInt();
        }
    
        if (userInput == 0) {
            isOver = true;
            System.out.println("ゲームを終了します");
        }
    }
    
    private void printCards(List<Card> cards) {
    for (Card c : cards) {
      System.out.println(c);
    }
  }
}

class Deck {
  Card[] cards;
  int currIndex;

  public Deck() {
    cards = new Card[52];
    currIndex = 0;
    
    int index = 0;
    // 52枚のカードのデックを作る。
    for (int i = 1; i <= 13; i++) {
      for (int j = 0; j < 4; j++) {
        cards[index] = new Card(i, j);
        index++;
      }
    }
  }

  public void shuffle() {
    List<Card> list = Arrays.asList(cards);
    Collections.shuffle(list);
    list.toArray(cards);
  }

  public Card draw() {
    if (currIndex == 52) {
      return null; // 本当はここでExceptionを返すべきだけど今回はしません。
    }
    Card card = cards[currIndex];
    currIndex++;
    return card;
  }
}

class Card {
  int num;
  int suit; // 0 => spade, 1 => heart, 2 => club, 3 => diamond

  public Card(int num, int suit) {
    this.num = num;
    this.suit = suit;
  }

  public int getNum() {
    return this.num;
  }

  public String toString() {
    return getSuitString() + " " + getNum();
  }

  public String getSuitString() {
    if (suit == 0) {
      return "Spade";
    } else if (suit == 1) {
      return "Heart";
    } else if (suit == 2) {
      return "Club";
    } else if (suit == 3) {
      return "Diamond";
    } else {
      return "";
    }
  }     
}