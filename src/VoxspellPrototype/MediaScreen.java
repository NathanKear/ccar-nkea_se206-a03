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
import javax.swing.border.Border;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MediaScreen extends Parent {
	
	private final int BTN_HEIGHT = 70;
	
	private Window _window;
	private EmbeddedMediaPlayerComponent _mediaPlayerComponent;
	private EmbeddedMediaPlayer _mediaPlayer;
	
	public MediaScreen(Window window) {	
		
		this._window = window;
		_window.Show(false);
        
		createJFrame().setVisible(true);
		
		_mediaPlayer.playMedia("media/bunny.mp4");
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
    	
    	btnPause.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	btnLeave.setPreferredSize(new Dimension(_window.GetWidth(), BTN_HEIGHT));
    	
    	btnPause.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	btnLeave.setBackground(Color.decode(VoxspellPrototype.LIGHT_BLUE));
    	
    	btnPause.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	btnLeave.setForeground(Color.decode(VoxspellPrototype.WHITE));
    	
    	btnPause.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
    	btnLeave.setFont(new Font("Arial", Font.PLAIN, VoxspellPrototype.BTN_FONT_SIZE));
        
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
    	
        _mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        _mediaPlayer = _mediaPlayerComponent.getMediaPlayer();
        
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(_mediaPlayerComponent, BorderLayout.CENTER);
        frame.add(btnPause, BorderLayout.NORTH);
        frame.add(btnLeave, BorderLayout.SOUTH);
        frame.setLocation(_window.GetPosX(), _window.GetPosY());
        frame.setSize(_window.GetWidth(), _window.GetHeight() + 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        return frame;
	}
}
