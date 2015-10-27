package QuizProgram.Frames;

import QuizProgram.GameInfo.ImageRound;
import QuizProgram.Information;
import QuizProgram.KeyValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Daniel on 11/03/2015.
 */
public class PictureGameSelect extends JFrame implements ActionListener
{
   private JButton button=new JButton("open file");
   private JButton gettextButton=new JButton("get text");
   private String filePath;
   private File imageFile;
   private int selector;
   private JTextField textField=new JTextField();
   private JFileChooser chooser=new JFileChooser("src//QuizProgram//Resources//Images");
   private JLabel label=new JLabel();
   private JPanel panel=new JPanel();
   private ImageIcon icon;
   private ArrayList<ImageRound>roundlist;

   public PictureGameSelect(String title,ArrayList<ImageRound>pRoundList,int pRoundNumber) throws HeadlessException
   {
      super(title);
      roundlist=pRoundList;
      gettextButton.addActionListener(this);
      button.addActionListener(this);
      this.setLayout(new FlowLayout());
      //this.add(button);
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
     // filePath=roundList.get(pRoundNumber).getFilePath();
    //  label.setIcon(new ImageIcon(filePath));
      panel.add(button);
      panel.add(gettextButton);
      panel.add(label);
      panel.add(textField);


      this.add(panel);
      //this.add(label);
      this.setVisible(true);
      this.pack();

   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()==button) {
         selector = chooser.showOpenDialog(this);


         if (selector == JFileChooser.APPROVE_OPTION) {
            imageFile = chooser.getSelectedFile();
            filePath = imageFile.getPath();
            label.setIcon(new ImageIcon(filePath));
            String answer=textField.getText();



           // Information.printToFile(roundlist, KeyValue.RESOURCEPATH + "ImageInfo");
           // System.out.println(roundlist.get(0).findLettersInWord('o'));
            this.pack();

         }
      }
      else if(e.getSource()==gettextButton)
      {
         String answer=textField.getText();
         ImageRound round=new ImageRound(filePath,answer,"who is this");
          roundlist.add(round);
         textField.setText("");
         label.setIcon(null);
         Information.printToFile(roundlist,KeyValue.RESOURCEPATH + "ImageInfo");
         this.pack();


      }

   }
}//class
