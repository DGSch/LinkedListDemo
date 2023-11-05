package prjlinkedlists.linkedlistdemo;

public class gameLeaderboard {
    private static final int MaxSize = 10;

    private class Player {
        String name;
        int score;
        Player next;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
            this.next = null;
        }
    }
    private Player head;

    public gameLeaderboard() {
        head = null;
    }
    public void insert(String name, int score) {
        Player newPlayer = new Player(name, score);
        if (head == null || head.score < score) {
            newPlayer.next = head;
            head = newPlayer;
        } else {
            Player current = head;
            while (current.next != null && current.next.score >= score) {
                current = current.next;
            }
            newPlayer.next = current.next;
            current.next = newPlayer;
        }

        Player temp = head;
        int count = 1;
        while (temp.next != null) {
            if (count == MaxSize) {
                temp.next = null;
                break;
            }
            count++;
            temp = temp.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Player current = head;
        while (current != null) {
            stringBuilder.append("Name: ").append(current.name)
                    .append(", Score: ").append(current.score).append("\n");
            current = current.next;
        }
        return stringBuilder.toString();
    }

}
