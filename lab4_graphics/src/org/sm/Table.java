package org.sm;

import javax.media.j3d.*;
import javax.vecmath.*;

public class Table {
    private TransformGroup objectTransformGroup;
    private Transform3D tableTransform3D = new Transform3D();
    private TransformGroup transformGroupLegs = new TransformGroup();
    private TransformGroup transformGroupBase = new TransformGroup();
    private float angle = 0;


    public BranchGroup createSceneGraph() {

        BranchGroup objRoot = new BranchGroup();
        objectTransformGroup = new TransformGroup();
        objectTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildObject();
        objRoot.addChild(objectTransformGroup);


        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 1f, 1f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);

        return objRoot;
    }

    private void buildObject() {


        transformGroupBase.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroupBase.addChild(TableFactory.getBase());
        objectTransformGroup.addChild(transformGroupBase);

        Transform3D transform3D = new Transform3D();
        double[] array = new double[]{0, 0.05, 0};
        transform3D.setTranslation(new Vector3d(array));
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(TableFactory.getMesh());
		objectTransformGroup.addChild(transformGroup);



		Transform3D transformLeg1 = new Transform3D();
		 array = new double[]{0.45, -0.2, -0.65};
		transformLeg1.setTranslation(new Vector3d(array));
		TransformGroup transformGroupLeg1 = new TransformGroup();
		transformGroupLeg1.setTransform(transformLeg1);
		transformGroupLeg1.addChild(TableFactory.getLeg());


		Transform3D transformLeg2 = new Transform3D();
		array = new double[]{0.45, -0.2, 0.65};
		transformLeg2.setTranslation(new Vector3d(array));
		TransformGroup transformGroupLeg2 = new TransformGroup();
		transformGroupLeg2.setTransform(transformLeg2);
		transformGroupLeg2.addChild(TableFactory.getLeg());

		Transform3D transformLeg3 = new Transform3D();
		array = new double[]{-0.45, -0.2, 0.65};
		transformLeg3.setTranslation(new Vector3d(array));
		TransformGroup transformGroupLeg3 = new TransformGroup();
		transformGroupLeg3.setTransform(transformLeg3);
		transformGroupLeg3.addChild(TableFactory.getLeg());

		Transform3D transformLeg4 = new Transform3D();
		array = new double[]{-0.45, -0.2, -0.65};
		transformLeg4.setTranslation(new Vector3d(array));
		TransformGroup transformGroupLeg4 = new TransformGroup();
		transformGroupLeg4.setTransform(transformLeg4);
		transformGroupLeg4.addChild(TableFactory.getLeg());



		objectTransformGroup.addChild(transformGroupLeg1);
		objectTransformGroup.addChild(transformGroupLeg2);
		objectTransformGroup.addChild(transformGroupLeg3);
		objectTransformGroup.addChild(transformGroupLeg4);
    }


    public void rotateForw() {
        tableTransform3D.setRotation(new AxisAngle4f(angle, angle, angle, angle));
        angle += 0.05;
        objectTransformGroup.setTransform(tableTransform3D);
    }

    public void rotateBack() {
        tableTransform3D.setRotation(new AxisAngle4f(angle, angle, angle, angle));
        angle -= 0.05;
        objectTransformGroup.setTransform(tableTransform3D);
    }


}

