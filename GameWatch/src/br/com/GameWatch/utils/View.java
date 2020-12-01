package br.com.GameWatch.utils;
import javax.swing.JOptionPane;

abstract public class View {
	public static void showMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
	}
	
	public static void showError(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showWarning(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	public static int inputInt(String title, String message) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, message, title,JOptionPane.DEFAULT_OPTION));
	}
	
	public static String inputString(String title, String message) {
		return JOptionPane.showInputDialog(null, message, title, JOptionPane.DEFAULT_OPTION);
	}
	
	public static double inputDouble(String title, String message) {
		String input = JOptionPane.showInputDialog(null, message, title, JOptionPane.DEFAULT_OPTION); 
		return Double.parseDouble(input);
	}
	
	public static int menu(String[] options, String title, String message) {
		int retorno = JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
		return retorno+1;
	}
}
