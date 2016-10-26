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
public class MutexesisWork {

    public int IdMutexesisWork;
    public Date Date;
    public int IdMutexesisler;
    public String Status;
    public int IdDaxilOlan;

    public MutexesisWork(int IdMutexesisWork, Date Date, int IdMutexesisler, String Status, int IdDaxilOlan) {
        this.IdMutexesisWork = IdMutexesisWork;
        this.Date = Date;
        this.IdMutexesisler = IdMutexesisler;
        this.Status = Status;
        this.IdDaxilOlan = IdDaxilOlan;
    }

    public MutexesisWork() {
    }

}