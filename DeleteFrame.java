import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//User Interface to Delete Employee Records

class DeleteFrame extends JFrame
{
Container c;
JLabel lblId;
JTextField txtId;
JButton btnSave,btnBack;
JPanel p1,p2;

DeleteFrame()
{
c=getContentPane();

p1=new JPanel();
lblId=new JLabel("Id:");
txtId=new JTextField(5);



p1.add(lblId);
p1.add(txtId);


c.add(p1);

p2=new JPanel();
btnSave=new JButton("Save");
btnBack=new JButton("Back");

p2.add(btnSave);
p2.add(btnBack);

c.add("South",p2);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
String i=txtId.getText();
if(i.length()==0)//Basic Validation
{
JOptionPane.showMessageDialog(new JDialog(),"Id is empty");
txtId.requestFocus();
return;//void function
}

DatabaseHandler d=new DatabaseHandler();
d.deleteEmployee(Integer.parseInt(i));

txtId.setText("");
txtId.requestFocus();
}//end of action Performed()
});//end of btnSave


btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae)
{
MainFrame a=new MainFrame();
dispose();
}
});
setTitle("Delete Employee");
setSize(500,150);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}