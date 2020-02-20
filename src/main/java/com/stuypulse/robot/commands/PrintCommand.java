package com.stuypulse.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PrintCommand extends CommandBase{
    
    String string;

    public PrintCommand(String string) {
        this.string = string;
    }

    @Override
    public void execute() {
        System.out.println(string);
    }

}