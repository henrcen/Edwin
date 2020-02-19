package com.stuypulse.robot.commands.command.groups;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.commands.DrivetrainMovementCommand;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.DriveCommand;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.TurnCommand;
import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootThreeAtStartThreeTrenchTwoRdvsCommandGroup extends SequentialCommandGroup{
    public ShootThreeAtStartThreeTrenchTwoRdvsCommandGroup(Drivetrain drivetrain, Intake intake, Funnel funnel, Chimney chimney) {
        addCommands(
            new AlignAtStartAndShootThreeCommandGroup(drivetrain),
            new TurnCommand(drivetrain, Constants.ANGLE_FROM_START_TO_TRENCH),
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_START_TO_TRENCH),
            new TurnCommand(drivetrain, -Constants.ANGLE_FROM_START_TO_TRENCH),
            new AcquireNewBallCommandGroup(intake, funnel, chimney),
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_BALL_TO_BALL),

            new TurnCommand(drivetrain, Constants.ANGLE_FROM_TRENCH_TO_RDVS),
            new AcquireNewBallCommandGroup(intake, funnel, chimney),
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_TRENCH_TO_RDVS),
            new TurnCommand(drivetrain, Constants.ANGLE_FROM_RDVS_TO_TWO_BALL),
            new DriveCommand(drivetrain, Constants.DISTANCE_BETWEEN_TWO_BALL),
            new TurnCommand(drivetrain, 90),
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_RDVS_TO_INTERSECTION_BEWTWEEN_TWO_BALL_AND_GOAL),
            new TurnCommand(drivetrain, 80), //estimate
            new AlignAtTrenchAndShootThreeCommandGroup(drivetrain)
        );
    }
}