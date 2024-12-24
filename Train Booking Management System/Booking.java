import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
/*
<applet  code="Extra1" height=100 width=100>
</applet>
*/

class Frame1 extends Frame implements ActionListener
{
    String msg="";
    Button btnNew,btnSubmit,btnView;
    Label lblName,lblGender,lblCurrentLocation,lblDestination,lblSeats,lblGuj;
    TextField txtName,txtCurrentLocation,txtDestination;
    
    CheckboxGroup ChkGrp;
    Checkbox chkMale,chkFemale;
    Checkbox chkWindowseat,chkAisel;
 
     Frame1(String name)
    {    
        super(name);
        setLayout(new FlowLayout(FlowLayout.LEFT));
	lblGuj=new Label("  ********GUJARAT EXPRESS********      ");
        lblName = new Label("Name: ");
        lblCurrentLocation = new Label("Current Location: ");
        lblDestination = new Label("Destination: ");
       lblGender = new Label("          Gender : ");
	lblSeats = new Label("               Seats:");
        
        txtName = new TextField(20);
        txtCurrentLocation = new TextField(10);
        txtDestination = new TextField(15);
      
        ChkGrp = new CheckboxGroup();
        chkMale = new Checkbox("Male",ChkGrp,false);
        chkFemale = new Checkbox("Female",ChkGrp,false); 
        chkWindowseat = new Checkbox("Window seat");
        chkAisel = new Checkbox("Aisel");
          btnNew = new Button("     NEW");
        btnSubmit = new Button("SUBMIT");
        btnView = new Button("VIEW");
        
        btnNew.addActionListener(this);
        btnSubmit.addActionListener(this);
        btnView.addActionListener(this);
	add(lblGuj);
        add(lblName);
        add(txtName);
        add(lblCurrentLocation);
        add(txtCurrentLocation);
        add(lblDestination);
        add(txtDestination);
       add(lblGender);
        add(chkMale);
        add(chkFemale);
add(lblSeats);
            add(chkWindowseat); 
        add(chkAisel);
       
    
        add(btnNew);
        add(btnSubmit);
        add(btnView);
        
              
    }    
        
    public void actionPerformed(ActionEvent ae)
    {
        String s="";
        boolean b;
        FileInputStream Fin;
        DataInputStream dis;
        FileOutputStream Fout;
        DataOutputStream dos;
        
        try
        {
            Fout = new FileOutputStream("TrainBooking.txt",true);
            dos = new DataOutputStream(Fout);
	
        
            String str = ae.getActionCommand();
            if(str.equals("SUBMIT"))
            {
            
                s=txtName.getText().trim();
dos.writeUTF("Name: ");
                dos.writeUTF(s);


            
                	s=txtCurrentLocation.getText().trim();
dos.writeUTF("Current Location:");
                dos.writeUTF(s);
s=txtDestination.getText().trim();
dos.writeUTF("Deestination");
                dos.writeUTF(s);
dos.writeUTF("Gender:");
                if(chkMale.getState())

                    dos.writeUTF("Male ");
                if(chkFemale.getState())
                    dos.writeUTF("Female ");
dos.writeUTF("Seat : ");
                s="";                    
                if(chkWindowseat.getState())
                    s="Window Seat ";    
                                
                if(chkAisel.getState())
                    s+="Aisel ";                                 
  s+="!";
                dos.writeUTF(s);
                Fout.close();
            } 
            
            if(str.equals("VIEW"))
            {
                String tmp,name,gender,Seats,CurrentLocation,Destination;
                
                Fin = new FileInputStream("Recorded.txt");
                dis = new DataInputStream(Fin);

        
                int i=0,j;
                
                while(Fin.available()>0)
                {
                    name = dis.readUTF();
CurrentLocation = dis.readUTF();
Destination = dis.readUTF();                   
                   
                    gender = dis.readUTF();
Seats=dis.readUTF();

                    if(name.equals(txtName.getText().trim()))
                      {
                        txtCurrentLocation.setText(CurrentLocation+"");   
txtDestination.setText(Destination+"");                  
                       
                        if(gender.equals("Male "))
                            chkMale.setState(true);
                        else
                            chkFemale.setState(true);
                        while(Seats.charAt(i)!='!')
                        {
                            j=Seats.indexOf(' ');
                            tmp = Seats.substring(i,j);
    
                            if(tmp.equals("Window Seat"))
                                chkWindowseat.setState(true);                    

                            if(tmp.equals("Aisel"))
                                chkAisel.setState(true);                    

                            i=j+1;
                        }
                        break;
                    }
                }
                Fin.close();    
            }

            if(str.equals("NEW"))
            {
                txtName.setText("");
                txtCurrentLocation.setText("");
                txtDestination.setText("");
              
                chkMale.setState(false);
                chkFemale.setState(false);
                chkWindowseat.setState(false);                    
                chkAisel.setState(false);                    
            }
        }
        catch(Exception e)
        {
            System.out.println("The Exception Is : " +e);
        }

    }

}

class Booking
{

    public static void main(String args[])
    {
        try{
        Frame1 F = new Frame1("Recorded");
        F.setSize(300,300);
        F.show();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }    

}
