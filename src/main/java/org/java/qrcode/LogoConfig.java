package org.java.qrcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LogoConfig {

	public BufferedImage LogoMatrix(BufferedImage matrixImage) throws IOException {

		Graphics2D g2 = matrixImage.createGraphics();

		int matrixWidth = matrixImage.getWidth();

		int matrixHeigh = matrixImage.getHeight();

		/**
		 * 
		 * 读取Logo图片
		 * 
		 */

		BufferedImage logo = ImageIO.read(new File("D:\\mavenProject\\app-api\\src\\main\\resources\\static\\images\\ios_logo.png"));

		// 开始绘制图片

		g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);// 绘制

		BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

		g2.setStroke(stroke);// 设置笔画对象

		// 指定弧度的圆角矩形

		RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2,
				matrixWidth / 5, matrixHeigh / 5, 20, 20);

		g2.setColor(Color.white);

		g2.draw(round);// 绘制圆弧矩形

		// 设置logo 有一道灰色边框

		BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

		g2.setStroke(stroke2);// 设置笔画对象

		RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2,
				matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);

		g2.setColor(new Color(128, 128, 128));

		g2.draw(round2);// 绘制圆弧矩形

		g2.dispose();

		matrixImage.flush();

		return matrixImage;

	}

}