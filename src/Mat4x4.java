public class Mat4x4 {
  float[][] m = new float[4][4];

  public Mat4x4(){
    
  }

  public void printMat(){
    for (int i = 0; i < m.length; i++){
      for (int j = 0; j < m[i].length; j++){
        System.out.print(m[i][j] + " ");
      }
    }
  }
}
