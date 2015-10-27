package QuizProgram.Frames;

import QuizProgram.GameInfo.ImageRound;
import QuizProgram.GameInfo.Questions;
import QuizProgram.Information;
import QuizProgram.KeyValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Daniel on 01/05/2015.
 */
public class GameSelectFrame extends JFrame implements ActionListener
{
   private JPanel buttonPanel;
   private JButton quizButton,pictureGameButton,getScoreButton;
   private JLabel infoText;

   private JLabel teamInfo;


   private ArrayList<Questions>questionsList;
   private ArrayList<ImageRound>imagesList;

   public JLabel getTeamInfo()
   {
      return teamInfo;
   }

   public GameSelectFrame() throws HeadlessException {
   }

   public GameSelectFrame(String title,ArrayList<Questions>pQuestions,ArrayList<ImageRound>pImages) throws HeadlessException
   {
      super(title);


      if(KeyValue.team==null) {
         String teamName = JOptionPane.showInputDialog(null, "please enter your username");
         KeyValue.team = new Team(teamName);
         KeyValue.getToServer().println("add");
         KeyValue.getToServer().println(teamName);
      }

      getScoreButton=new JButton("get scores");
      getScoreButton.addActionListener(this);
      KeyValue.selectFrame=this;

      questionsList=pQuestions;
      imagesList=pImages;


      teamInfo=new JLabel();
      teamInfo.setText("Team " + KeyValue.team.getTeamName() + "\n Score :" + KeyValue.team.getScore());
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      this.setExtendedState(JFrame.MAXIMIZED_BOTH);

      buttonPanel=new JPanel();
      buttonPanel.setLayout(new GridLayout(1,1));

      infoText=new JLabel("please select which game mode you wish to play");

      quizButton=new JButton("Main Quiz");
      quizButton.addActionListener(this);

      pictureGameButton=new JButton("Image Round");
      pictureGameButton.addActionListener(this);

      buttonPanel.add(quizButton);
      buttonPanel.add(pictureGameButton);

      this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
      this.add(infoText);
      this.add(teamInfo);
      this.add(buttonPanel);
      this.add(getScoreButton);

      this.pack();
      this.setVisible(true);
   }


   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource().equals(quizButton))
      {
         String title=questionsList.get(0).getQuestion();
         QuestionFrame frame=new QuestionFrame(title,questionsList,0);
         //this.dispose();
         this.setVisible(false);
         //System.out.println("quiz button pressed");
      }
      else if(e.getSource().equals(pictureGameButton))
      {
         ImageGameFrame frame=new ImageGameFrame("who is this",imagesList,0);
         //this.dispose();
         this.setVisible(false);
         //System.out.println("image game button pressed");
      }
      else if(e.getSource().equals(getScoreButton))
      {
         if(KeyValue.getSocket().isConnected())
         {
            new PointsDisplayFrame("team points");
         }
         else
         {
            JOptionPane.showMessageDialog(null,"sorry you need to be connected \n to the server to do that");
         }
      }
   }
}//class
