package com.stuypulse.robot.commands.auton.routines;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.commands.DrivetrainAlignmentCommand;
import com.stuypulse.robot.commands.DrivetrainGoalAligner;
import com.stuypulse.robot.commands.DrivetrainInnerGoalAligner;
import com.stuypulse.robot.commands.DrivetrainMovementCommand;
import com.stuypulse.robot.commands.DrivetrainStopCommand;
import com.stuypulse.robot.commands.command.groups.AlignAtStartAndShootThreeCommandGroup;
import com.stuypulse.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootThreeAutonCommand extends SequentialCommandGroup {

    private final double FEET_TO_MOVE_AFTER_SHOOTING = 1;

    public ShootThreeAutonCommand(Drivetrain drivetrain) {
        addCommands(
            new AlignAtStartAndShootThreeCommandGroup(drivetrain),
            new DrivetrainMovementCommand(drivetrain, 0, 12),
            new DrivetrainStopCommand(drivetrain)
        );
    }
    
}