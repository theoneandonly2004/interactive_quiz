package QuizProgram;

import java.io.File;

/**
 * Created by Daniel on 13/01/2015.
 */
public class GameMode
{
   private String modeName;
   private File infoFile;
   private int maxGameScore;
   private int scorePerAnswer;

   public GameMode(String modeName, File infoFile, int maxGameScore, int scorePerAnswer)
   {
      this.modeName = modeName;
      this.infoFile = infoFile;
      this.maxGameScore = maxGameScore;
      this.scorePerAnswer = scorePerAnswer;
   }

   public String getModeName() {
      return modeName;
   }

   public void setModeName(String modeName) {
      this.modeName = modeName;
   }

   public File getInfoFile() {
      return infoFile;
   }

   public void setInfoFile(File infoFile) {
      this.infoFile = infoFile;
   }

   public int getMaxGameScore() {
      return maxGameScore;
   }

   public void setMaxGameScore(int maxGameScore) {
      this.maxGameScore = maxGameScore;
   }

   public int getScorePerAnswer() {
      return scorePerAnswer;
   }

   public void setScorePerAnswer(int scorePerAnswer) {
      this.scorePerAnswer = scorePerAnswer;
   }
}//class
