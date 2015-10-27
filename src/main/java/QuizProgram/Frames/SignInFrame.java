package QuizProgram.Frames;

import QuizProgram.Profiles.UserProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 27/02/2015.
 */
public class SignInFrame extends JFrame implements ActionListener
{
   private UserProfile profile;
   private JTextField inputName;
   private JButton button;
   private String userNameInput;
   private JPasswordField password;

   public SignInFrame() throws HeadlessException {
   }

   public SignInFrame(GraphicsConfiguration gc) {
      super(gc);
   }

   public SignInFrame(String title) throws HeadlessException
   {

      super(title);

      this.setLayout(new FlowLayout());
      inputName=new JTextField();
      password=new JPasswordField();
      inputName.setLocation(100,100);
      password.setLocation(100,80);

      inputName.setSize(50, 20);

      password.setSize(50,20);
      add(inputName);
      button=new JButton();

      button.setLocation(100,60);
      add(password);
      button.addActionListener(this);
      add(button);
      this.pack();
      this.setVisible(true);

   }

   public SignInFrame(String title, GraphicsConfiguration gc) {
      super(title, gc);

   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()==button)
      {
         if(inputName.getText()!= null)
         {
            userNameInput=inputName.getText();
            System.out.println(userNameInput);
         }
      }

   }
}//class
