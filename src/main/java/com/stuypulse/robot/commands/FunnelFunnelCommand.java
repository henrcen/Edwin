package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Funnel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FunnelFunnelCommand extends CommandBase {
    
    private final Funnel funnel;

    public FunnelFunnelCommand(Funnel funnel) {
        this.funnel = funnel;
        addRequirements(funnel);
    }

    @Override
    public void execute() {
        System.out.println("Running FunnelFunnelCommand");
        funnel.funnel();
    }

    @Override
	public boolean isFinished() {
      return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Ending FunnelFunnelCommand");
        funnel.stop();
    }
}