package md.convertit.hydraulicsystem.util;

import java.io.File;

import javax.swing.JFileChooser;

public class FileUtil {
	/**
	 * Show open file dialog
	 * @return
	 */
	public static String showOpenFileDialog(){
		// init a file chooser
		JFileChooser fileChooser = new JFileChooser();
		// show to user a dialog to choose a file
		int result = fileChooser.showOpenDialog(null);
		// check result of dialog. If approved then return the file path else null
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * Show save file dialog
	 * @return
	 */
	public static String showSaveFileDialog(){
		// init a file chooser
		JFileChooser fileChooser = new JFileChooser();
		// show to user a dialog to choose a file
		int result = fileChooser.showSaveDialog(null);
		// check result of dialog. If approved then return the file path else null
		if(result == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return null;
	}

}
