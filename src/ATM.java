import requests.Request;
import requests.RequestTypeException;

import java.util.ArrayList;

public class ATM {
    int id;
    ArrayList<Request> requestList = new ArrayList<>();

    public ATM(int id, ArrayList<Request> requestList) {
        this.id = id;
        this.requestList = requestList;
    }

    public ATM(int id){
        this.id = id;
    }

    public void addRequest(Request request){
        requestList.add(request);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Request> getRequestList() {
        return requestList;
    }

    public void setRequestResult(int requestPosition, String result){
        requestList.get(requestPosition).setRequestResult(result);
    }

    public void processing() {
        boolean flag;
        for(Request rq: requestList){
            flag = true;
            try {
                flag = Bank.handleRequest(rq);
            } catch (RequestTypeException e) {
                flag = false;
                rq.setRequestResult("Провал - Приносим свои извинения, банкомат не работает");
            } catch (InterruptedException e) {
                flag = false;
                rq.setRequestResult("Провал - Приносим свои извинения, банкомат не работает");
            } finally {
                Bank.feedback(flag);
                System.out.println(rq);
            }

        }
    }
}
