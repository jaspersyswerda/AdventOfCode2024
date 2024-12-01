package adventofcode2024;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DayOneTest{

    @Test
    public void test(){
        DayOne dayOne = new DayOne();
        List<String> list = EveryDay.readInputFile("DayOne.txt");
        assertFalse(list.isEmpty());
        dayOne.fillLists(list);
        assertEquals(1000, dayOne.rightList.size());
        assertEquals(1000, dayOne.leftList.size());
    }


}