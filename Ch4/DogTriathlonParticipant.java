// Andres Succar
//p. 141

public class DogTriathlonParticipant {
    private final int NUM_EVENTS;
    private static int totalCumulativeScore;
    private String name;
    private int obedienceScore;
    private int conformationScore;
    private int agilityScore;
    private int total;
    private double avg;
    public DogTriathlonParticipant(String name, int numEvents, int score1, int score2, int score3)
        {
            this.name = name;
            NUM_EVENTS = numEvents;
            obedienceScore = score1;
            conformationScore = score2;
            agilityScore = score3;
            total = obedienceScore + conformationScore + agilityScore;
            avg = total / NUM_EVENTS;

        }

        public void display()
        {
        System.out.println(name + " participated in " + NUM_EVENTS);
        System.out.println ( "  " + name + "has an average score score of " + avg);
}

}
