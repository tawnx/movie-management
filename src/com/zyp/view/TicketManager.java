package com.zyp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.zyp.entity.Session;
import com.zyp.entity.Ticket;
import com.zyp.entity.User;
import com.zyp.service.SessionService;
import com.zyp.service.TicketService;
import com.zyp.service.UserService;
import com.zyp.service.impl.SessionServiceImpl;
import com.zyp.service.impl.TicketServiceImpl;
import com.zyp.service.impl.UserServiceImpl;

public class TicketManager extends JInternalFrame {
	private JTable tickettable;
	private List<Ticket> ticketlist;
    private TicketService ticketservice=new TicketServiceImpl();
    private Font font = new Font("Times New Roman", 0, 20);
    private int trow=-1;
    private JTextField sIdtext;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketManager frame = new TicketManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TicketManager() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1200, 900);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton deletButton = new JButton("Delete");// 删除 Delete
		deletButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletactionPerformed(e);
			}
		});
		deletButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		sIdtext = new JTextField();
		sIdtext.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sIdtext.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Session ID: ");// 场次编号 Session ID
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("Search");// 搜索 Search
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchactionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(169)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 857, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(783)
							.addComponent(deletButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(282)
							.addComponent(lblNewLabel)
							.addGap(46)
							.addComponent(sIdtext, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(76)
							.addComponent(btnNewButton)))
					.addContainerGap(158, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sIdtext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton))
					.addGap(78)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(deletButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		
		tickettable = new JTable(){
			  public boolean isCellEditable( int row, int column) {
					return false;
				}

			};
			tickettable.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
							"Ticket ID", "User ID", "Session ID","Seat Number"
							// 影票编号 Ticket ID 用户编号 User ID 场次编号 Session ID 座位号 Seat Number
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			ticketlist=ticketservice.queryAllTicket();
			filltickettable(ticketlist);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			tickettable.setDefaultRenderer(Object.class, r);
			tickettable.setRowHeight(70);
			tickettable.setFont(font);
			tickettable.getTableHeader().setFont(new Font("Times New Roman", 1, 20));
			tickettable.getTableHeader().setBackground(Color.orange);
			tickettable.getTableHeader().setReorderingAllowed(false); // 不可交换顺序
			tickettable.getTableHeader().setResizingAllowed(false); // 不可拉动表格
			tickettable.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent me) {
					tickettablemousePressed(me);
				}
			});
		scrollPane.setViewportView(tickettable);
		getContentPane().setLayout(groupLayout);

	}

	

	protected void searchactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			int sId=Integer.parseInt(sIdtext.getText());
			ticketlist=ticketservice.queryAllTicketsId(sId);
			filltickettable(ticketlist);
			
		} catch (NumberFormatException  e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Format error");// 格式错误 Format error
		}
		
	}

	protected void deletactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		User user;
		UserService userservice=new UserServiceImpl();
		SessionService sessionservice=new SessionServiceImpl();
		double moneyprice;
		if(trow!=-1) {
			ticketservice.delTicket(ticketlist.get(trow).gettId());
			user=userservice.queryUserByid(ticketlist.get(trow).getuId());
			Session mysession=sessionservice.querySessionById(ticketlist.get(trow).getsId());
			mysession.setRemain(mysession.getRemain()+1);
			sessionservice.updateSession(mysession);
			moneyprice=sessionservice.querySessionById(ticketlist.get(trow).getsId()).getPrice();
			user.setBalance(user.getBalance()+moneyprice);
			if(userservice.updateUser(user)) {
				JOptionPane.showMessageDialog(null, "Delete ticket successfully");// 删除影票成功 Delete ticket successfully
				ticketlist=ticketservice.queryAllTicket();
				filltickettable(ticketlist);
			}else {
				JOptionPane.showMessageDialog(null, "Delete ticket failed");// 删除影票失败 Delete ticket failed
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please select a ticket");// 请选择影票 Please select a ticket
		}
	}

	protected void tickettablemousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		trow=tickettable.getSelectedRow();
		
	}

	private void filltickettable(List<Ticket> ticketlist2) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) tickettable.getModel();
		dtm.setRowCount(0);
		int row=ticketlist2.size();
		for(int i=0;i<row;i++) {
			Vector v=new Vector();
			v.add(ticketlist2.get(i).gettId());
			v.add(ticketlist2.get(i).getuId());
			v.add(ticketlist2.get(i).getsId());
			v.add(ticketlist2.get(i).getSeat());
			dtm.addRow(v);
		}
	}
}
