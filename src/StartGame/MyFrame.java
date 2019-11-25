package StartGame;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//��������
public class MyFrame extends JFrame {
	// static boolean canPlay1 = false;�ж��Ƿ���Կ�ʼ��Ϸ
	final MyPanel panel = new MyPanel();

	public MyFrame() {

		// ���ô���Ĵ�С������
		this.setSize(500, 600); // ���ô����С
		this.setTitle("��������Ϸ"); // ���ô������

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;// ��ȡ��Ļ�Ŀ��
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;// ��ȡ��Ļ�ĸ߶�
		this.setLocation((width - 500) / 2, (height - 500) / 2); // ���ô����λ�ã����У�
		this.setResizable(false); // ���ô��岻���ԷŴ�
		// this.setLocationRelativeTo(null);//��仰Ҳ�������ô������
		/*
		 * �˵�����Ŀ¼����
		 */
		// ���ò˵���
		JMenuBar bar = new JMenuBar();
		this.setJMenuBar(bar);
		// ��Ӳ˵���Ŀ¼
		JMenu menu1 = new JMenu("��Ϸ�˵�"); // ʵ�����˵���Ŀ¼
		JMenu menu2 = new JMenu("����");
		JMenu menu3 = new JMenu("����");
		bar.add(menu1); // ��Ŀ¼��ӵ��˵���
		bar.add(menu2);
		bar.add(menu3);

		JMenu menu4 = new JMenu("����ģʽ"); // ����ģʽ���˵���ӵ������á�����
		menu2.add(menu4);

		// JMenuItem item1=new JMenuItem("���˲���");
		// JMenuItem item2=new JMenuItem("�˻�����");
		// ���á���Ŀ¼�������Ŀ¼
		JRadioButtonMenuItem item1 = new JRadioButtonMenuItem("���˲���");
		JRadioButtonMenuItem item2 = new JRadioButtonMenuItem("�˻�����");
		// item1��ť���ʱ�䲢��Ϊ������
		item1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���沢���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ񱣴����ò����¿�ʼ", "�˻���������", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.setIsManAgainst(true);
					panel.Start();
					item1.setSelected(true);
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// ����item2��ť���¼������¼���Ҳ���������˻�����
		item2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���沢���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ񱣴����ò����¿�ʼ", "�˻���������", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.setIsManAgainst(false);
					panel.Start();
					item2.setSelected(true);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// ���ð�ť�鲢���˻����������˲�����ӵ�һ����ť������
		ButtonGroup bg = new ButtonGroup();
		bg.add(item1);
		bg.add(item2);
		// ����ť����ӵ��˵�����
		menu4.add(item1);
		menu4.add(item2);
		item2.setSelected(true);

		// ��������
		JMenu menu5 = new JMenu("��������"); // �����������á��˵���ӵ������á�����
		menu2.add(menu5);
		// ���ú������л��ǰ������еİ�ť
		JRadioButtonMenuItem item3 = new JRadioButtonMenuItem("�ڷ�����");
		JRadioButtonMenuItem item4 = new JRadioButtonMenuItem("��������");
		// ����item3��������¼������úڷ�����
		item3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���沢���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ񱣴����ò����¿�ʼ", "�˻���������", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.setIsBlack(true);
					panel.Start();
					item3.setSelected(true);
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// ����item4��������¼�
		item4.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���沢���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ񱣴����ò����¿�ʼ", "�˻���������", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.setIsBlack(false);
					panel.Start();
					item4.setSelected(true);
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// ���ð�ť�鲢���˻����������˲�����ӵ�һ����ť������
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(item3);
		bg1.add(item4);
		// ����ť����ӵ��˵�����
		menu5.add(item3);
		menu5.add(item4);
		item3.setSelected(true);
		// ���á��������������Ŀ¼
		JMenuItem menu6 = new JMenuItem("����");
		menu3.add(menu6);
		/*
		 * �˵�����Ŀ¼�������
		 */
		// ��ʼ��Ϸ�˵�����
		JMenuItem menu7 = new JMenuItem("��ʼ��Ϸ");
		menu1.add(menu7);
		JMenuItem menu8 = new JMenuItem("���¿�ʼ");
		menu1.add(menu8);
		menu7.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				panel.Start();
				// panel.repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		menu8.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ����¿�ʼ", "��Ϣ", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.Start();
				}

				// panel.repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// �˳���Ϸѡ������
		JMenuItem menu9 = new JMenuItem("�˳���Ϸ");
		menu1.add(menu9);
		menu9.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "�˳���Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ��˳���Ϸ", "��Ϣ", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					System.exit(0);// �˳�����
				}

				// panel.repaint();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// ��Ϸ�Ѷ�����
		JRadioButtonMenuItem item5 = new JRadioButtonMenuItem("ɵ��");// ��Ӱ�ť
		JRadioButtonMenuItem item6 = new JRadioButtonMenuItem("��");
		JRadioButtonMenuItem item7 = new JRadioButtonMenuItem("��ͨ");
		// JRadioButtonMenuItem item8= new JRadioButtonMenuItem("����");
		ButtonGroup bg3 = new ButtonGroup();// ���ð�ť��
		bg3.add(item5);
		bg3.add(item6);
		bg3.add(item7);
		// bg3.add(item8);
		JMenu menu10 = new JMenu("��Ϸ�Ѷ�����");// ��Ӳ˵������˵�
		menu2.add(menu10);
		menu10.add(item5);// ���ѡ��Ѷ����ò˵�
		menu10.add(item6);
		menu10.add(item7);
		// menu2.add(item8);
		item5.setSelected(true);// Ĭ��ѡ�ť
		// ɵ���Ѷ����õ�������¼�
		item5.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���沢���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ񱣴����ò����¿�ʼ", "�˻���������", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.setGameDifficulty(0);
					panel.Start();
					item5.setSelected(true);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// ���Ѷ�����ģʽ
		item6.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���沢���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ񱣴����ò����¿�ʼ", "�Ѷ�����", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.setGameDifficulty(1);
					panel.Start();
					item6.setSelected(true);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// ��ͨ�Ѷ�����
		item7.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				Object[] options = { "���沢���¿�ʼ��Ϸ", "����лл" };
				int n = JOptionPane.showOptionDialog(null, "�Ƿ񱣴����ò����¿�ʼ", "�˻���������", 0, 1, icon, options, "���沢���¿�ʼ��Ϸ");
				if (n == 0) {
					panel.setGameDifficulty(2);
					panel.Start();
					item7.setSelected(true);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		//��Ϸ������ʾ��Ϣ
		menu6.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Icon icon = new Icon() {

					@Override
					public void paintIcon(Component c, Graphics g, int x, int y) {
						// TODO Auto-generated method stub

					}

					@Override
					public int getIconWidth() {
						// TODO Auto-generated method stub
						return 0;
					}

					@Override
					public int getIconHeight() {
						// TODO Auto-generated method stub
						return 0;
					}
				};
				JOptionPane.showMessageDialog(null, "������Ա�����콣");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		/*
		 * �����������������
		 */
		Container con = this.getContentPane(); // ʵ����һ����������
		con.add(panel); // ��������ӵ�����
		/*
		 * ��������������������
		 */

	}

}
