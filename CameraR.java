


import java.awt.Color;


public class CameraR extends PhysicalObjectR{

	private double fovHori;
	private double fovVer;
	
	private double halfFovHori;
	private double halfFovVer;

	private int screenWidth;
	private int screenHeight;

	public Color[][] projection;

	private double halfScreenWidth;
	private double halfScreenHeight;


	public CameraR(int screenWidthIn, int screenHeightIn){
		this.x = 0;
		this.y = 0;
		this.z = 0;

		this.roll = 0;
		this.pitch = 0;
		this.yaw = 0;

		//this.fovHori = 3.49066; // 200 Degrees in Radians
		this.fovHori = 1.8326; // 105 Degrees in Radians
		
		//this.fovVer = 2.35619; // 135 Degrees in Radians
		this.fovVer = 1.22173; // 70 Degrees in Radians
	
		//Already in radians
		this.halfFovHori = fovHori / 2.0;	
		this.halfFovVer = fovVer / 2.0;
		
		this.screenWidth = screenWidthIn;
		this.screenHeight = screenHeightIn;

		this.halfScreenWidth = (double)(screenWidth)/2.0;
		this.halfScreenHeight = (double)(screenHeight)/2.0;

		this.projection = new Color[screenWidthIn][screenHeightIn];


	
	} 

	public CameraR(int xIn, int yIn, int zIn, 
				   double xAngIn, double yAngIn, double zAngIn, 
				   double fovHoriIn, double fovVerIn, 
				   int screenWidthIn, int screenHeightIn){
		this.x = xIn;
		this.y = yIn;
		this.z = zIn;
		this.roll = (xAngIn);
		this.pitch = (yAngIn);
		this.yaw = (zAngIn);
		this.fovHori = (fovHoriIn);
		this.fovVer = (fovVerIn);
		this.halfFovHori = ((fovHoriIn/2));
		this.halfFovVer = ((fovVerIn/2));
		this.screenWidth = screenWidthIn;
		this.screenHeight = screenHeightIn;
		this.projection = new Color[screenWidthIn][screenHeightIn];
	}

	
	public double getHalfFovHori(){
		return halfFovHori;
	}
	public double getHalfFovVer(){
		return halfFovVer;
	}
	
	public String toString(){
		return "DEBUG: \n x: "+ String.valueOf(this.x) +
					  "\n y: "+ String.valueOf(this.y) +
					  "\n z: "+ String.valueOf(this.z) +
					  "\n xAng: " + String.valueOf(this.roll) +
					  "\n yAng: " + String.valueOf(this.pitch) +
					  "\n zAng: " + String.valueOf(this.yaw) +
					  "\n fovHori: "+String.valueOf(this.halfFovHori) +
					  "\n fovVer: "+ String.valueOf(this.halfFovVer) +
					  "\n screenWidth: "+String.valueOf(this.screenWidth) +
					  "\n screenHeight: "+String.valueOf(this.screenHeight);
	}

	public void camUpdate(){
		//TODO: This
	}
	public void updateProjection(EnvironmentR env){


		for (int screenX = 0; screenX < this.screenWidth; screenX++){
			for (int screenY = 0; screenY < this.screenHeight; screenY++){

				Color calculatedColor = getColorAtScreenPosition(screenX, screenY, env);


				projection[screenX][screenY] = calculatedColor;

			}
		}
	}

