package outils;

import java.util.HashMap;


public class CustomException extends Exception{
        
    private HashMap<String, String> messages;

    public CustomException() {
    }

    public CustomException(HashMap<String, String> messages, String message) {
        super(message);
        this.messages = messages;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }
    
    public String getMessage(String cle){
        return messages.get(cle);
    }
   
}
