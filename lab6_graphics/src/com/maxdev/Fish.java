package com.maxdev;

import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import java.util.Hashtable;


public class Fish extends JFrame{
    public Canvas3D myCanvas3D;

    public Fish(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);

        simpUniv.getViewingPlatform().setNominalViewingTransform();

        createSceneGraph(simpUniv);

        addLight(simpUniv);

        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        setTitle("Fish");
        setSize(700,700);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }

    public void createSceneGraph(SimpleUniverse su){
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene fishScene = null;
        try {
            fishScene = f.load("models/fish.obj");
        } catch (Exception e){
            System.out.println("File loading failed:" + e);
        }

        Transform3D scaling = new Transform3D();
        scaling.setScale(1.0/6);
        Transform3D tfRoach = new Transform3D();
        tfRoach.rotY(3*Math.PI/2);
        tfRoach.mul(scaling);
        TransformGroup tgRoach = new TransformGroup(tfRoach);
        TransformGroup sceneGroup = new TransformGroup();


        Hashtable roachNamedObjects = fishScene.getNamedObjects();



        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0),Double.MAX_VALUE);
        BranchGroup theScene = new BranchGroup();

        TransformGroup tgBody = new TransformGroup();

        Appearance bodyApp = new Appearance();
        setToMyDefaultAppearance(bodyApp, new Color3f(0.2f, 0.2f, 0.2f));

        Shape3D body_fish = (Shape3D) roachNamedObjects.get("rt_body");
        body_fish.setAppearance(bodyApp);
        tgBody.addChild(body_fish.cloneTree());

        int noRotHour = 100;
        int timeRotationHour = 300;

        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);

        int timeStart = 0;

        Alpha leg1_1RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,timeStart,0,timeRotationHour,
                                        0,0,0,0,0);

        Shape3D leg1_1 = (Shape3D) roachNamedObjects.get("ventral_fin2");
        TransformGroup tgLeg1_1 = new TransformGroup();
        tgLeg1_1.addChild(leg1_1.cloneTree());

        Transform3D legRotAxis = new Transform3D();
        legRotAxis.rotZ(Math.PI/2);

        RotationInterpolator leg1_1Rotation = new RotationInterpolator(leg1_1RotAlpha,tgLeg1_1,legRotAxis,(float) Math.PI/2,0.0f);
        leg1_1Rotation.setSchedulingBounds(bs);
        tgLeg1_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg1_1.addChild(leg1_1Rotation);
        Alpha leg2_1RotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,timeStart,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D leg2_1 = (Shape3D) roachNamedObjects.get("ventral_finq");
        TransformGroup tgLeg2_1 = new TransformGroup();
        tgLeg2_1.addChild(leg2_1.cloneTree());

        Transform3D leg2RotAxis = new Transform3D();
        leg2RotAxis.rotZ(Math.PI/2);

        RotationInterpolator leg2_1Rotation = new RotationInterpolator(leg2_1RotAlpha,tgLeg2_1,leg2RotAxis,(float) Math.PI/2,0.0f);
        leg2_1Rotation.setSchedulingBounds(bs);
        tgLeg2_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeg2_1.addChild(leg2_1Rotation);

        Alpha tailRotAlpha = new Alpha(noRotHour,Alpha.INCREASING_ENABLE,timeStart,0,timeRotationHour,
                0,0,0,0,0);

        Shape3D tail = (Shape3D) roachNamedObjects.get("tail");
        TransformGroup tgtail = new TransformGroup();

        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(new Vector3d(0, 0, 0));

        tgtail.setTransform(transform3D);

        tgtail.addChild(tail.cloneTree());

        Transform3D tailRotAxis = new Transform3D();
        tailRotAxis.set(new Vector3d(0.1, 0.1, 0));
        tailRotAxis.setRotation(new AxisAngle4d(0.4, 0.1, 0, Math.PI/2));

        RotationInterpolator tailRotation = new RotationInterpolator(tailRotAlpha,tgtail,tailRotAxis,(float) Math.PI/18,-(float) Math.PI/18);
        tailRotation.setSchedulingBounds(bs);
        tgtail.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgtail.addChild(tailRotation);


        Shape3D rt_eye = (Shape3D) roachNamedObjects.get("rt_eye");
        Shape3D rt_mouth = (Shape3D) roachNamedObjects.get("rt_mouth");
        Shape3D head = (Shape3D) roachNamedObjects.get("head");
        Shape3D fin1 = (Shape3D) roachNamedObjects.get("fin1");
        Shape3D fin2 = (Shape3D) roachNamedObjects.get("fin2");

        sceneGroup.addChild(tgLeg1_1);
        sceneGroup.addChild(tgLeg2_1);
        sceneGroup.addChild(rt_eye.cloneTree());
        sceneGroup.addChild(tgtail);
        sceneGroup.addChild(rt_mouth.cloneTree());

        sceneGroup.addChild(head.cloneTree());
        sceneGroup.addChild(fin1.cloneTree());
        sceneGroup.addChild(fin2.cloneTree());



        sceneGroup.addChild(tgBody.cloneTree());


        Transform3D tCrawl = new Transform3D();
        tCrawl.rotY(-Math.PI/2);

        long crawlTime = 10000;
        Alpha crawlAlpha = new Alpha(1,
                Alpha.INCREASING_ENABLE,
                0,
                0, crawlTime,0,0,0,0,0);

        float crawlDistance = 5.0f;
        PositionInterpolator posICrawl = new PositionInterpolator(crawlAlpha,
                sceneGroup,tCrawl, -9.0f, crawlDistance);

        posICrawl.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(posICrawl);

        Transform3D tCrawl2 = new Transform3D();
        tCrawl2.rotX(Math.PI/2);
        RotationInterpolator testRot = new RotationInterpolator(crawlAlpha,sceneGroup,tCrawl2,(float) Math.PI/2,0.0f);
        testRot.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(testRot);

        tgRoach.addChild(sceneGroup);
        theScene.addChild(tgRoach);


        Canvas3D canvas;
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        TextureLoader t = new TextureLoader("./models/sea.jpg",
                canvas);
        Background bg = new Background(t.getImage());
        bg.setImageScaleMode(Background.SCALE_FIT_ALL);
        bg.setApplicationBounds(bounds);
        theScene.addChild(bg);
        theScene.compile();

        su.addBranchGraph(theScene);
    }

    public static void setToMyDefaultAppearance(Appearance app, Color3f col){
        app.setMaterial(new Material(col,col,col,col,150.0f));
    }



    public void addLight(SimpleUniverse su){
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }
    public static void main(String[] args) {
        Fish start = new Fish();
    }
}

