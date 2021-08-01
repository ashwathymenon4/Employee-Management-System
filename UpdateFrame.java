import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// User interface to update employee records

class UpdateFrame extends JFrame
{
Container c;
JLabel lblId,lblName;
JTextField txtId,txtName;
JButton btnSave,btnBack;
JPanel p1,p2;

UpdateFrame()
{
c=getContentPane();
p1=new JPanel();
lblId=new JLabel("Id:");
txtId=new JTextField(5);
lblName=new JLabel("Name:");
txtName=new JTextField(10);

p1.add(lblId);
p1.add(txtId);
p1.add(lblName);
p1.add(txtName);

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
String n=txtName.getText();
if(i.length()==0)//Basic Validation
{
JOptionPane.showMessageDialog(new JDialog(),"Id is empty");
txtId.requestFocus();
return;//void function
}
if(n.length()==0)
{
JOptionPane.showMessageDialog(new JDialog(),"Name is empty");
txtName.requestFocus();
return;
}

DatabaseHandler d=new DatabaseHandler();
d.updateEmployee(Integer.parseInt(i),n);

txtId.setText("");
txtName.setText("");
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
setTitle("Update Employee");
setSize(500,150);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}

}