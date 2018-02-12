import requests.Request;
import requests.RequestTypeException;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bank {
    private static int[] bills;
    private static int positiveRequests = 0;
    private static int negativeRequests = 0;
    private static Semaphore sem = new Semaphore(25);

    public static void createBankRepo(int count){
        Random rand = new Random() ;
        bills = new int[count];
        for (int i=0; i<count; i++)
            bills[i] = rand.nextInt(5000);

    }

    public static int getPositiveRequests() {
        return positiveRequests;
    }

    public static int getNegativeRequests() {
        return negativeRequests;
    }

    public static boolean handleRequest(Request request) throws RequestTypeException, InterruptedException {
        boolean res = true;
            sem.acquire();
            Random rand = new Random();
            if(rand.nextInt(20) == 9){
                request.setRequestResult("Провал - Неверный PIN");
                sem.release();
                return false;
            }

            switch (request.getType()){
                case BILL:
                    request.setRequestResult("Успешно - на счёте "+bills[request.getWhoSendThis()]);
                    break;
                case CASH_IN:
                    bills[request.getWhoSendThis()] += request.getHowMuch();
                    request.setRequestResult("Успешно - теперь у него "+bills[request.getWhoSendThis()]+" золота");
                    break;
                case CASH_OUT:
                    if(bills[request.getWhoSendThis()]<request.getHowMuch()){
                        request.setRequestResult("Провал - Недостаточно средств. Тут всего "+bills[request.getWhoSendThis()]+" золота.");
                        res = false;
                    }
                    else{
                        bills[request.getWhoSendThis()] -= request.getHowMuch();
                        request.setRequestResult("Успешно - теперь у него "+bills[request.getWhoSendThis()]+" золота");
                    }
                    break;
                case TRASFER:
                    if(bills[request.getWhoSendThis()]<request.getHowMuch()){
                        request.setRequestResult("Провал - на счёте недостоточно средств");
                        res = false;
                    }
                    else if(request.getToWhom()<bills.length){
                        bills[request.getWhoSendThis()] -= request.getHowMuch();
                        bills[request.getToWhom()] += request.getHowMuch();
                        request.setRequestResult("Успешно - перевод золота осуществлён");
                    }
                    else{
                        request.setRequestResult("Провал - счёт получателя указан неверно");
                        res = false;
                    }
                    break;

            }
            sem.release();
        return res;
    }

    public static void feedback(boolean result){
        try {
            sem.acquire();
            if(result)
                positiveRequests++;
            else
                negativeRequests++;
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
