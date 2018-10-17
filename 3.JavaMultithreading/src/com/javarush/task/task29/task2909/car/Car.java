package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car
{
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;
    public static final int MAX_TRUCK_SPEED = 80;
    public static final int MAX_SEDAN_SPEED = 120;
    public static final int MAX_CABRIOLET_SPEED = 90;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers)
    {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public double getWinterConsumption(int length)
    {
        return (double) length * this.winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length)
    {
        return (double) length * this.summerFuelConsumption;
    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd)
    {
        if (date.before(summerEnd) && date.after(summerStart)) return true;
        else return false;
    }

    public static Car create(int type, int numberOfPassengers)

    {
        if (type == TRUCK) return new Truck(numberOfPassengers);
        if (type == SEDAN) return new Sedan(numberOfPassengers);
        if (type == CABRIOLET) return new Cabriolet(numberOfPassengers);
        return null;
    }

    public void fill(double numberOfLiters) throws Exception
    {
        if (numberOfLiters > 0)
            fuel += numberOfLiters;
        else throw new Exception();
    }

    private boolean canPassengersBeTransferred()
    {
        return this.isDriverAvailable()&&this.fuel>0;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd)
    {
        double consumption;
        if (!isSummer(date, SummerStart, SummerEnd))
        {
            consumption = getWinterConsumption(length);
        } else
        {
            consumption = getSummerConsumption(length);
        }
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred()
    {
        if (canPassengersBeTransferred())
        return numberOfPassengers;
        else return 0;
    }

    public boolean isDriverAvailable()
    {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable)
    {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving()
    {
        fastenDriverBelt();
        if (numberOfPassengers > 0) fastenPassengersBelts();

    }

    public void fastenPassengersBelts()
    {
    }

    public void fastenDriverBelt()
    {
    }

    public abstract int getMaxSpeed();
}