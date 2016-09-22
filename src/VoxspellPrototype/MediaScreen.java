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
	
	// Media window
	private EmbeddedMediaPlayerComponent _mediaPlayerComponent;
	
	// Player for video
	private EmbeddedMediaPlayer _mediaPlayer;
	
	// Name of current media
	private String _currentMedia;
	
	private boolean _specialReward;
	
	public MediaScreen(Window window, boolean specialReward) {	
		
		this._window = window;
		this._specialReward = specialReward;
		_window.Show(false);
		
		testVLCJPresence();
        
		createJFrame().setVisible(true);
		
		_mediaPlayer.playMedia(_currentMedia = "media/bunny.mp4");
		_mediaPlayer.mute(false);
	}
	
	/**
	 * Is VLCJ supported on the machine
	 */
	private void testVLCJPresence() {
		boolean found = new NativeDiscovery().discover();
        System.out.println("VLCJ test: " + found);
        System.out.println("VLCJ version: " + LibVlc.INSTANCE.libvlc_get_version());
	}
	
	private JFrame createJFrame() {
		final JFrame frame = new JFrame("Suprise!");	
		
		// Create all buttons
    	final JButton btnPause = new JButton("Pause");
    	final JButton btnLeave = new JButton("Leave");
    	final JButton btnMute = new JButton("Mute");
    	final JButton btnForward = new JButton("Forward");
    	final JButton btnBack = new JButton("Back");
    	final JButton btnSpookify = new JButton("Spookify");
    	
    	// Set buttons size
    	btnPause.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	btnLeave.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	btnMute.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	btnForward.setPreferredSize(new Dimension(_window.GetWidth() / 3, BTN_HEIGHT));
    	btnBack.setPreferredSize(new Dimension(_window.GetWidth() / 3, BTN_HEIGHT));
    	btnSpookify.setPreferredSize(new Dimension(_window.GetWidth() / 3, BTN_HEIGHT));
    	
    	// Set button color
    	btnPause.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnLeave.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnMute.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnForward.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnBack.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnSpookify.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	
    	// Set button text color
    	btnPause.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnLeave.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnMute.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnForward.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnBack.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnSpookify.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	
    	// Set font and font size
    	btnPause.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnLeave.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnMute.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnForward.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnBack.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnSpookify.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	
    	// Enable special button on 10/10
    	btnSpookify.setEnabled(_specialReward);
        
    	// Pause play button action
    	btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Change between pausing and playing media
				if (_mediaPlayer.isPlaying()) {
					_mediaPlayer.pause();
					btnPause.setText("Play");
				} else {
					_mediaPlayer.play();
					btnPause.setText("Pause");
				}
			}	
    	});
    	
    	// Leave button action
    	btnLeave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Switch back to main screen on queued JavaFX thread
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
    	
    	// Mute unmute action
    	btnMute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Switch between muted and unmuted
				if (_mediaPlayer.isMute()) {
					_mediaPlayer.mute(false);
					btnMute.setText("Mute");
				} else {
					_mediaPlayer.mute(true);
					btnMute.setText("Unmute");
				}
			}	
    	});
    	
    	// Skip forward button action
    	btnForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_mediaPlayer.skip(10000);
			}	
    	});
    	
    	// Skip backwards button action
    	btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_mediaPlayer.skip(-10000);
			}	
    	});
    	
    	// Make spooky special reward action
    	btnSpookify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Switch between media sources. 
				// Make sure to set time on new media
				// to where old media was.
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
    	
    	// Create media player window and the video player
        _mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        _mediaPlayer = _mediaPlayerComponent.getMediaPlayer();
        
        // Layout buttons on JPanel
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
        
        // Position JFrame to size and position of previous Application window
        // So transition looks seamless
        frame.setLocation(_window.GetPosX(), _window.GetPosY());
        frame.setSize(_window.GetWidth(), _window.GetHeight() + 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        return frame;
	}
}
