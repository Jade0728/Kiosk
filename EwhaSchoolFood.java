import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EwhaSchoolFood {
	int count = 0;
	String show = "";
	private TextField[] numb;
	
	public EwhaSchoolFood() {
        
		JFrame frame = new JFrame("EWHA 분식점");
		frame.setBounds(0, 0, 625, 1000);
		frame.setBackground(new Color(204, 255, 204)); //Frame
 
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
		Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22); //Font

		Label highbar = new Label();
		highbar.setText("EWHA 분식점");
		highbar.setFont(font1);
		
		Panel pNorth = new Panel();
		pNorth.setBackground(Color.LIGHT_GRAY);
		pNorth.setLayout(null);
		pNorth.setSize(0, 500);
		pNorth.setFont(font); //North Panel
		pNorth.add(highbar);
		
		String menu[] = {"떡볶이", "라면", "우동", "김밥", "쫄면", "냉면"};
		int price[] = {3000, 1500, 2000, 2500, 2500, 2500};
		JButton bt[] = new JButton[menu.length];
		TextField suja[] = new TextField[menu.length];
		Button minus[] = new Button[menu.length];
		Button plus[] = new Button[menu.length];
		JButton ok[] = new JButton[menu.length];
		Label l[] = new Label[menu.length];
		ImageIcon icon[] = new ImageIcon[menu.length];
 
		for (int i = 0; i < menu.length; i++) {

			bt[i] = new JButton(menu[i]);
			if (i < 4) {
				bt[i].setBounds(25 + i * 150, 50, 100, 100);
			} else {
				bt[i].setBounds(25 + (i - 4) * 150, 300, 100, 100);
			}
			icon[i] = new ImageIcon(i + ".png");
			bt[i].setIcon(icon[i]); //Menu Button
 
			numb = new TextField[menu.length];
			numb[i] = new TextField(0);
			numb[i].setBackground(Color.white);
			numb[i].setEditable(false);
			numb[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 130, 40, 20);
 
			minus[i] = new Button("-");
			minus[i].setBounds(bt[i].getX(), numb[i].getY(), 20, 20);
			minus[i].setEnabled(false); //Minus button
 
			plus[i] = new Button("+");
			plus[i].setBounds(bt[i].getX() + (100 - 20), numb[i].getY(), 20, 20);
			plus[i].setEnabled(false); // Plus button
 
			l[i] = new Label(price[i] + "원");
			l[i].setBounds(bt[i].getX() + 20, numb[i].getY() - 25, 100, 20); //price button
 
			ok[i] = new JButton("주문");
			ok[i].setBounds(bt[i].getX(), numb[i].getY() + 30, 100, 20);
			ok[i].setEnabled(false); //주문 button
 
			pNorth.add(bt[i]);
			pNorth.add(numb[i]);
			pNorth.add(minus[i]);
			pNorth.add(plus[i]);
			pNorth.add(l[i]);
			pNorth.add(ok[i]);
		}
 
        TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        ta.setText("  메뉴명        단가        수량        합계\n\n");
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font1); //Center

        Panel pSouth = new Panel();
        pSouth.setFont(font);
        pSouth.setBackground(new Color(255, 255, 215)); //South
 
        Button bt1 = new Button("주문");
        Button bt2 = new Button("초기화");
        pSouth.add(bt1);
        pSouth.add(bt2);
 
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        frame.add(pSouth, BorderLayout.SOUTH);
        frame.setVisible(true); //Component
 
        
        for (int i = 0; i < menu.length; i++) {
            int j = i;
 
            bt[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    minus[j].setEnabled(true);
                    plus[j].setEnabled(true);
                    bt[j].setEnabled(false);
                    ok[j].setEnabled(true);
 
                    count = 0;
                }
            }); //Order event
 
            minus[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count > 0) {
                        count = count - 1;
                        numb[j].setText(count + "");
                        ok[j].setEnabled(false);
                    } else {
                        minus[j].setEnabled(false);
                    }
                }
            }); // Minus button event
            
            plus[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = count + 1;
                    numb[j].setText(count + "");
                    ok[j].setEnabled(false);
                    if (count > 0) {
                        minus[j].setEnabled(true);
                    }
                }
            }); //Plus button event
            
            ok[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                    show = bt[j].getActionCommand();
                    ta.append("   " + show + "       " + price[j] + "        " + count + "         " + price[j]*count
                            + "원" + "\n");
                    ok[j].setEnabled(true);
                }
            });
 
        } //Order button event

        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    } //Exit
	
	
	public static void main(String[] args) {
		new EwhaSchoolFood();
	}

}