	public Color getColorAtScreenPosition(int screenX, int screenY, EnvironmentR env){
		//Roll: x-axis
		//Pitch: y-axis
		//Yaw: z-axis

		//fovHori: Yaw, z-axis, screenX, zOffset
		//fovVer: Pitch, y-axis, screenY, yOffset

		double zOffset = (screenX - halfScreenWidth) * (halfFovHori/halfScreenWidth);
		double yOffset = (screenY - halfScreenHeight) * (halfFovVer/halfScreenHeight);

		// System.out.print("DEBUG: \nscreenX = "+String.valueOf(screenX)+"\nscreenY = "+String.valueOf(screenY)
		// +"\nzOffset = "+String.valueOf(zOffset*180/Math.PI)+"\nyOffset = "+String.valueOf(yOffset*180/Math.PI)+"\n\n");


		double[] endpoints = endpointFinder(this.x, this.y, this.z, this.roll, this.pitch+yOffset, this.yaw+zOffset, env.maxEnvLength);

		int currentPointX = this.x;
		int currentPointY = this.y;
		int currentPointZ = this.z;

		double x2 = (endpoints[0]);
		double y2 = (endpoints[1]);
		double z2 = (endpoints[2]);


		try{
			if (env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ)) != null){
				return env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ));
			}
		}
		catch(Exception e){
			return Color.BLACK;
		}
		

		double dx = Math.abs(x2 - currentPointX);
		double dy = Math.abs(y2 - currentPointY);
		double dz = Math.abs(z2 - currentPointZ);
		int xs;
		int ys;
		int zs;
		if (x2 > currentPointX) {
			xs = 1;
		} 
		else {
			xs = -1;
		}
		if (y2 > currentPointY) {
			ys = 1;
		} 
		else {
			ys = -1;
		}
		if (z2 > currentPointZ) {
			zs = 1;
		} 
		else {
			zs = -1;
		}

		// Driving axis is X-axis"
		if (dx >= dy && dx >= dz) {
		double p1 = 2 * dy - dx;
		double p2 = 2 * dz - dx;
		while (currentPointX != x2) {
			currentPointX += xs;
			if (p1 >= 0) {
				currentPointY += ys;
				p1 -= 2 * dx;
			}
			if (p2 >= 0) {
				currentPointZ += zs;
				p2 -= 2 * dx;
			}
			p1 += 2 * dy;
			p2 += 2 * dz;
			try{
				if (env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ)) != null){
					return env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ));
				}
			}
			catch(Exception e){
				return Color.BLACK;
			}
		}

		// Driving axis is Y-axis"
		} else if (dy >= dx && dy >= dz) {
		double p1 = 2 * dx - dy;
		double p2 = 2 * dz - dy;
		while (currentPointY != y2) {
			currentPointY += ys;
			if (p1 >= 0) {
				currentPointX += xs;
				p1 -= 2 * dy;
			}
			if (p2 >= 0) {
				currentPointZ += zs;
				p2 -= 2 * dy;
			}
			p1 += 2 * dx;
			p2 += 2 * dz;
					try{
			if (env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ)) != null){
				return env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ));
			}
			}
			catch(Exception e){
				return Color.BLACK;
			}
		}

		// Driving axis is Z-axis"
		} else {
		double p1 = 2 * dy - dz;
		double p2 = 2 * dx - dz;
		while (currentPointZ != z2) {
			currentPointZ += zs;
			if (p1 >= 0) {
				currentPointY += ys;
				p1 -= 2 * dz;
			}
			if (p2 >= 0) {
				currentPointX += xs;
				p2 -= 2 * dz;
			}
			p1 += 2 * dy;
			p2 += 2 * dx;
					try{
			if (env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ)) != null){
				return env.getColor((int)Math.round(currentPointX), (int)Math.round(currentPointY), (int)Math.round(currentPointZ));
			}
			}
			catch(Exception e){
				return Color.BLACK;
			}
		}
		}
		return Color.BLACK;
	}

	
	

	//Written by me, fixed by Copilot
	public double[] endpointFinder(int x, int y, int z, double xAng, double yAng, double zAng, double length){
    //Roll: x-axis
	//Pitch: y-axis
	//Yaw: z-axis

	//fovHori: Yaw, z-axis, screenX, zOffset
	//fovVer: Pitch, y-axis, screenY, yOffset
	
    double dx = Math.cos(yAng) * Math.sin(zAng);
    double dy = Math.sin(yAng);
    double dz = Math.cos(yAng) * Math.cos(zAng);

    double endPointX = x + (length * dx);
    double endPointY = y + (length * dy);
    double endPointZ = z + (length * dz);

    return new double[] { endPointX, endPointY, endPointZ };
	}


}