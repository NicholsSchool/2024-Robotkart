package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Kiwi Teleop Code
 */
@TeleOp(name="DK", group="Iterative Opmode")
public class DKTeleop extends OpMode
{
    DK dk;
    double power;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        dk = new DK(hardwareMap);
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {}

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop(){
        dk.funnyMode(gamepad1.dpad_left || dk.color() == 2);
        if(gamepad1.b){
            power = -1;
        }  else if(gamepad1.a){
            power = 1;
        }else{
            power = 0;
        }
        switch(dk.color()) {
            case 1:
            case 2:
                dk.driveLerp(power, gamepad1.left_stick_x);
                break;
            case 3:
                dk.driveLerp(0.5 * power, 0.5 * gamepad1.left_stick_x);
        }
        telemetry.addData("green",dk.getColors());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {}
}
