package org.firstinspires.ftc.teamcode.pedro.procedures;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ConstantsByFiona {
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("leftFront");
        c.frontRightName.set("rightFront");
        c.backLeftName.set("leftBack");
        c.backRightName.set("rightBack");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(5.174303430271899);
        c.yPodOffset.set(5.574721689299336);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.21127996875006322);
                Controller secondaryTranslationalForward = Controller.proportional(0.07806230294840862);
                Controller primaryTranslationalLateral = Controller.proportional(0.30137025284946184);
                Controller secondaryTranslationalLateral = Controller.proportional(0.11134825566641018);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.018327356606461148));
                c.brake.set(Controller.proportionalFeedforward(0.015578253115491976));

                c.headingFeedback.set(Controller.proportional(3.0154262547494928));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05459308166010704, 0.009764428907256047));

                c.linearBrakeCoefficients.set(Matrix.diag(0.06070042097290453, 0.048088716399158175));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0029143579062928244, 0.0025256049498171814));

                c.maxAchievableForwardVelocity.set(56.358863128758685);
                c.maxAchievableStrafeVelocity.set(47.179992727783116);
                c.naturalForwardDeceleration.set(38.41293846723762);
                c.naturalStrafeDeceleration.set(49.510374097154184);
            }
    );
}