package com.stuypulse.robot.commands.auton.routines;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.commands.DrivetrainAlignmentCommand;
import com.stuypulse.robot.commands.DrivetrainGoalAligner;
import com.stuypulse.robot.commands.DrivetrainInnerGoalAligner;
import com.stuypulse.robot.commands.DrivetrainMovementCommand;
import com.stuypulse.robot.commands.DrivetrainStopCommand;
import com.stuypulse.robot.commands.IntakeAcquireCommand;
import com.stuypulse.robot.commands.command.groups.AcquireNewBallCommandGroup;
import com.stuypulse.robot.commands.command.groups.AlignAtStartAndShootThreeCommandGroup;
import com.stuypulse.robot.commands.command.groups.AlignAtTrenchAndShootThreeCommandGroup;
import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SixBallThreeTrenchAutonCommand extends SequentialCommandGroup {
    public SixBallThreeTrenchAutonCommand(Drivetrain drivetrain, Intake intake, Chimney chimney, Funnel funnel) {
        addCommands(
            new AlignAtStartAndShootThreeCommandGroup(drivetrain),
            new DrivetrainMovementCommand(drivetrain, Constants.ANGLE_FROM_START_TO_TRENCH),
            new DrivetrainMovementCommand(drivetrain, 0, Constants.DISTANCE_FROM_START_TO_TRENCH),
            new DrivetrainMovementCommand(drivetrain, -Constants.ANGLE_FROM_START_TO_TRENCH),
            new AcquireNewBallCommandGroup(intake, funnel, chimney),
            new DrivetrainMovementCommand(drivetrain, 0, Constants.DISTANCE_FROM_BALL_TO_BALL),
            new AlignAtTrenchAndShootThreeCommandGroup(drivetrain),
            new DrivetrainStopCommand(drivetrain)
            // Add shoot three
        );
    }
}