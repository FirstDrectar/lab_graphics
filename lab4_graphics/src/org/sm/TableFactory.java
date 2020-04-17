package org.sm;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class TableFactory {


	public static Primitive getBase() {



		Appearance ap = new Appearance();
		Color3f col = new Color3f(0.0f, 0.7f, 0.5f);
		ColoringAttributes ca = new ColoringAttributes(col, ColoringAttributes.NICEST);
		ap.setColoringAttributes(ca);
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

		return new Box(0.5f,0.01f,0.7f,primflags,ap);
	}





	public static Primitive getMesh() {
		int flags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Appearance ap = new Appearance();
		Color3f col = new Color3f(0.0f, 0.5f, 0.5f);
		ColoringAttributes ca = new ColoringAttributes(col, ColoringAttributes.NICEST);
		ap.setColoringAttributes(ca);
		return new Box(0.5f, 0.05f, 0.01f, flags,ap);
	}
	public static Primitive getLeg() {
		int flags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		Appearance ap = new Appearance();
		Color3f col = new Color3f(0.2f, 0.2f, 0.2f);
		ColoringAttributes ca = new ColoringAttributes(col, ColoringAttributes.NICEST);
		ap.setColoringAttributes(ca);
		return new Box(0.01f, 0.2f, 0.01f, flags, ap);
	}




}
