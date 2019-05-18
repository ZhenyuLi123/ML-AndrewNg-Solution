package applications;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.MyException;
import logging.LogTest;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class GUIAtom {

	private JFrame frmAtomstructure;
	private JTextField textField;
	
	//������
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAtom window = new GUIAtom();
					window.frmAtomstructure.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAtom window = new GUIAtom();
					window.frmAtomstructure.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIAtom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtomstructure = new JFrame();
		frmAtomstructure.setTitle("AtomStructure");
		frmAtomstructure.setBounds(100, 100, 450, 300);
		//frmAtomstructure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtomstructure.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u6587\u4EF6\u540D\uFF1A");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		label.setBounds(93, 59, 101, 18);
		frmAtomstructure.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		textField.setBounds(208, 56, 174, 24);
		frmAtomstructure.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = textField.getText();
				AtomStructure atom = new AtomStructure();
				File f = new File("src/circularOrbit/test/" + str + ".txt");
				
				try {
					atom.readFile(f);
					GUIAtomFinal.start(atom);
				}catch(IOException e1) {
					//System.out.println("�����ļ�����");
					LogTest.logger.error("�����ļ�������");
					JOptionPane.showMessageDialog(frmAtomstructure, "�����ļ�������","Error",
			                 JOptionPane.WARNING_MESSAGE);
				}catch(MyException e2) {
					//e2.printInfo();
					LogTest.logger.error(e2.getInfo());
					JOptionPane.showMessageDialog(frmAtomstructure, e2.getInfo() ,"Error",
			                 JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(145, 115, 113, 27);
		frmAtomstructure.getContentPane().add(btnNewButton);
	}
}
