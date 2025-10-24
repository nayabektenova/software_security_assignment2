//Desktop computer: adds GPU type

public final class Desktop implements IComputer {
    private final Computer base;   //composition,has-a computer
    private final String GPUType;

    public Desktop(String CPU, String RAM, String disk, String GPUType) {
        this.base = new Computer(CPU, RAM, disk);
        this.GPUType = GPUType;
    }

    //delegate base fields
    @Override public String getCPU()  { return base.getCPU(); }
    @Override public String getRAM()  { return base.getRAM(); }
    @Override public String getDisk() { return base.getDisk(); }

    public String getGPUType() { return GPUType; }

    @Override public String getTypeLabel() { return "Desktop"; }
    @Override public String getExtraLabel() { return "GPU:" + GPUType; }

    @Override
    public String toString() {
        return "Type:Desktop\t" + base.toString() + "\tGPU:" + GPUType;
    }
}