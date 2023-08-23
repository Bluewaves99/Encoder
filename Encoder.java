import java.util.Random;
import java.util.Scanner;
public class Encoder {

  static Scanner scanner = new Scanner(System.in);

  static char[] refTable ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                    '0','1','2','3','4','5','6','7','8','9',
                    '(',')','*','+',',','-','.','/'};
  public static void main(String[] args) {
    Encoder encoderCopy = new Encoder();
    System.out.println("Enter E to Encode or D to Decode.");
    String input1 = scanner.nextLine();
    input1 = input1.toUpperCase();
    boolean check = true;

    while(check == true){
      switch(input1){
        case "E":
          System.out.println("Enter text to encode");
          String inputE = scanner.nextLine();
          inputE = inputE.toUpperCase();
          if(inputE.length()>0){
            encoderCopy.encode(inputE);
            check = false;
          }else{
            System.out.println("Input cannot be blank.");
          }
          break;

        case "D":
          System.out.println("Enter text to decode");
          String inputD = scanner.nextLine();
          inputD = inputD.toUpperCase();
          if(inputD.length()>0){
            encoderCopy.decode(inputD);
            check = false;
          }else{
            System.out.println("Input cannot be blank.");
          }
          break;

        default:
          System.out.println("That is not a valid input, please enter a valid input.");
          input1 = scanner.nextLine();
          input1 = input1.toUpperCase();
      }
    }
    
  }

//Random Offset
  public static class Randomizer{
    Random Randomizer = new Random();
    int randomSet = Randomizer.nextInt(43);
    char offset = refTable[randomSet];
  }
//Encoder
  public String encode (String plainText){
    boolean flag = false;
    Randomizer myRandom = new Randomizer();
    char[] plainArray = plainText.toCharArray();
    char[] encodedArray = new char[plainArray.length+1];

    encodedArray[0] = myRandom.offset;

    for (int c = 0; c < plainArray.length; c++) {
      flag = false;
      for (int i = 0; i < refTable.length; i++) {
        
        if(plainArray[c] == refTable[i]){
        
          int insert = (i-myRandom.randomSet);
        
          if(insert < 0){
              insert = refTable.length+insert;
            }
          encodedArray[c+1] = refTable[insert]; //+1 to account for the added offset letter
          flag = true;
        }

        if(i+1 == refTable.length && flag == false){
          encodedArray[c+1] = plainArray[c];
        }
      }
    }

    String answer = "Encoded text: ";

    for (char c : encodedArray) {
      answer += c;
    }

    System.out.println(answer);

    return answer;
  }
//Decoder
  public String decode (String encodedText){
    boolean flag = false;
    char[] encodedArray = encodedText.toCharArray();
    char[] decodedArray = new char[encodedArray.length-1];
    int offset = 0;

    for (int i = 0; i < refTable.length; i++) {
      if(refTable[i]==encodedArray[0]){
        offset = i;
        break;
      }
    }

    for (int c = 1; c < encodedArray.length; c++) {
      flag = false;
      for (int i = 0; i < refTable.length; i++) {
        
        if(encodedArray[c] == refTable[i]){
        
          int insert = (i+offset);
        
          if(insert > refTable.length-1){
              insert -= refTable.length;
            }
          decodedArray[c-1] = refTable[insert];
          flag = true;
        }

        if(i+1 == refTable.length && flag == false){
          decodedArray[c-1] = encodedArray[c];
        }
      }
    }
    String answer = "Decoded text: ";

    for (char c : decodedArray) {
      answer += c;
    }

    System.out.println(answer);

    return answer;
  }

}
