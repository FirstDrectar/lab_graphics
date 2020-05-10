import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Background;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFrame;

public class Cat extends JFrame {
	private static Canvas3D canvas;
	private static SimpleUniverse universe;
	private static BranchGroup root;
	
	private static TransformGroup cat;
	
    public Cat() throws IOException {
    	configureWindow();
        configureCanvas();
        configureUniverse();
        
        root = new BranchGroup();

        setBackground();
        
        setDirectionalLigh();
        setAmbientLight();
        
        changeAngle();
        

        
        cat = getCatGroup();
        root.addChild(cat);
        
        root.compile();
        universe.addBranchGraph(root);
    }

    private void configureWindow() {
        setTitle("Lab5");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void configureCanvas() {
    	canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        getContentPane().add(canvas, BorderLayout.CENTER);
    }
    
    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }
    
    private void setBackground() {
        TextureLoader t = new TextureLoader("source_folder/farm.jpg", canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }
    
    private void setDirectionalLigh() {
	    BoundingSphere bounds = new BoundingSphere();
	    bounds.setRadius(100);

	    DirectionalLight light = new DirectionalLight(new Color3f(1, 5, 3), new Vector3f(-1, -1, -1));
	    light.setInfluencingBounds(bounds);

	    root.addChild(light);
	}
    
    private void setAmbientLight() {
        AmbientLight light = new AmbientLight(new Color3f(1, 1, 1));
        light.setInfluencingBounds(new BoundingSphere());
        root.addChild(light);
    }
    
    private void changeAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        vpTranslation.setTranslation(new Vector3f(0, 0, 6));
        vpGroup.setTransform(vpTranslation);
    }

    private TransformGroup getCatGroup() throws IOException {

        Scene scene = getSceneFromFile("source_folder/Cat.obj");
        Map<String, Shape3D> modelMap = scene.getNamedObjects();
        TransformGroup group = getModelGroup(null);
        for(Shape3D shape : modelMap.values()) {
            scene.getSceneGroup().removeChild(shape);
            group.addChild(shape);
        }

    	Transform3D transform3D = new Transform3D();
    	transform3D.setScale(new Vector3d(1, 1, 1));
        group.setTransform(transform3D);

        Transform3D rotationY = new Transform3D();
        rotationY.rotY(Math.PI/2);
        transform3D.mul(rotationY);
        return group;
    }
    
    private TransformGroup getModelGroup(Shape3D shape) {
    	TransformGroup group = new TransformGroup();
    	group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    	group.addChild(shape);
    	return group;
    }

    private Scene getSceneFromFile(String path) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(path));
    }



    public static void main(String[] args) {
        try {
            Cat window = new Cat();
            CatAnimation catAnimation = new CatAnimation(cat);
            canvas.addKeyListener(catAnimation);
            window.setVisible(true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
