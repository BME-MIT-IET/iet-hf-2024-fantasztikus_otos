import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Memory{

    @Test
    public void testMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        // Simulate application logic
        byte[] array = new byte[1024 * 1024 * 100]; // Allocate 100 MB of memory
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Memory used: " + (usedMemoryAfter - usedMemoryBefore) + " bytes");

        assertTrue((usedMemoryAfter - usedMemoryBefore) > 0);
    }
}
