package state;

public class Ready extends State {
    private static final String EMPTY = "";

    public Ready() {
        super(false);
    }

    public State throwing(int throwing) {
        if (throwing == 10) {
            return new Strike();
        }
        return new FirstBowl(throwing);
    }

    @Override
    public String printState() {
        return EMPTY;
    }

    public static boolean isReady(State state) {
        return state instanceof Ready;
    }
}
