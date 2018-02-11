import requests.Request;
import requests.RequestType;

import java.util.ArrayList;
import java.util.Random;

public class Client {
    private static int count = 0;
    private static Random rand = new Random();
    public static Request generateRequest(){
        count++;
        int whoIs = count;
        RequestType rq;
        String PIN = "" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
        int howMuch=-1, toWhom=-1;


        switch (rand.nextInt(4)){
            case 0:{
                rq = RequestType.BILL;
                break;
            }
            case 1:{
                rq = RequestType.CASH_IN;
                howMuch = 100 + rand.nextInt(9901);
                break;
            }
            case 2:{
                rq = RequestType.CASH_OUT;
                howMuch = 100 + rand.nextInt(9901);
                break;
            }
            case 3:{
                rq = RequestType.TRASFER;
                howMuch = 100 + rand.nextInt(9901);
                toWhom = rand.nextInt(1001);
                break;
            }
            default:{
                rq = RequestType.BILL;
                break;
            }
        }
        Request r = new Request(whoIs, PIN, rq, howMuch, toWhom);
        return r;
    }
}
