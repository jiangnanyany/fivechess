package StartGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//设置窗体里面的容器操作
public class MyPanel extends JPanel implements MouseListener, Runnable {
	private static final Toolkit ResourceUtil = null;
	public Image boardImg; // 定义背景图片

	static int[][] allChess = new int[15][15]; // 棋盘数组
	static int[][] temporaryChess = new int[15][15];
	int x;// 保存棋子的横坐标
	int y;// 保存棋子的纵坐标
	Boolean canPlay = false; // 游戏是否继续，默认为继续
	Boolean isBlack = true;// 是否是黑子，默认为黑子
	Boolean isManAgainst = false; // 判断是否是人人对战
	String message = "用户下棋";
	Thread t = new Thread(this);
	int maxTime = 120;
	int blackTime = 120;
	int whiteTime = 120;
	String blackMessage = "无限制";
	String whiteMessage = "无限制";
	static int gameDifficulty = 0;// 设置游戏难度，0为傻瓜模式，1为简单，2为普通，3为困难
	// 获取isBlack的值

	public boolean getIsBlack() {
		return this.isBlack;
	}

	// 设置isBlack的值
	public void setIsBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	// 获取isManAgainst的值
	public boolean getIsManAgainst() {
		return this.isManAgainst;
	}

	// 获取isManAgainst的值
	public void setIsManAgainst(boolean isManAgainst) {
		this.isManAgainst = isManAgainst;
	}

	// 获取isManAgainst的值
	public int getGameDifficulty() {
		return this.gameDifficulty;
	}

	// 设置setGameDifficulty的值
	public void setGameDifficulty(int gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}

	// 构造函数
	public MyPanel() {

		boardImg = Toolkit.getDefaultToolkit().getImage("./src/StartGame/fiveCHessBourd.jpg");
		this.repaint();
		// 添加鼠标监视器
		addMouseListener((MouseListener) this);
		// addMouseMotionListener((MouseMotionListener) this);
		// this.requestFocus();
		t.start();
		t.suspend();// 线程挂起

		// t.resume();

	}

	// 数据初始化

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		int imgWidth = boardImg.getWidth(this); // 获取图片的宽度
		int imgHeight = boardImg.getHeight(this); // 获取图片的高度
		int FWidth = getWidth();
		int FHeight = getHeight();
		String message; // 标记谁下棋
		int x = (FWidth - imgWidth) / 2;
		int y = (FHeight - imgHeight) / 2;
		g.drawImage(boardImg, x, y, null); // 添加背景图片到容器里面
		g.setFont(new Font("宋体", 0, 14));
		g.drawString("黑方时间：" + blackTime, 30, 470);
		g.drawString("白方时间：" + whiteTime, 260, 470);
		// 绘制棋盘
		for (int i = 0; i < 15; i++) {
			g.drawLine(30, 30 + 30 * i, 450, 30 + 30 * i);
			g.drawLine(30 + 30 * i, 30, 30 + 30 * i, 450);
		}

		// 绘制五个中心点
		g.fillRect(240 - 5, 240 - 5, 10, 10); // 绘制最中心的正方形
		g.fillRect(360 - 5, 360 - 5, 10, 10); // 绘制右下的正方形
		g.fillRect(360 - 5, 120 - 5, 10, 10); // 绘制右上的正方形
		g.fillRect(120 - 5, 360 - 5, 10, 10);// 绘制左下的正方形
		g.fillRect(120 - 5, 120 - 5, 10, 10);// 绘制左上的正方形

