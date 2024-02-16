
public class KeyControl {
	private KeyControlStrategy strategy;

	public void setKey(KeyControlStrategy strategy) {
		this.strategy = strategy;
	}

	public void executeKey() {
		strategy.execute();
	}
}