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
 * Created by Daniel on 12/03/2015.
 */
public class ImageGameFrame extends JFrame implements ActionListener
{
   private ArrayList<ImageRound>roundList;
   private ImageRound currentRound;
   private int currentQuestionNumber;
   private JPanel panel;
   private JPanel inputPanel;
   private JTextField textInput;
   private JLabel textlabel;
   private JLabel imageLabel;
   private JLabel currentWordLabel;
   private JButton confirmButton;
   private int score=10;

   public ImageGameFrame() throws HeadlessException
   {

   }

   public ImageGameFrame(String title, ArrayList<ImageRound>pRound,int pCurrentQuestion) throws HeadlessException
   {
      super(title);
      currentQuestionNumber=pCurrentQuestion;
      roundList=pRound;
      currentRound=roundList.get(currentQuestionNumber);
      currentRound.hideWord();

      panel=new JPanel(new GridLayout(2,2));
      inputPanel=new JPanel();
      textInput=new JTextField();
      currentWordLabel=new JLabel();
      textlabel=new JLabel();
      imageLabel=new JLabel();
      confirmButton=new JButton();

      Information.print("starting now");
      textlabel.setText(currentRound.getQuestion());

      System.out.println(currentRound.getHiddenWord());
      currentWordLabel.setText("Current Word: \n" + currentRound.getQuestion());
      Information.print("the question is " + currentRound.getQuestion());
      imageLabel.setIcon(new ImageIcon(currentRound.getFilePath()));
      currentWordLabel.setText(currentRound.getHiddenWord());

      panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
      panel.add(textlabel);
      panel.add(imageLabel);
      panel.add(currentWordLabel);

      confirmButton.addActionListener(this);

      //textInput.setText("hidden word is " +currentRound.getHiddenWord());
      //textInput.setSize(150,20);
     // textInput.setSize(new Dimension(150,20));
      textInput.setPreferredSize(new Dimension(150,20));
      confirmButton.setText("Confirm");

      inputPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
      inputPanel.add(textInput);
      inputPanel.add(confirmButton);

      this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
      this.add(panel);
      this.add(inputPanel);
      this.pack();
      this.setVisible(true);
      this.setResizable(false);
      Information.print("now ended with hidden word " + currentRound.getHiddenWord());
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String answer = textInput.getText();


      if (e.getSource() == confirmButton) {
         if (answer != null)
         {
            if (currentRound.compareWords(answer, currentRound.getAnswers()))
            {
               System.out.println("congrats you got it right");
             //  JOptionPane.showMessageDialog(this,"you got it right and have been awarded " + score + " points");
               //currentQuestionNumber++;
               BetweenFrame frame=new BetweenFrame("congrats you got it right",roundList,currentQuestionNumber,answer,this);
               KeyValue.team.setScore(KeyValue.team.getScore()+score);
               //ImageGameFrame frame=new ImageGameFrame("Image round",roundList,currentQuestionNumber);

            }
            else if (currentRound.findAnswer(answer.charAt(0)))
            {

               currentWordLabel.setText(currentRound.getCorrectList());
               score--;
               JOptionPane.showMessageDialog(this,"you got a letter right in the word");
               textInput.setText("");
            }
            else
            {
               score-=2;
               JOptionPane.showMessageDialog(this, "you guessed completely wrong\n score remaining: " + score);
               textInput.setText("");
            }

            if(score<=0)
            {
               BetweenFrame frame=new BetweenFrame("sorry you got it wrong",roundList,currentQuestionNumber,answer,this);
            }
         }
      }
   }
}//class
