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

//窗体框架类
public class MyFrame extends JFrame {
	// static boolean canPlay1 = false;判断是否可以开始游戏
	final MyPanel panel = new MyPanel();

	public MyFrame() {

		// 设置窗体的大小并居中
		this.setSize(500, 600); // 设置窗体大小
		this.setTitle("五子棋游戏"); // 设置窗体标题

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;// 获取屏幕的宽度
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;// 获取屏幕的高度
		this.setLocation((width - 500) / 2, (height - 500) / 2); // 设置窗体的位置（居中）
		this.setResizable(false); // 设置窗体不可以放大
		// this.setLocationRelativeTo(null);//这句话也可以设置窗体居中
		/*
		 * 菜单栏的目录设置
		 */
		// 设置菜单栏
		JMenuBar bar = new JMenuBar();
		this.setJMenuBar(bar);
		// 添加菜单栏目录
		JMenu menu1 = new JMenu("游戏菜单"); // 实例化菜单栏目录
		JMenu menu2 = new JMenu("设置");
		JMenu menu3 = new JMenu("帮助");
		bar.add(menu1); // 将目录添加到菜单栏
		bar.add(menu2);
		bar.add(menu3);

		JMenu menu4 = new JMenu("博弈模式"); // 将“模式”菜单添加到“设置”里面
		menu2.add(menu4);

		// JMenuItem item1=new JMenuItem("人人博弈");
		// JMenuItem item2=new JMenuItem("人机博弈");
		// 设置“”目录下面的子目录
		JRadioButtonMenuItem item1 = new JRadioButtonMenuItem("人人博弈");
		JRadioButtonMenuItem item2 = new JRadioButtonMenuItem("人机博弈");
		// item1按钮添加时间并且为匿名类
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
				Object[] options = { "保存并重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否保存设置并重新开始", "人机博弈设置", 0, 1, icon, options, "保存并重新开始游戏");
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
		// 设置item2按钮的事件监听事件，也就是设置人机博弈
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
				Object[] options = { "保存并重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否保存设置并重新开始", "人机博弈设置", 0, 1, icon, options, "保存并重新开始游戏");
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
		// 设置按钮组并把人机博弈与人人博弈添加到一个按钮组里面
		ButtonGroup bg = new ButtonGroup();
		bg.add(item1);
		bg.add(item2);
		// 将按钮组添加到菜单里面
		menu4.add(item1);
		menu4.add(item2);
		item2.setSelected(true);

		// 先行设置
		JMenu menu5 = new JMenu("先行设置"); // 将“先行设置”菜单添加到“设置”里面
		menu2.add(menu5);
		// 设置黑子先行还是白字先行的按钮
		JRadioButtonMenuItem item3 = new JRadioButtonMenuItem("黑方先行");
		JRadioButtonMenuItem item4 = new JRadioButtonMenuItem("白字先行");
		// 设置item3的鼠标点击事件，设置黑方先行
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
				Object[] options = { "保存并重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否保存设置并重新开始", "人机博弈设置", 0, 1, icon, options, "保存并重新开始游戏");
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
		// 设置item4的鼠标点击事件
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
				Object[] options = { "保存并重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否保存设置并重新开始", "人机博弈设置", 0, 1, icon, options, "保存并重新开始游戏");
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
		// 设置按钮组并把人机博弈与人人博弈添加到一个按钮组里面
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(item3);
		bg1.add(item4);
		// 将按钮组添加到菜单里面
		menu5.add(item3);
		menu5.add(item4);
		item3.setSelected(true);
		// 设置“帮助”下面的子目录
		JMenuItem menu6 = new JMenuItem("帮助");
		menu3.add(menu6);
		/*
		 * 菜单栏的目录设置完毕
		 */
		// 开始游戏菜单设置
		JMenuItem menu7 = new JMenuItem("开始游戏");
		menu1.add(menu7);
		JMenuItem menu8 = new JMenuItem("重新开始");
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
				Object[] options = { "重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否重新开始", "消息", 0, 1, icon, options, "保存并重新开始游戏");
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
		// 退出游戏选项设置
		JMenuItem menu9 = new JMenuItem("退出游戏");
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
				Object[] options = { "退出游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否退出游戏", "消息", 0, 1, icon, options, "保存并重新开始游戏");
				if (n == 0) {
					System.exit(0);// 退出程序
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
		// 游戏难度设置
		JRadioButtonMenuItem item5 = new JRadioButtonMenuItem("傻瓜");// 添加按钮
		JRadioButtonMenuItem item6 = new JRadioButtonMenuItem("简单");
		JRadioButtonMenuItem item7 = new JRadioButtonMenuItem("普通");
		// JRadioButtonMenuItem item8= new JRadioButtonMenuItem("困难");
		ButtonGroup bg3 = new ButtonGroup();// 设置按钮组
		bg3.add(item5);
		bg3.add(item6);
		bg3.add(item7);
		// bg3.add(item8);
		JMenu menu10 = new JMenu("游戏难度设置");// 添加菜单到主菜单
		menu2.add(menu10);
		menu10.add(item5);// 添加选项到难度设置菜单
		menu10.add(item6);
		menu10.add(item7);
		// menu2.add(item8);
		item5.setSelected(true);// 默认选项按钮
		// 傻瓜难度设置的鼠标点击事件
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
				Object[] options = { "保存并重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否保存设置并重新开始", "人机博弈设置", 0, 1, icon, options, "保存并重新开始游戏");
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
		// 简单难度设置模式
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
				Object[] options = { "保存并重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否保存设置并重新开始", "难度设置", 0, 1, icon, options, "保存并重新开始游戏");
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
		// 普通难度设置
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
				Object[] options = { "保存并重新开始游戏", "不，谢谢" };
				int n = JOptionPane.showOptionDialog(null, "是否保存设置并重新开始", "人机博弈设置", 0, 1, icon, options, "保存并重新开始游戏");
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
		//游戏帮助提示信息
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
				JOptionPane.showMessageDialog(null, "制作人员：韩红剑");

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
		 * 窗口里面的容器设置
		 */
		Container con = this.getContentPane(); // 实例化一个容器父类
		con.add(panel); // 将容器添加到父类
		/*
		 * 窗口里面的容器设置完毕
		 */

	}

}
