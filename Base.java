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
     int res = BD.executerSelect(co, "SELECT auID FROM AUTEUR WHERE auNom = '" + nom + "' AND auPrenom = '" + prenom + "'");
      BD.suivant(res);
      Ecran.afficher("id Auteur = ", BD.attributInt(res, "auID"));
    }
  
    static void addPublication(int auteur, String titre, String type, String recueil, int annee)
    {
      BD.executerUpdate(co, "INSERT INTO PUBLICATION (pubTitre, pubType, pubRecueil, pubAnnee) VALUES ('" + titre + "', '" + type + "', '" + recueil + "', '" + String.valueOf(annee) + "')");
      int res = BD.executerSelect(co, "SELECT pubID FROM PUBLICATION WHERE pubTitre = '" + titre + "' AND pubType = '" + type + "' AND pubRecueil = '" + recueil + "' AND pubAnnee = '" + annee + "'");
      BD.suivant(res);
      addPubliAuteur(auteur, BD.attributInt(res, "pubID"));
      Ecran.afficher("id Publication = ", BD.attributInt(res, "pubID"), ". La table PUBLI_AUTEUR a été mise à jour.\n");
    }
  
    static void addPubliAuteur(int auteur, int publication)
    {
      BD.executerUpdate(co, "INSERT INTO PUBLI_AUTEUR VALUES ('" + auteur + "', '" + publication + "')");
    }

    static void getAuteurs()
    {
      int res = BD.executerSelect(co, "SELECT * FROM AUTEUR");
      while (BD.suivant(res))
        Ecran.afficher("id : ", BD.attributInt(res, "auID"), "          nom : ", BD.attributString(res, "auNom"), "             prenom : ", BD.attributString(res, "auPrenom"), "\n");
    }
  
    static void getPublications(int auteur)
    {
      int res = BD.executerSelect(co, "SELECT paPublication FROM PUBLI_AUTEUR WHERE PUBLI_AUTEUR.paAuteur = " + auteur);
      while (BD.suivant(res))
        getPublication(BD.attributInt(res, "paPublication"));
    }
  
    static void getPublication(int id)
    {
      int res = BD.executerSelect(co, "SELECT * FROM PUBLICATION WHERE pubID = " + id);
      while (BD.suivant(res))
        Ecran.afficher("Publication ", BD.attributInt(res, "pubID"), "          Titre : ", BD.attributString(res, "pubTitre"), "                Recueil : ", BD.attributString(res, "pubRecueil"), "            Type : ", BD.attributString(res, "pubType"), "          Annee : ", BD.attributInt(res, "pubAnnee"), "\n");
    }
  
    static void getStats(int auteur)
    {
      int res = BD.executerSelect(co, "SELECT count(paPublication) AS nbPubli FROM PUBLI_AUTEUR WHERE paAuteur = '" + auteur + "'");
      BD.suivant(res);
      Ecran.afficher("Nombre de publication pour l'auteur ", auteur, " : ", BD.attributInt(res, "nbPubli"), "\n");
      res = BD.executerSelect(co, "SELECT pubAnnee, pubType, count(paPublication) AS nbPubli FROM PUBLI_AUTEUR, PUBLICATION WHERE paPublication = pubID AND paAuteur = '" + auteur + "' GROUP BY pubAnnee");
      while (BD.suivant(res))
        Ecran.afficher("Annee : " + BD.attributInt(res, "pubAnnee") + "         " + BD.attributString(res, "pubType") + " : " + BD.attributInt(res, "nbPubli"), "\n");
    }
}
