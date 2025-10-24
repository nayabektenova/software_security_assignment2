//Laptop computer: adds screen size to other Computer info

public final class Laptop implements ComputerLike {
    private final Computer base;   //Composition: Laptop HAS-A Computer
    private final String screenSize;

    //Only constructor - no setters for immutability
    public Laptop(String CPU, String RAM, String disk, String screenSize) {
        this.base = new Computer(CPU, RAM, disk);
        this.screenSize = screenSize;
    }

    //Delegate base Computer fields to the composed Computer object
    @Override
    public String getCPU()  { return base.getCPU(); }
    
    @Override
    public String getRAM()  { return base.getRAM(); }
    
    @Override
    public String getDisk() { return base.getDisk(); }

    //Laptop-specific getter
    public String getScreenSize() { return screenSize; }

    @Override
    public String toString() {
        return "Type:Laptop\t" + base.toString() + "\tScreen:" + screenSize;
    }
}