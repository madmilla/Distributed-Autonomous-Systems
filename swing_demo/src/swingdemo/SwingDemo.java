package swingdemo;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SwingDemo extends JFrame 
{
    private static final long serialVersionUID = 1L;
    JTextField jtf1_;
    JTextField jtf2_;
    JTextArea jta_;
    JButton jb1_;

    public SwingDemo()
    {
        jta_ = new JTextArea(7, 40);
        jta_.setFont(new Font("Courier", Font.PLAIN, 12));
        jta_.setBackground(new Color(255, 255, 200));
        jta_.setForeground(new Color(64, 32, 0));

        JScrollPane jsp = new JScrollPane(jta_,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel jp = new JPanel();
        GridLayout gl = new GridLayout(3, 2);
        jp.setLayout(gl);

        JLabel jl1 = new JLabel("x: ");
        jl1.setHorizontalAlignment(SwingConstants.RIGHT);
        jtf1_ = new JTextField(15);
        jtf1_.setFont(new Font("Courier", Font.PLAIN, 12));
        jtf1_.setText("0");
        JLabel jl2 = new JLabel("y: ");
        jl2.setHorizontalAlignment(SwingConstants.RIGHT);
        jtf2_ = new JTextField(15);
        jtf2_.setFont(new Font("Courier", Font.PLAIN, 12));
        jtf2_.setText("0");
        jb1_ = new JButton("+");
        jb1_.addActionListener(new MyListener(this));
        jp.add(jl1);
        jp.add(jtf1_);
        jp.add(jl2);
        jp.add(jtf2_);

        jp.add(new JLabel());
        jp.add(jb1_);
        add("Center", jsp);
        add("South", jp);

        setTitle(getClass().getName());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height
                / 2 - getHeight() / 2);
        
        setVisible(true);
    }

    public static void main(String[] args)
    {
        // JFrame.setDefaultLookAndFeelDecorated(true);
        SwingDemo wcs = new SwingDemo();
        wcs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
