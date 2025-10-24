//Desktop computer: adds GPU type

public final class Desktop implements ComputerLike {
    private final Computer base;   //Composition: Desktop HAS-A Computer
    private final String GPUType;

    //Only constructor - no setters for immutability
    public Desktop(String CPU, String RAM, String disk, String GPUType) {
        this.base = new Computer(CPU, RAM, disk);
        this.GPUType = GPUType;
    }

    //Delegate base Computer fields to the composed Computer object
    @Override
    public String getCPU()  { return base.getCPU(); }
    
    @Override
    public String getRAM()  { return base.getRAM(); }
    
    @Override
    public String getDisk() { return base.getDisk(); }

    //Desktop-specific getter
    public String getGPUType() { return GPUType; }

    @Override
    public String toString() {
        return "Type:Desktop\t" + base.toString() + "\tGPU:" + GPUType;
    }
}