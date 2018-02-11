package requests;

import java.lang.invoke.WrongMethodTypeException;

public class Request {
    private int whoSendThis;
    private String PIN;
    private requests.RequestType type;
    private int howMuch;// сколько денег перевести/снять/внести
    private int toWhom; // идентификатор пользователя, которому перевести денежку
    private String requestResult;// точная информация о том, чем закончилась операция. Устанавливается только банком

    public Request(int whoSendThis, String PIN, requests.RequestType type, int howMuch, int toWhom) {
        this.whoSendThis = whoSendThis;
        this.PIN = PIN;
        this.type = type;
        this.howMuch = howMuch;
        this.toWhom = toWhom;
    }

    public void setRequestResult(String requestResult) {
        this.requestResult = requestResult;
    }

    @Override
    public String toString() {
        switch (type){
            case BILL: return whoSendThis+"-й пользователь запросил номер счёта. PIN = "+PIN+" "+requestResult;
            case CASH_IN: return whoSendThis+"-й пользователь внёс на счёт "+howMuch+" золота. PIN = "+PIN+" "+requestResult;
            case CASH_OUT: return whoSendThis+"-й пользователь снял со счёта "+howMuch+" золота. PIN = "+PIN+" "+requestResult;
            case TRASFER: return whoSendThis+"-й пользователь перевёл "+howMuch+" золота "+toWhom+"-му пользователю. PIN = "+PIN+" "+requestResult;
        }
        return "requests.Request{" +
                "whoSendThis=" + whoSendThis +
                ", PIN='" + PIN + '\'' +
                ", type=" + type +
                ", howMuch=" + howMuch +
                ", toWhom=" + toWhom +
                '}';
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public void setHowMuch(int howMuch) {
        this.howMuch = howMuch;
    }

    public void setToWhom(int toWhom) {
        this.toWhom = toWhom;
    }

    public int getWhoSendThis() {
        return whoSendThis;
    }

    public String getPIN() {
        return PIN;
    }

    public requests.RequestType getType() {
        return type;
    }

    public int getHowMuch() throws RequestTypeException {
        if (type == requests.RequestType.BILL)
            throw new RequestTypeException();
        return howMuch;
    }

    public int getToWhom() throws RequestTypeException {
        if(type == requests.RequestType.TRASFER) {
            return toWhom;
        }
        else{
            throw new RequestTypeException();
        }
    }





}
