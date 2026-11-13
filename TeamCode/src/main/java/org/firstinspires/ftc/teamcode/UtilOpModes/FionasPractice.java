

package org.firstinspires.ftc.teamcode.UtilOpModes;


import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;




@TeleOp(name="FionasOp")

public class FionasPractice extends OpMode
{
    Servo myServ;
    double changeAmount = .1;

    double startingPos = .5;
    double UPPER_LIM = 0.8;
    double LOWER_LIM = 0.2;

    double myServePos;

    @Override
    public void init() {



         myServ = hardwareMap.get(Servo.class, "myServ");



        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
        telemetry.addData("Servo position is:", myServ.getPosition());
    }


    @Override
    public void start()
    {
        myServePos = startingPos;
        myServ.setPosition(myServePos);

    }


    @SuppressLint("DefaultLocale")
    @Override
    public void loop() {


        if(gamepad1.dpadDownWasReleased() && (myServePos-changeAmount >= LOWER_LIM))
        {
           myServePos -= changeAmount;
        }
        else if(gamepad1.dpadUpWasReleased()&& (myServePos+changeAmount <= UPPER_LIM))
        {
            myServePos += changeAmount;
        }
        myServ.setPosition(myServePos);





        telemetry.addData("Servo position is:", myServePos);
        telemetry.addLine(String.format("Servo position is: %.4f, eee=%.6f", myServePos, myServePos));


    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
