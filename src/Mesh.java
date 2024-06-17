import java.util.Vector;

public class Mesh {
  Vector<Triangle> tris;

  //constructors
  public Mesh(){
    
  }
  public Mesh(Vector<Triangle> newTris){
    this.tris = newTris;
  }
}
