import requests.Request;
import requests.RequestType;

import java.util.Random;

public class Tester {
    public static void main(String[] args){
        Random rand = new Random();
        int clientCount = 200 + rand.nextInt(801);
        int atmCount = 50 + rand.nextInt(51);
        ATM[] atm = new ATM[atmCount];
        Thread[] th = new Thread[atmCount];
        Bank.createBankRepo(clientCount+1);
        for(int i=0; i<atmCount; i++)
            atm[i] = new ATM(i);
        System.out.println("Количество пользователей: "+clientCount);
        System.out.println("Количество банкоматов: "+atmCount);
        for (int i=0; i<clientCount; i++){
            int pos = rand.nextInt(atmCount);
            atm[pos].addRequest(Client.generateRequest());
        }
        for(int i=0; i<atmCount; i++){
            th[i] = new Thread(atm[i]);
            th[i].start();
        }

        try {
            for(int i=0; i<atmCount; i++) th[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Количество успешных запросов: "+Bank.getPositiveRequests());
        System.out.println("Количество неудачных запросов: "+Bank.getNegativeRequests());


    }
}
