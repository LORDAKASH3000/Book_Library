package lib_bin;
import java.util.Calendar;
import java.util.Scanner;
public class Date {
    private static Scanner sc = new Scanner(System.in);
    private String date,month,year;
    private static Calendar c = Calendar.getInstance();
    public Date(){
        this.year="";
        this.month="";
        this.date="";
    }
    public Date(String date,String month,String year){
        if(Integer.parseInt(year)<=c.get(Calendar.YEAR)){
            if(Integer.parseInt(month)>0&&Integer.parseInt(month)<=12){
                if(Integer.parseInt(month)==1||Integer.parseInt(month)==3||Integer.parseInt(month)==5||Integer.parseInt(month)==7||Integer.parseInt(month)==8||Integer.parseInt(month)==10||Integer.parseInt(month)==12){
                    if(Integer.parseInt(date)==31)this.date=date;
                    else{
                        System.out.println("Invalid Date");
                        setDate(date,31);
                    }
                }
                else if(Integer.parseInt(month)==2){
                    if(isLeap_Year(Integer.parseInt(year))){
                        if(Integer.parseInt(date)==29)this.date=date;
                        else{
                            System.out.println("Invalid Date");
                            setDate(date,29);
                        }
                    }
                    else{
                        if(Integer.parseInt(date)==28)this.date=date;
                        else{
                            System.out.println("Invalid Date");
                            setDate(date,28);
                        }
                    }
                }
                else{
                    if(Integer.parseInt(date)==30)this.date=date;
                    else{
                        System.out.println("Invalid Date");
                        setDate(date,30);
                    }
                }
            }
            else{
                System.out.println("Invalid Month");
                setMonth(month);
            }
        }
        else{
            System.out.println("Invalid Year");
            setYear(year);
        }
        // if(Integer.parseInt(date)>31){
        //     System.out.println("Invalid date");
        //     setDate(date);
        // }
        // else this.date=date;
        // if(Integer.parseInt(month)>12){
        //     System.out.println("Invalid month");
        //     setMonth(month);
        // }
        // else this.month=month;
        // this.year=year;
    }
    public Date(Date refDob){
        this.year=refDob.year;
        this.month=refDob.month;
        this.date=refDob.date;
    }
    private boolean isLeap_Year(int year){
        if(year%4==0)return true;
        return false;
    }
    public void setYear(String year){
        if(Integer.parseInt(year)<=c.get(Calendar.YEAR))this.year=year;
        else do{
            System.out.println("Please enter a valid Year");
            this.year=sc.nextLine();
        }while(Integer.parseInt(this.year)>c.get(Calendar.YEAR));
    }
    public void setMonth(String month){
        if(Integer.parseInt(month)>0&&Integer.parseInt(month)<=12)this.month=month;
        else do{
            System.out.println("Please enter a valid month");
            this.month=sc.nextLine();
        }while(Integer.parseInt(this.month)>12||Integer.parseInt(this.month)<=0);
    }
    public void setDate(String date){
        if(!this.month.equals("")){ 
            if(Integer.parseInt(this.month)>0&&Integer.parseInt(this.month)<=12){
                if(Integer.parseInt(this.month)==1||Integer.parseInt(this.month)==3||Integer.parseInt(this.month)==5||Integer.parseInt(this.month)==7||Integer.parseInt(this.month)==8||Integer.parseInt(this.month)==10||Integer.parseInt(this.month)==12){
                    if(Integer.parseInt(date)>0&&Integer.parseInt(date)<=31)this.date=date;
                    else{
                        System.out.println("Invalid Date");
                        setDate(date,31);
                    }
                }
                else if(Integer.parseInt(this.month)==2){
                    if(isLeap_Year(Integer.parseInt(this.year))){
                        if(Integer.parseInt(date)>0&&Integer.parseInt(date)<=29)this.date=date;
                        else{
                            System.out.println("Invalid Date");
                            setDate(date,29);
                        }
                    }
                    else{
                        if(Integer.parseInt(date)>0&&Integer.parseInt(date)<=28)this.date=date;
                        else{
                            System.out.println("Invalid Date");
                            setDate(date,28);
                        }
                    }
                }
                else{
                    if(Integer.parseInt(date)>0&&Integer.parseInt(date)<=30)this.date=date;
                    else{
                        System.out.println("Invalid Date");
                        setDate(date,30);
                    }
                }
            }
        }
        else System.out.println("First initialize Month");
    }
    public void setDate(String date,int range){
        if(Integer.parseInt(date)>0&&Integer.parseInt(date)<=range)
            this.date=date;
        else do{
            System.out.println("Please enter a valid date");
            this.date=sc.nextLine();
        }while(Integer.parseInt(this.date)>range||Integer.parseInt(date)<=0);
    }
    public String getYear(){
        return this.year;    
    }
    public String getMonth(){
        return this.month;
    }
    public String getDate(){
        return this.date;
    }
    public String toString(){
        return "\n(DD/MM/YY): "+date+"/"+month+"/"+year;
    }
    public String toStringdate(){
        return date+"/"+month+"/"+year;
    }
    public boolean equals(Date d){
        if(this.year.equals(d.year)){
            if(d.month.equals(d.month)){
                if(d.date.equals(d.date)){
                    return true;
                }
            }
        }
        return false;
    }
    // public static Date getnewDob() {
    //     Date D = new Date();
    //     System.out.println("Enter Birth Date");
    //     D.setDate(sc.nextLine());
    //     System.out.println("Enter Birth Month");
    //     D.setMonth(sc.nextLine());
    //     System.out.println("Enter Birth Year");
    //     D.setYear(sc.nextLine());
    //     return D;
    // }
}
