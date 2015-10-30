package QuizProgram.GameInfo;

import QuizProgram.KeyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Daniel on 16/12/2014.
 */
public class Questions
{
   private String question;
   private List<String> options,answers;
   private int score=10;

   public Questions(String pQuestion,List<String>pOptions,List<String>pAnswers)
   {
      question=pQuestion;
      options=pOptions;
      answers=pAnswers;
   }

   public Questions()
   {
      options=new ArrayList<String>();
      answers=new ArrayList<String>();
   }

   public int getScore()
   {
      return score;
   }

   public void subtractScore(int pScore)
   {
      score-=pScore;
   }

   public String getQuestion()
   {
      return question;
   }

   public void setQuestion(String pQuestion)
   {
      question = pQuestion;
   }

   public List<String> getOptions()
   {
      return options;
   }

   public void addOptions(String pOption)
   {
      options.add(pOption);
   }

   public List<String> getAnswers()
   {
      return answers;
   }



   public void addAnswers(String answer)
   {
      answers.add(answer);
   }

   public boolean doesListContain(List<String>pContainer,String pCheck)
   {
      return pContainer.contains(pCheck);
   }

   public String toString()
   {
      String allOptions="";
      String allAnswers="";

      for(int count=0;count<answers.size();count++)
      {
         allAnswers=allAnswers + "\n" + answers.get(count);
      }

      for(int count=0;count<options.size();count++)
      {
         allOptions=allOptions + "\n" + options.get(count);
      }
      return KeyValue.QUESTION + "\n" + question + "\n"+KeyValue.ANSWER + allAnswers + "\n"+KeyValue.OPTION + allOptions +"\n" + KeyValue.END ;
   }

   public void shuffleOptions()
   {
      String temp;
      Random rand=new Random();
      int positionOne=0,positionTwo=0;

         for(int count=5;count>0;count--)
         {
            positionOne=rand.nextInt(options.size()-1);
            positionTwo=rand.nextInt(options.size()-1);

            while(positionOne == positionTwo)
            {
               positionOne=rand.nextInt(options.size());
               positionTwo=rand.nextInt(options.size());
            }


            System.out.println(count  + " one" + options.get(positionOne) + ":" + options.get(positionTwo));
            temp=options.get(positionOne);
            options.set(positionOne,options.get(positionTwo));
            options.set(positionTwo,temp);
            System.out.println(count + " two " + options.get(positionOne) + ":" + options.get(positionTwo));



         }

   }
}//class
