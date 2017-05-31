/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import core.connection.RequestWrapper;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

/**
 *
 * @author AIM632
 */
public abstract class Command {
    protected RequestWrapper request;
    protected ObjectOutputStream output;
    
    public Command(RequestWrapper request, ObjectOutputStream output){
        this.request = request;
        this.output = output;
    }
    public abstract void Execute() throws SQLException, IOException;
}
