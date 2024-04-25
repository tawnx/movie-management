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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.zyp.entity.Cinema;
import com.zyp.entity.Session;
import com.zyp.service.CinemaService;
import com.zyp.service.SessionService;
import com.zyp.service.impl.CinemaServiceImpl;
import com.zyp.service.impl.SessionServiceImpl;

public class CinemaManage extends JInternalFrame {
	private JTable cinematable;
	private JTextField cinenametext;
	private JTextField cineaddrtest;
	private JTextField sercinnametest;
	private JTextField sercinaddresstest;
	private List<Cinema> cinemalist;
	private CinemaService cinemaservice=new CinemaServiceImpl();
	private int row=-1;
	private Font font = new Font("Times New Roman", 0, 20);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CinemaManage frame = new CinemaManage();
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
	public CinemaManage() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1100, 900);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("Cinema Name: ");// 影院名 Cinema Name
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		cinenametext = new JTextField();
		cinenametext.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cinenametext.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cinema Address: ");// 影院地址 Cinema Address
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		cineaddrtest = new JTextField();
		cineaddrtest.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cineaddrtest.setColumns(10);
		
		JButton addButton = new JButton("Add Cinema");// 添加影院 Add Cinema
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcinemaactionPerformed(e);
			}
		});
		addButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton updateButton = new JButton("Modify Cinema");// 修改影院 Modify Cinema
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateactionPerformed(e);
			}
		});
		updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton delButton = new JButton("Delete Cinema");// 删除影院 Delete Cinema
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delactionPerformed(e);
			}
		});
		delButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("Cinema Name: ");// 影院名 Cinema Name
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		sercinnametest = new JTextField();
		sercinnametest.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sercinnametest.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cinema Address: ");// 影院地址 Cinema Address
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		sercinaddresstest = new JTextField();
		sercinaddresstest.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sercinaddresstest.setColumns(10);
		
		JButton serachButton = new JButton("Search");// 搜索 Search
		serachButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchactionPerformed(e);
			}
		});
		serachButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(225)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cinenametext, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addGap(174)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cineaddrtest)
					.addContainerGap(121, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(93)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 907, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(84, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(183)
					.addComponent(addButton)
					.addGap(200)
					.addComponent(updateButton)
					.addGap(134)
					.addComponent(delButton)
					.addContainerGap(226, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(147)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sercinnametest, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(63)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sercinaddresstest, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(77)
					.addComponent(serachButton)
					.addContainerGap(224, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(99)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(sercinnametest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(sercinaddresstest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(serachButton))
					.addGap(82)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(cineaddrtest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(cinenametext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(updateButton)
						.addComponent(delButton))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		cinemalist=cinemaservice.queryAllCinema();
		cinematable = new JTable(){
			  public boolean isCellEditable( int row, int column) {
					return false;
				}

			};
			cinematable.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Cinema ID", "Cinema Name", "Cinema Address"
							// 影院编号 Cinema ID, 影院名字 Cinema Name, 影院地址 Cinema Address
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			fillTable(cinemalist);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			cinematable.setDefaultRenderer(Object.class, r);
			cinematable.setRowHeight(70);
			cinematable.setFont(font);
			cinematable.getTableHeader().setFont(new Font("Times New Roman", 1, 20));
			cinematable.getTableHeader().setBackground(Color.orange);
			cinematable.getTableHeader().setReorderingAllowed(false); // 不可交换顺序
			cinematable.getTableHeader().setResizingAllowed(false); // 不可拉动表格
			cinematable.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent me) {
					cinematablemousePressed(me);
				}
			});
		scrollPane.setViewportView(cinematable);
		getContentPane().setLayout(groupLayout);

	}

	protected void searchactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if("".equals(sercinnametest.getText())&&!"".equals(sercinaddresstest.getText())) {
			cinemalist=cinemaservice.queryCinemaByCinemaAddress(sercinaddresstest.getText());
			fillTable(cinemalist);
		}
		if("".equals(sercinaddresstest.getText())&&!"".equals(sercinnametest.getText())) {
			cinemalist=cinemaservice.queryCinemaByCinemaName(sercinnametest.getText());
			fillTable(cinemalist);
		}
		if(!"".equals(sercinnametest.getText())&&!"".equals(sercinaddresstest.getText())) {
			cinemalist=cinemaservice.queryCinemaByCinemaNameAndCinemaAddress(sercinnametest.getText(), sercinaddresstest.getText());
			fillTable(cinemalist);
		}
	}

	protected void delactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(row!=-1) {
			SessionService sessionservice=new SessionServiceImpl();
			List<Session> list=sessionservice.querySessionBycId(cinemalist.get(row).getCid());
			if(list.size()!=0) {
				JOptionPane.showMessageDialog(null, "无法删除影院,该影院有场次");// 无法删除影院,该影院有场次 Cannot delete cinema, the cinema has sessions
			}else {
			if(cinemaservice.delCinema(cinemalist.get(row).getCid())) {
				JOptionPane.showMessageDialog(null, "Delete cinema successfully");// 删除影院成功 Delete cinema successfully
				cinemalist.remove(row);
				fillTable(cinemalist);
			}else {
				JOptionPane.showMessageDialog(null, "Delete cinema failed");// 删除影院失败 Delete cinema failed
			}
			}
		}
	}

	protected void updateactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(row!=-1) {
			cinemalist.get(row).setCaddress(cineaddrtest.getText());
			cinemalist.get(row).setCname(cinenametext.getText());
			if(cinemaservice.updateCinema(cinemalist.get(row))) {
				JOptionPane.showMessageDialog(null, "Modify cinema successfully");// 修改影院成功 Modify cinema successfully
				fillTable(cinemalist);
			}else {
				JOptionPane.showMessageDialog(null, "Modify cinema failed");// 修改影院失败 Modify cinema failed
			}
		}else {
			JOptionPane.showMessageDialog(null, "Please select cinema");// 请选择影院 Please select cinema
		}
	}

	protected void addcinemaactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(judge()) {
			Cinema cinema=new Cinema(cinenametext.getText(),cineaddrtest.getText());
			if(cinemaservice.addCinema(cinema)==1) {
				cinemalist=cinemaservice.queryAllCinema();
				JOptionPane.showMessageDialog(null, "Add cinema successfully");// 添加影院成功 Add cinema successfully
				fillTable(cinemalist);
				reset();
			}else if(cinemaservice.addCinema(cinema)==2){
				JOptionPane.showMessageDialog(null, "Add cinema failed");// 添加影院失败 Add cinema failed
			}else {
				JOptionPane.showMessageDialog(null, "Duplicate, add cinema failed");// 重复,添加影院失败 Duplicate, add cinema failed
			}
		}
		
	}

	private void fillTable(List<Cinema> cinemalist2) {
		// TODO Auto-generated method stub
		DefaultTableModel dtm=(DefaultTableModel) cinematable.getModel();
		dtm.setRowCount(0);
		int row=cinemalist2.size();
		for(int i=0;i<row;i++) {
			Vector v=new Vector();
			v.add(cinemalist2.get(i).getCid());
			v.add(cinemalist2.get(i).getCname());
			v.add(cinemalist2.get(i).getCaddress());
			dtm.addRow(v);
		}
	}

	protected void cinematablemousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		
		 row=cinematable.getSelectedRow();
		 if(row!=-1) {
			 cinenametext.setText(cinemalist.get(row).getCname());
			 cineaddrtest.setText(cinemalist.get(row).getCaddress());
		 }else {
			 
		 }
		
	}
	public boolean judge() {
		if("".equals(cinenametext.getText())) {
			JOptionPane.showMessageDialog(null, "Movie name cannot be empty");// 电影名字不能为空 Movie name cannot be empty
			return false;
		}
		if("".equals(cineaddrtest.getText())) {
			JOptionPane.showMessageDialog(null, "Movie address cannot be empty");// 电影地址不能为空 Movie address cannot be empty
			return false;
		}
	
		return true;
	}
    public void reset() {
    	cinenametext.setText("");
    	cineaddrtest.setText("");
    }
}
