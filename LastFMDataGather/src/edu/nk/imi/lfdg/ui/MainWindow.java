package edu.nk.imi.lfdg.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.nk.imi.lfdg.network.HttpClientHelper;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try
		{
			HttpClientHelper.get("http://ws.audioscrobbler.com/2.0/?method=track.getTags&api_key=e62924d7da1c1bef1ab87b89e6660916&artist=AC/DC&track=Hells+Bells&user=RJ&format=json");
			System.out.println("dengdeng");
			//HttpClientHelper.get("http://www.apache.org/");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
