package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.stuylib.input.gamepads.Logitech;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FunnelFunnelWithMotorStallingCommand extends CommandBase {
    
    private final Funnel funnel;
    private final Logitech operatorGamepad;

    public FunnelFunnelWithMotorStallingCommand(Funnel funnel, Logitech operatorGamepad) {
        this.funnel = funnel;
        this.operatorGamepad = operatorGamepad;
        addRequirements(funnel);
    }

    @Override
    public void execute() {
        System.out.println("Running FunnelFunnelWithMotorStallingCommand");
        funnel.funnel();
    }

    @Override
	public boolean isFinished() {
      return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Ending FunnelFunnelWithMotorStallingCommand");
        funnel.stop();
    }
}