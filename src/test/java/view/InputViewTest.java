package view;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class InputViewTest {
    @Test
    public void userCount() {
        Scanner scanner = new Scanner("3");
        assertEquals(3, InputView.getUserCount(scanner));
    }
}
