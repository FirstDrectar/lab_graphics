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
		int flags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.5f,0.01f,0.7f,flags,getAppearance());
	}

	public static Primitive getMesh() {
		int flags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.5f, 0.05f, 0.01f, flags,getAppearance());
	}
	public static Primitive getLeg() {
		int flags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.01f, 0.2f, 0.01f, flags,getAppearance());
	}
	public static Appearance getAppearance() {
		Appearance ap = new Appearance();

		Color3f emissive = new Color3f(0.05f, 0.05f, 0.05f);
		Color3f ambient = new Color3f(0.05f, 0.05f, 0.05f);
		Color3f diffuse = new Color3f(0.15f, 0.15f, .15f);
		Color3f specular = new Color3f(0, 0, 0);
		ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

		return ap;
	}



}
