package QuizProgram.Profiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by Daniel on 13/01/2015.
 */
public class UserProfile
{
   protected String password;
   protected String userName;
   private File userFile;
   private int score=0;
   private int coins=0;

   public UserProfile(String pPassword, String pUserName)
   {
      password = pPassword;
      userName = pUserName;
      userFile=new File(pUserName+".txt");
   }



   protected String getPassword()
   {
      return password;
   }

   protected String getUserName()
   {
      return userName;
   }

   public boolean checkUserName(String pUserName)
   {
      if(pUserName.equals(getUserName()))
      {
         return true;
      }
      return false;
   }

   public boolean checkPassword(String pPassword)
   {
      if(pPassword.equals(getPassword()))
      {
         return true;
      }
      return false;
   }

   public void saveInfo()
   {
      PrintWriter writer;
      boolean isOpen=false;

      try
      {
         writer=new PrintWriter(userFile);
         isOpen=true;
      }
      catch(Exception ex)
      {
         System.out.println("there was an error");
         System.out.println(ex.getMessage());
         isOpen=false;
         writer=null;
      }

      try
      {
         writer.write(toString());
         writer.close();
      }
      catch(NullPointerException ex)
      {
         System.out.println("there was a null pointer with the writer");
         System.out.println(ex.getMessage());
      }
      catch (Exception ex)
      {
         System.out.println("there was an error");
         System.out.println(ex.getMessage());
      }

   }

   public String toString()
   {
      return("Password\n"+ password + "\n Username\n" + userName + "Score\n" + score + "\nCoins\n" + coins);
   }


}//class
