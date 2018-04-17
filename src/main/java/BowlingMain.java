import domain.Frame;
import domain.NormalFrame;
import domain.Player;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BowlingMain {
    public static void main(String[] args) {
        final int MAX_FRAME_NO = 10;

        Scanner scanner = new Scanner(System.in);
        Player player = new Player(InputView.getUserName(scanner));
        List<Frame> frames = new ArrayList<>();

        Frame frame = new NormalFrame(1);
        frames.add(frame);
        for (int index = 1; index < frames.size() + 1; index++) {
            while (!frame.isEnd()) {
                frame.throwing(InputView.getThrowing(scanner, index));
                ResultView.printFrames(frames);
            }
            if (index != MAX_FRAME_NO) {
                frame = frame.next();
                frames.add(frame);
            }
        }
    }
}