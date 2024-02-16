package keycontroller;

public enum ControlCommands {

	LEFTFORWARD(new KeyForward()),
	LEFTBACKWARD(new KeyBackward()),
	LEFTSPACEBAR(new KeyPause()),

	RIGHTCLICK(new KeyClick()),
	RIGHTMOUSE(new KeyMouse());


	private final KeyControlStrategy controlStrategy;
	private ControlCommands(KeyControlStrategy cnt) {this.controlStrategy = cnt;}

	public KeyControlStrategy getCommandStrategy() {return this.controlStrategy;}
}
