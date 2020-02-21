package com.stuypulse.robot.commands.auton.routines;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.commands.DrivetrainMovementCommand;
import com.stuypulse.robot.commands.command.groups.AcquireNewBallCommandGroup;
import com.stuypulse.robot.commands.command.groups.AcquireThreeBallFromRdvsCommandGroup;
import com.stuypulse.robot.commands.command.groups.AlignAtStartAndShootThreeCommandGroup;
import com.stuypulse.robot.commands.command.groups.AlignAtTrenchAndShootThreeCommandGroup;
import com.stuypulse.robot.commands.command.groups.ShootThreeAtStartThreeTrenchTwoRdvsCommandGroup;
import com.stuypulse.robot.subsystems.Chimney;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.TurnCommand;
import com.stuypulse.robot.commands.DrivetrainMovementCommand.DriveCommand;
import com.stuypulse.robot.subsystems.Funnel;
import com.stuypulse.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TenBallAutonCommand extends SequentialCommandGroup {
    public TenBallAutonCommand(Drivetrain drivetrain, Intake intake, Funnel funnel, Chimney chimney) {
        addCommands(
            //shooter on
            //Getting 2 balls from enemy trench
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_START_TO_ENEMY_TRENCH),  
            new AcquireNewBallCommandGroup(intake, funnel, chimney), 
            new TurnCommand(drivetrain, 90),  
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_ENEMY_TRENCH_TO_TRENCH),
            new TurnCommand(drivetrain, 90),
            new AlignAtTrenchAndShootThreeCommandGroup(drivetrain),
            new TurnCommand(drivetrain, 180),
            new TurnCommand(drivetrain, Constants.ANGLE_FROM_TRENCH_TO_TWO_BALL),
            new TurnCommand(drivetrain, Constants.DISTANCE_FROM_TRENCH_TO_TWO_BALL),
            new AcquireNewBallCommandGroup(intake, funnel, chimney),
            new DriveCommand(drivetrain, -Constants.DISTANCE_FROM_TRENCH_TO_TWO_BALL),
            new TurnCommand(drivetrain, 360-Constants.ANGLE_FROM_TRENCH_TO_TWO_BALL),
            new AcquireNewBallCommandGroup(intake, funnel, chimney),
            new DriveCommand(drivetrain, Constants.DISTANCE_FROM_BALL_TO_BALL),
            new TurnCommand(drivetrain, 180),
            new AlignAtTrenchAndShootThreeCommandGroup(drivetrain)
        );
    }
}