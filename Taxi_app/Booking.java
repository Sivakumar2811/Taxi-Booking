package Taxi_app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Booking {
    public static void bookTaxi(int CustomerID, char PickupPoint, char DropPoint, int PickupTime, List<Taxi> freeTaxis){
        //to find nearest
        int min=999;

        //distance between pickup and drop
        int distanceBetweenpickUpandDrop = 0;

        //this trip earning
        int earning = 0;

        //when taxi will be free next
        int nextfreeTime = 0;

        //where taxi is after trip is over
        char nextSpot = 'Z';

        //booked taxi
        Taxi bookedTaxi = null;

        //all details of current trip as string
        String tripDetail = "";

        for(Taxi t : freeTaxis)
        {
            int distanceBetweenCustomerAndTaxi = Math.abs(t.currentSpot-PickupPoint) * 15;
            if(distanceBetweenCustomerAndTaxi < min)
            {
                bookedTaxi = t;
                distanceBetweenpickUpandDrop = Math.abs(DropPoint-PickupPoint) * 15;
                earning = (distanceBetweenpickUpandDrop-5) * 10 + 100;

                int DropTime  = PickupTime + distanceBetweenpickUpandDrop/15;

                nextfreeTime = DropTime;

                nextSpot = DropPoint;

                tripDetail = "\t"+CustomerID + " \t\t\t " + CustomerID + "  \t\t  " + PickupPoint +  " \t " + DropPoint + "  \t\t  " + PickupTime + " \t\t\t " +DropTime + " \t\t " + earning;
                min = distanceBetweenCustomerAndTaxi;
            }
        }

        bookedTaxi.setDetails(nextSpot,nextfreeTime,bookedTaxi.totalEarnings + earning,tripDetail);
        System.out.println("\nTaxi can be allotted");
        System.out.println("Taxi-" + bookedTaxi.id + " is allotted");
        System.out.println("--*--*--*--*--*--*--*--*--*--*--*--*--*--");
    }

    public static List<Taxi> createTaxis(int n){
        List<Taxi> Taxis = new ArrayList<Taxi>();
        for (int i=1;i<=n;i++)
        {
            Taxi t = new Taxi();
            Taxis.add(t);
        }
        return Taxis;
    }

    public static List<Taxi> getFreeTaxis(List<Taxi> Taxis,int PickupTime,char PickupPoint){
        List<Taxi> freeTaxis = new ArrayList<>();
        for (Taxi t : Taxis)
        {
            if (t.freeTime<=PickupTime && (Math.abs(t.currentSpot-PickupPoint)<=(PickupTime-t.freeTime)))
                freeTaxis.add(t);
        }
        return freeTaxis;
    }

    public static void main(String[] args) {

        List<Taxi> Taxis = createTaxis(4);

        Scanner s = new Scanner(System.in);
        int CustomerID = 1;

        while(true)
        {
            System.out.println("\n1-> Book Taxi");
            System.out.println("2-> Display Taxi details");

            System.out.print("Enter the your choice :");
            int choice = s.nextInt();


            switch (choice)
            {
                case 1:
                {
                    System.out.println("\nCustomer ID :"+CustomerID);
                    System.out.print("Pickup point :");
                    char PickupPoint = s.next().charAt(0);
                    System.out.print("Drop Point :");
                    char DropPoint = s.next().charAt(0);
                    System.out.print("Pickup Time :");
                    int PickupTime = s.nextInt();

                    if(PickupPoint<'A' || PickupPoint>'F')
                    {
                        System.out.println("\nInvalid Pickup Point only Valid Pickup Point are A, B, C, D, E, F");
                        return;
                    }

                    if (DropPoint<'A' || DropPoint>'F')
                    {
                        System.out.println("\nInvalid Drop Point only Valid Drop Point are A, B, C, D, E, F");
                        return;
                    }

                    List<Taxi> freeTaxis = getFreeTaxis(Taxis,PickupTime,PickupPoint);

                    if (freeTaxis.size()==0) {
                        System.out.println("\nTaxis are Not Available");
                        System.out.println("So No Taxi can be allotted");
                        return;
                    }

                    Collections.sort(freeTaxis,(a,b)->a.totalEarnings - b.totalEarnings);

                    bookTaxi(CustomerID,PickupPoint,DropPoint,PickupTime,freeTaxis);
                    CustomerID++;
                    break;
                }

                case 2:
                {
                    for (Taxi t : Taxis)
                    {
                        t.printDetails();
                    }
                    break;
                }

                default: {
                    System.out.println("\nInvalid Input. So please Enter the valid input");
                    break;
                }
            }
        }
    }
}
