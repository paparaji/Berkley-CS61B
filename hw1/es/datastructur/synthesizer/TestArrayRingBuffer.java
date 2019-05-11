package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(6);
        arb.enqueue(33.1);
        arb.enqueue(44.8);
        arb.enqueue(62.3);
        arb.enqueue(-3.4);
        arb.enqueue(77.0);
        arb.enqueue(90.4);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer(6);
        arb2.enqueue(33);
        arb2.enqueue(44);
        arb2.enqueue(62);
        arb2.enqueue(-3);
        arb2.enqueue(77);
        arb2.enqueue(90);
        assertFalse(arb.equals(arb2));
    }
}
