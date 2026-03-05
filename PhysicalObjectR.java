public class PhysicalObjectR {
    int x;
	int y;
	int z;
	
	double roll;
	double pitch;
	double yaw;

	public boolean leftBounds = false;

    
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
	public void update(EnvironmentR env){
		//TODO: Implement
		if (env.isOutsideRange(this.x, this.y, this.z)){
			this.posReset();
		}
	}
	public void posReset(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.roll = 0;
		this.yaw = 0;
		this.pitch = 0;
		this.leftBounds = true;
	}
}
