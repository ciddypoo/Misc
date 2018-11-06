package com.routing;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainGUI {

	protected Shell shell;
	public Text text;
	public Text text_1;
	public Text text_2;
	public Text text_3;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(335, 245);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(90, 10, 213, 26);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(90, 42, 213, 26);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(90, 84, 213, 26);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(90, 116, 213, 26);
		
		Label lblGps = new Label(shell, SWT.NONE);
		lblGps.setBounds(10, 13, 74, 20);
		lblGps.setText("GPS Lat:");
		
		Label lblGpsLon = new Label(shell, SWT.NONE);
		lblGpsLon.setText("GPS Lon:");
		lblGpsLon.setBounds(10, 45, 74, 20);
		
		Label lblGoalLat = new Label(shell, SWT.NONE);
		lblGoalLat.setText("Goal Lat:");
		lblGoalLat.setBounds(10, 87, 74, 20);
		
		Label lblGoalLon = new Label(shell, SWT.NONE);
		lblGoalLon.setText("Goal Lon:");
		lblGoalLon.setBounds(10, 119, 74, 20);
		
		Button btnStop = new Button(shell, SWT.NONE);
		btnStop.setBounds(213, 161, 90, 30);
		btnStop.setText("Stop");
		
		Button btnStart = new Button(shell, SWT.NONE);
		btnStart.setText("Start");
		btnStart.setBounds(90, 161, 90, 30);

	}

	public void setVehiclePos(double[] loc) {
		text.setText(Double.toString(loc[0]));
		text_1.setText(Double.toString(loc[1]));
	}
	
	public double[] getUserInput() {
		double[] loc = new double[1];
		loc[0] = Double.parseDouble(text_2.getText());
		loc[1] = Double.parseDouble(text_3.getText());
		return loc;
	}
}
