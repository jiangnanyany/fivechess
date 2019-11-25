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

//���ô����������������
public class MyPanel extends JPanel implements MouseListener, Runnable {
	private static final Toolkit ResourceUtil = null;
	public Image boardImg; // ���屳��ͼƬ

	static int[][] allChess = new int[15][15]; // ��������
	static int[][] temporaryChess = new int[15][15];
	int x;// �������ӵĺ�����
	int y;// �������ӵ�������
	Boolean canPlay = false; // ��Ϸ�Ƿ������Ĭ��Ϊ����
	Boolean isBlack = true;// �Ƿ��Ǻ��ӣ�Ĭ��Ϊ����
	Boolean isManAgainst = false; // �ж��Ƿ������˶�ս
	String message = "�û�����";
	Thread t = new Thread(this);
	int maxTime = 120;
	int blackTime = 120;
	int whiteTime = 120;
	String blackMessage = "������";
	String whiteMessage = "������";
	static int gameDifficulty = 0;// ������Ϸ�Ѷȣ�0Ϊɵ��ģʽ��1Ϊ�򵥣�2Ϊ��ͨ��3Ϊ����
	// ��ȡisBlack��ֵ

	public boolean getIsBlack() {
		return this.isBlack;
	}

	// ����isBlack��ֵ
	public void setIsBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	// ��ȡisManAgainst��ֵ
	public boolean getIsManAgainst() {
		return this.isManAgainst;
	}

	// ��ȡisManAgainst��ֵ
	public void setIsManAgainst(boolean isManAgainst) {
		this.isManAgainst = isManAgainst;
	}

	// ��ȡisManAgainst��ֵ
	public int getGameDifficulty() {
		return this.gameDifficulty;
	}

	// ����setGameDifficulty��ֵ
	public void setGameDifficulty(int gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}

	// ���캯��
	public MyPanel() {

		boardImg = Toolkit.getDefaultToolkit().getImage("./src/StartGame/fiveCHessBourd.jpg");
		this.repaint();
		// �����������
		addMouseListener((MouseListener) this);
		// addMouseMotionListener((MouseMotionListener) this);
		// this.requestFocus();
		t.start();
		t.suspend();// �̹߳���

		// t.resume();

	}

	// ���ݳ�ʼ��

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		int imgWidth = boardImg.getWidth(this); // ��ȡͼƬ�Ŀ��
		int imgHeight = boardImg.getHeight(this); // ��ȡͼƬ�ĸ߶�
		int FWidth = getWidth();
		int FHeight = getHeight();
		String message; // ���˭����
		int x = (FWidth - imgWidth) / 2;
		int y = (FHeight - imgHeight) / 2;
		g.drawImage(boardImg, x, y, null); // ��ӱ���ͼƬ����������
		g.setFont(new Font("����", 0, 14));
		g.drawString("�ڷ�ʱ�䣺" + blackTime, 30, 470);
		g.drawString("�׷�ʱ�䣺" + whiteTime, 260, 470);
		// ��������
		for (int i = 0; i < 15; i++) {
			g.drawLine(30, 30 + 30 * i, 450, 30 + 30 * i);
			g.drawLine(30 + 30 * i, 30, 30 + 30 * i, 450);
		}

		// ����������ĵ�
		g.fillRect(240 - 5, 240 - 5, 10, 10); // ���������ĵ�������
		g.fillRect(360 - 5, 360 - 5, 10, 10); // �������µ�������
		g.fillRect(360 - 5, 120 - 5, 10, 10); // �������ϵ�������
		g.fillRect(120 - 5, 360 - 5, 10, 10);// �������µ�������
		g.fillRect(120 - 5, 120 - 5, 10, 10);// �������ϵ�������

