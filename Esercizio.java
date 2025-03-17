//Import di Classi Java necessarie al funzionamento del programma
import java.util.Scanner;
import java.io.fileReader;
import java.io.fileWriter;

// Classe principale, con metodo main
class Esercizio {
    
    //Creo l'oggetto in per l'input da tastiera
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int c;
        int nv, np, i;
        String e;
        Scanner inputFile = new Scanner(new File("Libri.txt"));
        Scanner inputFile = new Scanner(new File("Prestiti.txt"));
        FileWriter outfile = new FileWriter("Libri.txt");
        FileWriter outfile = new FileWriter("Prestiti.txt");
        // Inizialmente non ci sono libri in biblioteca.
        nv = 0;
        np = 0;

        // Alloco più spazio rispetto alla dimensione effettiva perchè sul vettore saranno effettuate
        // operazioni di inserimento e cancellazione. Supponiamo di avere massimo 100 libri in biblioteca.
        String[] v = new String[100];
        String[] p = new String[100];

        // Inserisco dei libri di partenza
        nv = leggiFile(String[]v, int N);
        np = leggiFile(String[]p, int N);
        do {
            c = leggiComando();
            if (c == 1) {
                System.out.println("Nuovo libro da inserire in biblioteca:");
                e = input.nextLine();
                nv = inserisciInVettore(v, nv, e, 0);
            }
            if (c == 2) {
                if (nv > 0) {
                    System.out.println("--- --- --- --- --- --- --- --- ---");
                    visualizzaVettore(v, nv);
                    System.out.println("--- --- --- --- --- --- --- --- ---");
                } else {
                    System.out.println("Non ci sono libri in biblioteca");
                }
            }
            if (c == 3) {
                if (np > 0) {
                    System.out.println("--- --- --- --- --- --- --- --- ---");
                    visualizzaVettore(p, np);
                    System.out.println("--- --- --- --- --- --- --- --- ---");
                } else {
                    System.out.println("Non ci sono libri in prestito");
                }
            }
            if (c == 4) {
                System.out.println("--- --- --- --- --- --- --- --- ---");
                visualizzaVettore(v, nv);
                System.out.println("--- --- --- --- --- --- --- --- ---");
                System.out.println("Titolo del libro (o una sua parola) da prendere in prestito: ");
                e = input.nextLine();
                i = ricercaNelVettore(v, nv, e);
                if (i >= 0) {
                    System.out.println("Libro scelto: " + System.lineSeparator() + i + ":" + v[i]);
                    np = inserisciInVettore(p, np, v[i], 0);
                    nv = eliminaDaVettore(v, nv, i);
                } else {
                    System.out.println("Libro " + e + " non trovato.");
                }
            }
            if (c == 5) {
                if (np > 0) {
                    System.out.println("--- --- --- --- --- --- --- --- ---");
                    visualizzaVettore(p, np);
                    System.out.println("--- --- --- --- --- --- --- --- ---");
                    do {
                        System.out.println("Indice del libro da restituire: ");
                        i = Integer.parseInt( input.nextLine() );
                    } while (i >= np);
                    System.out.println("Libro restituito: " + System.lineSeparator() + i + ":" + p[i]);
                    nv = inserisciInVettore(v, nv, p[i], 0);
                    np = eliminaDaVettore(p, np, i);
                } else {
                    System.out.println("Non ci sono libri in prestito");
                }
            }
        } while (c != 6);
        scriviFile(v, nv);
        scriviFile(p, np);
    }
    
    public static int eliminaDaVettore(String[] v, int n, int ie) {
        int i, n2;

        n2 = n - 1;
        i = ie;
        while (i <= n - 2) {
            v[i] = v[i + 1];
            i = i + 1;
        }
        
        return n2;
    }
    
    public static int leggiFile(String[]v, int N) {
        N = Integer.parseInt(inputFile.nextLine());
        for (int i = 0; i<N; ++i) {
            V[i] = inputFile.nextLine();
        }
        inputFile.close();
        return N;
    }

    public static void scriviFile(String[]v, int N) {
        outfile.write(n);
        outfile.writeLine();
        for (int i = 0; i<N; ++i) {
            outfile.write(V[i]);
            outfile.writeLine();
        }
    }
    
    public static int inserisciInVettore(String[] v, int n, String e, int ie) {
        int i, n2;

        n2 = n + 1;
        i = n2 - 1;
        while (i >= ie + 1) {
            v[i] = v[i - 1];
            i = i - 1;
        }
        v[ie] = e;
        
        return n2;
    }
    
    public static int leggiComando() {
        int c;

        do {
            System.out.println("1) Nuovo libro in biblioteca" + (char) 10 + "2) Visualizza libri in biblioteca" + (char) 10 + "3) Visualizza libri in prestito" + (char) 10 + "4) Prendi in prestito un libro" + (char) 10 + "5) Restituisci un libro" + (char) 10 + "6) Esci dal programma");
            c = Integer.parseInt( input.nextLine() );
        } while (c < 1 || 6 < c);
        
        return c;
    }
    
    public static int ricercaNelVettore(String[] v, int n, String valore) {
        int i, iTrovato;

        i = 0;
        iTrovato = -1;
        while (i < n && iTrovato == -1) {

            // Controllo se valore è una sottostringa di V[i]
            if ( v[i].indexOf(valore) != -1) {
                iTrovato = i;
            }
            i = i + 1;
        }
        
        return iTrovato;
    }
        
    public static void visualizzaVettore(String[] v, int n) {

        int i;

        i = 0;
        while (i < n) {
            System.out.println(Integer.toString(i) + ": " + v[i]);
            i = i + 1;
        }
    }
}