package domain;

import state.*;

import java.util.ArrayList;
import java.util.List;

public class LastFrame extends Frame {
    private static final int FIRST_STATE = 0;
    private static final int MAX = 3;

    private List<State> states = new ArrayList<>();
    private State state = new Ready();
    private int index;
    private Score score;

    public LastFrame(int no) {
        super(no);
    }

    @Override
    public State bowl(int falledPins) {
        state = updateState(falledPins);
        if (FirstBowl.isFirstBowl(state) || Strike.isStrike(state)) {
            states.add(state);
            return state;
        }
        try {
            states.set(index, state.clone());
            index++;
            if (!Open.isOpen(state)) {
                state = new Ready();
            }
        } catch (CloneNotSupportedException e) {
            e.getMessage();
        }
        return state;
    }

    @Override
    public State updateState(int throwing) {
        return state.throwing(throwing);
    }

    @Override
    public boolean isEnd() {
        if (Open.isOpen(state) || states.size() == MAX) {
            return true;
        }
        if (states.size() == 2 && Spare.isSpare(states.get(FIRST_STATE))) {
            return true;
        }
        return false;
    }

    @Override
    public String printState() {
        StringBuilder sb = new StringBuilder();
        for (State state : states) {
            sb.append(state.printState());
        }
        return sb.toString();
    }

    @Override
    public Score createScore(int beforeScore) {
        return state.getScore().calculateScore(beforeScore);
    }

    @Override
    public int getScore(int beforeScore) {
        Score score = createScore(beforeScore);
        if (score.canCalculateScore()) {
            return score.getScore();
        }
        return calculateAdditionalScore(score);
    }

    public int calculateAdditionalScore(Score beforeScore) {
        for (State state :states) {
            beforeScore = state.updateScore(beforeScore);
            if (beforeScore.canCalculateScore()) {
                return beforeScore.getScore();
            }
        }
        return 0;
    }

    @Override
    public Frame next() {
        throw new RuntimeException("마지막 프레임에서는 다음 프레임을 생성할 수 없습니다.");
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }
}
