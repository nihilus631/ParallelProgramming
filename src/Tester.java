import requests.Request;
import requests.RequestType;

import java.util.Random;

public class Tester {
    public static void main(String[] args){
        Random rand = new Random();
        int clientCount = 200 + rand.nextInt(801);
        int atmCount = 50 + rand.nextInt(51);
        int[] atm = new int[atmCount];
        System.out.println(clientCount);
        System.out.println(atmCount);
        for (int i=0; i<clientCount; i++){
            System.out.print(Client.generateRequest() + " ");
            int thisATM = rand.nextInt(atmCount);
            System.out.println(thisATM);
            atm[thisATM]++;
        }


    }
}
