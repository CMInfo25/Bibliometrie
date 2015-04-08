public class Bibliometrie
{
    static run = true;

    static void printCommands()
    {
        Ecran.afficher("Quitter : q\n");
    }

    public static void main(String[] argv)
    {
        String input = "";
        printCommands();
        while (run)
        {
            input = Clavier.saisirString();
            if (input != ":q")
                run = false;
        }
    }
}


