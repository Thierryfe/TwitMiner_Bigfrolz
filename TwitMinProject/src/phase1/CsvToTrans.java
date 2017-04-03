package phase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvToTrans {
	
	private String file;
	private String pathFolder;
	private ArrayList<String> dictionnaire;

	
	
	public CsvToTrans(String file, String pathFolder){
		super();
		this.file = file;
		this.pathFolder = pathFolder;
		this.dictionnaire = new ArrayList<String>();

	}
	

	private int dictionnaireContient (String s) {
		int index = -1;
		boolean find = false;

		for (int i = 0; i < this.dictionnaire.size();++i) {
			if (s.equals(this.dictionnaire.get(i))) {
				index = i;
				find = true;
			}
		}

		if (!find) {
			this.dictionnaire.add(s);
			index = this.dictionnaire.size()-1;
		}

		return index;
	}
	

	
	public void toTrans(){
		
		try {
			
			BufferedWriter w= new BufferedWriter( new OutputStreamWriter(new FileOutputStream(this.pathFolder+this.file.substring(0,this.file.length()-4)+".trans")));
			
			File f= new File(this.file);
			BufferedReader r= new BufferedReader(new FileReader(f));
			
			String dataLigne="";

			
			while(r.readLine() != null){
				
				dataLigne=r.readLine();
				String motActuel="";
				ArrayList <String> listeMots= new ArrayList<String>();
				System.out.println(dataLigne);
				for (int i = 0; i < dataLigne.length(); i++) {
					if(dataLigne.charAt(i)!=';'){
						motActuel += dataLigne.charAt(i);
					}else{
						listeMots.add(motActuel);
						motActuel="";
					}
				}
				
				for (String mot : listeMots) {
					System.out.println(mot);
					w.write(new Integer(dictionnaireContient(mot)).toString()+" ");
					
				}
				 w.write('\n');
				 w.newLine();
				
				

			} 
			 w.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void toCsv() throws IOException{
		

		try {
			BufferedWriter w= new BufferedWriter( new OutputStreamWriter(new FileOutputStream(this.pathFolder+this.file.substring(0,this.file.length()-4)+".csv")));
			File f= new File(this.file);
			BufferedReader r= new BufferedReader(new FileReader(f));
			
			String dataLigne="";

			while(r.readLine() != null){
				
				dataLigne=r.readLine();
				String motif="";
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < dataLigne.length(); i++) {
					
					if(dataLigne.charAt(i)==' '){
						sb.append(motif);
						sb.append(';');
						w.write(sb.toString());
						
					}else {
						motif +=dataLigne.charAt(i);
					}
					
				}
				sb.append('\n');
				w.write(sb.toString());
				
				 } 
			w.close();
			
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	

	public static void main(String[] args) throws IOException {

		CsvToTrans trad= new CsvToTrans(new String("test.trans"), new String(""));
		
		trad.toCsv();
		System.out.println("done");

	}

}
