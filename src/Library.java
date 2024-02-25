package lib_bin;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.time.LocalDate; 
import java.time.Month; 
import java.time.Period;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
class Book_Head{
    public ArrayList<Book> Books = new ArrayList<Book>();
    private Book b=null;
    public Integer Quantity = 0,start=0,end=0;
    private static Integer Accession=1;
    private Boolean Availability=false;
    public Book_Head(int Quantity,Book book){
        this.Quantity=Quantity;
        for(int i=0;i<Quantity;i++){
            book.set_Accessionnumber(Accession++);
            this.Books.add(book);
            book.set_Availability();
            if(i!=Quantity-1)book=book.clone();
        }
        this.b=this.Books.get(0);
    }
    public void increase_quantity(int Quantity){
        Book book;
        this.Quantity+=Quantity;
        for(int i=0;i<Quantity;i++){
            book=this.b.clone();
            book.set_Accessionnumber(Accession++);
            book.set_Availability();
            this.Books.add(book);
        }
    }
    public void set_Availability(){
        if(this.Quantity>0)this.Availability=true;
        else this.Availability=false;
    }
    public String Check_Availability(){
        if(this.Availability)
            return "Available";
        return "Not Available";
    }
    public void set_range(){
        this.start=Integer.parseInt(b.get_Accessionnumber());
        this.end=Integer.parseInt(Books.get(Quantity-1).get_Accessionnumber());
    }
    public Integer get_start(){
        return this.start;
    }
    public Integer get_end(){
        return this.end;
    }
    public String toString(){
        return "\nBook Name is : "+b.get_Bookname()+"\nAuthor Name is :"+b.get_Authorname()+"\nTotal page is :"+b.get_Pagecount()+"\nPrice :"+b.get_Price()+b.get_Publishdate()+"\nQuantity :"+this.Quantity;
    }
}
public class Library {
    private ArrayList<Book_Head> book_head = new ArrayList<Book_Head>();
    static Integer section_no = 1;
    private static Scanner s = new Scanner(System.in);
    private static Scanner s2 = new Scanner(System.in);
    Calendar c = Calendar.getInstance();
    private void addBook(){
        Book b = new Book();
		b.set_Bookname();
		b.set_Authorname();
		b.set_Price();
		b.set_Pagecount();
		b.set_Publishdate();
        if(book_head.size()>0){
            for(Book_Head i : book_head){
                if(i.Books.get(0).equals(b)){
                    System.out.println("Enter The Quantity1");
                    i.increase_quantity(s.nextInt());
                    return;
                }
            }
        }
        System.out.println("Enter The Quantity");
        Book_Head bh = new Book_Head(s.nextInt(),b);
        bh.set_Availability();
        bh.set_range();
        this.book_head.add(bh);
    }
    private void print_database(){
        if(this.book_head.size()>0)for(Book_Head i : this.book_head)System.out.println(i+"\n");
        else System.out.println("Data Base is empty");
    }
    private void indirect_borrow(Book_Head refHead){
        Book b;
        if(refHead.Quantity!=0){
            for(int j=0;j<refHead.Books.size();j++){
                b=refHead.Books.get(j);
                if(b.Check_Availability().equals("Available")){
                    b.set_Borrower_Id();
                    b.set_Borrower_Name();
                    b.set_Borrower_Phone();
                    b.set_Availability(false);
                    b.set_Issuedate();
                    System.out.println("Your issue date is: \b"+b.get_Issuedate());
                    b.set_Returndate();
                    System.out.println("Your return date is: \b"+b.get_Returndate());
                    refHead.Quantity--;
                    try{
                        update_Record_Book(b);
                    }catch(IOException e){
                        System.out.println(e);
                    }
                    break;
                }
            }
        }
        else System.out.println("Currently Unavailable");
    }
    private void search_by_Author(){
        Integer i=0;
        ArrayList<Book_Head> book_by_author = new ArrayList<Book_Head>();
        System.out.println("Enter the Author Name");
        String author = s.nextLine();
        for(Integer j=1;i<this.book_head.size();i++)
            if(this.book_head.get(i).Books.get(0).get_Authorname().equals(author)){
                book_by_author.add(this.book_head.get(i));
                System.out.println(j.toString()+".\b"+this.book_head.get(i));
                j++;
                System.out.println("If you want to Borrow a book then enter the index of the book from the above list.\nif you want to quit to main menu then enter 0");
                i = s.nextInt();
                try{if(i!=0)indirect_borrow(book_by_author.get(i-1));}catch(Exception e){System.out.println("Wrong Input");}
                return;
            }
        System.out.println("Book with Author Name: "+ author+" is not available");
    }
    private void search_by_Title(){
        Integer i=0;
        ArrayList<Book_Head> book_by_title = new ArrayList<Book_Head>();
        System.out.println("Enter the Title");
        String title = s.nextLine();
        for(Integer j=1;i<this.book_head.size();i++)
            if(this.book_head.get(i).Books.get(0).get_Bookname().equals(title)){
                book_by_title.add(this.book_head.get(i));
                System.out.println(j.toString()+".\b"+this.book_head.get(i));
                j++;
                System.out.println("If you want to Borrow a book then enter the index of the book from the above list\nif you want to quit to main menu then enter 0");
                i = s.nextInt();
                try{if(i!=0)indirect_borrow(book_by_title.get(i-1));}catch(Exception e){System.out.println("Wrong Input");}
                return;
            }
            System.out.println("Book with Title: "+ title+" is not available");
    }
    private void search_by_Accession_Number(){
        boolean flag = false;
        Book_Head b=null;
        Book B=null;
        System.out.println("Enter the Accession Number");
        Integer Accession_Number = s.nextInt();
        for(int i=0;i<this.book_head.size();i++){
            b=this.book_head.get(i);
            if(Accession_Number>=b.get_start()&&Accession_Number<=b.get_end())break;
        }
        if(b==null){System.out.println("Book with Accession number "+Accession_Number+" is not exist");return;}
        for(int j=0;j<b.Books.size();j++){
            B=b.Books.get(j);
            if(!B.get_Accessionnumber().equals(Accession_Number.toString()))continue;
            System.out.println(B+"\nIf you want to Borrow this book then enter 1\nif you want to quit to main menu then enter 0");
            if(s.nextInt()==0)return;
            if(!b.Check_Availability().equals("Available")){System.out.println("Currently Unavailable");return;}
            B.set_Borrower_Id();
            B.set_Availability(false);
            B.set_Issuedate();
            System.out.println("Your issue date is: \b"+B.get_Issuedate());
            B.set_Returndate();
            System.out.println("Your return date is: \b"+B.get_Returndate());
            b.Quantity--;
            break;
        }
    }
    private void Borrow_Book(){
        boolean flag=false;
        Book b;
        System.out.println("Enter the Title");
        String title = s.nextLine();
        System.out.println("Enter the Author Name");
        String author = s.nextLine();
        for(int i=0;i<this.book_head.size();i++){
            b=this.book_head.get(i).Books.get(0);
            if(b.get_Bookname().equals(title)&&b.get_Authorname().equals(author)){
                flag=true;
                if(this.book_head.get(i).Quantity!=0){
                    for(int j=0;j<this.book_head.get(i).Books.size();j++){
                        b=this.book_head.get(i).Books.get(j);
                        if(b.Check_Availability().equals("Available")){
                            b.set_Borrower_Id();
                            b.set_Borrower_Name();
                            b.set_Borrower_Phone();
                            b.set_Availability(false);
                            b.set_Issuedate();
                            System.out.println("Your issue date is: \b"+b.get_Issuedate());
                            b.set_Returndate();
                            System.out.println("Your return date is: \b"+b.get_Returndate());
                            this.book_head.get(i).Quantity--;
                            try{
                                update_Record_Book(b);
                            }catch(IOException e){
                                System.out.println(e);
                            }
                            break;
                        }
                    }
                }
                else System.out.println("Currently Unavailable");
                break;
            }
        }
        if(!flag)System.out.println("Book with Title "+title+" and Author "+author+" is not present at this Library");
    }
    private void Return_Book(){
        Integer k=1;
        Book b = new Book();
        ArrayList<Book> book_by_Id = new ArrayList<Book>();
        System.out.println("Enter Your Id");
        String id = s.nextLine();
        for(int i=0;i<this.book_head.size();i++)for(int j=0;j<this.book_head.get(i).Books.size();j++){
            if(this.book_head.get(i).Books.get(j).Check_Availability().equals("Available"))continue;
            if(this.book_head.get(i).Books.get(j).get_Borrower_Id().equals(id))book_by_Id.add(this.book_head.get(i).Books.get(j));
        }
        if(book_by_Id.size()>0){
            System.out.println("You had Borrowed:-");
            for(Book i : book_by_Id)System.out.println(k+++"."+i);
            System.out.println("Which Book You Want To Return(Enter the index no.):");
            k=s.nextInt();
            b = book_by_Id.get(k-1);
            LocalDate returndate = LocalDate.of(Integer.parseInt(b.get_Returndate().getYear()),Integer.parseInt(b.get_Returndate().getMonth()),Integer.parseInt(b.get_Returndate().getDate()));
            LocalDate today = LocalDate.now();
            Period difference = Period.between(returndate, today);
            int years = difference.getYears();
            int months = difference.getMonths();
            int days = difference.getDays();
            if(years==0){
                if(months==0){
                    if(days<=0)System.out.println("Your Book Successfully Returned");
                    else if(days>10&&days<=30)System.out.println("You have to pay fine of 100Rs\nYour Book Successfully Returned");
                }
                else System.out.println("You have to pay fine of 1000Rs\nYour Book Successfully Returned");
            }
            else System.out.println("Your Membership has been cancled and You have to pay fine of 1000Rs\nYour Book Successfully Returned");
            b.set_Availability(true);
            b.set_Borrower_Id_to_null();
            b.set_Borrower_Name_to_null();
            b.set_Borrower_Phone_to_null();
            b.set_Issuedate();
            b.set_Returndate();
            for(int i=0;i<this.book_head.size();i++){
                if(this.book_head.get(i).Books.get(0).get_Bookname().equals(b.get_Bookname())&&this.book_head.get(i).Books.get(0).get_Authorname().equals(b.get_Authorname()))this.book_head.get(i).Quantity++;
            }
        }
    
    }
    private void update_Record_Book(Book refBook)throws IOException{
        File RB = new File("Record_Book.txt");
        String sep = "----------------------------------------------------------------------------------------------\n";
        if(!RB.exists()){
            FileWriter nf = new FileWriter("Record_Book.txt");
            String collumn = "|         Name         |     Phone      |    ID    |  Book Accession Number  |     Date      |\n";
            nf.write(collumn+sep+refBook.toBorrower(section_no++)+sep);
            nf.close();
        }
        else{
            FileWriter ef = new FileWriter("Record_Book.txt",true);
            ef.write(refBook.toBorrower(section_no++)+sep);
            ef.close();
        }
    }
    public static void main(String[] args) throws Exception{
        Library l = new Library();
        while(true){
            System.out.println("Press 1 to add some Book Details in data base");
            System.out.println("Press 2 to take a view of Data Base");
            System.out.println("Press 3 to search a book by Author Name");
            System.out.println("Press 4 to search a book by Title");
            System.out.println("Press 5 to search a book by Accession Number");
            System.out.println("Press 6 to Borrow a book");
            System.out.println("Press 7 to Return a book");
            System.out.println("Press 0 to Exit");
            System.out.println("Enter Your Choice");
            switch(s.nextLine()){
                case "1"->l.addBook();
                case "2"->l.print_database();
                case "3"->l.search_by_Author();
                case "4"->l.search_by_Title();
                case "5"->l.search_by_Accession_Number();
                case "6"->l.Borrow_Book();
                case "7"->l.Return_Book();
                case "0"->System.exit(0);
                default -> System.out.println("Wrong input");
            }
        }
    }
}
