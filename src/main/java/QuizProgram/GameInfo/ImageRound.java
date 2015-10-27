package QuizProgram.GameInfo;

import QuizProgram.Information;
import QuizProgram.KeyValue;

/**
 * Created by Daniel on 11/03/2015.
 */
public class ImageRound
{
   int A='A',a='a',z='z',Z='Z';
   String filePath;
   String answers;
   String currentWord;
   String hiddenWord;
   String question;
   String correctList="";

   //hangman type game. Letters are given as * then with each guess they will be added to the word

   public void setQuestion(String question) {
      this.question = question;
   }

   public ImageRound(String pFilePath,String pAnswer,String pQuestion)
   {
      filePath=pFilePath;
      answers=pAnswer;

      if(pQuestion != null) {
         question = pQuestion;
      }
      else
      {
         question="who is this?";
      }






     hiddenWord=hideWord();
   }

   public ImageRound()
   {

   }

   public String getCorrectList() {
      return correctList;
   }

   public String getQuestion() {
      return question;
   }

   public boolean compareWords(String pStringOne, String pStringTwo)
   {
      return pStringOne.equalsIgnoreCase(pStringTwo);
   }


   public boolean doesWordContain(String pWord,char pChar)
   {
      for(int count=0;count<pWord.length();count++)
      {
         if(pWord.charAt(count)==pChar)
         {
            return false;
         }
      }
      return true;
   }

   public String hideWord()
   {
      hiddenWord="";

      for(int count=0;count<answers.length();count++)
      {
         if(answers.charAt(count)!= 32)
         {
            hiddenWord = hiddenWord + '*';
         }
      }

      return hiddenWord;
   }

   public boolean findAnswer(char pLetter) {
      char currentPosition;
      boolean isUpper = Character.isUpperCase(pLetter);
      char bound;
      boolean isCharFound=doesWordContain(correctList,pLetter);


         for (int count = 0; count < answers.length(); count++)
         {
            currentPosition = answers.charAt(count);

            if (isUpper)
            {
               bound = Character.toLowerCase(currentPosition);
            }
            else
            {
               bound = Character.toUpperCase(currentPosition);
            }



            if (currentPosition == pLetter || bound == Character.toUpperCase(pLetter))
            {
               if(isCharFound) {
                  correctList = correctList + "," + pLetter;
               }
               return true;
            }
            else if (currentPosition == pLetter || bound == Character.toLowerCase(pLetter))
            {
               if(isCharFound) {
                  correctList = correctList + "," + pLetter;
               }
               return true;
            }

         }
         return false;
      }






   public String getFilePath()
   {
      return filePath;
   }

   public void setFilePath(String filePath)
   {
      this.filePath = filePath;
   }

   public String getAnswers() {
      return answers;
   }

   public void setAnswers(String pAnswer)
   {
      answers=pAnswer;
   }

   public String getHiddenWord() {
      return hiddenWord;
   }

   public String toString()
   {
      return KeyValue.FILEPATH + "\n" + filePath + "\n"+KeyValue.ANSWER +"\n"+ answers+"\n" +KeyValue.QUESTION+"\n"+question+"\n"+ KeyValue.END ;
   }
}//class