		// ������������

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				// if (allChess[i][j] == 1) {
				// // ����
				// int tempX = i * 30 + 30;
				// int tempY = j * 30 + 30;
				// g.fillOval(tempX - 7, tempY - 7, 14, 14);
				// }
				// if (allChess[i][j] == 2) {
				// // ����
				// int tempX = i * 30 + 30;
				// int tempY = j * 30 + 30;
				// g.setColor(Color.WHITE);
				// g.fillOval(tempX - 7, tempY - 7, 14, 14);
				// g.setColor(Color.BLACK);
				// g.drawOval(tempX - 7, tempY - 7, 14, 14);
				// }
				draw(g, i, j); // ���������Ӻ���
			}

		}
	}

	// �����ʱ�����ĺ���
	@Override
	public void mousePressed(MouseEvent e) {
		// x = e.getX();// ��ȡ���������ĺ�����
		// y = e.getY();// ��ȡ����������������
		// if (x >= 29 && x <= 451 && y >= 29 && y <= 451) { // ����������ӿ��������Ч
		//
		// }

		if (canPlay == true) {// �ж��Ƿ���Կ�ʼ��Ϸ
			x = e.getX(); // ��ȡ���Ľ���
			y = e.getY();
			if (isManAgainst == true) {// �ж��Ƿ������˶�ս
				manToManChess();
			} else { // �������˻���ս���˻�����
				manToMachine();
			}
		}
	}

	// �ж��Ƿ���Ӯ�ĺ���
	private boolean checkWin(int x, int y) {
		// TODO Auto-generated method stub

		boolean flag = false;
		// ���湲�ж�����ͬ��ɫ��������
		int count = 1;
		// �жϺ��� �ص㣺allChess[x][y]��yֵ��ͬ
		int color = allChess[x][y];
		// �жϺ���
		count = this.checkCount(x, y, 1, 0, color);
		if (count >= 5) {
			flag = true;
		} else {
			// �ж�����
			count = this.checkCount(x, y, 0, 1, color);
			if (count >= 5) {
				flag = true;
			} else {
				// �ж���������
				count = this.checkCount(x, y, 1, -1, color);
				if (count >= 5) {
					flag = true;
				} else {
					// �ж���������
					count = this.checkCount(x, y, 1, 1, color);
					if (count >= 5) {
						flag = true;
					}
				}
			}
		}
		return flag;
	}

	// �ж���ͬ�������ӵĸ���
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

	// �������жϺ�������������
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

	// ���ƺڰ�����
	public void draw(Graphics g, int i, int j) {
		if (allChess[i][j] == 1) {
			g.setColor(Color.black);// ��ɫ����
			g.fillOval(30 * i + 30 - 7, 30 * j + 30 - 7, 14, 14);
			g.drawString(message, 230, 20);

		}
		if (allChess[i][j] == 2) {
			g.setColor(Color.white);// ��ɫ����
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
		// �ж��Ƿ���ʱ������

		if (maxTime > 0) {
			while (true) {
				// System.out.println(canPlay + "11");
				if (isManAgainst) {
					if (isBlack) {
						blackTime--;
						if (blackTime == 0) {
							JOptionPane.showMessageDialog(this, "�ڷ���ʱ����Ϸ����!");
						}
					} else {
						whiteTime--;
						if (whiteTime == 0) {
							JOptionPane.showMessageDialog(this, "�׷���ʱ����Ϸ����!");
						}
					}
				} else {
					// ��غ��������ʱ�䣬Ҳ�����û������ʱ��
					blackTime--;
					if (blackTime == 0) {
						JOptionPane.showMessageDialog(this, "�û���ʱ����Ϸ����!");
					}

					// ����ص��԰�������

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

	// �����ʼ��Ϸ�������ԣ���Ϸ��ʼ
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
		JOptionPane.showMessageDialog(this, "��Ϸ��ʼ�ˣ��뿪ʼ����");

		if (isBlack == false && isManAgainst == false) {
			machineChess(gameDifficulty);
		}
		// ��һ�ַ�ʽ allChess=new int[19][19]
		// message = "�ڷ�����";
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
		// blackMessage = "������";
		// whiteMessage = "������";
		// }
		// this.repaint();// ��������µ��ã�����治��ˢ��

	}

	// ���˶�ս���庯��
	public void manToManChess() {
		if (x >= 29 && x <= 451 && y >= 29 && y <= 451) {
			// System.out.println("�����̷�Χ�ڣ�"+x+"--"+y);
			x = (x + 15) / 30 - 1; // ��Ϊ��ȡ�ý���������
			y = (y + 15) / 30 - 1;
			if (allChess[x][y] == 0) {
				// �жϵ�ǰҪ�µ���ʲô����
				if (isBlack == true) {
					allChess[x][y] = 1;
					isBlack = false;
					blackTime = 120;
					message = "�ֵ��׷�";
				} else {
					allChess[x][y] = 2;
					isBlack = true;
					whiteTime = 120;
					message = "�ֵ��ڷ�";
				}
			}

			// �ж���������Ƿ��������������5��
			boolean winFlag = this.checkWin(x, y);
			this.repaint(); // ��������
			if (winFlag == true) {
				JOptionPane.showMessageDialog(this, "��Ϸ����," + (allChess[x][y] == 1 ? "�ڷ�" : "�׷�") + "��ʤ!");
				canPlay = false;
			}
		} else {
			// JOptionPane.showMessageDialog(this,
			// "��ǰλ���Ѿ������ӣ����������ӣ�����");
		}
	}

	// �˻���ս���庯��
	public void manToMachine() {
		if (x >= 29 && x <= 451 && y >= 29 && y <= 451) {
			// System.out.println("�����̷�Χ�ڣ�"+x+"--"+y);
			x = (x + 15) / 30 - 1; // ��Ϊ��ȡ�ý���������
			y = (y + 15) / 30 - 1;
			if (allChess[x][y] == 0) {
				// �жϵ�ǰҪ�µ���ʲô����
				if (isBlack == true) {
					allChess[x][y] = 1;
					this.repaint(); // ��������
					machineChess(gameDifficulty);
					isBlack = true;
					blackTime = 120;
					message = "�û�����";
					whiteTime = 120;
					boolean winFlag = this.checkWin(x, y);
					this.repaint(); // ��������

					if (winFlag == true) {
						JOptionPane.showMessageDialog(this, "��Ϸ����," + (allChess[x][y] == 1 ? "�ڷ�" : "�׷�") + "��ʤ!");
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
					this.repaint(); // ��������

					if (winFlag == true) {
						JOptionPane.showMessageDialog(this, "��Ϸ����," + (allChess[x][y] == 1 ? "�ڷ�" : "�׷�") + "��ʤ!");
						canPlay = false;
					}
					machineChess(gameDifficulty);
				}
			}

			// �ж���������Ƿ��������������5��
			// boolean winFlag = this.checkWin(x, y);
			// this.repaint(); // ��������
			//
			// if (winFlag == true) {
			// JOptionPane.showMessageDialog(this, "��Ϸ����," + (allChess[x][y] ==
			// 1 ? "�ڷ�" : "�׷�") + "��ʤ!");
			// canPlay = false;
			// }
		}
	}

	// �����������㷨
	public void machineChess(int gameDifficulty) {
		if (gameDifficulty == 0) {
			int i, j;
			boolean chessSucceed = true;// ����ɹ��ı�־
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
			int figureSort[] = new int[4];// �����ӵ��ĸ�������д��
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					if (allChess[i][j] == 0) {
						figureSort[0] = checkCountMachine(i, j, 0, 1, 1);
						figureSort[1] = checkCountMachine(i, j, 1, 0, 1);
						figureSort[2] = checkCountMachine(i, j, 1, -1, 1);
						figureSort[3] = checkCountMachine(i, j, 1, 1, 1);
						sortFourFigure(figureSort);
						temporaryChess[i][j] = figureSort[0] * 12 + figureSort[1] * 25 + figureSort[2] * 50// ����հ����ӵ�����
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

	// ����������
	public void sortFourFigure(int n[]) {
		Arrays.sort(n);// ��������
	}
}
