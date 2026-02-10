public class PhysicalObjectR {
    int x;
	int y;
	int z;
	
	double roll;
	double pitch;
	double yaw;
    
	public void moveForward(double speed){
		z += (int)Math.round(speed * Math.cos(yaw));
		x += (int)Math.round(speed * Math.sin(yaw));
	}
	public void moveBackward(double speed){
		z -= (int)Math.round(speed * Math.cos(yaw));
		x -= (int)Math.round(speed * Math.sin(yaw));
	}
	public void moveLeft(double speed){
		z += (int)Math.round(speed * Math.sin(yaw));
		x -= (int)Math.round(speed * Math.cos(yaw));
	}
	public void moveRight(double speed){
		z -= (int)Math.round(speed * Math.sin(yaw));
		x += (int)Math.round(speed * Math.cos(yaw));
	} 
}
