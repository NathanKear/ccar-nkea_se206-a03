package VoxspellPrototype;

import javafx.concurrent.Task;

class FestivalSpeakTask extends Task<Void> {

	private String _word;

	public FestivalSpeakTask(String word) {
		this._word = word;
	}

	@Override
	protected Void call() throws Exception {
		new ProcessBuilder("/bin/bash", "-c", "echo \"" + _word + "\" | festival --tts").start();

		return null;
	}
}
