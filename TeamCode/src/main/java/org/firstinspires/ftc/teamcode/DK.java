package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DK {
    //left, right
    DcMotorEx leftMotor, rightMotor;
    Servo wheel;
    ColorSensor colorSensor;
    double outputPower;
    double lerp;
    public DK(HardwareMap hwMap){
        leftMotor = hwMap.get(DcMotorEx.class, "left");
        rightMotor = hwMap.get(DcMotorEx.class, "right");
        wheel = hwMap.get(Servo.class, "wheel");
        colorSensor = hwMap.get(ColorSensor.class, "colorSensor");
        outputPower = 0;
        lerp = 0.005;
    }

    public void drive(double power, double turn){
        leftMotor.setPower(power - 0.4 * turn);
        rightMotor.setPower(-power - 0.4 * turn);
    }
    public void driveLerp(double power, double turn){
        outputPower = outputPower + (power - outputPower) * lerp;
        drive(outputPower, turn);
    }
    public int color() {
        int green = colorSensor.green();
        int red = colorSensor.red();
        int blue = colorSensor.blue();
        //1 gray 2 green 3 red
        if(green > 550){
            return 2;
        }else if (red > 450){
            return 3;
        }else{
            return 1;
        }
    }


    public int getColors(){
        return colorSensor.red();
    }
    public void funnyMode(boolean funnyMode){
        lerp = funnyMode ? 1 : 0.05;
    }

    public void wheel(double turn){
        wheel.setPosition(0.5 - turn);
    }
}
