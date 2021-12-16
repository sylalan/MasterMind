import javax.swing.JOptionPane;

class MasterMind {
	private int lengthOfSecretCode = 6;
	private int [] secretCode = new int[lengthOfSecretCode];
	private int [] guessArray = new int[lengthOfSecretCode];
	private int [] digit = new int[lengthOfSecretCode];
	private int guessCount=0;
	private int maxInteger=0;
	public MasterMind(int amaxInteger){
		maxInteger=amaxInteger;
		for (int i=0;i<lengthOfSecretCode;i++)
			{secretCode[i]=(1+(int)(Math.random()*(maxInteger)));}
	}
	public void printSecretCode(){
		System.out.print ("secretCode = ");
		for (int i=0;i<lengthOfSecretCode;i++)
			{System.out.print (secretCode[i]);}
	}

	public int[] oneGuess(int guess){
		guessCount++;
		int [] hint=new int[2];
		int number = guess;
		int i = lengthOfSecretCode-1;
		while (number > 0) {
			int remainder = number % 10;
			number = number / 10;
			guessArray[i]=remainder;
			i--;
		}
		int match=0;
		for (int j=0;j<guessArray.length;j++) {
			if (secretCode[j]==guessArray[j])
				{match++;}
		}
		hint[0]=match;
		hint[1]=findHint1();
		System.out.println (+hint[0]+" position match and "+hint[1]+" digit match");
		return hint;
	}
	public int findHint1(){
		int count =0;
		for (int i=0; i<secretCode.length; i++) {
			if (secretCode[i]==guessArray[i])
				{digit[i]=-1;}
			else {digit[i]=secretCode[i];} 
		}
		/**** For debug use ******
		System.out.print (" digit[] = ");
		for (int i=0;i<lengthOfSecretCode;i++)
		{System.out.print (digit[i]+",");}
		**************************/
		for (int i=0; i<digit.length; i++) {
			for (int j=0; j<digit.length;j++) {
				if (guessArray[i]==digit[j]) {
					digit[j]=-1;
					count++;
					System.out.println ("digit match:"+i+j);
					//j=digit.length;
					break;
				}
			}
		}
		/**** For debug use ******
		System.out.print ("\n digit[] = ");
		for (int i=0;i<lengthOfSecretCode;i++)
		{System.out.print (digit[i]+",");}
		**************************/
		return count;
	}

	public void play(){
		boolean indicator = false;
		do{
			String inputA = JOptionPane.showInputDialog("Please enter an integer with "+lengthOfSecretCode+" digits in the range 1 to "+maxInteger);
			int guess = Integer.parseInt(inputA);
			System.out.println ("\n     guess = "+inputA);
			int [] hints = oneGuess(guess);
			if (hints[0]!=lengthOfSecretCode) {
				JOptionPane.showMessageDialog (null,hints[0]+" position match and "+hints[1]+" digit match");
				indicator=true;
			} 
			else {indicator=false;}
		} while (indicator==true);
		JOptionPane.showMessageDialog (null,"You Win! No of Guess "+guessCount);
	}
}