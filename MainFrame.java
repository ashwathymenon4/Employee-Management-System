import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;//import for drivermanager statement - prepared statement


class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;

MainFrame()
{
c=getContentPane();
c.setLayout(new FlowLayout());
btnAdd=new JButton("Add");
btnView=new JButton("View");
btnUpdate=new JButton("Update");
btnDelete=new JButton("Delete");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

// Add Action Listeners for Add, Delete, Update and View Buttons

btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
AddFrame a=new AddFrame();
dispose();
}
});
btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
ViewFrame a=new ViewFrame();
dispose();
}
});
btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
UpdateFrame a=new UpdateFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
DeleteFrame a=new DeleteFrame();
dispose();
}
});

setSize(500,150);
setLocationRelativeTo(null);
setTitle("Employee Management System");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}//end of constructor
public static void main(String args[])
{
MainFrame m=new MainFrame();	
}//end of main

}//end of class

// DatabaseHandler defines connection parameters to Oracle Database
class DatabaseHandler
{
static Connection con;//static because connection happens only once
static void getCon()
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());//throws SQLException
con=DriverManager.getConnection("API:DB:DRIVER:@SERVERNAME:PORT_NUMBER:SERVICENAME","username","password"); // Replace the parameters of getConnection function with applicable values.
}
catch(SQLException e)
{
System.out.println(e);
}
}

//Define Add, Delete, Update and View functions

public void addEmployee(int id,String name)
{
getCon();//to get connection
try
{
String sql="insert into employee values(?,?)";//? inside values() because input is dynamic
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,id);
pst.setString(2,name);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+" records inserted");
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog()," insert issue");
}
}//end of add employee

public String viewEmployee()
{
getCon();
StringBuffer sb=new StringBuffer();
try
{
Statement s1=con.createStatement();
String s2="select * from employee order by eid";
ResultSet rs=s1.executeQuery(s2);
while(rs.next())//next fetches the record
{
int id=rs.getInt(1);//java and oracle have different data types so conversion to int is required
String name=rs.getString(2);
sb.append("Id: "+id+" Name: "+name + "\n");
}
}
catch(SQLException e){}
return sb.toString();
}//end of viewEmployee

public void updateEmployee(int id,String name)
{
getCon();
try
{
String sql="update employee set ename=? where eid=?";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(2,id);
pst.setString(1,name);
int r=pst.executeUpdate();
if(r==0)
{
JOptionPane.showMessageDialog(new JDialog()," Invalid ID");
}
else
{
JOptionPane.showMessageDialog(new JDialog(),r+" records updated");
}
}
catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog()," update issue");}
}//end of update function


public void deleteEmployee(int id)
{
getCon();
try
{
String sql="delete from employee where eid=?";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,id);
int r=pst.executeUpdate();
if(r==0)
{
JOptionPane.showMessageDialog(new JDialog(),"  Invalid ID ");
}
else
{
JOptionPane.showMessageDialog(new JDialog(),r+"  records deleted");
}
}
catch(SQLException e)
{ }
}//end of delete employee
}//end of class