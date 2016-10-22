package GameObjects;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Parser.XMLParser;
import animations.ApplicationWindow;
import animations.AnimationWindow;
import animations.AnimationEventListener;

// This filter contains some code from:
// http://stackoverflow.com/questions/1246857/using-a-filedialog-to-choose-a-file-of-a-certain-extension

class CustomFilter extends javax.swing.filechooser.FileFilter {
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".xml");
    }
    public String getDescription() {
        return "*.xml";
    }
}

public class Game {
	
	public static BouncingBall ball = new BouncingBall();
	public static ArrayList<GameTakoz> takozlar = new ArrayList<GameTakoz>();
	public static Cezmi cezmi;
	
	public static void main(String[] args) {
		
		boolean valid = false;
		
		while (!valid){
			// User selects new file
			final JFileChooser fc = new JFileChooser();
			File defaultLoc = new File("src\\xmls");
			fc.setCurrentDirectory(defaultLoc);
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.addChoosableFileFilter(new CustomFilter());
			fc.setAcceptAllFileFilterUsed(false);
			
			fc.showOpenDialog(null);
			String loc = fc.getSelectedFile().getAbsolutePath();
			System.out.println(loc);
			
			//String loc = "src\\xmls\\CezmiPrototype3.xml";
			XMLParser parser = new XMLParser(loc);
			valid = parser.validateXMLFile();
			
			if (valid){
				parser.parseXMLFile();
				ApplicationWindow frame = new ApplicationWindow();
			    // the following code realizes the top level application window
			    frame.pack();
			    frame.setVisible(true);
			}
		}
		
		
	  }
}