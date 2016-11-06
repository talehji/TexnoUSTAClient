/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author Pallas
 */
public class GroupChat {

    public int idGroupChat;
    public int idServer;
    public int idMembers;
    public String Status;

    public GroupChat() {
    }

    public GroupChat(int idGroupChat, int idServer, int idMembers, String Status) {
        this.idGroupChat = idGroupChat;
        this.idServer = idServer;
        this.idMembers = idMembers;
        this.Status = Status;
    }
}
