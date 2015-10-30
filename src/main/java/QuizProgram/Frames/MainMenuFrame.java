package QuizProgram.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel on 28/03/2015.
 */
public class MainMenuFrame extends JFrame implements ActionListener
{
   private JButton imageGameButton;
   private JButton questionButton;
   private JButton getScoreButton;
   private JLabel label;
   private JPanel panel;

   public MainMenuFrame() throws HeadlessException {
   }

   public MainMenuFrame(String title) throws HeadlessException
   {
      super(title);
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      panel=new JPanel(new GridLayout(2,2));
      label=new JLabel("please select a game mode");

      questionButton=new JButton("questions");
      imageGameButton=new JButton("Who is this round");
      getScoreButton=new JButton("get score");

      questionButton.addActionListener(this);
      imageGameButton.addActionListener(this);
      getScoreButton.addActionListener(this);

      this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      this.add(label);
      panel.add(questionButton);
      panel.add(imageGameButton);
      this.add(panel);


      this.pack();
      this.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()==questionButton)
      {

      }
      else if(e.getSource()==imageGameButton)
      {

      }

   }
}//class
