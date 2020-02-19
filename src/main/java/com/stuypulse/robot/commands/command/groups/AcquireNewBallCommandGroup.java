package com.stuypulse.robot.commands.command.groups;

import com.stuypulse.robot.commands.ChimneyStopCommand;
import com.stuypulse.robot.commands.ChimneyUpCommand;
import com.stuypulse.robot.commands.FunnelFunnelCommand;
import com.stuypulse.robot.commands.FunnelUnfunnelCommand;
import com.stuypulse.robot.commands.IntakeAcquireCommand;
import com.stuypulse.robot.commands.IntakeDeacquireCommand;
import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AcquireNewBallCommandGroup extends SequentialCommandGroup{
    public AcquireNewBallCommandGroup(Intake intake, Funnel funnel, Chimney chimney) {
        addCommands(
            new IntakeAcquireCommand(intake),
            new FunnelFunnelCommand(funnel),
            new ChimneyUpCommand(chimney),
            new IntakeDeacquireCommand(intake),
            new FunnelUnfunnelCommand(funnel),
            new ChimneyStopCommand(chimney)
        );
    }

}