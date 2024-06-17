import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JFrame;


public class Display extends Canvas{
  public static void main(String[] args) {
    //Creating display window
    JFrame frame = new JFrame("Display");
    Canvas canvas = new Display();
    canvas.setSize(400, 400);
    frame.add(canvas);
    frame.pack();
    frame.setVisible(true);

    /* The following is the layout of a cube mesh in the following steps:
     * 1. Define a Vec3d for every point on the square 
     * 2. Create tris out of groups of three points. These tris should correspond to the 6 faces on the cube
     * 3. Add those tris to a Vector. This will be our Mesh
    */

    // 8 points of the unit cube
    Vec3d point1 = new Vec3d(0.0f, 0.0f, 0.0f);
    Vec3d point2 = new Vec3d(0.0f, 1.0f, 0.0f);
    Vec3d point3 = new Vec3d(1.0f, 1.0f, 0.0f);
    Vec3d point4 = new Vec3d(1.0f, 0.0f, 0.0f);
    Vec3d point5 = new Vec3d(0.0f, 0.0f, 1.0f);
    Vec3d point6 = new Vec3d(0.0f, 1.0f, 1.0f);
    Vec3d point7 = new Vec3d(1.0f, 1.0f, 1.0f);
    Vec3d point8 = new Vec3d(1.0f, 0.0f, 1.0f);

    //South Face
    Triangle triSA = new Triangle(new Vec3d[]{point1,point2,point3});
    Triangle triSB = new Triangle(new Vec3d[]{point1,point3,point4});

    //East Face
    Triangle triEA = new Triangle(new Vec3d[]{point4,point3,point7});
    Triangle triEB = new Triangle(new Vec3d[]{point4,point7,point8});

    //West Face
    Triangle triWA = new Triangle(new Vec3d[]{point5,point6,point2});
    Triangle triWB = new Triangle(new Vec3d[]{point5,point2,point1});

    //North Face
    Triangle triNA = new Triangle(new Vec3d[]{point8,point7,point6});
    Triangle triNB = new Triangle(new Vec3d[]{point8,point6,point5});

    //Top Face
    Triangle triTA = new Triangle(new Vec3d[]{point2,point6,point7});
    Triangle triTB = new Triangle(new Vec3d[]{point2,point7,point3});

    //Bottom Face
    Triangle triBA = new Triangle(new Vec3d[]{point1,point5,point8});
    Triangle triBB = new Triangle(new Vec3d[]{point1,point8,point4});

    //Putting all tris in the Vector
    Vector<Triangle> meshTris = new Vector<Triangle>();
    meshTris.add(triSA);
    meshTris.add(triSB);
    meshTris.add(triEA);
    meshTris.add(triEB);
    meshTris.add(triWA);
    meshTris.add(triWB);
    meshTris.add(triNA);
    meshTris.add(triNB);
    meshTris.add(triTA);
    meshTris.add(triTB);
    meshTris.add(triBA);
    meshTris.add(triBB);
    
    //Create mesh
    Mesh meshCube = new Mesh(meshTris);
  }

  //overridign the paint method in canvas to paint what we want
  public void paint(Graphics g) {
    g.drawLine(100, 100, 200, 200);
  }
}
