package com.stuypulse.robot.commands.auton.routines;

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
            new AlignAtStartAndShootThreeCommandGroup(drivetrain),
            new ShootThreeAtStartThreeTrenchTwoRdvsCommandGroup(drivetrain, intake, funnel, chimney),
            new AcquireThreeBallFromRdvsCommandGroup(drivetrain, intake, funnel, chimney),
            
            new TurnCommand(drivetrain, 90),
            new DriveCommand(drivetrain, 80),
            new TurnCommand(drivetrain, 40),  
            new AcquireNewBallCommandGroup(intake, funnel, chimney),   
            new DriveCommand(drivetrain, 60),
            new TurnCommand(drivetrain, 180),
            new AlignAtTrenchAndShootThreeCommandGroup(drivetrain)
        );
    }
}