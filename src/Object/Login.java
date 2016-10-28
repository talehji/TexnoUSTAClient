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
public class Login {
    public int idLogin;
    public int idMutexesis;
    public String Password;
    public String Status;

    public Login() {
    }

    public Login(int idLogin, int idMutexesis, String Password, String Status) {
        this.idLogin = idLogin;
        this.idMutexesis = idMutexesis;
        this.Password = Password;
        this.Status = Status;
    }
    
}
