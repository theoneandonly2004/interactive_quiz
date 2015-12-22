package QuizProgram.Frames;

import QuizProgram.GameInfo.Questions;
import QuizProgram.KeyValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Daniel on 10/03/2015.
 */
public class QuestionFrame extends JFrame implements ActionListener
{
   private String[]questions;
   private JButton[]buttons;
   private Questions questionInfo;
   private JLabel questionLabel;
   private ArrayList<Questions> questionList;
   private int currentQuestionNumber=0;


   private void newQuestion(ArrayList<Questions>pQuestions)
   {
      Random rand=new Random();
      questionInfo=pQuestions.get(rand.nextInt(pQuestions.size()-1));
      questions=questionInfo.getOptions().toArray(new String[questionInfo.getOptions().size()]);
      for(int count=0;count<buttons.length;count++) {
         this.remove(buttons[count]);
      }

      addButton(questions, null);

   }


   private void newQuestion(ArrayList<Questions>pQuestions,int pSelected)
   {

   }


   private void setButtonStatus(boolean isEnabled){
      for(JButton button : buttons)
      {
         button.setEnabled(isEnabled);
      }
   }

   private void addButton(String[]pArray,JPanel pPanel)
   {
      int size=pArray.length;
      buttons=new JButton[size];

      for(int count=0;count<size;count++)
      {
         buttons[count]=new JButton(pArray[count]);
         buttons[count].addActionListener(this);
         if(pPanel != null)
         {
            pPanel.add(buttons[count]);
         }
         else
         {
            this.add(buttons[count]);
         }
      }
   }

   public QuestionFrame() throws HeadlessException {
   }

   public QuestionFrame(GraphicsConfiguration gc) {
      super(gc);
   }

   public QuestionFrame(String title, ArrayList<Questions> pQuestion,int pCurrentQuestion) throws HeadlessException
   {
      super(title);
      Toolkit tk = Toolkit.getDefaultToolkit();
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      currentQuestionNumber=pCurrentQuestion;
      questionList=pQuestion;
      JPanel panel=new JPanel(new GridLayout(2,2));
      JPanel panelTwo=new JPanel(new GridLayout());
      questionLabel=new JLabel("text",SwingConstants.CENTER);

      questionInfo=pQuestion.get(currentQuestionNumber);
      questionInfo.shuffleOptions();
      questions=questionInfo.getOptions().toArray(new String[questionInfo.getOptions().size()]);
      questionLabel.setText(questionInfo.getQuestion());

      this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
      panelTwo.add(questionLabel);
      panel.setLocation(SwingConstants.CENTER,SwingConstants.TOP);
      this.add(panelTwo,BorderLayout.NORTH);
      this.add(panel,BorderLayout.CENTER);
      addButton(questions, panel);
      //this.add(panel);
      this.pack();
      this.setVisible(true);

   }

   public QuestionFrame(String title, GraphicsConfiguration gc) {
      super(title, gc);
   }


   public void actionPerformed(ActionEvent e)
   {
      boolean isFound=false;
      int count=0;

      while(count < questions.length && !isFound)
      {
         if(e.getSource()==buttons[count])
         {
           ArrayList<String> answers=(ArrayList)questionInfo.getAnswers();
            BetweenFrame frame=new BetweenFrame(questionInfo.getQuestion(), questionList, currentQuestionNumber,buttons[count].getText(),this);
            KeyValue.team.setScore(KeyValue.team.getScore()+10);



         }
         count++;
      }

   }
}//class
