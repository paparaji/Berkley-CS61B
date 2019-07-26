import org.junit.Test;

import static org.junit.Assert.*;

public class TestExperimental {

    @Test
    public void testOptimalIP(){
        assertEquals(0, ExperimentHelper.optimalIPL(1));
        assertEquals(6, ExperimentHelper.optimalIPL(5));
        assertEquals(13, ExperimentHelper.optimalIPL(8));
        assertEquals(10, ExperimentHelper.optimalIPL(7));
        assertEquals(4, ExperimentHelper.optimalIPL(4));
    }

    @Test
    public void testAverage(){
        assertEquals(0, ExperimentHelper.optimalAverageDepth(1), 0.001);
        assertEquals(1.2, ExperimentHelper.optimalAverageDepth(5), 0.001);
        assertEquals(1.625, ExperimentHelper.optimalAverageDepth(8), 0.001);
    }
}
