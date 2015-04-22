public class Bibliometrie
{
    static boolean run = true;

    static void printCommands()
    {
      Ecran.afficher("Ajouter un auteur : a\n");
      Ecran.afficher("Ajouter une publication : p\n");
      Ecran.afficher("Afficher les auteurs : aa\n");
      Ecran.afficher("Afficher les publications d'un auteur : ap\n");
      Ecran.afficher("Afficher les statistiques d'un auteur : as\n");
      Ecran.afficher("Afficher l'aide : h\n");
      Ecran.afficher("Quitter : q\n");
    }

    public static void main(String[] argv)
    {
        String input = "";
        printCommands();
        while (run)
        {
            Ecran.afficher("> ");
            input = Clavier.saisirString();
            if (input.equals("a"))
            {
              Ecran.afficher("Nom : ");
              String nom = Clavier.saisirString();
              Ecran.afficher("Prenom : ");
              String prenom = Clavier.saisirString();
              Base.addAuteur(nom, prenom);
            }
            if (input.equals("p"))
            {
              Ecran.afficher("id Auteur : ");
              int auteur = Clavier.saisirInt();
              Ecran.afficher("Titre : ");
              String titre = Clavier.saisirString();
              Ecran.afficher("Type ('conference' ou 'journal') : ");
              String type = Clavier.saisirString();
              Ecran.afficher("Recueil : ");
              String recueil = Clavier.saisirString();
              Ecran.afficher("Annee : ");
              int annee = Clavier.saisirInt();
              Base.addPublication(auteur, titre, type, recueil, annee);
            }
            if (input.equals("aa"))
              Base.getAuteurs();
            if (input.equals("ap"))
            {
              Ecran.afficher("id Auteur : ");
              int auteur = Clavier.saisirInt();
              Base.getPublications(auteur);
            }
            if (input.equals("as"))
            {
              Ecran.afficher("id Auteur : ");
              int auteur = Clavier.saisirInt();
              Base.getStats(auteur);
            }
            if (input.equals("h"))
                printCommands();
            if (input.equals("q"))
                run = false;
        }
    }
}


