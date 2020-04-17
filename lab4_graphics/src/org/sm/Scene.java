package org.sm;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Scene extends JFrame implements ActionListener, KeyListener {
	Table table;
	boolean rotateForw = false;
	boolean rotateBack = false;

	public Scene() {
		// Frame title
		super("Tennis table");

		table = new Table();
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		add(canvas3D);
		canvas3D.addKeyListener(this);
		Timer timer = new Timer(50, this);
		timer.start();
		BranchGroup scene = table.createSceneGraph();
		SimpleUniverse u = new SimpleUniverse(canvas3D);
		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(scene);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Scene();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(rotateForw) {
			table.rotateForw();
		}
		if(rotateBack) {
			table.rotateBack();
		}
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode() == KeyEvent.VK_1) {
			rotateBack = true;


		}
		if(keyEvent.getKeyCode() == KeyEvent.VK_0) {
			rotateForw = true;


		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode() == KeyEvent.VK_1) {
			rotateBack = false;
		}
		if(keyEvent.getKeyCode() == KeyEvent.VK_0) {
			rotateForw = false;

		}
	}
}
