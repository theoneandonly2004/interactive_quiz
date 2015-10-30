package QuizProgram.Frames;

/**
 * Created by Daniel on 19/03/2015.
 */
public class Team
{
   private String teamName;
   private int score;
   public Team(String pName, int pScore)
   {
      score=pScore;
      teamName=pName;
   }

   public Team(String pName)
   {
      teamName=pName;
      score=0;
   }

   public Team()
   {
      teamName="no name";
      score=0;
   }

   public String getTeamName() {
      return teamName;
   }

   public int getScore() {
      return score;
   }

   public void setTeamName(String teamName) {
      this.teamName = teamName;
   }

   public void setScore(int score) {
      this.score = score;
   }

   @Override
   public String toString()
   {
      return "TEAMNAME:" + teamName +"\n SCORE:"+ score + "\nEND";
   }
}//class
