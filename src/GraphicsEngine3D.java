import java.awt.Color;
import java.util.Vector;

public class GraphicsEngine3D extends PixelGameEngine{

  public static final Color BLACK = new Color(0);
  public static final Color WHITE = new Color(255,255,255);
  public Mesh meshCube = new Mesh();
  public Mat4x4 matProj = new Mat4x4();
  
  public GraphicsEngine3D(String sTitle, int width, int height, int scaleX, int scaleY) {
    super(sTitle, width, height, scaleX, scaleY);
  }
  public GraphicsEngine3D(String sTitle, int maxFPS, int width, int height, int scaleX, int scaleY) {
    super(sTitle,maxFPS, width, height, scaleX, scaleY);
  }


  public boolean OnUserCreate(){
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
    meshCube.tris = meshTris;

    //Projection Matrix
    float fNear = .1f;
    float fFar = 1000f;
    double fFov = 90f;
    float fAspectRatio = (float)ScreenHeight() / (float)ScreenWidth();
    float fFovRad = (float)Math.toRadians(fFov);

    Mat4x4 matProj = new Mat4x4();
    matProj.m[0][0] = fAspectRatio * fFovRad;
    matProj.m[1][1] = fFovRad;
    matProj.m[2][2] = fFar / (fFar - fNear);
    matProj.m[3][2] = (-fFar * fNear) / (fFar - fNear);
    matProj.m[2][3] = 1f;
    matProj.m[3][3] = 0f;
    return true;
  }

  public boolean OnUserUpdate(float fElapsedTime){
    Clear(BLACK);

    for(Triangle tri : meshCube.tris){
      Triangle triProjected = new Triangle(new Vec3d[]{MultiplyMatrixVector(tri.points[0], matProj), MultiplyMatrixVector(tri.points[1], matProj), MultiplyMatrixVector(tri.points[2], matProj)});

      triProjected.points[0].x += 1f;
      triProjected.points[0].y += 1f;
      triProjected.points[1].x += 1f;
      triProjected.points[1].y += 1f;
      triProjected.points[2].x += 1f;
      triProjected.points[2].y += 1f;

      triProjected.points[0].x *= .5f * (float)ScreenWidth();
      triProjected.points[0].y *= .5f * (float)ScreenHeight();
      triProjected.points[1].x *= .5f * (float)ScreenWidth();
      triProjected.points[1].y *= .5f * (float)ScreenHeight();
      triProjected.points[2].x *= .5f * (float)ScreenWidth();
      triProjected.points[2].y *= .5f * (float)ScreenHeight();

      DrawTriangle(WHITE, triProjected.points[0].x, triProjected.points[0].y, triProjected.points[1].x, triProjected.points[1].y, triProjected.points[2].x, triProjected.points[2].y);
    }
    return true;
  }


  private Vec3d MultiplyMatrixVector(Vec3d vec, Mat4x4 mat){
    Vec3d output = new Vec3d();
    output.x = (vec.x * mat.m[0][0]) + (vec.y * mat.m[1][0]) + (vec.z * mat.m[2][0]) + mat.m[3][0];
    output.y = (vec.x * mat.m[0][1]) + (vec.y * mat.m[1][1]) + (vec.z * mat.m[2][1]) + mat.m[3][1];
    output.z = (vec.x * mat.m[0][2]) + (vec.y * mat.m[1][2]) + (vec.z * mat.m[2][2]) + mat.m[3][2];
    float w = (vec.x * mat.m[0][3]) + (vec.y * mat.m[1][3]) + (vec.z * mat.m[2][3]) + mat.m[3][3];

    if (w != 0){
      output.x /= w;
      output.y /= w;
      output.z /= w;
    }

    return output;
  }


  public static void main(String[] args){
    GraphicsEngine3D demo = new GraphicsEngine3D("3D Demo", 256, 240, 1, 1);
    demo.start();
  }
}

