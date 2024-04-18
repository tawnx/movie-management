package com.zyp.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.zyp.entity.User;
import com.zyp.service.UserService;
import com.zyp.service.impl.UserServiceImpl;

public class LoginUi implements ActionListener{
	private final JFrame jf = new JFrame("Movie Ticketing System");
	private final Container con = jf.getContentPane();// Acquire Panel

	private final ImageIcon bg = new ImageIcon("images/bg.jpg");
	private final ImageIcon btnbg = new ImageIcon("images/btn.png");
	private final Toolkit toolkit = Toolkit.getDefaultToolkit();
	private final Dimension sc = toolkit.getScreenSize();// Acquire Screen Size
	
	private final JButton in = new JButton("",btnbg);
	//"Enter System" using background image
	
	private final JLabel bglabel = new JLabel();
	
	public LoginUi() {
		con.setLayout(null);
		jf.setSize(1000, 600);
		jf.setLocation((sc.width - 1000) / 2, (sc.height - 618) / 2);		
		jf.setResizable(false);// Window size is fixed
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setVisible(true);		
		bglabel.setIcon(bg);

		bglabel.setBounds(0, 0, 1000, 600);		
		in.setBounds(425, 250, 150, 56);

		con.add(in);
		con.add(bglabel);

		
		/*	if (!Util.getConnet()) {
			winMessage("The server is not started, unable to proceed!");
			return;
		}*/
		in.addActionListener(this);
		
	}

	public static void winMessage(String str) {
		// Prompt window, called from multiple places
		JOptionPane.showMessageDialog(null, str, "Prompt",
				JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.in) {
			new Login();
			jf.dispose();
		}
	}
}

class Login implements ActionListener {
	private final JFrame jf = new JFrame("Movie Ticketing System");
	private final Container con = jf.getContentPane();// Obtain Panel

	private final Toolkit toolkit = Toolkit.getDefaultToolkit();
	private final Dimension sc = toolkit.getScreenSize();// Obtain Screen Size
	private final JLabel title = new JLabel("Movie Ticketing System");
	private final JLabel name1 = new JLabel("User");
	private final JLabel pass1 = new JLabel("Pass");
	private final JTextField textName = new JTextField();
	private final JPasswordField textPs = new JPasswordField();// Password Box

	private final JRadioButton choice1 = new JRadioButton("User");
	private final JRadioButton choice2 = new JRadioButton("Administrator");

	private final JLabel code1 = new JLabel("CAPTCHA");
	private final JTextField textCode = new JTextField();
	private final JLabel code2 = new JLabel();

	private final JButton button1 = new JButton("Register");
	private final JButton button2 = new JButton("Login");
	// 按钮

	private final Font font = new Font("楷体", 1, 28);
	private final Font font1 = new Font("楷体", 0, 20);
	private final Font font2 = new Font("楷体", 0, 18);
	// 字体，样式（粗体，斜体），大小

	private final ButtonGroup buttongroup = new ButtonGroup();

	private final ImageIcon loginbg = new ImageIcon("images/loginbg.jpg");
	
	public Login() {
		con.setLayout(null);
		jf.setSize(1000, 618);
		jf.setLocation((sc.width - 1000) / 2, (sc.height - 618) / 2);

		jf.setResizable(false);// Window size is fixed
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setVisible(true);

		JLabel maxlabel = new JLabel(loginbg);
		
		title.setBounds(355, 10, 370, 30);
		title.setFont(font);
		title.setForeground(Color.black);

		name1.setBounds(140, 140, 85, 30);// Position and size of the account
		name1.setFont(font1);// Font
		name1.setForeground(Color.black);// Color of "name1" text

		pass1.setBounds(140, 190, 85, 30);// Position and size of the password
		pass1.setForeground(Color.black);
		pass1.setFont(font1);

		textName.setBounds(200, 143, 140, 25);
		textName.setBorder(null);// Border
		textName.setFont(font1);

		textPs.setBounds(200, 193, 140, 25);
		textPs.setBorder(null);
		textPs.setEchoChar('*');// Passwords can be displayed as *; Default is ..
		textPs.setFont(font1);

		choice1.setBounds(140, 290, 120, 25);
		choice1.setSelected(true);// Default login as a regular user
		choice2.setBounds(260, 290, 120, 25);

		code1.setBounds(140, 240, 100, 25);
		code1.setFont(font1);
		code1.setForeground(Color.black);
		textCode.setBounds(220, 240, 95, 25);
		textCode.setBorder(null);
		textCode.setFont(font1);
		code2.setBounds(320, 240, 70, 25);
		code2.setFont(font1);
		code2.setText(code());
		code2.setForeground(Color.black);

		button1.setBounds(110, 340, 130, 25);
		button1.setFont(font2);
		button1.addActionListener(this);

		button2.setBounds(260, 340, 130, 25);
		button2.setFont(font2);
		button2.addActionListener(this);

		JLabel bg = new JLabel(loginbg);
		
		maxlabel.add(title);
		maxlabel.add(name1);
		maxlabel.add(pass1);
		maxlabel.add(textName);
		maxlabel.add(textPs);
		maxlabel.add(choice1);
		maxlabel.add(choice2);

		buttongroup.add(choice1);
		buttongroup.add(choice2);

		maxlabel.add(code1);
		maxlabel.add(code2);
		maxlabel.add(textCode);
		maxlabel.add(button1);
		maxlabel.add(button2);
		
		maxlabel.setBounds(0, 0, 999, 617);
		con.add(maxlabel);

	}

	private String code() {
		int m = 0;
		for (int i = 0; i < 4; i++) {
			m *= 10;
			m += (int) (Math.random() * 9 + 1);
		}
		return ((Integer) m).toString();
	}

	public void cleanUserInfo() {
		this.textName.setText("");
		this.textPs.setText("");
		this.textCode.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent ac) {
		// TODO Auto-generated method stub
		if (ac.getSource() == this.button2) {
			String id = textName.getText();
			String pswd = new String(textPs.getPassword());
			if (id.equals("") || pswd.equals("")) {
				LoginUi.winMessage("Account and password cannot be empty!");
				cleanUserInfo();
				this.code2.setText(code());
			} else {
				String code1 = textCode.getText();
				String code = code2.getText();
				if (code1.equals(code)) {
					int choice=0;
					if (choice1.isSelected())
						choice = 0;
					else 
						choice = 1;
					UserService userService = new UserServiceImpl();
					User user = userService.login(new User(id, pswd, choice));
					if (user == null) {
						LoginUi.winMessage("Incorrect username or password! Login failed!");
						cleanUserInfo();
						this.code2.setText(code());
					} else {
						
						user=userService.queryUser(user);
					    				   
							if (user.getuType()==0) {
								new UserUi(user,1);
							} else if (user.getuType()==1) {
								new AdminUi();
							}
							this.jf.dispose();
						
						LoginUi.winMessage("Login successful!");
					}
				} else {
					LoginUi.winMessage("Incorrect CAPTCHA!");
					cleanUserInfo();// Consider not clearing the information
					this.code2.setText(code());
				}
			}
		} else if (ac.getSource() == this.button1) {
			new RegisterUi();
			this.jf.dispose();// When clicking the button, create a new frame, destroy the original frame
		}	
	}
}
