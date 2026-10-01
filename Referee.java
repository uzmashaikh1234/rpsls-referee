public class Referee {

    public static final String[] MOVES = {"rock", "paper", "scissors", "lizard", "spock"};

    // {winner, loser, verb}
    public static final String[][] RULES = {
        {"scissors", "paper",    "cuts"},
        {"paper",    "rock",     "covers"},
        {"rock",     "lizard",   "crushes"},
        {"lizard",   "spock",    "poisons"},
        {"spock",    "scissors", "smashes"},
        {"scissors", "lizard",   "decapitates"},
        {"lizard",   "paper",    "eats"},
        {"paper",    "spock",    "disproves"},
        {"spock", "rock", "vaporizes"},
        {"rock",     "scissors", "crushes"}
    };

    public static String normalize(String move) {
        if (move == null) {
            throw new IllegalArgumentException("Move is missing");
        }
        String m = move.trim().toLowerCase();
        for (String valid : MOVES) {
            if (valid.equals(m)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Invalid move: " + move);
    }

    // Returns the verb if a beats b, otherwise null
    public static String verb(String a, String b) {
        for (String[] r : RULES) {
            if (r[0].equals(a) && r[1].equals(b)) {
                return r[2];
            }
        }
        return null;
    }

    // 0 = draw, 1 = player 1 wins, 2 = player 2 wins
    public static int judge(String p1, String p2) {
        String a = normalize(p1);
        String b = normalize(p2);
        if (a.equals(b)) {
            return 0;
        }
        return verb(a, b) != null ? 1 : 2;
    }

    public static String cap(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static String describe(String p1, String p2) {
        String a = normalize(p1);
        String b = normalize(p2);
        int result = judge(a, b);
        if (result == 0) {
            return cap(a) + " vs " + cap(b) + " -> Draw, nobody wins";
        } else if (result == 1) {
            return cap(a) + " " + verb(a, b) + " " + cap(b) + " -> Player 1 wins!";
        }
        return cap(b) + " " + verb(b, a) + " " + cap(a) + " -> Player 2 wins!";
    }

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("  ROCK PAPER SCISSORS LIZARD SPOCK");
        System.out.println("           REFEREE");
        System.out.println("================================");

        if (args.length == 2) {
            System.out.println("Player 1: " + args[0]);
            System.out.println("Player 2: " + args[1]);
            System.out.println("--------------------------------");
            System.out.println(describe(args[0], args[1]));
        } else {
            System.out.println(describe("rock", "scissors"));
            System.out.println(describe("lizard", "spock"));
            System.out.println(describe("paper", "paper"));
            System.out.println(describe("spock", "rock"));
        }

        System.out.println("================================");
    }
}
