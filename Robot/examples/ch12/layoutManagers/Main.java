

public class Main extends Object
{
   public static void main(String[] args)
   {
      new LayoutFrame("NullLayout", new DemoNullLayout());
      new LayoutFrame("GridBagLayout", new DemoGridBagLayout());
      new LayoutFrame("BorderLayout", new DemoBorderLayout());
      new LayoutFrame("GridLayout", new DemoGridLayout());
      new LayoutFrame("BoxLayout", new DemoBoxLayout());
      new LayoutFrame("Box", new DemoBox());
      new LayoutFrame("FlowLayout", new DemoFlowLayout());
   }
}