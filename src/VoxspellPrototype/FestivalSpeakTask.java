package VoxspellPrototype;

import javafx.concurrent.Task;

class FestivalSpeakTask extends Task<Void> {

	private String _word;
	private static double _speed = 1.0;
	private static String _voice = "kal_diphone";
	
	public static void SetSpeed(double speed) {
		if (speed > 0.0) {
			_speed = speed;
		}
	}
	
	public FestivalSpeakTask(String word) {
		this._word = word;
	}

	public static void changeVoice(String voice) {
		_voice = voice;
	}
	
	public static void changeSpeed(double speed) {
		_speed = speed;
	}

	@Override
	protected Void call() throws Exception {

		String setVoice = "(voice_" + _voice + ")";
		String setSpeed = "(Parameter.set 'Duration_Stretch " + _speed + ")";
		String sayPhrase = "(SayText \"" + this._word + "\")";
		
		new ProcessBuilder("/usr/bin/festival", setVoice, setSpeed, sayPhrase, "(exit)").start();
		
		return null;
	}
}
