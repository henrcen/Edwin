package com.stuypulse.robot.commands.command.groups;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.DriveCommand;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.TurnCommand;
import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AcquireThreeBallFromRdvsCommandGroup extends SequentialCommandGroup{
    public AcquireThreeBallFromRdvsCommandGroup(Drivetrain drivetrain, Intake intake, Funnel funnel, Chimney chimney) {
        addCommands(
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_START_TO_RDVS),
            new TurnCommand(drivetrain, Constants.ANGLE_FROM_START_POINT_TO_THREE_BALL),
            new AcquireNewBallCommandGroup(intake, funnel, chimney),
            new DriveCommand(drivetrain, Constants.DISTANCE_FOR_THREE_BALLS_IN_RDVS)
        );
    }
}