package com.zyp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zyp.entity.Hall;
import com.zyp.entity.Movie;
import com.zyp.entity.Session;
import com.zyp.entity.Ticket;
import com.zyp.entity.User;
import com.zyp.service.SessionService;
import com.zyp.service.TicketService;
import com.zyp.service.UserService;
import com.zyp.service.impl.SessionServiceImpl;
import com.zyp.service.impl.TicketServiceImpl;
import com.zyp.service.impl.UserServiceImpl;

public class BuyTicketUi extends JFrame implements ActionListener {
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JButton btlist[];
	private JButton reset = new JButton("Reset");// 重置 Reset
	private JButton jbuy = new JButton("Buy");// 购买 Buy
	private JButton returnuser = new JButton("Return to homepage");// 返回主页 Return to homepage
	private JButton returnlast = new JButton("Return to last level");// 返回上一层 Return to last level

	private TextField lblRe = new TextField(20);
	private UserService userservice = new UserServiceImpl();
	private SessionService sessionservice = new SessionServiceImpl();
	private TicketService ticketService = new TicketServiceImpl();
	private int num;
	private User u;
	private Session se;
	private Movie mo;
	private Hall ha;
	private List<Ticket> list;
	private List<Integer> seatlist = new ArrayList<Integer>();
	private StringBuffer sb = new StringBuffer();

	public BuyTicketUi(User u, Session se, Movie mo, Hall ha) {
		this.u = u;
		this.se = se;
		this.mo = mo;
		this.ha = ha;
		list = ticketService.queryAllTicketsId(se.getsId());
		jbuy.setFont(new Font("Times New Roman", Font.BOLD, 20));
		returnuser.setFont(new Font("Times New Roman", Font.BOLD, 20));
		returnlast.setFont(new Font("Times New Roman", Font.BOLD, 20));
		reset.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblRe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.setSize(1000, 1000);
		num = ha.getCapacity();
		int rows = (num + 9) / 10;
		btlist = new JButton[num];
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(p1, BorderLayout.CENTER);
		this.getContentPane().add(p2, BorderLayout.SOUTH);
		p1.setLayout(new GridLayout(rows, 10, 20, 10));
		p2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

		for (int i = 0; i < num; i++) {
			btlist[i] = new JButton(i + 1 + "");
			btlist[i].setBackground(Color.WHITE);
			btlist[i].addActionListener(this);
			p1.add(btlist[i]);
		}
		for (Ticket lis : list) {
			for (int i = 0; i < num; i++) {
				if (lis.getSeat() == (i + 1)) {
					btlist[i].setBackground(Color.ORANGE);
					btlist[i].setEnabled(false);
				}
			}
		}
		reset.addActionListener(this);
		jbuy.addActionListener(this);
		returnuser.addActionListener(this);
		returnlast.addActionListener(this);
		p3.add(returnuser);
		p3.add(returnlast);
		p2.add(reset);
		p2.add(lblRe);
		p2.add(jbuy);
		p2.add(p3);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		for (int i = 0; i < num; i++) {
			if (obj == btlist[i]) {
				sb.append(i + 1 + " ");
				btlist[i].setBackground(Color.ORANGE);
				seatlist.add(Integer.parseInt(btlist[i].getText()));
				btlist[i].setEnabled(false);
				lblRe.setText(sb + "");
			}
		}

		if (obj == reset) {
			sb.setLength(0);
			if (seatlist.size() > 0) {
				for (Integer seat : seatlist) {

					btlist[seat - 1].setEnabled(true);
					btlist[seat - 1].setBackground(Color.WHITE);
					lblRe.setText(sb + "");
				}
				seatlist.clear();
			}
		}
		if (obj == returnuser) {
			new UserUi(u, 1);
			dispose();
		}
		if (obj == returnlast) {
			new SessionUi(mo, u);
			dispose();
		}
		if (obj == jbuy) {
			double money;
			Ticket t2;
			if (seatlist.size() > 0) {
				money = seatlist.size() * se.getPrice();
				if(seatlist.size()>=5) {
					money=money*0.9;
				}
				if (u.getBalance() - money >= 0) {
					for (Integer seat : seatlist) {
						t2 = new Ticket(u.getUid(), se.getsId(), seat);
						ticketService.addTicket(t2);

					}
					double remoney=u.getBalance() - money;
					u.setBalance(u.getBalance() - money);
					if (userservice.updateUser(u)) {
						se.setRemain(se.getRemain() - seatlist.size());
						sessionservice.updateSession(se);
						
						JOptionPane.showMessageDialog(null, "Purchase successful, Balance:"+remoney);// 购买成功，余额 Purchase successful, Balance
						new BuyTicketUi(u,se,mo,ha);
						dispose();
						
					} else {
						JOptionPane.showMessageDialog(null, "System error");// 系统错误 System error
					}
				} else {
					JOptionPane.showMessageDialog(null, "Insufficient balance");// 余额不足 Insufficient balance
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please select a seat");// 请选择座位 Please select a seat
			}
		}
	}

}