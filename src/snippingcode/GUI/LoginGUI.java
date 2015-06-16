package snippingcode.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.sun.jna.platform.win32.Netapi32Util.User;

import snippingcode.HttpRequest.LoginHttpRequest;
import snippingcode.controller.LoginController;
import snippingcode.domain.UserDomain;

public class LoginGUI extends JFrame {
	private JTextField username;
	private JPasswordField pass;
	private JButton submit;
	private JPanel window;
	private JPanel panelTextview;
	private JPanel panelTextinput;
	private LoginController loginController;

	public void constructLogin() {

		setTitle("Login");

		// text input for user username and password
		username = new JTextField(20);
		pass = new JPasswordField(20);

		// configration of Window show
		window = new JPanel(new BorderLayout(3, 3));
		window.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(window);

		// text view and text input configration on windows
		panelTextview = new JPanel(new GridLayout(0, 1));
		panelTextinput = new JPanel(new GridLayout(0, 1));
		window.add(panelTextview, BorderLayout.WEST);
		window.add(panelTextinput, BorderLayout.CENTER);

		// username configration
		panelTextview.add(new JLabel("Username:   "));
		panelTextinput.add(username);

		// new Line
		panelTextview.add(new JLabel(""));
		panelTextinput.add(new JLabel(""));

		// passowrd configration
		panelTextview.add(new JLabel("Password:   "));
		panelTextinput.add(pass);

		submit = new JButton("Submit");
		panelTextview.add(new JLabel(""));
		window.add(submit, BorderLayout.SOUTH);
		pack();

	}

	public LoginGUI() {
		constructLogin();
		setWindowPosition();
		addActionListner();
		loginController = new LoginController();
	}

	private void addActionListner() {
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String user = username.getText();
				String password = new String(pass.getPassword());
				Boolean check = loginController.login(user, password);
				if (check) {
					Run(false);
				}
			}
		});
	}

	private void setWindowPosition() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = getWidth();
		int height = getHeight();
		int x = (int) ((dimension.getWidth() - width) / 2);
		int y = (int) ((dimension.getHeight() - height) / 2);
		setLocation(x, y);
	}

	public void Run(boolean turn) {
		setVisible(turn);
	}
}
