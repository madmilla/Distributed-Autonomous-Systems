package swingdemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener
{
    private SwingDemo sd;
	
	public MyListener(SwingDemo sd)
	{
		this.sd = sd;
	}
	
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sd.jb1_)
        {
            int x = 0;
            int y = 0;
            x = Integer.parseInt(sd.jtf1_.getText());
            y = Integer.parseInt(sd.jtf2_.getText());
            telop(x, y);
        }
    }

    public void telop(int x, int y)
    {
        sd.jta_.append("" + (x + y) + '\n');
    }

}
