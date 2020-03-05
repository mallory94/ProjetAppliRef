package appli;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUploadFile {

 public static void main(String[] args) {
  String serveur = "localhost";
  int port = 2121;
//  String user = "nomUtilisateur";
//  String password = "votreMotdePasse";

  FTPClient ftpClient = new FTPClient();
  try {

   ftpClient.connect(serveur, port);
   System.out.println("connection accept�e");
   //ftpClient.login(user, password );
   ftpClient.enterLocalPassiveMode();

   ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

   // Approche 1: upload d'un fichier en utilisant InputStream
   File file = new File("C:/Users/gack/Desktop/fichierAUpload.txt");

   String chemin = "fichierAUpload.txt";
   InputStream inputStream = new FileInputStream(file);

   System.out.println("D�but de l'upload");
   //r�sultat de l'upload
   boolean res = ftpClient.storeFile(chemin, inputStream);
   //fermet le flut de lecture
   inputStream.close();
   
   if (res==true) {
     System.out.println("Le fichier "+chemin+" a �t� transf�r� avec succ�s");
   }

   // Approche 2: upload d'un fichier en utilisant OutputStream
//   file = new File("C:/piste 1.wma");
//   chemin = "audio/piste 1.wma";
//   inputStream = new FileInputStream(file);
//
//   System.out.println("D�but de l'upload");
//   OutputStream outputStream = ftpClient.storeFileStream(chemin);
//   byte[] bytesIn = new byte[4096];
//   int buffer = 0;
//
//   //tant qu'on a pas atteint la fin du fichier
//   System.out.println("Transfert en cours...");
//   int transfer� = 0;
//   int pourcentage = 0;
//   //tant qu'on a pas atteint la fin du fichier
//   while ((buffer = inputStream.read(bytesIn)) != -1) {
//       //lire les donn�es avec un buffer de 4096 octets
//       outputStream.write(bytesIn, 0, buffer);
//       transfer� += buffer;
//       pourcentage = (int) (transfer�*100/file.length());
//       System.out.println(pourcentage+"%");
//   }
//   //fermer les flux de lecture de d'�criture
//   inputStream.close();
//   outputStream.close();
//
//   //r�sultat de l'upload
//   res = ftpClient.completePendingCommand();
//   if (res) {
//     System.out.println("Le fichier "+chemin+" a �t� transf�r� avec succ�s");
//   }

  } catch (IOException e) {
   System.out.println(e.getMessage());
   e.printStackTrace();
  } finally {
   try {
    if (ftpClient.isConnected()) {
     //fermer la connexion FTP
     ftpClient.logout();
     ftpClient.disconnect();
    }
   } catch (IOException ex) {
    ex.printStackTrace();
   }
  }
 }
}