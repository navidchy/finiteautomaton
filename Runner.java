public class Runner {
    public static void main(String[] args) {
        try {
            System.out.println(LexicalAnalyser.analyse("0.5"));
        }
        catch (NumberException e) {

        }
        catch (ExpressionException e) {
            
        }
    }
}
