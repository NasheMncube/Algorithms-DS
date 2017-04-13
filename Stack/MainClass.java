/**
 * Created by nashe on 13/04/2017.
 */
public class MainClass{

    public static void main(String[] args)
    {
         Stack<String> names = new Stack<>(5);

         String[] stackValues = {"Jenny", "Forest", "Bubba", "Dan"};

         for(String n : stackValues)
             names.push(n);

         for(int i = 0; i < names.size; i++)
         {
             if(!names.isEmpty())
                 System.out.println(names.pop());
         }
    }
}
