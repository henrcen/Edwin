package com.stuypulse.robot.commands.command.groups;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.commands.DrivetrainAlignmentCommand;
import com.stuypulse.robot.commands.DrivetrainGoalAligner;
import com.stuypulse.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AlignAtStartAndShootThreeCommandGroup extends SequentialCommandGroup{
    public AlignAtStartAndShootThreeCommandGroup(Drivetrain drivetrain) {
        addCommands(
            new DrivetrainAlignmentCommand(drivetrain, new DrivetrainGoalAligner(Constants.SHOOT_FROM_START_TO_GOAL))
            // TODO: Add shoot 3
        );
    }
}