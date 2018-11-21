package com.javarush.task.task37.task3711;

public class Computer
{
    private CPU cpu;
    private HardDrive hardDrive;
    private Memory memory;

    public Computer()
    {
        cpu = new CPU();
        hardDrive = new HardDrive();
        memory = new Memory();
    }
    void run()
    {
      cpu.calculate();
      memory.allocate();
      hardDrive.storeData();
    }
}
