import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CPU {

    @Test
    public void testCpuIntensiveTask() {
        long startTime = System.nanoTime();
        // Simulate a CPU-intensive operation
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Math.sqrt(i);
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Duration: " + duration + " ms");

        assertTrue(duration > 0);
    }
}
