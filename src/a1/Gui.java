package a1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.*;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener,ItemListener{

	JRadioButtonMenuItem rbMnuItmSortedDictionary;
	JRadioButtonMenuItem rbMnuItmHashDictionary;
	JRadioButtonMenuItem rbMnuItmTreeDictionary;
	
	JRadioButtonMenuItem rbMnuItmMapDictionaryTree;
	JRadioButtonMenuItem rbMnuItmMapDictionaryHash;
	JMenuItem menuFileLoad; 
	
	JFileChooser fc = new JFileChooser();
	
	JTextField txtGerman;
	JTextField txtEnglish;
	JLabel lblGerman;
	JLabel lblEnglish;
	JLabel lblGermanResult;
	JLabel lblEnglishResult;
	JButton btnInsert;
	JButton btnSearch;
	
	private Dictionary<String,String> myDictionary;
	
	public Gui()
	{
		super();
		
		myDictionary = new SortedArrayDictionary<String,String>(); 
		
		
		initMenu();
		initControls();
		
		
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void initControls()
	{
		JPanel pnl = new JPanel(null);
		txtGerman = new JTextField(20);
		txtEnglish = new JTextField(20);
		lblGerman = new JLabel("Deutsch: ");
		lblEnglish = new JLabel("Englisch: ");
		btnInsert = new JButton("Hinzufügen");
		btnSearch = new JButton("Suchen");
		
		lblGermanResult = new JLabel("");
		lblEnglishResult = new JLabel("");
		
		pnl.add(lblGerman);
		pnl.add(txtGerman);
		pnl.add(lblEnglish);
		pnl.add(txtEnglish);
		pnl.add(btnInsert);
		pnl.add(btnSearch);
		pnl.add(lblGermanResult);
		pnl.add(lblEnglishResult);
		
		lblGerman.setLocation(12,12);
		lblEnglish.setLocation(12,42);
		txtGerman.setLocation(100,12);
		txtEnglish.setLocation(100,42);
		lblGermanResult.setLocation(12,120);
		lblEnglishResult.setLocation(100,120);
		
		lblGerman.setSize(80, 22);
		lblEnglish.setSize(80, 22);
		txtGerman.setSize(280, 22);
		txtEnglish.setSize(280, 22);
		lblGermanResult.setSize(80, 22);
		lblEnglishResult.setSize(80, 22);
		
		btnInsert.setLocation(60,72);
		btnInsert.setSize(150, 36);
		btnInsert.addActionListener(this);
		
		btnSearch.setLocation(230,72);
		btnSearch.setSize(150, 36);
		btnSearch.addActionListener(this);
		
		this.add(pnl);	
	}
	
	public void initMenu()
	{
		/*MenuBar einrichten*/
		JMenuBar menuBar = new JMenuBar();
		JMenu menuSettings = new JMenu("Einstellungen");
		JMenu dictMapMenu = new JMenu("DictionaryMap");
		JMenu menuFile = new JMenu("Datei");
		
		
		menuFileLoad = new JMenuItem("Datei laden");
		menuFileLoad.addActionListener(this);
		
		//buttonGruppe Dictionarys
		ButtonGroup group = new ButtonGroup();
		
		rbMnuItmSortedDictionary = new JRadioButtonMenuItem("SortedArray");
		rbMnuItmSortedDictionary.setSelected(true);
		rbMnuItmSortedDictionary.addActionListener(this);
		group.add(rbMnuItmSortedDictionary);
		menuSettings.add(rbMnuItmSortedDictionary);

		rbMnuItmHashDictionary = new JRadioButtonMenuItem("HashDictionary");
		rbMnuItmHashDictionary.addActionListener(this);
		group.add(rbMnuItmHashDictionary);
		menuSettings.add(rbMnuItmHashDictionary);
		
		rbMnuItmTreeDictionary = new JRadioButtonMenuItem("TreeDictionary");
		rbMnuItmTreeDictionary.addActionListener(this);
		group.add(rbMnuItmTreeDictionary);
		menuSettings.add(rbMnuItmTreeDictionary);
		
		menuSettings.add(dictMapMenu);
		
		rbMnuItmMapDictionaryTree = new JRadioButtonMenuItem("MapDictionaryTree");
		rbMnuItmMapDictionaryTree.addActionListener(this);
		group.add(rbMnuItmMapDictionaryTree);
		dictMapMenu.add(rbMnuItmMapDictionaryTree);
		
		rbMnuItmMapDictionaryHash = new JRadioButtonMenuItem("MapDictionaryHash");
		rbMnuItmMapDictionaryHash.addActionListener(this);
		group.add(rbMnuItmMapDictionaryHash);
		dictMapMenu.add(rbMnuItmMapDictionaryHash);
		
		menuFile.add(menuFileLoad);
		menuBar.add(menuFile);
		
		menuBar.add(menuSettings);
		setJMenuBar(menuBar);
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	
		if(arg0.getSource() == rbMnuItmHashDictionary)
		{
			
		}
		else if(arg0.getSource() == rbMnuItmMapDictionaryHash)
		{
			myDictionary = new MapDictionary<String,String>(new java.util.HashMap<String,String>());
		}
		else if(arg0.getSource() == rbMnuItmMapDictionaryTree)
		{
			myDictionary = new MapDictionary<String,String>(new java.util.TreeMap<String,String>());
		}
		else if(arg0.getSource() == rbMnuItmSortedDictionary)
		{
			myDictionary = new SortedArrayDictionary<String,String>();
		}
		else if(arg0.getSource() == rbMnuItmTreeDictionary)
		{
			
		}
		else if(arg0.getSource() == menuFileLoad)
		{
			loadFile();
		}
		else if(arg0.getSource() == btnInsert)
		{
			myDictionary.insert(txtGerman.getText(), txtEnglish.getText());
		}
		else if(arg0.getSource() == btnSearch)
		{
			String Result = myDictionary.search(txtGerman.getText());
			lblGermanResult.setText(txtGerman.getText());
			if(Result == null)
				lblEnglishResult.setText("");
			else
				lblEnglishResult.setText(Result);
		}
	}
	
	private void loadFile()
	{
		int Elements = 0;
		int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            Scanner s = null;

            try {
                s = new Scanner( new BufferedReader(new FileReader(file)));
                try
            	{
	                while (s.hasNext()) {
	                	String line = s.nextLine();
	                	if(line != null)
	                	{
	                		if(line.split("\\s").length > 1)
	                		{
			                	String key = line.split("\\s")[0];
			                	String value = line.split("\\s")[1];
			                	myDictionary.insert(key, value);
			                	
		                	}
	                		else
	                			break;
	                	}
	                	Elements++;
	                	if(Elements%100 == 0)
	                		this.setTitle("Elemente: " + Elements);
	                }
            	}
                catch(Exception e){}//Dateiende oder Dateifehler -> aufhören
                
            } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                if (s != null) {
                    s.close();
                }
            }
        }
        //System.out.println(myDictionary.toString());
	}
	
	
}
