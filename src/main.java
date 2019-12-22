import java.io.*;
public class main {


    public static void main(String[] args) throws IOException {

        int anzahlPunkte=0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wie viele Messpunkte?");
        do{
             anzahlPunkte = Integer.parseInt(in.readLine());
        }
       while(anzahlPunkte<1);
       int[][] messPunkte = new int[anzahlPunkte][2];

        System.out.println("Gib alle " + anzahlPunkte + " Messwerte ein im Muster ZAHL,ZAHL :");
        int i = 0;
        while(i < anzahlPunkte) {
            String input = in.readLine();

            String[] beideWerteAusInput = input.split(",");
            if(beideWerteAusInput.length == 2) {
                int zahl1 = Integer.parseInt(beideWerteAusInput[0]);
                int zahl2 = Integer.parseInt(beideWerteAusInput[1]);

                messPunkte[i][0] = zahl1;
                messPunkte[i][1] = zahl2;
                System.out.println("Verstandene Eingabe: " + messPunkte[i][0] + "," + messPunkte[i][1]);
                i++;
            }
            else{
                System.out.println("Falsche Eingabe! Bitte Wiederholen! in Zahl,Zahl");
            }
        }

        boolean guteMessreihe = true;
        for(int j = 0; j < messPunkte.length; j++){
            int temp1 = messPunkte[j][0];
            int temp2 = messPunkte[j][1];
            for (int k = 0; k < messPunkte.length; k++) {
                if(k != j){
                    if(messPunkte[k][0] == temp1 &&
                        messPunkte[k][1] == temp2){
                        guteMessreihe = false;
                    }
                    else {
                        guteMessreihe = true;
                    }
                }
            }
        }
        if(!guteMessreihe){
            System.out.println("Keine geeignete Messreihe, da nicht mindestens ein unterschliedlicher Wert!");
            System.exit(-1);
        }

        //This is where the magic happens:
        double xStrich = arithmetischesMittel(messPunkte, 0);
        double yStrich = arithmetischesMittel(messPunkte, 1);

        double rTOP = 0;
        double rDOWN = 0;
        for (int o = 0; o < messPunkte.length; o++){
            rTOP += (messPunkte[o][0] - xStrich) * (messPunkte[o][1] - yStrich);
        }

        for(int u = 0; u < messPunkte.length; u++){
            rDOWN += Math.pow((messPunkte[u][0] - xStrich),2);
            int rTemp = 0;
            for (int j = 0; j < messPunkte.length; j++) {
                rTemp *= Math.pow((messPunkte[u][1] - yStrich),2);
            }
            rDOWN *= rTemp;
        }
        rDOWN = Math.sqrt(rDOWN);

        if(rDOWN == 0 || rTOP == 0){
            System.out.println("kein linearer Zusammenhang");
        }
        double r = rTOP/rDOWN;
        if(r >= 0.9 && r < 1){
            System.out.println("sehr hohe Korrelation (linearer funktionaler Zusammenhang)");
        }
        else if(r >= 0.8 && r < 0.9){
            System.out.println("hohe Korrelation (linearer funktionaler Zusammenhang wahrscheinlich)");
        }
        else if(r >= 0.7 && r < 0.8){

            System.out.println("mittlere Korrelation (linearer funktionaler Zusammenhang möglich)");
        }
        else if(r >= 0.5 && r < 0.7){
            System.out.println("geringe Korrelation (keine Aussage zum linearen funktionalen Zusammenhang möglich)");
        }
        else if(r > 0.0 && r < 0.5){

            System.out.println("sehr geringe Korrelation (linearer funktionaler Zusammenhang nicht wahrscheinlich)");
        }
        else if(r == 1){

            System.out.println("perfekt linearer Zusammenhang");
        }
        System.out.println("Der Korrelationskoeefizient ist: " + r);
    }

    private static double arithmetischesMittel(int[][] messPunkte, int i) {
        double sum = 0;
        for (int j = 0; j < messPunkte.length; j++) {
            sum += messPunkte[j][i];
        }
        return (sum/messPunkte.length);
    }
}
