//Computer class: manages computer CPU, RAM and Disk information


public final class Computer {
    private final String CPU;
    private final String RAM;
    private final String disk;

    //Only full constructor, no setters for immutability
    public Computer(String CPU, String RAM, String disk) {
        this.CPU = CPU;
        this.RAM = RAM;
        this.disk = disk;
    }

    //Getters only - no setters to maintain immutability
    public String getCPU()  { return CPU;  }
    public String getRAM()  { return RAM;  }
    public String getDisk() { return disk; }

    @Override
    public String toString() {
        return "CPU:" + CPU + "\tRAM:" + RAM + "\tDisk:" + disk;
    }
}