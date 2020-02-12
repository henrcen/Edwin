package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Chimney;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ChimneyUpCommand extends CommandBase {

    Chimney chimney;

    public ChimneyUpCommand(Chimney chimney) {
        this.chimney = chimney;
        addRequirements(chimney);
    }

    @Override
    public void execute() {
        System.out.println("Running ChimneyUpCommand");
        chimney.liftUp();
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Ending ChimneyUpCommand");
        chimney.stop();
    }
    
}