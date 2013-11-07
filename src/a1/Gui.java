package a1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.*;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener,ItemListener{

	JRadioButtonMenuItem rbMnuItmSortedDictionary;
	JRadioButtonMenuItem rbMnuItmHashDictionary;
	JRadioButtonMenuItem rbMnuItmTreeDictionary;
	
	JRadioButtonMenuItem rbMnuItmMapDictionaryTree;
	JRadioButtonMenuItem rbMnuItmMapDictionaryHash;
	
	JMenuItem menuPerformance;
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
	JButton btnRemove;
	
	private Dictionary<String,String> myDictionary;
	
	public Gui()
	{
		super();
		
		myDictionary = new SortedArrayDictionary<String,String>(); 
		
		
		initMenu();
		initControls();
		
		
		setSize(780,300);
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
		btnRemove = new JButton("Entfernen");
		
		lblGermanResult = new JLabel("");
		lblEnglishResult = new JLabel("");
		
		pnl.add(lblGerman);
		pnl.add(txtGerman);
		pnl.add(lblEnglish);
		pnl.add(txtEnglish);
		pnl.add(btnInsert);
		pnl.add(btnSearch);
		pnl.add(btnRemove);
		pnl.add(lblGermanResult);
		pnl.add(lblEnglishResult);
		
		lblGerman.setLocation(12,12);
		lblEnglish.setLocation(12,42);
		txtGerman.setLocation(100,12);
		txtEnglish.setLocation(100,42);
		lblGermanResult.setLocation(320,12);
		lblEnglishResult.setLocation(320,42);
		
		lblGerman.setSize(80, 22);
		lblEnglish.setSize(80, 22);
		txtGerman.setSize(200, 22);
		txtEnglish.setSize(200, 22);
		lblGermanResult.setSize(280, 22);
		lblEnglishResult.setSize(280, 22);
		
		btnInsert.setLocation(10,72);
		btnInsert.setSize(150, 36);
		btnInsert.addActionListener(this);
		
		btnSearch.setLocation(180,72);
		btnSearch.setSize(150, 36);
		btnSearch.addActionListener(this);
		
		btnRemove.setLocation(350,72);
		btnRemove.setSize(150, 36);
		btnRemove.addActionListener(this);
		
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
		
		menuPerformance = new JMenuItem("Performance Messungen");
		menuPerformance.addActionListener(this);
		menuSettings.add(menuPerformance);
		
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
			myDictionary = new HashDictionary<String,String>();
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
			myDictionary = new TreeDictionary<String,String>();
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
			
			if(Result == null){
				lblGermanResult.setText("");
				lblEnglishResult.setText("");
			}
			else
			{
				lblGermanResult.setText(txtGerman.getText());
				lblEnglishResult.setText(Result);
			}
		}
		else if(arg0.getSource() == btnRemove)
		{
			String Result = myDictionary.remove(txtGerman.getText());
			lblGermanResult.setText(txtGerman.getText());
			if(Result == null)
				lblEnglishResult.setText("Eintrag konnte nicht gefunden werden");
			else
				lblEnglishResult.setText("Eintrag wurde entfernt");
		}
		else if(arg0.getSource() == menuPerformance)
		{
			performanceTest();
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
	//performance
	private void performanceTest()
	{
		java.util.HashMap<String, String> temp = new HashMap<>();
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
			                	temp.put(key, value);
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
        
        String[] performanceTestDataA = new String[temp.size()];
        String[] performanceTestDataB = new String[temp.size()];
        int i = 0;
        int size;
        for(Entry<String,String> a : temp.entrySet())
        {
        	performanceTestDataA[i] = a.getValue();
        	performanceTestDataB[i] = a.getKey();
        	i++;
        }
        size = i;
        for(i = 0;i<1000000;i++);
        long time = System.nanoTime();
        long time2 = System.nanoTime();
        long TimeCallDelay = time2-time;
        System.out.println("TimeCallDelay: " + TimeCallDelay);
        time = System.nanoTime();
        for(i=0;i<size;i++)
        {
        	myDictionary.insert(performanceTestDataA[i], performanceTestDataB[i]);
        }
        time2 = System.nanoTime();
        System.out.println("Einfügen: " + (time2-time-TimeCallDelay));
        time = System.nanoTime();
        for(i=0;i<size;i++)
        {
        	myDictionary.search(performanceTestDataA[i]);
        }
        time2 = System.nanoTime();
        System.out.println("Suchen: " + (time2-time-TimeCallDelay));
        time = System.nanoTime();
        for(i=0;i<size;i++)
        {
        	myDictionary.remove(performanceTestDataA[i]);
        }
        time2 = System.nanoTime();
        System.out.println("entfernen: " + (time2-time-TimeCallDelay));
        
        
        
	}
	
}
