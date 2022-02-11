package fr.miage.fsgbd;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

import fr.miage.fsgbd.Noeud.Indexation;

public class Main {
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //On cr�e une nouvelle instance de notre JDialog
                GUI fenetre = new GUI();
                fenetre.setVisible(true);
            }
        });

		testSearchByIndex();

        /*
        TestInteger testInt = new TestInteger();
        fr.miage.fsgbd.BTreePlus<Integer> bInt = new fr.miage.fsgbd.BTreePlus<Integer>(2, 4, testInt);
        
        
        int valeur;
        for(int i=0; i < 200; i++)
        {
            valeur =(int) (Math.random() * 250);    
            System.out.println("valeur " + valeur + " " + i );
            bInt.addValeur(valeur);        
            bInt.afficheArbre();
            
        }
        */

        /*
        TestString test = new TestString();
        Noeud<String> noeud = new Noeud<String>(2, 5,test, null);
        Noeud<String> noeud1 = new Noeud<String>(2, 5,test, null);
        Noeud<String> noeud2 = new Noeud<String>(2, 5,test, null);
        */
    }


	public static void testSearchByIndex() {
		File fileRecherche = new File("src/asset/Test.csv");

		List<String> lines;
		try {
			lines = Files.readAllLines(fileRecherche.toPath());
		} catch (IOException e) {
			lines = new ArrayList<>();
		}

		BTreePlus<Integer> bTreeInt = new BTreePlus<>(4, new TestInteger(), fileRecherche);

        System.out.println("\n\n**********************************************************************************");
        System.out.println("**********************************************************************************");
        System.out.println("\nComparaison du temps de recherhce entre les deux approches :\n");

        int tailleEchantillon = 100;
        int indexSearch;
        long[] tExecRechSeq = new long[tailleEchantillon];
        long[] tExecRechIndex = new long[tailleEchantillon];

        // On lance la recherche de 100 valeurs differentes avec les deux approches et on stocke à chaque recherche
        // et pour chaqu'une des approches le temps d'éxecution dans un tableau
        String line = "";
		Random random = new Random();
        for (int i=0; i<tailleEchantillon; i++) {
            indexSearch = random.nextInt(lines.size() - 1);

            System.out.println("Recherche séquencielle de la valeur " + indexSearch + " :\n");
            long start1 = System.currentTimeMillis();
            for (String l : lines) {
				if (l.equals(Integer.toString(indexSearch))) {
					line = l;
					break;
				}
			}
            long end1 = System.currentTimeMillis();
            System.out.println("Résultat : " + line);
            tExecRechSeq[i] = end1-start1;
            System.out.println("Temps de recherche : " + tExecRechSeq[i] + " ms\n\n");

            System.out.println("Recherche avec l'index de la valeur " + indexSearch + " :\n");
            long start2 = System.currentTimeMillis();
			Indexation indexation = bTreeInt.search(indexSearch);
            long end2 = System.currentTimeMillis();
            line = indexation != null ? indexation.getValues().toString() : "null";
            System.out.println("Résultat : " + line);
            tExecRechIndex[i] = end2 - start2;
            System.out.println("Temps de recherche : " + tExecRechIndex[i] + " ms\n**************************************" +
                    "***********************************\n");
        }

        // On calcule le min, le max et la moyenne pour chaqu'une des deux approches
        long min1 = tExecRechSeq[0], min2 = tExecRechIndex[0], max1 = tExecRechSeq[0], max2 =tExecRechIndex[0];
        double moy1 = 0, moy2 = 0;
        for (int i = 0; i<tailleEchantillon; i++) {
            moy1 += tExecRechSeq[i];
            moy2 += tExecRechIndex[i];

            if (tExecRechSeq[i] < min1) min1 = tExecRechSeq[i];
            if (tExecRechIndex[i] < min2) min2 = tExecRechIndex[i];

            if (tExecRechSeq[i] > max1) max1 = tExecRechSeq[i];
            if (tExecRechIndex[i] > max2) max2 = tExecRechIndex[i];
        }
        moy1 = moy1/tailleEchantillon;
        moy2 = moy2/tailleEchantillon;

        System.out.println("\n*****************************************************************************");
        System.out.println("\nStatistiques \n");

        System.out.println("\nTemps de recherche séquenciel dans le fichier :\n\nmin =  " + min1 + " ms\nmax = "
        + max1 + " ms\nmoyenne = " + moy1 + " ms");

        System.out.println("\n\nTemps de recherche par l'index :\n\nmin =  " + min2 + " ms\nmax = "
                + max2 + " ms\nmoyenne = " + moy2 + " ms");
	}
}