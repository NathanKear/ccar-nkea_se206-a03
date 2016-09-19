package VoxspellPrototype;

import javafx.concurrent.Task;

class FestivalSpeakTask extends Task<Void> {

	private String _word;
	private static double _speed = 1.0;
	
	public static void SetSpeed(double speed) {
		if (speed > 0.0) {
			_speed = speed;
		}
	}
	
	public FestivalSpeakTask(String word) {
		this._word = word;
	}
	
	@Override
	protected Void call() throws Exception {

		String setSpeed = "(Parameter.set 'Duration_Stretch " + _speed + ")";
		String sayPhrase = "(SayText \"" + this._word + "\")";
		
		new ProcessBuilder("/usr/bin/festival", setSpeed, sayPhrase, "(exit)").start();
		
		return null;
	}
}
