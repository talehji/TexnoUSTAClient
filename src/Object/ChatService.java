/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.sql.Date;

/**
 *
 * @author Pallas
 */
public class ChatService {
    public int idChatService;
    public int idGroup;
    public String Text;
    public Date Date;
    
    public ChatService() {
    }

    public ChatService(int idChatService, int idGroup, String Text, Date Date) {
        this.idChatService = idChatService;
        this.idGroup = idGroup;
        this.Text = Text;
        this.Date = Date;
    }
}
