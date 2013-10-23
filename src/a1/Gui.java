package a1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener,ItemListener{

	JRadioButtonMenuItem rbMnuItmSortedDictionary;
	JRadioButtonMenuItem rbMnuItmHashDictionary;
	JRadioButtonMenuItem rbMnuItmTreeDictionary;
	JRadioButtonMenuItem rbMnuItmMapDictionary;
	
	@SuppressWarnings("unused")
	private Dictionary<String,String> myDictionary;
	
 	public Gui()
	{
		super();
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel pnl = new JPanel(new GridLayout(4,4));
		
		/*MenuBar einrichten*/
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Einstellungen");
				
		
		//buttonGruppe Dictionarys
		ButtonGroup group = new ButtonGroup();
		
		rbMnuItmSortedDictionary = new JRadioButtonMenuItem("SortedArray");
		rbMnuItmSortedDictionary.setSelected(true);
		rbMnuItmSortedDictionary.addActionListener(this);
		group.add(rbMnuItmSortedDictionary);
		menu.add(rbMnuItmSortedDictionary);

		rbMnuItmHashDictionary = new JRadioButtonMenuItem("HashDictionary");
		rbMnuItmHashDictionary.addActionListener(this);
		group.add(rbMnuItmHashDictionary);
		menu.add(rbMnuItmHashDictionary);
		
		rbMnuItmTreeDictionary = new JRadioButtonMenuItem("TreeDictionary");
		rbMnuItmTreeDictionary.addActionListener(this);
		group.add(rbMnuItmTreeDictionary);
		menu.add(rbMnuItmTreeDictionary);
		
		rbMnuItmMapDictionary = new JRadioButtonMenuItem("MapDictionary");
		rbMnuItmMapDictionary.addActionListener(this);
		group.add(rbMnuItmMapDictionary);
		menu.add(rbMnuItmMapDictionary);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		add(pnl);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Gui();
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Action performed: " + arg0 );
	}

}
