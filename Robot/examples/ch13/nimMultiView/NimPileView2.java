import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import becker.util.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NimPileView2 extends JPanel implements IView
{
   private NimModel model;
   private PileComponent pile1 = new PileComponent(NimModel.MAX_PILESIZE);
   private PileComponent2 pile2 = new PileComponent2(NimModel.MAX_PILESIZE);
   
   public NimPileView2(NimModel aModel)
   {  super();
      this.model = aModel;
      
      this.layoutView();
      this.registerControllers();
      
      this.model.addView(this);
      this.updateView();
   }
   
   private void layoutView()
   {  this.setBorder(BorderFactory.createTitledBorder("Pile Size"));
      this.setLayout(new GridLayout(1, 2));
      this.add(this.pile1);
      this.add(this.pile2);
   }
   
   private void registerControllers()
   {  this.pile2.addActionListener(new PileController());
   }
   
   public void updateView()
   {  this.pile1.setPileSize(this.model.getPileSize());
   	this.pile2.setPileSize(this.model.getPileSize());
   }
   
   private class PileController extends Object implements ActionListener
   {  public void actionPerformed(ActionEvent e)
      {  NimPileView2.this.model.removeTokens(NimPileView2.this.pile2.getNumSelected());
      }
   }
}
