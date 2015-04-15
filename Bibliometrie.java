public class Bibliometrie
{
    static boolean run = true;

    static void printCommands()
    {
      Ecran.afficher("Ajouter un auteur : a\n");
      Ecran.afficher("Ajouter une publication : p\n");
      Ecran.afficher("Ajouter une publication à un auteur : ap\n");
      Ecran.afficher("Afficher les publications d'un auteur : apa");
      Ecran.afficher("Afficher l'aide : h\n");
      Ecran.afficher("Quitter : q\n");
    }

    public static void main(String[] argv)
    {
        String input = "";
        printCommands();
        while (run)
        {
            input = Clavier.saisirString();
            if (input.equals("a"))
            {
              Ecran.afficher("\nNom : ");
              String nom = Clavier.saisirString();
              Ecran.afficher("\nPrenom : ");
              String prenom = Clavier.saisirString();
              Base.addAuteur(nom, prenom);
            }
            if (input.equals("p"))
            {
              Ecran.afficher("\nTitre : ");
              String titre = Clavier.saisirString();
              Ecran.afficher("\nType ('conference' ou 'journal') : ");
              String type = Clavier.saisirString();
              Ecran.afficher("\nRecueil : ");
              String recueil = Clavier.saisirString();
              Ecran.afficher("\nAnnee : ");
              int annee = Clavier.saisirInt();
              Base.addPublication(titre, type, recueil, annee);
            }
            if (input.equals("ap"))
            {
              Ecran.afficher("\nid Auteur : ");
              int auteur = Clavier.saisirInt();
              Ecran.afficher("\nid Publication : ");
              int publication = Clavier.saisirInt();
              Base.addPubliAuteur(auteur, publication);
            }
            if (input.equals("apa"))
            {
              Ecran.afficher("\nid Auteur : ");
              int auteur = Clavier.saisirInt();
              Base.getPublications(auteur);
            }
            if (input.equals("h"))
                printCommands();
            if (input.equals("q"))
                run = false;
        }
    }
}


