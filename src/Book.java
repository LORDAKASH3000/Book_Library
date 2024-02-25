package lib_bin;
import java.util.Calendar;
import java.util.Scanner;
public class Book {
    private String Book_name=null,Author_name=null,Publisher_name=null,Borrower_Name=null,Borrower_Phone=null;
    private Integer Page_count=0,Accession_no=0,Borrower_Id=0;
    private Float Price=0f;
    private Date Publish_date=null,Issue_date=null,Return_date=null;
    private Boolean Availability=false;
    private Scanner s = new Scanner(System.in);
    private static Calendar c = Calendar.getInstance();
    public Book(){}
    public Book(String Book_name,String Author_name,Integer Page_count,Integer Accession_no,Float Price,Date Publish_date){
        this.Book_name=Book_name;
        this.Author_name=Author_name;
        this.Page_count=Page_count;
        this.Accession_no=Accession_no;
        this.Price=Price;
        this.Publish_date=Publish_date;
        // set_Availability();
    }
    public void set_Bookname(){
        System.out.println("Enter The Book Name");
        this.Book_name = s.nextLine();
    }
    public void set_Authorname(){
        System.out.println("Enter The Author Name");
        this.Author_name = s.nextLine();
    }
    public void set_Publishername(){
        System.out.println("Enter The Publisher Name");
        this.Publisher_name = s.nextLine();
    }
    public void set_Pagecount(){
        System.out.println("Enter The Page count");
        this.Page_count = s.nextInt();
    }
    public void set_Borrower_Id(){
        System.out.println("Enter The Borrower Id");
        Borrower_Id = s.nextInt();
    }
    public void set_Borrower_Id_to_null(){
        Borrower_Id = null;
    }
    public void set_Borrower_Name(){
        System.out.println("Enter Borrower Name");
        s.nextLine();
        Borrower_Name = s.nextLine();
    }
    public void set_Borrower_Name_to_null(){
        Borrower_Name = null;
    }
    public void set_Borrower_Phone(){
        System.out.println("Enter Borrower Phone Number");
        Borrower_Phone = s.nextLine();
    }
    public void set_Borrower_Phone_to_null(){
        Borrower_Phone = null;
    }
    public void set_Price(){
        System.out.println("Enter The Price");
        this.Price = s.nextFloat();
    }
    public void set_Accessionnumber(int Accessionnumber){
        // System.out.println("Enter The Accession number");
        // this.Accession_no = s.nextInt();
        this.Accession_no = Accessionnumber;
    }
    public void set_Publishdate(){
        Publish_date = new Date();
        System.out.println("Enter The Publishing Date\nYear:\b");
        s.nextLine();
        Publish_date.setYear(s.nextLine());
        System.out.println("Month:\b");
        Publish_date.setMonth(s.nextLine());
        System.out.println("Date:\b");
        Publish_date.setDate(s.nextLine());
    }
    public void set_Issuedate(){
        if(Check_Availability().equals("Not Available")){
            Integer t;
            Issue_date = new Date();
            t=c.get(Calendar.YEAR);
            Issue_date.setYear(t.toString());
            t=c.get(Calendar.MONTH)+1;
            Issue_date.setMonth(t.toString());
            t=c.get(Calendar.DATE);
            Issue_date.setDate(t.toString());
        }
        else Issue_date = null;
    }
    public void set_Returndate(){
        if(Check_Availability().equals("Not Available")){
            Integer d = Integer.parseInt(Issue_date.getDate())+10;
            Integer m = Integer.parseInt(Issue_date.getMonth());
            Return_date = new Date();
            Return_date.setYear(Issue_date.getYear());
            if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
            if(d>31){
                d-=31;
                m++;
            }
        }
            else if(m==2){
            if(Integer.parseInt(Issue_date.getYear())%4==0){
                if(d>29){
                    d-=29;
                    m++;
                }
            }
            else{
                if(d>28){
                    d-=28;
                    m++;
                }
            }
        }
            else{
            if(d>30){
                d-=30;
                m++;
            }
        }
            Return_date.setMonth(m.toString());
            Return_date.setDate(d.toString());
        }
        else Return_date = null;
    }
    public void set_Availability(){
        if(Availability==true)Availability=false;
        Availability=true;
    }
    public void set_Availability(boolean b){
        this.Availability=b;
    }
    public String get_Bookname(){
        return Book_name;
    }
    public String get_Authorname(){
        return Author_name;
    }
    public String get_Publishername(){
        return Publisher_name;
    }
    public String get_Pagecount(){
        return Page_count.toString();
    }
    public String get_Borrower_Id(){
        if(Borrower_Id!=null)return Borrower_Id.toString();
        return null;
    }
    public String get_Borrower_Name(){
        return Borrower_Name;
    }
    public String get_Borrower_Phone(){
        return Borrower_Phone;
    }
    public String get_Price(){
        return Price.toString();
    }
    public String get_Accessionnumber(){
        return Accession_no.toString();
    }
    public Date get_Publishdate(){
        return Publish_date;
    }
    public String Check_Availability(){
        if(this.Availability)
            return "Available";
        return "Not Available";
    }
    public Date get_Issuedate(){
        return Issue_date;
    }
    public Date get_Returndate(){
        return Return_date;
    }
    public String toString(){
        return "   Book Name is : "+this.Book_name+"\n   Author Name is :"+this.Author_name+"\n   Total page is :"+this.Page_count+"\n   Accession Number is :"+this.Accession_no+"\n   Book Publish Date :"+this.Publish_date;
    }
    private String allign(String s,int r){
        if(r-s.length()==1)s=" "+s;
        else if(s.length()<r){
            int diff=r-s.length();
            String temp="";
            if(diff%2==0){
                for(int i=0;i<diff/2;i++)temp+=" ";
                s=temp+s+temp;
            }
            else{
                for(int i=0;i<diff/2;i++)temp+=" ";
                s=s+temp;
                temp+=" ";
                s=temp+s;
            }
        }
        return s+"|";
    }
    public String toBorrower(Integer s){
        String name=""+this.get_Borrower_Name()+"   ",
        phone="   "+this.get_Borrower_Phone()+"   |",
        id="   "+this.get_Borrower_Id()+"   ",
        asno="   "+this.get_Accessionnumber()+"   ",
        date="   "+this.get_Issuedate().toStringdate()+"   |";
        if(this.get_Borrower_Id()!=null&&this.get_Borrower_Name()!=null&&this.get_Borrower_Phone()!=null){
            name=allign(name, 22-s.toString().length()-1);
            id=allign(id, 50-39-1);
            asno=allign(asno, 76-50-1);
            return "|"+s.toString()+")"+name+phone+id+asno+date+"\n";
        }
        return null;
    }
    public Book clone(){
        Book b = new Book();
        b.Book_name=this.get_Bookname();
		b.Author_name=this.get_Authorname();
		b.Price=Float.parseFloat(this.get_Price());
		b.Page_count=Integer.parseInt(this.get_Pagecount());
		b.Publish_date=this.get_Publishdate();
        return b;
    }
    public boolean equals(Book b){
        if(this.Book_name.equals(b.Book_name)){
            if(this.Author_name.equals(b.Author_name)){
                if(this.Page_count.equals(b.Page_count)){
                    if(this.Price.equals(b.Price)){
                        if(this.Publish_date.equals(b.Publish_date)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
