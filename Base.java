public class Base
{
    static String ip = "172.20.128.64";
    static String user = "beaumann";
    static String pass = "beaumann";
    static String base = "beaumann_BD";

    static int co = BD.ouvrirConnexion(ip, base, user, pass);

    static void addAuteur(String nom, String prenom)
    {
      BD.executerUpdate(co, "INSERT INTO AUTEUR (auNom, auPrenom) VALUES ('" + nom + "', '" + prenom + "')");
    }
  
    static void addPublication(String titre, String type, String recueil, int annee)
    {
      BD.executerUpdate(co, "INSERT INTO PUBLICATION (pubTitre, pubType, pubRecueil, pubAnnee) VALUES ('" + titre + "', '" + type + "', '" + recueil + "', '" + String.valueOf(annee) + "')");
    }
  
    static void addPubliAuteur(int auteur, int publication)
    {
      BD.executerUpdate(co, "INSERT INTO PUBLI_AUTEUR VALUES ('" + auteur + "', '" + publication + "')");
    }

    static void getPublications(int auteur)
    {
      int res = BD.executerSelect(co, "SELECT paPublication FROM PUBLI_AUTEUR WHERE PUBLI_AUTEUR.paAuteur = " + auteur);
      while (BD.suivant(res))
      {
        Ecran.afficher("Publication ", BD.attributInt(res, "paPublication"), "\n");
        getPublication(BD.attributInt(res, "paPublication"));
      }
    }
  
    static void getPublication(int id)
    {
      int res = BD.executerSelect(co, "SELECT * FROM PUBLICATION WHERE pubID = " + id);
      while (BD.suivant(res))
      {
        Ecran.afficher("Publication ", BD.attributInt(res, "pubID"), "\n");
        Ecran.afficher("Titre : ", BD.attributString(res, "pubTitre"), "\n");
        Ecran.afficher("Recueil : ", BD.attributString(res, "pubRecueil"), "\n");
        Ecran.afficher("Type : ", BD.attributString(res, "pubType"), "\n");
        Ecran.afficher("Annee : ", BD.attributInt(res, "pubAnnee"), "\n");
      }
    }
}
