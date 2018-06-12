import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * HexInterface
 * @author Jan Schelhaas, Pascal Polchow, Larissa Wagnerberger
 * @version 2018.06.13
 */
public class HexInterface extends UserInterface implements ActionListener {

	protected JPanel hexpanel;
	protected JButton switchbutton;
	protected JButton abutton;
	protected JButton bbutton;
	protected JButton cbutton;
	protected JButton dbutton;
	protected JButton ebutton;
	protected JButton fbutton;

	public HexInterface(Engine engine) {
		super(engine);

		makebuttons();

		frame.setVisible(true);

		// make the program exit if we close the window
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
	}

	protected void makebuttons() {
		switchbutton = new JButton("HEX");
		switchbutton.addActionListener(this);

		abutton = new JButton("A");
		abutton.addActionListener(this);
		bbutton = new JButton("B");
		bbutton.addActionListener(this);
		cbutton = new JButton("C");
		cbutton.addActionListener(this);
		dbutton = new JButton("D");
		dbutton.addActionListener(this);
		ebutton = new JButton("E");
		ebutton.addActionListener(this);
		fbutton = new JButton("F");
		fbutton.addActionListener(this);

		activatebuttons(false);

		assemble();
	}

	protected void assemble() {

		JPanel contentPane = (JPanel) frame.getContentPane();

		hexpanel = new JPanel(new GridLayout(7, 1));
		hexpanel.add(switchbutton);
		// addButton(hexpanel, "HEX");
		hexpanel.add(abutton);
		hexpanel.add(bbutton);
		hexpanel.add(cbutton);
		hexpanel.add(dbutton);
		hexpanel.add(ebutton);
		hexpanel.add(fbutton);

		contentPane.add(hexpanel, BorderLayout.EAST);

		frame.pack();
	}

	public void actionPerformed(ActionEvent event) {

		super.actionPerformed(event);

		String command = event.getActionCommand();

		if (command.equals("A") || command.equals("B") || command.equals("C") || command.equals("D")
				|| command.equals("E") || command.equals("F")) {
			//command = "0x" + command;
			//int number = Integer.decode(command);
			// System.out.println(number);
			calc.op(command);
			//calc.setHexMode(true);
		} else if (command.equals("HEX")) {
			if (calc.getHexMode()) {
				calc.setHexMode(false);
				activatebuttons(false);
				showInfo();
			} else {
				calc.setHexMode(true);
				activatebuttons(true);
				switchbutton.setText("DEC");
				showInfo();
			}
		} else if (command.equals("DEC")) {
			if (!calc.getHexMode()) {
				calc.setHexMode(false);
				activatebuttons(false);
				showInfo();
			} else {
				calc.setHexMode(false);
				activatebuttons(false);
				switchbutton.setText("HEX");
				showInfo();
			}
		}

		redisplay();
	}

	private void activatebuttons(boolean b) {
		abutton.setEnabled(b);
		bbutton.setEnabled(b);
		cbutton.setEnabled(b);
		dbutton.setEnabled(b);
		ebutton.setEnabled(b);
		fbutton.setEnabled(b);

	}

	protected void redisplay() {

		if (calc.getHexMode()) {
			showInfo();
			display.setText("" + calc.getDisplayString());
		}
		else
			super.redisplay();
	}

}
