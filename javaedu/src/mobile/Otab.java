package mobile;

public class Otab extends Mobile {
	public Otab() {
	}
	public Otab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);		
	}
	public void operate(int time) {
		super.setBatterySize(super.getBatterySize() - time*12);
	}
	public void charge(int time) {
		super.setBatterySize(super.getBatterySize() + time*8);
	}

}
