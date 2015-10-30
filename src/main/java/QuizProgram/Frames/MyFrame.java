package QuizProgram.Frames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 11/01/2015.
 */
public class MyFrame extends JFrame
{
   private JMenu menu;
   private JMenuBar bar;
   private JMenuItem itemOne;


   public MyFrame() throws HeadlessException {
      super();

   }

   public MyFrame(GraphicsConfiguration gc) {
      super(gc);
   }

   public MyFrame(String title) throws HeadlessException {
      super(title);
      this.setSize(200, 200);

      menu=new JMenu("stuff");
      bar=new JMenuBar();
      itemOne=new JMenuItem("menu item 2");
      bar.add(menu);
      bar.setVisible(true);
      bar.getMenu(0);
      add(bar);
      this.setLayout(new FlowLayout());
      this.setVisible(true);
      this.setJMenuBar(bar);
      bar.add(menu);
      menu.add(itemOne);


      this.pack();

      //add(menu);
   }

   public MyFrame(String title, GraphicsConfiguration gc) {
      super(title, gc);
   }
}//class
