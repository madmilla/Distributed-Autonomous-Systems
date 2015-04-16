package mysqlite;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DatabaseSwingDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField hostField;
	JTextField queryField;
	QueryTableModel qtm;

	public DatabaseSwingDemo() {
		super("Database Test Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350, 200);

		qtm = new QueryTableModel();
		JTable table = new JTable(qtm);
		JScrollPane scrollpane = new JScrollPane(table);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 2));
		p1.add(new JLabel("Enter the Host URL: "));
		p1.add(hostField = new JTextField());
		hostField.setText("jdbc:sqlite:d:/sqlite/database/sportvereniging.sqlite");
		p1.add(new JLabel("Enter your query: "));
		p1.add(queryField = new JTextField());
		p1.add(new JLabel("Click here to send: "));

		JButton jb = new JButton("Search");
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtm.setHostURL(hostField.getText().trim());
				qtm.setQuery(queryField.getText().trim());
			}
		});
		p1.add(jb);
		getContentPane().add(p1, BorderLayout.NORTH);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		DatabaseSwingDemo dsd = new DatabaseSwingDemo();
		dsd.setVisible(true);
	}
}