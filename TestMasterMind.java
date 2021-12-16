public class TestMasterMind {
    public static void main (String []args){
		MasterMind masterMind = new MasterMind(4);
		masterMind.printSecretCode();
		masterMind.play();
    }
}