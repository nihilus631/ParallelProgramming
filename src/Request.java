import java.lang.invoke.WrongMethodTypeException;

public class Request {
    int whoSendThis;
    String PIN;
    RequestType type;
    int howMuch;// сколько денег перевести/снять/внести
    int toWhom; // идентификатор пользователя, которому перевести денежку
    String requestResult;// точная информация о том, чем закончилась операция. Устанавливается только банком

    public Request(int whoSendThis, String PIN, RequestType type, int howMuch, int toWhom) {
        this.whoSendThis = whoSendThis;
        this.PIN = PIN;
        this.type = type;
        this.howMuch = howMuch;
        this.toWhom = toWhom;
    }

    @Override
    public String toString() {
        switch (type){
            case BILL: return whoSendThis+"-й пользователь запросил номер счёта. PIN = "+PIN+" "+requestResult;
            case CASH_IN: return whoSendThis+"-й пользователь внёс на счёт "+howMuch+" золота. PIN = "+PIN+" "+requestResult;
            case CASH_OUT: return whoSendThis+"-й пользователь снял со счёта "+howMuch+" золота. PIN = "+PIN+" "+requestResult;
            case TRASFER: return whoSendThis+"-й пользователь перевёл "+howMuch+" золота"+toWhom+"-му пользователю. PIN = "+PIN+" "+requestResult;
        }
        return "Request{" +
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

    public RequestType getType() {
        return type;
    }

    public int getHowMuch() {
        if (type == RequestType.BILL)
            throw new WrongMethodTypeException("Для данного типа этот параментр несущественен");
        return howMuch;
    }

    public int getToWhom() {
        if(type == RequestType.TRASFER) {
            return toWhom;
        }
        else{
            throw new WrongMethodTypeException("Для данного типа этот параментр несущественен");
        }
    }





}
