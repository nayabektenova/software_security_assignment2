//Laptop computer: adds screen size to other Computer info
//Laptop computer: adds GPU type

public final class Laptop implements IComputer {
    private final Computer base;   //composition,has-a computer
    private final String screenSize;

    public Laptop(String CPU, String RAM, String disk, String screenSize) {
        this.base = new Computer(CPU, RAM, disk);
        this.screenSize = screenSize;
    }

    //delegate base fields
    @Override public String getCPU()  { return base.getCPU(); }
    @Override public String getRAM()  { return base.getRAM(); }
    @Override public String getDisk() { return base.getDisk(); }

    public String getscreenSize() { return screenSize; }

    @Override public String getTypeLabel() { return "Laptop"; }
    @Override public String getExtraLabel() { return "Screen Size:" + screenSize; }

    @Override
    public String toString() {
        return "Type:Desktop\t" + base.toString() + "\tScreen Size:" + screenSize;
    }
}

