import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyser {

	public static List<Token> analyse(String input) throws NumberException, ExpressionException {

		final List<Token> result = new ArrayList<Token>(); 
        String string = "";                                     
        final Token token = new Token();                   
        double num;
		int l = 0;                                       
        int state = 0;                                     


		for (int  i=0; i<input.length(); i++){
			switch(state){
				case 0:
				switch(input.charAt(i)){
					case ' ':
					state = 0;
					break;
					
					case '0':
					string = string + input.charAt(i);
					num = Double.valueOf(String.valueOf(input.charAt(i)));
					result.add(new Token(Token.typeOf(input.charAt(i))));

					state = 2;
					break;

					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
					string = string + input.charAt(i);
					num = Double.valueOf(String.valueOf(input.charAt(i)));
					result.add(new Token(Token.typeOf(input.charAt(i))));
					result.get(0).changeValue(num);
					state = 3;
					break;

					case '.':
					throw new NumberException();

					case '+':
					case '-':
					case '*':
					case '/':
					default:
					throw new ExpressionException();
				}
				break;

				case 3:
				switch(input.charAt(i)){
					case '+':
					case '-':
					case '*':
					case '/':
					result.add(new Token(Token.typeOf(input.charAt(i))));
					state = 0;
				    l=result.size();
					break;

					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
					string = string + input.charAt(i);
					num = Double.parseDouble(string);
					result.get(l).changeValue(num);
					break;

					case ' ':
					state = 4;
					break;

					case '.':
					throw new NumberException();
					default:
					throw new ExpressionException();
				}
				break;

				case 2:
				switch(input.charAt(i)){
					case '+':
					case '-':
					case '*':
					case '/':
					result.add(new Token(Token.typeOf(input.charAt(i))));
					
					state = 0;
				
					
					break;

					case ' ':
					state = 4;
					break;

					case '.':
					state = 5;
					break;
					
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
					throw new NumberException();

					default:
					throw new ExpressionException(); 
				}
				break;

				case 5:
				string = string + ".";
				switch(input.charAt(i)){

					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
					string = string + input.charAt(i);
					num = Double.parseDouble(string);
					result.get(l).changeValue(num);
					state = 3;
					break;

					default:
					throw new NumberException();
				}
				break;

				case 4:
				switch(input.charAt(i)){
					case '+':
					case '-':
					case '*':
					case '/':
					result.add(new Token(Token.typeOf(input.charAt(i))));
					state = 0;
					l=result.size();
					break;

					case ' ':
					state = 4;
					break;

					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
				    case '.':
					throw new ExpressionException();

					default:
					throw new ExpressionException();
				}
				break;



			}
		}

		if (state==5){
					throw new NumberException();
				}


		return result;

	}

}
