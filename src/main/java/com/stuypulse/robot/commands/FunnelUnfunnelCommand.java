package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Funnel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FunnelUnfunnelCommand extends CommandBase {
    
    private final Funnel funnel;

    public FunnelUnfunnelCommand(Funnel funnel) {
        this.funnel = funnel;
        addRequirements(funnel);
    }

    @Override
    public void execute() {
        System.out.println("Running FunnelUnfunnelCommand");
        funnel.unfunnel();
    }

    @Override
	public boolean isFinished() {
      return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Ending FunnelUnfunnelCommand");
        funnel.stop();
    }
}