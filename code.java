import java.util.*;
import java.lang.*;

class Slot 
{
    private String slottime;  //stores time slot
    private Map <String,Integer []> halls=new TreeMap<>();    // stores movie code for each hall for time slots   hall[0]=hall1  hall[1]=hall2   hall[2]=hall3
    private  Map <String,Integer []> seats=new TreeMap<>();  //keeps account of no of seats available for each screening
    Slot()
    {
        
    }
    public void setter(String time,Integer h[])
    {
       
        slottime=time;
        halls.put(time,h);
        seats.put(time,new Integer[]{100,70,50});
        
    }
    
   public boolean available(String time,int id)    // returns false if housefull
  {
    Integer [] x=halls.get(time);
    Integer [] y=seats.get(time);
       if(x[0]==id)
        {
          if( y[0]>0)
            {
              return true;
            }
            else
            {
                return false;
            }
        }
        else if(x[1]==id)
        {
           if( y[1]>0)
            {
              return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
           if( y[2]>0)
            {
              return true;
            }
            else
            {
                return false;
            }
        } 
       
  }
    public int getseats(String time,int id)    // returns available no of seats
    {
        Integer  x[]=halls.get(time);
        Integer  y[]=seats.get(time);
        if(x[0]==id)
        {
          return y[0];
        }
        else if(x[1]==id)
        {
          return y[1];
        }
        else
        {
          return y[2];
        }
      
    }
    public int gethall(String time,int id)  //returns hall no
    {
        Integer [] x=halls.get(time);
        if(x[0]==id)
        {
          return 1;
        }
        else if(x[1]==id)
        {
          return 2;
        }
        else
        {
          return 3;
        }
    }
    public void update(String time,int id,int sts)   //updates no of seats left after booking
    {
        Integer [] x=halls.get(time);
        Integer [] y=seats.get(time);
         if(x[0]==id)
        {
           y[0]=y[0]-sts;
        }
        else if(x[1]==id)
        {
           y[1]=y[1]-sts;
        }
        else
        {
           y[2]=y[2]-sts;
        }
    }
}
public class MyClass {
    public static void main(String args[]) {
      Map <Integer,String> h1=new HashMap<>();
      Map <String,Integer []> h2=new TreeMap<>();
      Map <Integer,String []> h3=new TreeMap<>();
      Scanner scan=new Scanner(System.in);
      h1.put(1,"3 idiots");
      h1.put(2,"Sonu ke Titu ki Sweety");
      h1.put(3,"Raazi");
      h1.put(4,"Hera Pheri");
      h1.put(5,"Bahubali");
      h1.put(6,"Kabir Singh");
      h1.put(7,"Andhadhun");
      h1.put(8,"Badhaii Ho");
      h1.put(9,"Dangaal");
      //hardcoding h2
      h2.put("8:00",new Integer[]{1,3,7});
      h2.put("11:00",new Integer[]{2,4,6});
      h2.put("15::00",new Integer[]{3,5,8});
      h2.put("18:00",new Integer[]{7,1,9});
      h2.put("21:00",new Integer[]{6,8,4});
      h2.put("24:00",new Integer[]{5,9,2});
      //passing data to class Slot
       Slot s=new Slot();
      for(Map.Entry<String,Integer []> entry:h2.entrySet())
      {
        String a=entry.getKey();
        Integer [] b=entry.getValue();
        s.setter(a,b);
      }
      //hardcoding h3
      h3.put(1,new String[]{"8:00","18:00"});
      h3.put(2,new String[]{"11:00","24:00"});
      h3.put(3,new String[]{"8:00","15:00"});
      h3.put(4,new String[]{"11:00","21:00"});
      h3.put(5,new String[]{"15:00","24:00"});
      h3.put(6,new String[]{"11:00","21:00"});
      h3.put(7,new String[]{"8:00","18:00"});
      h3.put(8,new String[]{"15:00","21:00"});
      h3.put(9,new String[]{"18:00","24:00"});
      //Screen displays start here
      System.out.println("\tWelcome user");
      System.out.println("Movie code\tMovie Name");
      for(int i=1;i<10;i++)
      {
         String x=h1.get(i);
          System.out.println("0"+i+"\t\t\t"+x);
      }
      
      System.out.println("Enter the code from above for the movie you want to book");
      int mc=scan.nextInt();  //mc=movie code which user inputs
      if((mc>9)||(mc<1))  // if movie code input by user is invalid
      {
          System.out.println("Invalid input");
          System.out.println("Booking Unsuccessful");
          System.exit(0);
      }
      System.out.println("Available time slot for "+h1.get(mc)+"(0"+mc+") are");
      String s1[]=h3.get(mc);   
      System.out.println(s1[0]);
      System.out.println(s1[1]);
      System.out.println("Enter the time slot you want to book");
      String st=scan.next();    //st=slot time which user inputs
      if(!st.equals(s1[0]))       // if time slot input by user is nt what is asked
      {
           if(!st.equals(s1[1]))
          {
              System.out.println("Invalid input");
              System.out.println("Booking Unsuccessful");
               System.exit(0);
          }
      }
      boolean avail=s.available(st,mc);     // to check whether screening is housefull or nt
      if(!avail)     // if screening is housefull
      {
          System.out.println("Sorry!Housefull");
          System.out.println("Booking Unsuccessful");
          System.exit(0);
      }
      int seat_avail=s.getseats(st,mc);    // to get the no of seats available for movie at particular time slot
      System.out.println("No of seats available for "+h1.get(mc)+"(0"+mc+") at "+st+" are\n"+seat_avail);
      System.out.println("Enter the no of seats you want to book");
      int seat_req=scan.nextInt();
      if((seat_req>seat_avail)||(mc<1))
      {
          System.out.println("Sorry! No of seats required arent available");
          System.out.println("Booking Unsuccessful");
          System.exit(0);
      }
      s.update(st,mc,seat_req);      // updates the no of seats left after current booking
      int hall_no=s.gethall(st,mc);   // returns the hall no 
      System.out.println("\t\tBooking Successful");
      System.out.println("Movie Code- "+mc);
      System.out.println("Movie Name- "+h1.get(mc));
      System.out.println("Time Slot- "+st);
      System.out.println("No of seats- "+seat_req);
      System.out.println("Hall no- "+hall_no);
      scan.close();
      
      
    }
}
