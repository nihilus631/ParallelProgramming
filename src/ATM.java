import requests.Request;

import java.util.ArrayList;

public class ATM {
    int id;
    ArrayList<Request> requestList = new ArrayList<>();

    public ATM(int id, ArrayList<Request> requestList) {
        this.id = id;
        this.requestList = requestList;
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
}
