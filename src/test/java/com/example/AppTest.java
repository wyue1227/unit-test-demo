package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private TestDataCSVReader test = new TestDataCSVReader(this.getClass());

    private App app = new App();

    private int cnt = 0;

    public Integer getCnt() {
        return cnt++;
    }

    @Before
    public void resetCnt() {
        cnt = 0;
    }

    @Test
    public void addTest() {

        int failedCount = 0;

        Map<Integer, ArrayList<String>> data = test.getData("add");
        ArrayList<String> noList = data.get(getCnt());
        ArrayList<String> param1List = data.get(getCnt());
        ArrayList<String> param2List = data.get(getCnt());
        ArrayList<String> expectedResultList = data.get(getCnt());
        ArrayList<String> commentList = data.get(getCnt());
        ArrayList<String> remarkList = data.get(getCnt());

        // csv头为第0行，略过0，直接从1开始
        for (int i = 1; i < noList.size(); i++) {
            try {
                String comment = commentList.get(i);
                Integer expectedResult = Integer.valueOf(expectedResultList.get(i));
                Integer param1 = Integer.valueOf(param1List.get(i));
                Integer param2 = Integer.valueOf(param2List.get(i));
                String remark = remarkList.get(i);

                assertEquals(comment, expectedResult, app.add(param1, param2));
                System.out.println(remark);
            } catch (Exception e) {
                e.printStackTrace();
                failedCount++;
            }
        }

        if (failedCount > 0) {
            fail("执行失败" + failedCount + "个");
        }
    }
}
