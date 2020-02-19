package com.stuypulse.robot.commands.command.groups;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.commands.DrivetrainAlignmentCommand;
import com.stuypulse.robot.commands.DrivetrainGoalAligner;
import com.stuypulse.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AlignAtTrenchAndShootThreeCommandGroup extends SequentialCommandGroup{
    public AlignAtTrenchAndShootThreeCommandGroup(Drivetrain drivetrain) {
        addCommands(
            new DrivetrainAlignmentCommand(drivetrain, new DrivetrainGoalAligner(Constants.DISTANCE_FROM_TRENCH_TO_GOAL))
                //Add shoot 3
        );
    }
}