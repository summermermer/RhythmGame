package GameElement;

public class Beat {

   private double time;      //������ؼ� double �� ����
   private int noteName;
   
   //coin���� �ƴ��� Ȯ���ϴ� ����
   private boolean coin=false;
   
   public Beat(double time, int noteName) {
      this.time = time;
      this.noteName = noteName;
   }
   
   public Beat(double time, boolean coin, int noteName) {
      this.time = time;
      this.noteName = noteName;
      this.coin = coin;
   }
   
   public double getTime() {
      return time; 
   }
   public void setTime(double time) {
      this.time = time;
   }
   public int getNoteName() {
      return noteName;
   }
   public void setNoteName(int noteName) {
      this.noteName = noteName;
   }
   public boolean getCoin() {
      return coin;
   }
   
}