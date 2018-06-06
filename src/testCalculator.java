import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class testCalculator {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Calculator c = new Calculator();

//		System.out.println(0xA + 0xA);

	}

}
