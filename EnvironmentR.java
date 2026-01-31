import java.awt.Color;

//this 3d space will have negative coordinates, which is why some values are multiplied
//by 2 and indexes are offset by the coordinate maxes (because an array can't have negative indexes)
public class EnvironmentR {
    Color[][][] envColors;

    // Bound of x value indexes are [-xMax, yMax] etc. for y and z
    int xMax;
    int yMax;
    int zMax;

    double maxEnvLength;


    public EnvironmentR(int xMaxIn, int yMaxIn, int zMaxIn){
        //one index is added for 0
        this.envColors = new Color[xMaxIn*2+1][yMaxIn*2+1][zMaxIn*2+1];
        this.xMax = xMaxIn;
        this.yMax = yMaxIn;
        this.zMax = zMaxIn;

        //Max Environment Length from corner to corner
        this.maxEnvLength = Math.sqrt(4*xMax*xMax+4*yMax*yMax+4*zMax*zMax);
    }
    public void setColor(Color c, int x, int y, int z){
        envColors[x+xMax][y+yMax][z+zMax] = c;
    }
    public Color getColor(int x, int y, int z){
        return envColors[x+xMax][y+yMax][z+zMax];
    }

    public void drawLine(Color c, int x1, int y1, int z1, int x2, int y2, int z2){
        //Bresenham's Algorithm to draw a line in 3D Space
        //Copied from https://www.geeksforgeeks.org/python/bresenhams-algorithm-for-3-d-line-drawing/
        this.setColor(c, x1, y1, z1);
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int dz = Math.abs(z2 - z1);
        int xs;
        int ys;
        int zs;
        if (x2 > x1) {
            xs = 1;
        } 
        else {
            xs = -1;
        }
        if (y2 > y1) {
            ys = 1;
        } 
        else {
            ys = -1;
        }
        if (z2 > z1) {
            zs = 1;
        } 
        else {
            zs = -1;
        }

        // Driving axis is X-axis"
        if (dx >= dy && dx >= dz) {
        int p1 = 2 * dy - dx;
        int p2 = 2 * dz - dx;
        while (x1 != x2) {
            x1 += xs;
            if (p1 >= 0) {
                y1 += ys;
                p1 -= 2 * dx;
            }
            if (p2 >= 0) {
                z1 += zs;
                p2 -= 2 * dx;
            }
            p1 += 2 * dy;
            p2 += 2 * dz;
            this.setColor(c, x1, y1, z1);
        }

        // Driving axis is Y-axis"
        } else if (dy >= dx && dy >= dz) {
        int p1 = 2 * dx - dy;
        int p2 = 2 * dz - dy;
        while (y1 != y2) {
            y1 += ys;
            if (p1 >= 0) {
                x1 += xs;
                p1 -= 2 * dy;
            }
            if (p2 >= 0) {
                z1 += zs;
                p2 -= 2 * dy;
            }
            p1 += 2 * dx;
            p2 += 2 * dz;
            this.setColor(c, x1, y1, z1);
        }

        // Driving axis is Z-axis"
        } else {
        int p1 = 2 * dy - dz;
        int p2 = 2 * dx - dz;
        while (z1 != z2) {
            z1 += zs;
            if (p1 >= 0) {
                y1 += ys;
                p1 -= 2 * dz;
            }
            if (p2 >= 0) {
                x1 += xs;
                p2 -= 2 * dz;
            }
            p1 += 2 * dy;
            p2 += 2 * dx;
            this.setColor(c, x1, y1, z1);
        }
        }

        


    }
    public void drawTetrahedron(Color c, int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3, int x4, int y4, int z4){
        this.drawLine(c, x1, y1, z1, x2, y2, z2);
        this.drawLine(c, x1, y1, z1, x3, y3, z3);
        this.drawLine(c, x1, y1, z1, x4, y4, z4);

        this.drawLine(c, x2, y2, z2, x3, y3, z3);
        this.drawLine(c, x2, y2, z2, x4, y4, z4);

        this.drawLine(c, x3, y3, z3, x4, y4, z4);
        
        

    }
    public void drawPlane(Color c, int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3, int x4, int y4, int z4){
 
    }
    public void clear(){
        for (int x = 0; x < envColors.length; x++){
            for (int y = 0; y < envColors[x].length; y++){
                for (int z = 0; z < envColors[x][y].length; z++){
                    envColors[x][y][z] = null;
                }
            }
        }
    }
    
    
    
    
    
    public String toString(){
        String returnString = "";
        for (Color[][] x:this.envColors){
            returnString+="slice:\n";
            for (Color[] y:x){
                for (Color z:y){
                    if (z == null){
                        returnString += "0 ";
                    }
                    else {
                        returnString += "1 ";
                    }
                }
                returnString+="\n";
            }
            returnString+="\n";
            returnString+="\n";
        }

        return returnString;
    }
}
