package com.example.paymentreminders;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    List<List<Integer>> inputArray = new ArrayList<>();


    @Test
    public void addition_isCorrect() {

    }

    void addOnes(int start, int end, Object obj) {
        if (obj instanceof Integer) {

            for (int i = end; i < end + 4; i++) {
                inputArray.get(start).add(i, 1);
            }

        } else if (obj instanceof Double) {
            for (int i = end; i < end + 8; i++) {
                inputArray.get(start).add(i, 1);
            }
        } else if (obj instanceof Character) {
            for (int i = end; i < end + 1; i++) {
                inputArray.get(start).add(i, 1);
            }
        }
    }

    void showList() {
        int a = 0;
        addOnes(0, 0, a);
        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 20; j++) {
                System.out.print(inputArray.get(i).get(i));
            }
            System.out.println();
        }

    }


}