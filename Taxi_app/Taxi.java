package Taxi_app;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    static int taxicount = 0;
    int id;
    char currentSpot;
    int freeTime;
    int totalEarnings;
    List<String> trips;

    public Taxi(){
        currentSpot = 'A';
        freeTime = 6;
        totalEarnings = 0;
        trips = new ArrayList<String>();
        taxicount += 1;
        id = taxicount;
    }

    public void setDetails(char currentSport,int freeTime,int totalEarnings,String tripDetail){
        this.currentSpot = currentSport;
        this.freeTime = freeTime;
        this.totalEarnings = totalEarnings;
        this.trips.add(tripDetail);
    }

    public void printDetails(){
        System.out.println("\nTaxi-"+this.id+"  Total Earnings: Rs."+this.totalEarnings);
        System.out.println("TaxiID \t BookingID \t CustomerID \t From \t To \t PickupTime \t DropTime \t Amount");
        for (String trip : trips)
        {
            System.out.println("  "+id+" \t "+trip);
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

}

