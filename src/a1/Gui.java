package a1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Gui<K,V> extends JFrame implements ActionListener,ItemListener{

	JRadioButtonMenuItem rbMnuItmSortedDictionary;
	JRadioButtonMenuItem rbMnuItmHashDictionary;
	JRadioButtonMenuItem rbMnuItmTreeDictionary;
	
	JRadioButtonMenuItem rbMnuItmMapDictionaryTree;
	JRadioButtonMenuItem rbMnuItmMapDictionaryHash;
	
	@SuppressWarnings("unused")
	private Dictionary<K,V> myDictionary;
	
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
		JMenu dictMapMenu = new JMenu("DictionaryMap");
				
		
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
		
		menu.add(dictMapMenu);
		
		rbMnuItmMapDictionaryTree = new JRadioButtonMenuItem("MapDictionaryTree");
		rbMnuItmMapDictionaryTree.addActionListener(this);
		group.add(rbMnuItmMapDictionaryTree);
		dictMapMenu.add(rbMnuItmMapDictionaryTree);
		
		rbMnuItmMapDictionaryHash = new JRadioButtonMenuItem("MapDictionaryHash");
		rbMnuItmMapDictionaryHash.addActionListener(this);
		group.add(rbMnuItmMapDictionaryHash);
		dictMapMenu.add(rbMnuItmMapDictionaryHash);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		add(pnl);
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Dictionary<K,V> newDict = null;
		
		if(arg0.getSource() == rbMnuItmHashDictionary)
		{
			
		}
		else if(arg0.getSource() == rbMnuItmMapDictionaryHash)
		{
			newDict = new MapDictionary<K, V>(new java.util.HashMap<K, V>());
		}
		else if(arg0.getSource() == rbMnuItmMapDictionaryTree)
		{
			newDict = new MapDictionary<K, V>(new java.util.TreeMap<K, V>());
		}
		else if(arg0.getSource() == rbMnuItmSortedDictionary)
		{
			newDict = (Dictionary<K, V>) new SortedArrayDictionary<K,V>();
		}
		else if(arg0.getSource() == rbMnuItmTreeDictionary)
		{
			
		}
		
		if(newDict != null)
		{
			//foreach()
		}
			
		System.out.println("Action performed: " + arg0 );
	}
	
	
}
