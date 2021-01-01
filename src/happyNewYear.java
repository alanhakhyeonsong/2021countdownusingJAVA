import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TimerThread extends Thread {
	private JLabel timerLabel;
	
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	
	@Override
	public void run() {
		while(true) {
			Calendar firstDay = Calendar.getInstance();
			firstDay.clear();
			firstDay.set(2020, 12, 31, 23, 59, 59);
			int hourFirstDay = firstDay.get(Calendar.HOUR_OF_DAY);
			int minFirstDay = firstDay.get(Calendar.MINUTE);
			int secondFirstDay = firstDay.get(Calendar.SECOND);
			
			Calendar now = Calendar.getInstance();
			
			int hourNow = now.get(Calendar.HOUR_OF_DAY);
			int minNow = now.get(Calendar.MINUTE);
			int secondNow = now.get(Calendar.SECOND);
			
			String clockText = Integer.toString(hourFirstDay-hourNow);
			clockText = clockText.concat("시간:");
			clockText = clockText.concat(Integer.toString(minFirstDay-minNow));
			clockText = clockText.concat("분:");
			clockText = clockText.concat(Integer.toString(secondFirstDay-secondNow + 1)); // 새해 첫 날 보다 1초 차이이기 때문
			timerLabel.setText("2021년까지: "+clockText);
			if((hourFirstDay-hourNow <= 0) && (minFirstDay-minNow < 0) && (secondFirstDay-secondNow+1 >= 0)) {
				timerLabel.setText("Happy New Year~! 2021년은 코로나 out!!");
			}
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) { return; }
		}
	}
}

public class happyNewYear extends JFrame {
	public happyNewYear() {
		setTitle("CountDown 2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		c.add(timerLabel);
		
		TimerThread th = new TimerThread(timerLabel);
		
		setSize(1700, 400);
		setVisible(true);
		th.start();
	}
	public static void main(String[] args) {
		new happyNewYear();
	}
}
