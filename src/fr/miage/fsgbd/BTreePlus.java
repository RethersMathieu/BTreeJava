package fr.miage.fsgbd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import javax.swing.tree.DefaultMutableTreeNode;

import fr.miage.fsgbd.Noeud.Indexation;
import fr.miage.fsgbd.property.ConvertToGenericType;

/**
 * @author Galli Gregory, Mopolo Moke Gabriel
 * @param <Type>
 */
public class BTreePlus<Type> implements java.io.Serializable {
    private Noeud<Type> racine;

    private File csv;

    public BTreePlus(int u, Executable e) throws Exception {
        this(u, e, null);
    }

    public BTreePlus(int u, Executable e, File csv) throws Exception {
        this.csv = csv;
        if (csv != null) {
            String fileName = csv.toString();
            int indexExtension = fileName.lastIndexOf(".");
            if (indexExtension > 0 && fileName.substring(indexExtension + 1).equals("csv")) {
                this.racine = new Noeud<Type>(u, e, null, this.csv);
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(this.csv));
                    ConvertToGenericType<Type> convertIndex = new ConvertToGenericType<>();
                    String line;
                    reader.readLine();
                    while ((line = reader.readLine()) != null) {
                        String[] datas = line.split(";");
                        if (datas.length > 0) {
                            String strIndex = datas[0];
                            Type index;
                            if ((index = convertIndex.get(strIndex)) != null) {
                                this.addValeur(index);
                            }
                        }
                    }
                } catch (Exception exception) {
                    throw exception;
                }
            } else {
                throw new Exception("Is not a CSV file. Please choose a file with extension \".csv\".");
            }
        } else {
            this.racine = new Noeud<>(u, e, null);
        }
    }

    public void afficheArbre() {
        racine.afficheNoeud(true, 0);
    }

    /**
     * Méthode récursive permettant de récupérer tous les noeuds
     *
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode bArbreToJTree() {
        return bArbreToJTree(racine);
    }

    private DefaultMutableTreeNode bArbreToJTree(Noeud<Type> root) {
        StringBuilder txt = new StringBuilder();
        for (Indexation key : root.keys)
            txt.append(key.getIndex().toString()).append(" ");

        DefaultMutableTreeNode racine2 = new DefaultMutableTreeNode(txt.toString(), true);
        for (Noeud<Type> fil : root.fils)
            racine2.add(bArbreToJTree(fil));

        return racine2;
    }

    public boolean addValeur(Type valeur) {
        System.out.println("Ajout de la valeur : " + valeur.toString());
        if (racine.contient(valeur) == null) {
            Noeud<Type> newRacine = racine.addValeur(valeur);
            if (racine != newRacine)
                racine = newRacine;
            return true;
        }
        return false;
    }

    public void removeValeur(Type valeur) {
        System.out.println("Retrait de la valeur : " + valeur.toString());
        if (racine.contient(valeur) != null) {
            Noeud<Type> newRacine = racine.removeValeur(valeur, false);
            if (racine != newRacine)
                racine = newRacine;
        }
    }

    public Collection<Noeud<Type>> getLeafs() {
        Noeud<Type> noeud = Noeud.firstLeaf(this.racine);
        Collection<Noeud<Type>> leafs = new ArrayList<>();
        leafs.add(noeud);
        while((noeud = noeud.getNextLeaf()) != null) {
            leafs.add(noeud);
        }
        return leafs;
    }

    public Indexation search(Type index) {
        if (this.racine == null) return null;
        return this.racine.getByIndex(index);
    }
}
