package Osztalyok;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class SystemMonitor {
    private final SystemInfo si;
    private final HardwareAbstractionLayer hal;
    private final CentralProcessor processor;
    private final GlobalMemory memory;
    private long[] prevTicks;
    private long prevMemoryUsed;

    public SystemMonitor() {
        si = new SystemInfo();
        hal = si.getHardware();
        processor = hal.getProcessor();
        memory = hal.getMemory();
        prevTicks = processor.getSystemCpuLoadTicks();  // Capture initial CPU state
        prevMemoryUsed = memory.getTotal() - memory.getAvailable(); // Capture initial memory usage
    }

    // Method to start CPU and memory measurement
    public void startMeasurements() {
        prevTicks = processor.getSystemCpuLoadTicks();
        prevMemoryUsed = memory.getTotal() - memory.getAvailable(); // Update memory usage before the task
    }

    // Method to calculate CPU load difference
    public double calculateCpuLoadDifference() {
        long[] newTicks = processor.getSystemCpuLoadTicks();
        double cpuLoadDifference = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        System.arraycopy(newTicks, 0, prevTicks, 0, prevTicks.length); // Update prevTicks for next measurement
        return cpuLoadDifference;
    }

    // Method to calculate memory usage difference in MB
    public long calculateMemoryUsageDifference() {
        long currentMemoryUsed = memory.getTotal() - memory.getAvailable();
        long memoryUsageDifference = currentMemoryUsed - prevMemoryUsed;
        prevMemoryUsed = currentMemoryUsed; // Update memory usage for next measurement
        return memoryUsageDifference / (1024 * 1024); // Convert from bytes to megabytes
    }

    public void printSystemUsage() {
        long usedMemory = memory.getTotal() - memory.getAvailable();
        long totalMemory = memory.getTotal();
        System.out.printf("Memory Usage: %d MB / %d MB\n", usedMemory / (1024 * 1024), totalMemory / (1024 * 1024));
    }
}