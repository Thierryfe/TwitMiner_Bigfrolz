package csvReaderWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EcrireCSV {
	
	private BufferedWriter bW = null;
    private String separateur = ";";

    public EcrireCSV(String separateur) {
        this.separateur = separateur;
    }

    public boolean ouvrir(String nom) {
        try {
            File fichier = new File(nom);
            if (!fichier.exists()) {
            	fichier.createNewFile();
            }
            this.bW = new BufferedWriter(new FileWriter(fichier));
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public void fermer() {
    	try {
			this.bW.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public String formater(String mot) {
    	mot = mot.replaceAll("\"", "\\\"");
    	mot = mot.replaceAll(";", "");
    	mot = "\"" + mot + "\"";
        return mot;
    }

    public void ecritureList(List<String> set) throws IOException {
        String data = "";
        for (int i = 0; i < set.size(); ++i) {
            data += formater(set.get(i));
            if (i != set.size() - 1) {
                data += separateur;
            }
        }
        bW.write(data);
        bW.newLine();
    }

}
