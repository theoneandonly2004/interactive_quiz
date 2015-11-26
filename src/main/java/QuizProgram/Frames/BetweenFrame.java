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
 * Created by Daniel on 11/03/2015.
 */
public class BetweenFrame extends JFrame implements ActionListener
{
   private Questions currentQuestion;
   private JButton continueButton;
   private JButton imageContinueButton;
   private JLabel label;
   private JPanel panel;
   private String answerGiven;
   private ArrayList<Questions>questionList;
   private ArrayList<ImageRound>imageRoundList;
   private int currentQuestionNumber;
   private QuestionFrame pastFrame;
   private ImageGameFrame pastImageFrame;
   private ImageRound currentImageRound;


   private void updateScore()
   {
      KeyValue.getToServer().println("update");
      KeyValue.getToServer().println(KeyValue.team.getTeamName());
      KeyValue.getToServer().println(KeyValue.team.getScore());
   }

   private void setLabel(String pAnswer,int setType)
   {
      if(setType==0)
      {
         if(currentQuestion.getAnswers().contains(pAnswer))
         {
        label.setText("congratulations your answer " + pAnswer + " was right for the question \n " + currentQuestion.getQuestion());
        this.setTitle("Congratulations you got it right");
         }
         else
         {
        label.setText("sorry your answer " + pAnswer + " was incorrect for the question \n " + currentQuestion.getQuestion());
        this.setTitle("Incorrect Answer");
         }
      }
      else if(setType==1)
      {
         if(currentImageRound.getAnswers().equalsIgnoreCase(pAnswer))
         {
            label.setText("congratulations your answer " + pAnswer + " was right for the question \n " + currentImageRound.getQuestion());
            this.setTitle("Congratulations you got it right");
         }
         else
         {
            label.setText("sorry your answer " + pAnswer + " was incorrect for the question \n " + currentImageRound.getQuestion());
            this.setTitle("Incorrect Answer");
         }
      }

      }

   public BetweenFrame() throws HeadlessException {
   }

   public BetweenFrame(GraphicsConfiguration gc) {
      super(gc);
   }

   public BetweenFrame(String title, ArrayList<Questions> pQuestion,int pCurrentQuestion,String pAnswerGiven,QuestionFrame pFrame) throws HeadlessException {
      super(title);

      pFrame.setEnabled(false);
      Toolkit tk = Toolkit.getDefaultToolkit();
      int width=tk.getScreenSize().width/3;
      int height=tk.getScreenSize().height/2;
      this.setLocation(width,height);
      this.setResizable(false);
      panel=new JPanel();
      pastFrame=pFrame;
      currentQuestion=pQuestion.get(pCurrentQuestion);
      questionList=pQuestion;
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      continueButton=new JButton("continue");
      label=new JLabel();
      continueButton.addActionListener(this);
      continueButton.setLocation(panel.getWidth()/2,panel.getHeight());
      currentQuestionNumber=pCurrentQuestion;
      setLabel(pAnswerGiven,0);
      this.add(label);
     // this.add(continueButton);
     // this.setLayout(new GridBagLayout());

      panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
      panel.add(label);
      panel.add(continueButton);


      this.add(panel);
      this.pack();
      this.setVisible(true);
   }

   public BetweenFrame(String title, ArrayList<ImageRound> pQuestion,int pCurrentQuestion,String pAnswerGiven,ImageGameFrame pFrame) throws HeadlessException {
      super(title);


      Toolkit tk = Toolkit.getDefaultToolkit();
      int width=tk.getScreenSize().width/3;
      int height=tk.getScreenSize().height/2;
      this.setLocation(width,height);
      this.setResizable(false);
      panel=new JPanel();
      pastImageFrame=pFrame;
      currentImageRound=pQuestion.get(pCurrentQuestion);
      imageRoundList=pQuestion;
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      imageContinueButton=new JButton("continue");
      label=new JLabel();
      imageContinueButton.addActionListener(this);
      imageContinueButton.setLocation(panel.getWidth()/2,panel.getHeight());
      currentQuestionNumber=pCurrentQuestion;
      setLabel(pAnswerGiven,1);
      this.add(label);
      // this.add(continueButton);
      // this.setLayout(new GridBagLayout());

      panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
      panel.add(label);
      panel.add(imageContinueButton);


      this.add(panel);
      this.pack();
      this.setVisible(true);
   }

   public BetweenFrame(String title, GraphicsConfiguration gc) {
      super(title, gc);
   }


   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()==continueButton)
      {
         if(currentQuestionNumber < questionList.size()-1)
               {
                  currentQuestionNumber++;
                  currentQuestion=questionList.get(currentQuestionNumber);
                  QuestionFrame frame = new QuestionFrame(currentQuestion.getQuestion(), questionList, currentQuestionNumber);

                  pastFrame.dispose();
                  this.dispose();
               }
         else
         {
            KeyValue.selectFrame.getTeamInfo().setText("Team " + KeyValue.team.getTeamName() + "\n Score :" + KeyValue.team.getScore());
            KeyValue.selectFrame.setVisible(true);

            this.dispose();
            pastFrame.dispose();
            JOptionPane.showMessageDialog(this,"congrats team " + KeyValue.team.getTeamName() + " you completed the question round \n your current score now is " + KeyValue.team.getScore());
         }
         updateScore();
      }
      else if(e.getSource()==imageContinueButton)
      {
         if(currentQuestionNumber < imageRoundList.size()-1)
         {
            Information.print("image button pressed and accepted");
            currentQuestionNumber++;
            currentImageRound=imageRoundList.get(currentQuestionNumber);
            ImageGameFrame frame = new ImageGameFrame(currentImageRound.getQuestion(), imageRoundList, currentQuestionNumber);
            pastImageFrame.dispose();
            this.dispose();
         }
         else
         {
            KeyValue.selectFrame.getTeamInfo().setText("Team " + KeyValue.team.getTeamName() + "\n Score :" + KeyValue.team.getScore());
            KeyValue.selectFrame.setVisible(true);

            this.dispose();
            pastImageFrame.dispose();
            JOptionPane.showMessageDialog(this,"congrats team " + KeyValue.team.getTeamName() + " you completed the Image round \n your current score now is " + KeyValue.team.getScore());

         }
         updateScore();
      }
   }
}//class