		// 定义棋盘数组

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				// if (allChess[i][j] == 1) {
				// // 黑子
				// int tempX = i * 30 + 30;
				// int tempY = j * 30 + 30;
				// g.fillOval(tempX - 7, tempY - 7, 14, 14);
				// }
				// if (allChess[i][j] == 2) {
				// // 白子
				// int tempX = i * 30 + 30;
				// int tempY = j * 30 + 30;
				// g.setColor(Color.WHITE);
				// g.fillOval(tempX - 7, tempY - 7, 14, 14);
				// g.setColor(Color.BLACK);
				// g.drawOval(tempX - 7, tempY - 7, 14, 14);
				// }
				draw(g, i, j); // 调用下棋子函数
			}

		}
	}

	// 鼠标点击时发生的函数
	@Override
	public void mousePressed(MouseEvent e) {
		// x = e.getX();// 获取鼠标点击坐标的横坐标
		// y = e.getY();// 获取鼠标点击坐标的纵坐标
		// if (x >= 29 && x <= 451 && y >= 29 && y <= 451) { // 鼠标点击在棋子框里面才有效
		//
		// }

		if (canPlay == true) {// 判断是否可以开始游戏
			x = e.getX(); // 获取鼠标的焦点
			y = e.getY();
			if (isManAgainst == true) {// 判断是否是人人对战
				manToManChess();
			} else { // 否则是人机对战，人机下棋
				manToMachine();
			}
		}
	}

	// 判断是否输赢的函数
	private boolean checkWin(int x, int y) {
		// TODO Auto-generated method stub

		boolean flag = false;
		// 保存共有多少相同颜色棋子相连
		int count = 1;
		// 判断横向 特点：allChess[x][y]中y值相同
		int color = allChess[x][y];
		// 判断横向
		count = this.checkCount(x, y, 1, 0, color);
		if (count >= 5) {
			flag = true;
		} else {
			// 判断纵向
			count = this.checkCount(x, y, 0, 1, color);
			if (count >= 5) {
				flag = true;
			} else {
				// 判断右上左下
				count = this.checkCount(x, y, 1, -1, color);
				if (count >= 5) {
					flag = true;
				} else {
					// 判断左下右上
					count = this.checkCount(x, y, 1, 1, color);
					if (count >= 5) {
						flag = true;
					}
				}
			}
		}
		return flag;
	}

	// 判断相同棋子连接的个数
	private int checkCount(int x, int y, int xChange, int yChange, int color) {
		// TODO Auto-generated method stub
		int count = 1;
		int tempX = xChange;
		int tempY = yChange;
		while (x + xChange >= 0 && x + xChange <= 14 && y + yChange >= 0 && y + yChange <= 14
				&& color == allChess[x + xChange][y + yChange]) {
			count++;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange != 0) {
				if (yChange > 0) {
					yChange++;
				} else {
					yChange--;
				}
			}
		}
		xChange = tempX;
		yChange = tempY;
		while (x - xChange >= 0 && x - xChange <= 14 && y - yChange >= 0 && y - yChange <= 14
				&& color == allChess[x - xChange][y - yChange]) {
			count++;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange != 0) {
				if (yChange > 0) {
					yChange++;
				} else {
					yChange--;
				}
			}
		}
		return count;
	}

	// 机器人判断黑棋相连的数量
	private int checkCountMachine(int x, int y, int xChange, int yChange, int color) {
		// TODO Auto-generated method stub
		int count = 0;
		int tempX = xChange;
		int tempY = yChange;
		while (x + xChange >= 0 && x + xChange <= 14 && y + yChange >= 0 && y + yChange <= 14
				&& color == allChess[x + xChange][y + yChange]) {
			count++;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange != 0) {
				if (yChange > 0) {
					yChange++;
				} else {
					yChange--;
				}
			}
		}
		xChange = tempX;
		yChange = tempY;
		while (x - xChange >= 0 && x - xChange <= 14 && y - yChange >= 0 && y - yChange <= 14
				&& color == allChess[x - xChange][y - yChange]) {
			count++;
			if (xChange != 0) {
				xChange++;
			}
			if (yChange != 0) {
				if (yChange > 0) {
					yChange++;
				} else {
					yChange--;
				}
			}
		}
		return count;
	}

	public void paintConmponents(Graphics g) {
		super.paintComponents(g);

	}

	// 绘制黑白棋子
	public void draw(Graphics g, int i, int j) {
		if (allChess[i][j] == 1) {
			g.setColor(Color.black);// 黑色棋子
			g.fillOval(30 * i + 30 - 7, 30 * j + 30 - 7, 14, 14);
			g.drawString(message, 230, 20);

		}
		if (allChess[i][j] == 2) {
			g.setColor(Color.white);// 白色棋子
			g.fillOval(30 * i + 30 - 7, 30 * j + 30 - 7, 14, 14);
			g.drawString(message, 230, 20);

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 判断是否有时间限制

		if (maxTime > 0) {
			while (true) {
				// System.out.println(canPlay + "11");
				if (isManAgainst) {
					if (isBlack) {
						blackTime--;
						if (blackTime == 0) {
							JOptionPane.showMessageDialog(this, "黑方超时，游戏结束!");
						}
					} else {
						whiteTime--;
						if (whiteTime == 0) {
							JOptionPane.showMessageDialog(this, "白方超时，游戏结束!");
						}
					}
				} else {
					// 监控黑子下棋的时间，也就是用户下棋的时间
					blackTime--;
					if (blackTime == 0) {
						JOptionPane.showMessageDialog(this, "用户超时，游戏结束!");
					}

					// 不监控电脑白字下棋

				}
				blackMessage = blackTime / 3600 + ":" + (blackTime / 60 - blackTime / 3600 * 60) + ":"
						+ (blackTime - blackTime / 60 * 60);
				whiteMessage = whiteTime / 3600 + ":" + (whiteTime / 60 - whiteTime / 3600 * 60) + ":"
						+ (whiteTime - whiteTime / 60 * 60);

				this.repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	// 点击开始游戏设置属性，游戏开始
	public void Start() {
		this.canPlay = true;
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 14; j++) {
				allChess[i][j] = 0;
			}
		}
		if (canPlay == true) {
			t.resume();
		}
		this.repaint();
		JOptionPane.showMessageDialog(this, "游戏开始了，请开始下棋");

		if (isBlack == false && isManAgainst == false) {
			machineChess(gameDifficulty);
		}
		// 另一种方式 allChess=new int[19][19]
		// message = "黑方先行";
		//
		// isBlack = true;
		// blackTime = maxTime;
		// whiteTime = maxTime;
		// if (maxTime > 0) {
		// blackMessage = maxTime / 3600 + ":" + (maxTime / 60 - maxTime / 3600
		// * 60) + ":"
		// + (maxTime - maxTime / 60 * 60);
		// whiteMessage = maxTime / 3600 + ":" + (maxTime / 60 - maxTime / 3600
		// * 60) + ":"
		// + (maxTime - maxTime / 60 * 60);
		// t.resume();
		// } else {
		// blackMessage = "无限制";
		// whiteMessage = "无限制";
		// }
		// this.repaint();// 如果不重新调用，则界面不会刷新

	}

	// 人人对战下棋函数
	public void manToManChess() {
		if (x >= 29 && x <= 451 && y >= 29 && y <= 451) {
			// System.out.println("在棋盘范围内："+x+"--"+y);
			x = (x + 15) / 30 - 1; // 是为了取得交叉点的坐标
			y = (y + 15) / 30 - 1;
			if (allChess[x][y] == 0) {
				// 判断当前要下的是什么棋子
				if (isBlack == true) {
					allChess[x][y] = 1;
					isBlack = false;
					blackTime = 120;
					message = "轮到白方";
				} else {
					allChess[x][y] = 2;
					isBlack = true;
					whiteTime = 120;
					message = "轮到黑方";
				}
			}

			// 判断这个棋子是否和其他棋子连成5个
			boolean winFlag = this.checkWin(x, y);
			this.repaint(); // 绘制棋子
			if (winFlag == true) {
				JOptionPane.showMessageDialog(this, "游戏结束," + (allChess[x][y] == 1 ? "黑方" : "白方") + "获胜!");
				canPlay = false;
			}
		} else {
			// JOptionPane.showMessageDialog(this,
			// "当前位子已经有棋子，请重新落子！！！");
		}
	}

	// 人机对战下棋函数
	public void manToMachine() {
		if (x >= 29 && x <= 451 && y >= 29 && y <= 451) {
			// System.out.println("在棋盘范围内："+x+"--"+y);
			x = (x + 15) / 30 - 1; // 是为了取得交叉点的坐标
			y = (y + 15) / 30 - 1;
			if (allChess[x][y] == 0) {
				// 判断当前要下的是什么棋子
				if (isBlack == true) {
					allChess[x][y] = 1;
					this.repaint(); // 绘制棋子
					machineChess(gameDifficulty);
					isBlack = true;
					blackTime = 120;
					message = "用户下棋";
					whiteTime = 120;
					boolean winFlag = this.checkWin(x, y);
					this.repaint(); // 绘制棋子

					if (winFlag == true) {
						JOptionPane.showMessageDialog(this, "游戏结束," + (allChess[x][y] == 1 ? "黑方" : "白方") + "获胜!");
						canPlay = false;
					}

				} else {

					allChess[x][y] = 1;
					// allChess[x][y] = 2;
					this.repaint();
					isBlack = false;

					whiteTime = 120;
					blackTime = 120;
					boolean winFlag = this.checkWin(x, y);
					this.repaint(); // 绘制棋子

					if (winFlag == true) {
						JOptionPane.showMessageDialog(this, "游戏结束," + (allChess[x][y] == 1 ? "黑方" : "白方") + "获胜!");
						canPlay = false;
					}
					machineChess(gameDifficulty);
				}
			}

			// 判断这个棋子是否和其他棋子连成5个
			// boolean winFlag = this.checkWin(x, y);
			// this.repaint(); // 绘制棋子
			//
			// if (winFlag == true) {
			// JOptionPane.showMessageDialog(this, "游戏结束," + (allChess[x][y] ==
			// 1 ? "黑方" : "白方") + "获胜!");
			// canPlay = false;
			// }
		}
	}

	// 机器人下棋算法
	public void machineChess(int gameDifficulty) {
		if (gameDifficulty == 0) {
			int i, j;
			boolean chessSucceed = true;// 下棋成功的标志
			while (chessSucceed) {
				i = (int) (Math.random() * 15);
				j = (int) (Math.random() * 15);
				if (allChess[i][j] == 0) {
					allChess[i][j] = 2;
					chessSucceed = false;
				}
			}
		} else if (gameDifficulty == 1) {
			int max = 0;
			int m = 0, n = 0;
			int figureSort[] = new int[4];// 对棋子的四个方向进行打分
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (allChess[i][j] == 0) {
						figureSort[0] = checkCountMachine(i, j, 0, 1, 1);
						figureSort[1] = checkCountMachine(i, j, 1, 0, 1);
						figureSort[2] = checkCountMachine(i, j, 1, -1, 1);
						figureSort[3] = checkCountMachine(i, j, 1, 1, 1);
						sortFourFigure(figureSort);
						temporaryChess[i][j] = figureSort[0] * 12 + figureSort[1] * 25 + figureSort[2] * 50// 算出空白棋子的最大分
								+ figureSort[3] * 100;
					}
					// System.out.print(temporaryChess[i][j] + " ");
				}
				// System.out.print("\n");
			}
			// System.out.print("=============================\n");
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (temporaryChess[i][j] > max && allChess[i][j] != 2 && allChess[i][j] != 1) {
						m = i;
						n = j;
						max = temporaryChess[i][j];
					}
				}
			}
			allChess[m][n] = 2;

		} else if (gameDifficulty == 2) {

		} else {

		}
		this.repaint();
	}

	// 对数组排序
	public void sortFourFigure(int n[]) {
		Arrays.sort(n);// 正序排序
	}
}
