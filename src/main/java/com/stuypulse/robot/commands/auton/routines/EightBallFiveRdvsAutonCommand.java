package com.stuypulse.robot.commands.auton.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.stuypulse.robot.commands.DrivetrainGoalAligner;
import com.stuypulse.robot.commands.DrivetrainMovementCommand;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.DriveCommand;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.TurnCommand;
import com.stuypulse.robot.commands.command.groups.AlignAtTrenchAndShootThreeCommandGroup;
import com.stuypulse.robot.commands.DrivetrainAlignmentCommand;
import com.stuypulse.robot.commands.DrivetrainStopCommand;
import com.stuypulse.robot.commands.IntakeAcquireCommand;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.subsystems.Intake;
import com.stuypulse.robot.Constants;

public class EightBallFiveRdvsAutonCommand extends SequentialCommandGroup {
    public EightBallFiveRdvsAutonCommand(Drivetrain drivetrain, Intake intake) {
        addCommands(new DrivetrainAlignmentCommand(drivetrain, new DrivetrainGoalAligner(Constants.SHOOT_FROM_START_TO_GOAL)),
            // TODO: Add shoot 3
            new DriveCommand(drivetrain, 1),

            //Move forward
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_START_TO_RDVS),
            new TurnCommand(drivetrain, Constants.ANGLE_FROM_START_POINT_TO_THREE_BALL),
            new IntakeAcquireCommand(intake),
            new DriveCommand(drivetrain, Constants.DISTANCE_FOR_THREE_BALLS_IN_RDVS),
            new DrivetrainMovementCommand(drivetrain, Constants.ANGLE_FROM_THREE_BALL_TO_TWO_BALL, Constants.DISTANCE_FROM_THREE_BALL_TO_TWO_BALL),
            new DrivetrainMovementCommand(drivetrain, 180),
            new AlignAtTrenchAndShootThreeCommandGroup(drivetrain),
            new DrivetrainStopCommand(drivetrain)
            );
    }
}