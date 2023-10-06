package nemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapTests {

    @Test
    public void test01MapHasASubmarine() {
        Map map = new Map(new Nemo());
        assertNotNull(map.submarine);
    }




}
