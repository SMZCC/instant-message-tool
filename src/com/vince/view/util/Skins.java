

package com.vince.view.util;
/*import java.awt.SystemTray;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;*/

/*import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;
import org.jvnet.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;
import org.jvnet.substance.skin.BusinessSkin;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.EmeraldDuskSkin;
import org.jvnet.substance.skin.NebulaBrickWallSkin;
import org.jvnet.substance.skin.NebulaSkin;
import org.jvnet.substance.skin.OfficeBlue2007Skin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.RavenSkin;
import org.jvnet.substance.skin.SaharaSkin;
import org.jvnet.substance.skin.SubstanceAutumnLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceCremeLookAndFeel;
import org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.jvnet.substance.skin.SubstanceRavenLookAndFeel;
import org.jvnet.substance.skin.SubstanceSaharaLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;*/
public class Skins {

/*	public static SystemTray st;

	public static void setThemeSahara(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_ANCHOR);
            watermark.setOpacity((float) 0.35);
            UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());
            SubstanceSkin skin = new SaharaSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void setThemeOfficeSilver2007(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.35);
            UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
            SubstanceSkin skin = new OfficeSilver2007Skin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void setThemeNebula(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.55);
            UIManager.setLookAndFeel(new SubstanceNebulaLookAndFeel());
            SubstanceSkin skin = new NebulaSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	
	public static void setThemeNebulaBrickWall(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceNebulaBrickWallLookAndFeel());
            SubstanceSkin skin = new NebulaBrickWallSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	public static void setThemeAutumn(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel());
            SubstanceSkin skin = new AutumnSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	}
	public static void setThemeBusiness(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
            SubstanceSkin skin = new BusinessSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeCreme(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());
            SubstanceSkin skin = new CremeSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeBusinessBlackSteel(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.55);
            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
            SubstanceSkin skin = new BusinessBlackSteelSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeRaven(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
            SubstanceSkin skin = new RavenSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	public static void setThemeEmeraldDusk(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.APP_CENTER);
            watermark.setOpacity((float) 0.9);
            UIManager.setLookAndFeel(new SubstanceEmeraldDuskLookAndFeel());
            SubstanceSkin skin = new EmeraldDuskSkin().withWatermark(watermark);

            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        }
	}
	
	public static void setThemeOfficeBlue2007(Class reflect,String path)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            SubstanceImageWatermark watermark = new SubstanceImageWatermark(reflect.getResourceAsStream(path));
            watermark.setKind(ImageWatermarkKind.SCREEN_CENTER_SCALE);
            watermark.setOpacity((float) 0.8);
            UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
            SubstanceSkin skin = new OfficeBlue2007Skin().withWatermark(watermark);
            SubstanceLookAndFeel.setSkin(skin);

        } catch (UnsupportedLookAndFeelException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
