package com.stuypulse.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PrintAutonCommand extends SequentialCommandGroup {

    public PrintAutonCommand() {

        addCommands(
            new TimeoutCommand(new PrintCommand("timing"), 2)
        );
    }

} 