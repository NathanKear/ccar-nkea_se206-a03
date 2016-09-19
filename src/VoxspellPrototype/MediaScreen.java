package VoxspellPrototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MediaScreen extends Parent {
	
	private final int BTN_HEIGHT = 50;
	
	private Window _window;
	private EmbeddedMediaPlayerComponent _mediaPlayerComponent;
	private EmbeddedMediaPlayer _mediaPlayer;
	private String _currentMedia;
	
	public MediaScreen(Window window) {	
		
		this._window = window;
		_window.Show(false);
		
		testVLCJPresence();
        
		createJFrame().setVisible(true);
		
		_mediaPlayer.playMedia(_currentMedia = "media/bunny.mp4");
		_mediaPlayer.mute(false);
	}
	
	private void testVLCJPresence() {
		boolean found = new NativeDiscovery().discover();
        System.out.println(found);
        System.out.println(LibVlc.INSTANCE.libvlc_get_version());
	}
	
	private JFrame createJFrame() {
		final JFrame frame = new JFrame("Suprise!");	
    	final JButton btnPause = new JButton("Pause");
    	final JButton btnLeave = new JButton("Leave");
    	final JButton btnMute = new JButton("Mute");
    	final JButton btnForward = new JButton("Forward");
    	final JButton btnBack = new JButton("Back");
    	final JButton btnSpookify = new JButton("Spookify");
    	
    	btnPause.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	btnLeave.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	btnMute.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	btnForward.setPreferredSize(new Dimension(_window.GetWidth() / 3, BTN_HEIGHT));
    	btnBack.setPreferredSize(new Dimension(_window.GetWidth() / 3, BTN_HEIGHT));
    	btnSpookify.setPreferredSize(new Dimension(_window.GetWidth() / 3, BTN_HEIGHT));
    	
    	btnPause.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnLeave.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnMute.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnForward.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnBack.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnSpookify.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	
    	btnPause.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnLeave.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnMute.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnForward.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnBack.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnSpookify.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	
    	btnPause.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnLeave.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnMute.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnForward.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnBack.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnSpookify.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
        
    	btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (_mediaPlayer.isPlaying()) {
					_mediaPlayer.pause();
					btnPause.setText("Play");
				} else {
					_mediaPlayer.play();
					btnPause.setText("Pause");
				}
			}	
    	});
    	btnLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						_window.SetWindowScene(new Scene(new MainScreen(_window), _window.GetWidth(), _window.GetHeight()));
						_window.SetWindowPosition(frame.getX(), frame.getY());
						_window.Show(true);
					}
				});
				
				_mediaPlayer.stop();
				frame.dispose();
			}	
    	});
    	btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (_mediaPlayer.isMute()) {
					_mediaPlayer.mute(false);
					btnMute.setText("Unmute");
				} else {
					_mediaPlayer.mute(true);
					btnMute.setText("Mute");
				}
			}	
    	});
    	btnForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_mediaPlayer.skip(10000);
			}	
    	});
    	btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_mediaPlayer.skip(-10000);
			}	
    	});
    	btnSpookify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				long time = _mediaPlayer.getTime();
				boolean isMute = _mediaPlayer.mute();
				if (_currentMedia == "media/bunny.mp4") {
					_mediaPlayer.playMedia(_currentMedia = "media/spooky.mp4");
					btnSpookify.setText("Too spooky!");
					
				} else if (_currentMedia == "media/spooky.mp4") {
					_mediaPlayer.playMedia(_currentMedia = "media/bunny.mp4");
					btnSpookify.setText("Spookify");
				}
				_mediaPlayer.mute(isMute);
				_mediaPlayer.setTime(time);
			}	
    	});
    	
        _mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        _mediaPlayer = _mediaPlayerComponent.getMediaPlayer();
        
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.add(btnForward, BorderLayout.EAST);
        lowerPanel.add(btnBack, BorderLayout.WEST);
        lowerPanel.add(btnPause, BorderLayout.SOUTH);
        lowerPanel.add(btnMute, BorderLayout.NORTH);
        lowerPanel.add(btnSpookify, BorderLayout.CENTER);
        
        
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(_mediaPlayerComponent, BorderLayout.CENTER);
        frame.add(btnLeave, BorderLayout.NORTH);
        frame.add(lowerPanel, BorderLayout.SOUTH);
        frame.setLocation(_window.GetPosX(), _window.GetPosY());
        frame.setSize(_window.GetWidth(), _window.GetHeight() + 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        return frame;
	}
}
