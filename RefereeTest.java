public class RefereeTest {

    public static void main(String[] args) {

        int passed = 0;
        int failed = 0;

        // Order: rock, paper, scissors, lizard, spock
        String[] moves = {"rock", "paper", "scissors", "lizard", "spock"};

        // Expected results, written by hand: 0 = draw, 1 = player 1 wins, 2 = player 2 wins
        // Rows = player 1, columns = player 2
        int[][] expected = {
            /* rock     */ {0, 2, 1, 1, 2},
            /* paper    */ {1, 0, 2, 2, 1},
            /* scissors */ {2, 1, 0, 1, 2},
            /* lizard   */ {2, 1, 2, 0, 1},
            /* spock    */ {1, 2, 1, 2, 0}
        };

        System.out.println("================================");
        System.out.println("      REFEREE TESTING");
        System.out.println("================================");

        // Test all 25 combinations
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves.length; j++) {
                int actual = Referee.judge(moves[i], moves[j]);
                if (actual == expected[i][j]) {
                    passed++;
                } else {
                    failed++;
                    System.out.println("FAIL : " + moves[i] + " vs " + moves[j]
                            + " (expected " + expected[i][j] + ", got " + actual + ")");
                }
            }
        }
        System.out.println("25 move combinations : " + (failed == 0 ? "PASS" : "FAIL"));

        // Test invalid move is rejected
        try {
            Referee.judge("fire", "rock");
            failed++;
            System.out.println("Invalid move test    : FAIL");
        } catch (IllegalArgumentException e) {
            passed++;
            System.out.println("Invalid move test    : PASS");
        }

        // Test input is not case sensitive and spaces are ignored
        if (Referee.judge("  ROCK ", "Scissors") == 1) {
            passed++;
            System.out.println("Case-insensitive test: PASS");
        } else {
            failed++;
            System.out.println("Case-insensitive test: FAIL");
        }

        System.out.println("================================");
        System.out.println("Checks passed : " + passed);
        System.out.println("Checks failed : " + failed);
        System.out.println("================================");

        // A non-zero exit code makes the Jenkins stage fail
        if (failed > 0) {
            System.exit(1);
        }
    }
}
