import java.sql.*;
import java.util.Scanner;



public class MysqlConnect{
	
static int ticketPrice=7;
	
public static void main(String[] args) throws SQLException {
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "database";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root"; 
	String password = "";

	try{
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url+dbName,userName,password);
		System.out.println("Connected to database\n\n");
	
		
	}catch(Exception e) {
		e.printStackTrace();
	
	}
	
	
	
	
	
	Scanner sc = new Scanner(System.in);
	int k ;
	String menu = new String(" 1.Insert into database values for all arrays(done) \n 2.Now playing movies per cinema \n 3.List of sold out shows \n 4.Search by customer preferences \n 5.Make reservation \n 6.Reservation Payment \n 7.Delete show \n !!! for exit press 0 !!! \n\n");
	
	System.out.println(menu);
	
	System.out.println("Which action would you like to make ? ");
	k=sc.nextInt();
	
do {
	
	   if(k==0)
		   break;
		
		
		if(k==1) {// database is loaded with values for all arrays ,only one time,the first time we run the programm//
			
			 try{
					Statement st = conn.createStatement();
					
					st.executeUpdate("INSERT INTO cinema VALUES('Peristeri')");
					st.executeUpdate("INSERT INTO cinema VALUES('Galatsi')");
					st.executeUpdate("INSERT INTO cinema VALUES('Renti')");
					
					st.executeUpdate("INSERT INTO movie VALUES('Goodfellas','plotplotplot','Scorsese','De Niro Liotta', 'drama,crime','160min')");
					st.executeUpdate("INSERT INTO movie VALUES('John_Wick','plotplotplot','Chad Stahelski','Reeves','action','160min')");
					st.executeUpdate("INSERT INTO movie VALUES('Taxi_Driver','plotplotplot','Scorsese','De Niro','drama-crime','200min')");
					st.executeUpdate("INSERT INTO movie VALUES('Pulp_Fiction','plotplotplot','Tarantino','De Niro','crime','180min')");
					
					st.executeUpdate("INSERT INTO movie_theater VALUES('room1',10,'Renti',10)");
					st.executeUpdate("INSERT INTO movie_theater VALUES('room2',10,'Renti',12)");
					st.executeUpdate("INSERT INTO movie_theater VALUES('room3',10,'Peristeri',8)");
					st.executeUpdate("INSERT INTO movie_theater VALUES('room4',10,'Peristeri',9)");
					st.executeUpdate("INSERT INTO movie_theater VALUES('room5',10,'Galatsi',11)");
					st.executeUpdate("INSERT INTO movie_theater VALUES('room6',10,'Galatsi',13)");
					
					st.executeUpdate("INSERT INTO customer VALUES('thodoris_tsoufis','6980785501','tt@gmail.com')");
					st.executeUpdate("INSERT INTO customer VALUES('giwta_dimitriou','6987401102','gd@gmail.com')");
					
					st.executeUpdate("INSERT INTO proboli VALUES('16/05/2019_22:00','John_Wick','room5','Galatsi',110);");
					st.executeUpdate("INSERT INTO proboli VALUES('15/05/2019_21:00','John_Wick','room4','Peristeri',0);");
					st.executeUpdate("INSERT INTO proboli VALUES('16/05/2019_22:30','John_Wick','room3','Peristeri',80);");
					st.executeUpdate("INSERT INTO proboli VALUES('13/05/2019_21:00','Taxi_Driver','room1','Renti',100);");
					st.executeUpdate("INSERT INTO proboli VALUES('20/05/2019_21:30','Pulp_Fiction','room2','Renti',120)");
					
					
					}
					catch(SQLException s){
						System.out.println("SQL code does not execute.");
					}
			
			
			
		} 
		
	
		
		if(k==2) {

    try{
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery("SELECT * FROM proboli ORDER BY cinema_cinema_location");
		System.out.println("Movies played per Cinema :  \n\n");
		while (res.next()) {
			String i = res.getString("cinema_cinema_location");
			String j = res.getString("movie_movie_name");
			System.out.println(j+" : "+i);
		}
		}catch(SQLException s){
			System.out.println("SQL code does not execute.");
		}
    
		}
		
		if(k==3) {

		    try{
				Statement st = conn.createStatement();
				
				System.out.println("Type cinema's location to check:  ");
				String loc=new String(sc.next());
				
				ResultSet res = st.executeQuery("SELECT * FROM proboli WHERE cinema_cinema_location='"+loc+"' AND availability<=0");
				System.out.println("sold out shows :  ");
				while (res.next()) {
					String a = res.getString("cinema_cinema_location");
					String b = res.getString("movie_movie_name");
					String c = res.getString("proboli_start");
					
					System.out.println(c+", "+b+" at "+a);
				}
				}catch(SQLException s){
					System.out.println("SQL code does not execute.");
				}
		    
				}
		
		if(k==4) {

		    try{
				Statement st = conn.createStatement();
				
				System.out.println("Cinema location :  ");
				String location = new String(sc.next());
				
				System.out.println("Movie name :  ");
				String name = new String(sc.next());
				
				
				System.out.println("date-time { example( 6/5/2019_21:30 ) } :  ");
				String dateTime = new String(sc.next());
				
				System.out.println("gender  :  ");
				String gender = new String(sc.next());
				
					
				ResultSet res = st.executeQuery("SELECT * FROM proboli WHERE cinema_cinema_location='"+location+"' AND movie_movie_name='"+name+"'  AND availability>0");
				System.out.println("Results :  ");
				while (res.next()) {
					String i = res.getString("cinema_cinema_location");
					String j = res.getString("movie_movie_name");
					String t = res.getString("proboli_start");
					String l = res.getString("movie_theater_mt_name");
					String av = res.getString("availability");
					
					
					System.out.println(t+", "+j+", "+l+" at  "+i);
					System.out.println("Availability : "+av);
					System.out.println("");
				}
				
				}catch(SQLException s){
					System.out.println("SQL code does not execute.");
				}
		    
				}
		
		
		if(k==5) {

		    try{
				Statement st = conn.createStatement();
				
				System.out.println("Customer's full name :  ");
				String fullname = new String(sc.next());
				
				System.out.println("phone number :  ");
				String phoneNum = new String(sc.next());
				
				System.out.println("e-mail :  ");
				String email = new String(sc.next());
				
				st.executeUpdate("INSERT INTO customer VALUES('"+fullname+"','"+phoneNum+"','"+email+"')");
				
				
				System.out.println("Cinema location :  ");
				String location = new String(sc.next());
				
							
				System.out.println("Movie name :  ");
				String name = new String(sc.next());
				
				System.out.println("date-time :  ");
				String dateTime = new String(sc.next());
				
				System.out.println("Movie theater :  ");
				String mt = new String(sc.next());
				
            //here1
				
				ResultSet res = st.executeQuery("SELECT * FROM movie_theater WHERE mt_name='"+mt+"' ");
				
				int count1=0;
				int count2=0;
				while(res.next()) {	
				 count1 =res.getInt("mt_rows");
				 count2 =res.getInt("mt_columns");
				}
				
				
				res = st.executeQuery("SELECT * FROM seat WHERE status='res' AND proboli_proboli_start='"+dateTime+"' AND proboli_mt_name='"+mt+"' ");
				
				String[][] array =new String[count1][count2];
				
				for(int i=0 ; i<count1 ; i++)
				{
					for(int j=0 ; j<count2 ; j++)
					{
						array[i][j]=("\t"+(i+1)+"-"+(j+1)+"\t");
					}
					
				} 
									
						
				while(res.next()) {
					int k1=res.getInt("row");
					int k2=res.getInt("column"); 
					array[k1][k2]=("\t["+(k1+1)+"-"+(k2+1)+"]\t");
				}
	 
						
						for(int i=0 ; i<count1 ; i++)
						{
							for(int j=0 ; j<count2 ; j++)
							{
								System.out.print(array[i][j]);
							}
							System.out.println("");
						} 
						
						
						System.out.println("Tickets number :  ");
						int tNum = (sc.nextInt());
						
						for(int i=0;i<tNum;i++) {
							System.out.println("Select seat for "+(i+1)+"st ticket");
							System.out.println("Select row ");
							int row=sc.nextInt();
							System.out.println("Select column ");
							int column=sc.nextInt();
							row--;
							column--;
							st.executeUpdate("INSERT INTO seat VALUES('"+row+"',"+column+",'res','"+dateTime+"','"+mt+"','"+name+"','"+location+"')");
						}
						
						
						//here2
				
				
				
				System.out.println("type reservation id :  ");
				String id = new String(sc.next());
				
				
				st.executeUpdate("INSERT INTO reservation VALUES('"+id+"',"+tNum+",'not_paid','"+dateTime+"','"+phoneNum+"','"+mt+"','"+name+"','"+location+"')");
				
				
				
						System.out.println("Reservation's information :  \n"); 
					
					System.out.println(id); 
					System.out.println(dateTime);
					System.out.println(location);
					System.out.println(name);
					System.out.println(mt);
					System.out.println(phoneNum);
					System.out.println("tickets number :  "+tNum);
					System.out.println("status : not paid");
					System.out.println( "Tickets price :  "+(ticketPrice*tNum) );
					
					st.executeUpdate("UPDATE proboli SET availability = availability-"+tNum+"  WHERE proboli_start='"+dateTime+"' AND movie_movie_name='"+name+"' AND cinema_cinema_location='"+location+"' ");	
				
				}catch(SQLException s){
					System.out.println("SQL code does not execute.");
				}
		    
				}
		
		if(k==6) {

		    try{
				Statement st = conn.createStatement();
				
				System.out.println("Res id :  ");
				String id = new String(sc.next());
				
				st.executeUpdate("UPDATE reservation SET status ='paid'  WHERE res_id='"+id+"' ");
				
				}catch(SQLException s){
					System.out.println("SQL code does not execute.");
				}
		    
				}
		
		if(k==7) {

		    try{
				Statement st = conn.createStatement();
				
				System.out.println("Movie's name :  ");
				String name = new String(sc.next());
				
				st.executeUpdate("DELETE FROM reservation WHERE proboli_movie_movie_name='"+name+"' ");
				st.executeUpdate("DELETE FROM seat WHERE proboli_movie_name='"+name+"' ");
				st.executeUpdate("DELETE FROM proboli WHERE movie_movie_name='"+name+"' ");
				st.executeUpdate("DELETE FROM movie WHERE movie_name='"+name+"' ");
				
				
				
				}catch(SQLException s){
					System.out.println("SQL code does not execute.");
				}
		    
				}
		
		
    
    System.out.println("\n\n");
    System.out.println(menu);
    System.out.println("Which action would you like to make  ? ");
	k=sc.nextInt();
	
}while(k != 0);

		conn.close();
	
}

}
