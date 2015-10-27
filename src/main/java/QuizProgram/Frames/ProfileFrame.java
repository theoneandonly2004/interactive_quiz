package QuizProgram.Frames;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Daniel on 14/01/2015.
 */
public class ProfileFrame extends JFrame
{
   private JLabel profilePicture;
   private JFileChooser fileChoser;
   private File chosenFile;
   private JButton button;
   private ImageIcon userImage;
   private int returnval=-1;

   public ProfileFrame(String title) throws HeadlessException
   {
      super(title);

      button=new JButton("open file");
      add(button);
      this.setSize(500, 500);
      this.setVisible(true);
      this.setLayout(new FlowLayout());
      profilePicture=new JLabel();
      fileChoser=new JFileChooser();
      fileChoser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));



      add(profilePicture);
      button.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button)
            {
               returnval=fileChoser.showOpenDialog(getParent());
            }

            if(returnval==JFileChooser.APPROVE_OPTION)
            {
               chosenFile=fileChoser.getSelectedFile();
               userImage.setImage(new ImageIcon(chosenFile.getPath()).getImage());
               profilePicture.setIcon(userImage);
               //remove(profilePicture);
               add(profilePicture);
            }
         }
      });










   }
}//class
