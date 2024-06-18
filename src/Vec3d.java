public class Vec3d {
  float x;
  float y; 
  float z;

  public Vec3d(){

  }
  public Vec3d(float x, float y, float z){
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public void printVec3d(){
    System.out.println(String.valueOf(x) + ", " + String.valueOf(y) + ", " + String.valueOf(z));
  }
}